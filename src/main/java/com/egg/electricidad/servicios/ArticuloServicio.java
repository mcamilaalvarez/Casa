package com.egg.electricidad.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.egg.electricidad.entidades.Articulo;
import com.egg.electricidad.entidades.Fabrica;
import com.egg.electricidad.entidades.Imagen;
import com.egg.electricidad.entidades.Usuario;
import com.egg.electricidad.excepciones.MiException;
import com.egg.electricidad.repositorios.ArticuloRepositorio;
import com.egg.electricidad.repositorios.FabricaRepositorio;

@Service
public class ArticuloServicio {
    @Autowired
    private ArticuloRepositorio articuloRepositorio;
    @Autowired
    private FabricaRepositorio fabricaRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;
    
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    @Transactional
    public void crearArticulo(String nombre, String descripcionArticulo, String idFabrica, MultipartFile archivo)
            throws MiException {
        validar(nombre, descripcionArticulo, idFabrica);
        Articulo articulo = new Articulo();
        Fabrica fabrica = fabricaRepositorio.findById(idFabrica).get();
        Imagen imagen = imagenServicio.guardar(archivo);
        articulo.setNombreArticulo(nombre);
        articulo.setNroArticulo(atomicInteger.incrementAndGet());
        articulo.setDescripcionArticulo(descripcionArticulo);
        articulo.setFabrica(fabrica);
        articulo.setImagen(imagen);
        articuloRepositorio.save(articulo);

    }

    public void validar(String nombre, String descripcionArticulo, String idFabrica) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (descripcionArticulo.isEmpty() || descripcionArticulo == null) {
            throw new MiException("La descripción del articulo no puede ser nulo o estar vacío");
        }
        if (idFabrica.isEmpty() || idFabrica == null) {
            throw new MiException("La fabrica no puede ser nula o estar vacía");
        }
    }

    @Transactional
    public void modificarArticulo(String id, String nombre, String descripcionArticulo, String idFabrica,
            MultipartFile archivo) throws MiException {
        validar(nombre, descripcionArticulo, idFabrica);
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);
        Optional<Fabrica> fabrica = fabricaRepositorio.findById(idFabrica);
        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();
            articulo.setNombreArticulo(nombre);
            articulo.setDescripcionArticulo(descripcionArticulo);
            if (fabrica.isPresent()) {
                Fabrica fabricaNew = fabrica.get();
                articulo.setFabrica(fabricaNew);
            }
            Imagen imagen = imagenServicio.guardar(archivo);
            articulo.setImagen(imagen);
            articuloRepositorio.save(articulo);
        }

    }

    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        articulos = articuloRepositorio.findAll();
        return articulos;
    }

    @Transactional
    public void eliminarArticulo(String id) throws MiException {
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();

            // Eliminar el artículo
            articuloRepositorio.delete(articulo);
        } else {
            throw new MiException("No se encontró un artículo con el ID proporcionado.");
        }
    }
}
