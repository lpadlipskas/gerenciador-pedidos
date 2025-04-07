package com.example.gerenciador_pedidos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {
    @Id
    private Long id;
    private String nome;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
