package com.maenrico.desafiojava.repository;

import com.maenrico.desafiojava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
