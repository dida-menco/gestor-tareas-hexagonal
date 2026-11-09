package com.gestor.backend.domain.port.in;

import com.gestor.backend.domain.model.Tarea;
import java.util.List;

public interface TareaUseCase {
    Tarea crear(String titulo);
    List<Tarea> listar();
    Tarea completar(Long id);
    void eliminar(Long id);
}