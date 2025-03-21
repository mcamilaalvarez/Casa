package com.egg.electricidad.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.electricidad.entidades.Fabrica;

@Repository
public interface FabricaRepositorio extends JpaRepository<Fabrica,String> {
    
}
