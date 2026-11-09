package com.gestor.backend.application.service;

import com.gestor.backend.domain.model.Tarea;
import com.gestor.backend.domain.port.in.TareaUseCase;
import com.gestor.backend.domain.port.out.TareaRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaService implements TareaUseCase {

    private final TareaRepositoryPort repo;

    public TareaService(TareaRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public Tarea crear(String titulo) {
        return repo.guardar(new Tarea(null, titulo, false));
    }

    @Override
    public List<Tarea> listar() {
        return repo.listarTodas();
    }

    @Override
    public Tarea completar(Long id) {
        Tarea t = repo.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        t.completar();
        return repo.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repo.eliminar(id);
    }
}