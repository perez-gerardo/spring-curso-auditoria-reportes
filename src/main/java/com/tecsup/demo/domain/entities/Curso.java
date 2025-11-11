package com.tecsup.demo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "El código es obligatorio")
    @Size(max = 20, message = "El código no debe exceder 20 caracteres")
    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Min(value = 0, message = "Los créditos no pueden ser menores a 0")
    @Max(value = 5, message = "Los créditos no pueden ser mayores a 5")
    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    public Curso() {
    }

    public Curso(Integer id, String codigo, String nombre, Integer creditos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public Curso(String codigo, String nombre, Integer creditos) {
        this(null, codigo, nombre, creditos);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}

