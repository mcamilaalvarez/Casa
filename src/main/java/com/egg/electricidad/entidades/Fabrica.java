package com.egg.electricidad.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;


@Entity
public class Fabrica {
    
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private String id;

    private String nombreFabrica;

    public Fabrica() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreFabrica() {
        return nombreFabrica;
    }

    public void setNombreFabrica(String nombreFabrica) {
        this.nombreFabrica = nombreFabrica;
    }

    
}
