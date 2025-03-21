package com.egg.electricidad.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.electricidad.entidades.Fabrica;
import com.egg.electricidad.excepciones.MiException;
import com.egg.electricidad.repositorios.FabricaRepositorio;

@Service
public class FabricaServicio {
    
    @Autowired
    private FabricaRepositorio fabricaRepositorio;

    @Transactional
    public void crearFabrica (String nombre) throws MiException{
        validar(nombre);
        Fabrica fabrica = new Fabrica();
        fabrica.setNombreFabrica(nombre);
        fabricaRepositorio.save(fabrica);
    }

    private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
    }

    
}
