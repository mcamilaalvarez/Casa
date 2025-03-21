package com.egg.electricidad.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.electricidad.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    public Usuario buscarPorCorreo(@Param("correo") String correo);

    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    public Optional<Usuario> buscarPorCorreoOptional(@Param("correo") String correo);
}
