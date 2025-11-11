package com.tecsup.demo.controllers;

import com.tecsup.demo.domain.entities.Curso;
import com.tecsup.demo.services.CursoService;
import com.tecsup.demo.views.CursoPdfView;
import com.tecsup.demo.views.CursoXlsView;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("curso")
@RequestMapping
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @ModelAttribute("titulo")
    public String titulo() {
        return "Gestión de Cursos";
    }

    @GetMapping({"/listar"})
    public Object listar(@RequestParam(value = "format", required = false) String format, Model model) {
        List<Curso> cursos = cursoService.listar();
        if ("pdf".equalsIgnoreCase(format)) {
            return new ModelAndView(new CursoPdfView(), Map.of("cursos", cursos));
        }
        if ("xls".equalsIgnoreCase(format) || "xlsx".equalsIgnoreCase(format)) {
            return new ModelAndView(new CursoXlsView(), Map.of("cursos", cursos));
        }

        model.addAttribute("cursos", cursos);
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("curso", new Curso());
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("curso") Curso curso,
                          BindingResult result,
                          Model model,
                          SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("curso", curso);
            return "form";
        }
        cursoService.grabar(curso);
        status.setComplete();
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        return cursoService.buscar(id)
                .map(curso -> {
                    model.addAttribute("curso", curso);
                    return "form";
                })
                .orElse("redirect:/listar");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        cursoService.eliminar(id);
        return "redirect:/listar";
    }
}

