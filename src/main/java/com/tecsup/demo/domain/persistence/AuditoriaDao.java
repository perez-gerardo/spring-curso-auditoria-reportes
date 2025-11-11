package com.tecsup.demo.domain.persistence;

import com.tecsup.demo.domain.entities.Auditoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaDao extends CrudRepository<Auditoria, Long> {
}

