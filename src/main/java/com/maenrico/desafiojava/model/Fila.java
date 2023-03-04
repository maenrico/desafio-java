package com.maenrico.desafiojava.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fila")
@Data
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String nome;


}

