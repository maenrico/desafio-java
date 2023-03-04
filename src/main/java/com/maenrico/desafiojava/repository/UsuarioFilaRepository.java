package com.maenrico.desafiojava.repository;

import com.maenrico.desafiojava.model.Fila;
import com.maenrico.desafiojava.model.Usuario;
import com.maenrico.desafiojava.model.UsuarioFila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioFilaRepository extends JpaRepository<UsuarioFila, Long> {

    List<UsuarioFila> findByFilaOrderByPosicao(Fila fila);

    void deleteByFila(Fila fila);

    Optional<UsuarioFila> findByUsuarioAndFila(Usuario usuario, Fila fila);
}
