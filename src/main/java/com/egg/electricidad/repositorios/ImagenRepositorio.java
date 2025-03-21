package com.egg.electricidad.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.electricidad.entidades.Imagen;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {
    
}
