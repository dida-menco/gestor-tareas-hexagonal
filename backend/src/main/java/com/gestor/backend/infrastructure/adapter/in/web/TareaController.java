package com.gestor.backend.infrastructure.adapter.in.web;

import com.gestor.backend.domain.model.Tarea;
import com.gestor.backend.domain.port.in.TareaUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaUseCase useCase;

    public TareaController(TareaUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Tarea> listar() {
        return useCase.listar();
    }

    @PostMapping
    public Tarea crear(@RequestBody CrearTareaRequest req) {
        return useCase.crear(req.getTitulo());
    }

    @PatchMapping("/{id}/completar")
    public Tarea completar(@PathVariable Long id) {
        return useCase.completar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        useCase.eliminar(id);
    }
}

class CrearTareaRequest {
    private String titulo;
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
}