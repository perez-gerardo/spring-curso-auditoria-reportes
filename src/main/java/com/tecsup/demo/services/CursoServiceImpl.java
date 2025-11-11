package com.tecsup.demo.services;

import com.tecsup.demo.domain.entities.Curso;
import com.tecsup.demo.domain.persistence.CursoDao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoDao cursoDao;

    public CursoServiceImpl(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    @Override
    public Curso grabar(Curso curso) {
        return cursoDao.save(curso);
    }

    @Override
    public void eliminar(Integer id) {
        cursoDao.deleteById(id);
    }

    @Override
    public Optional<Curso> buscar(Integer id) {
        return cursoDao.findById(id);
    }

    @Override
    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        cursoDao.findAll().forEach(cursos::add);
        return cursos;
    }
}

