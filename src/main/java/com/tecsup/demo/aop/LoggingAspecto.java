package com.tecsup.demo.aop;

import com.tecsup.demo.domain.entities.Auditoria;
import com.tecsup.demo.domain.entities.Curso;
import com.tecsup.demo.domain.persistence.AuditoriaDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Aspect
@Component
public class LoggingAspecto {

    private final AuditoriaDao auditoriaDao;

    private Long tx = System.currentTimeMillis();

    public LoggingAspecto(AuditoriaDao auditoriaDao) {
        this.auditoriaDao = auditoriaDao;
    }

    @After("execution(* com.tecsup.demo.controllers.*Controller.guardar*(..)) || " +
           "execution(* com.tecsup.demo.controllers.*Controller.editar*(..)) || " +
           "execution(* com.tecsup.demo.controllers.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Integer id = obtenerIdCurso(metodo, joinPoint.getArgs());

        String traza = "tx[" + tx + "] - " + metodo;
        logger.info("{}(): registrando auditoria...", traza);

        auditoriaDao.save(new Auditoria(
                "cursos",
                id,
                Calendar.getInstance().getTime(),
                "usuario",
                metodo
        ));

        tx = System.currentTimeMillis();
    }

    private Integer obtenerIdCurso(String metodo, Object[] parametros) {
        if (metodo.startsWith("guardar") && parametros.length > 0 && parametros[0] instanceof Curso curso) {
            return curso.getId();
        }
        if ((metodo.startsWith("editar") || metodo.startsWith("eliminar")) &&
                parametros.length > 0 && parametros[0] instanceof Integer id) {
            return id;
        }
        return null;
    }
}

