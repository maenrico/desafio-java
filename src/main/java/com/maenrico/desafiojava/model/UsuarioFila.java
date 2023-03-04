package com.maenrico.desafiojava.model;

import jakarta.persistence.*;import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_fila")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioFila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fila_id")
    private Fila fila;

    private int posicao;



}
