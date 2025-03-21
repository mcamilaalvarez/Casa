package com.egg.electricidad.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.User;


import com.egg.electricidad.entidades.Usuario;
import com.egg.electricidad.enumeradores.Rol;
import com.egg.electricidad.excepciones.MiException;
import com.egg.electricidad.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServicio implements UserDetailsService{
      @Autowired
    private UsuarioRepositorio usuarioRepositorio;
   

    @Transactional
    public void crearUsuario(String nombre, String correo, String password, String password2, MultipartFile archivo) throws MiException{
        validar(nombre, correo, password, password2);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        usuarioRepositorio.save(usuario);
    }


    private void validar(String nombre, String correo, String password, String password2) throws MiException {


        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (correo.isEmpty() || correo == null) {
            throw new MiException("el correo no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }
    
    @Transactional(readOnly = true)
    public Usuario getOne(String id){
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void modificarUsuario(String id, String nombre, String correo, String password, String password2, MultipartFile archivo) throws MiException {

        validar(nombre, correo, password, password2);

        Optional<Usuario> respuesta = Optional.ofNullable(usuarioRepositorio.buscarPorCorreo(correo));
        

        if (respuesta.isEmpty()) {
            throw new MiException("El usuario especificado no existe.");
        }

        Usuario usuario = respuesta.get();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));

        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void cambiarRol(String id){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            if(usuario.getRol().equals(Rol.USER)){
                usuario.setRol(Rol.ADMIN);
            }else if (usuario.getRol().equals(Rol.ADMIN)){
                usuario.setRol(Rol.USER);
            }
            
        }
    }



    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
       
        Usuario usuario = usuarioRepositorio.buscarPorCorreo(correo);
       
        
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            return new User(usuario.getCorreo(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

}
    
}
