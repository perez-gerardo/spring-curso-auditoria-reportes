package com.tecsup.demo.services;

import com.tecsup.demo.domain.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    Curso grabar(Curso curso);

    void eliminar(Integer id);

    Optional<Curso> buscar(Integer id);

    List<Curso> listar();
}

