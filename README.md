# Spring Boot CRUD de Cursos con Auditoría y Reportes

Aplicación construida para el laboratorio de **Desarrollo de Aplicaciones Web**. Demuestra un CRUD completo de cursos con validaciones, auditoría mediante AOP y generación de reportes en PDF / Excel usando Spring Boot 3.

## Tecnologías
- Spring Boot 3.5
- Spring MVC + Thymeleaf
- Spring Data JPA
- H2 Database (en memoria)
- Programación Orientada a Aspectos (Spring AOP)
- OpenPDF y Apache POI para exportes

## Funcionalidades
- CRUD de cursos (`/listar`, `/form`, `/editar/{id}`, `/eliminar/{id}`).
- Validación de campos (`@NotEmpty`, `@Min`, `@Max`).
- Auditoría automática de operaciones guardar/editar/eliminar.
- Exportes a PDF y Excel desde el listado de cursos.
- Plantillas Thymeleaf con fragmentos Bootstrap.
- Datos iniciales cargados mediante `schema.sql` e `import.sql`.
- Consola H2 disponible en `http://localhost:8080/h2-console`.

## Requisitos
- JDK 21 (probado con Microsoft OpenJDK 21.0.8).
- Gradle Wrapper incluido (`gradlew` / `gradlew.bat`).

## Ejecución
```bash
./gradlew bootRun
# o en Windows
gradlew.bat bootRun
```
Aplicación disponible en `http://localhost:8080`.  
Rutas principales:
- `/inicio`: página de bienvenida.
- `/listar`: tabla de cursos (opciones para PDF/XLS).
- `/form`: formulario de creación/edición.
- `/docente`: sección informativa.

## Auditoría
El aspecto `LoggingAspecto` registra cada operación en la entidad `Auditoria`.  
Consulta los registros en la consola H2:
```sql
SELECT * FROM AUDITORIAS ORDER BY id;
```

## Estructura destacada
- `com.tecsup.demo.controllers`: Controladores MVC.
- `com.tecsup.demo.services`: Servicio y reglas de negocio.
- `com.tecsup.demo.domain`: Entidades JPA y repositorios.
- `com.tecsup.demo.aop.LoggingAspecto`: Auditoría AOP.
- `com.tecsup.demo.views`: clases de exportación PDF/Excel.
- `src/main/resources/templates`: vistas Thymeleaf.

## Autoría
Proyecto de laboratorio desarrollado por **Ricardo Coello Palomino** (sección 5 - C24) con soporte de asistente IA en Cursor.

