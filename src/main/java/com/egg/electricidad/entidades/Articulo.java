package com.egg.electricidad.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;


@Entity
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer nroArticulo;

    private String nombreArticulo;

    private String descripcionArticulo;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "fabrica_id", referencedColumnName = "id", nullable = false)
    private Fabrica fabrica;

    @OneToOne(cascade = CascadeType.ALL, optional = true) 
    @JoinColumn(name = "imagen_id", referencedColumnName = "id", nullable = true)
    private Imagen imagen;

    public Articulo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNroArticulo() {
        return nroArticulo;
    }

    public void setNroArticulo(Integer nroArticulo) {
        this.nroArticulo = nroArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    
}
