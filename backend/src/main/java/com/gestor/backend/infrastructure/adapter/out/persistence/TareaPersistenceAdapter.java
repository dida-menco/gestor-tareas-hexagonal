package com.gestor.backend.infrastructure.adapter.out.persistence;

import com.gestor.backend.domain.model.Tarea;
import com.gestor.backend.domain.port.out.TareaRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TareaPersistenceAdapter implements TareaRepositoryPort {

    private final TareaJpaRepository jpa;

    public TareaPersistenceAdapter(TareaJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Tarea guardar(Tarea t) {
        TareaJpaEntity e = jpa.save(toEntity(t));
        return toDomain(e);
    }

    @Override
    public List<Tarea> listarTodas() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    private TareaJpaEntity toEntity(Tarea t) {
        return new TareaJpaEntity(t.getId(), t.getTitulo(), t.isCompletada());
    }

    private Tarea toDomain(TareaJpaEntity e) {
        return new Tarea(e.getId(), e.getTitulo(), e.isCompletada());
    }
}