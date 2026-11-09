package com.gestor.backend.domain.model;

public class Tarea {
    private Long id;
    private String titulo;
    private boolean completada;

    public Tarea(Long id, String titulo, boolean completada) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        this.id = id;
        this.titulo = titulo;
        this.completada = completada;
    }

    public void completar() {
        this.completada = true;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }
}