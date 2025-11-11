package com.tecsup.demo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tabla", nullable = false, length = 64)
    private String tabla;

    @Column(name = "recurso_id")
    private Integer recursoId;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "usuario", nullable = false, length = 64)
    private String usuario;

    @Column(name = "tipo", nullable = false, length = 64)
    private String tipo;

    public Auditoria() {
    }

    public Auditoria(String tabla, Integer recursoId, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.recursoId = recursoId;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public Integer getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Integer recursoId) {
        this.recursoId = recursoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

