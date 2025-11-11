package com.tecsup.demo.domain.persistence;

import com.tecsup.demo.domain.entities.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoDao extends CrudRepository<Curso, Integer> {
}

