package com.maenrico.desafiojava.service;


import com.maenrico.desafiojava.model.Fila;
import com.maenrico.desafiojava.model.Usuario;
import com.maenrico.desafiojava.model.UsuarioFila;
import com.maenrico.desafiojava.repository.UsuarioFilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilaService {

    @Autowired
    private UsuarioFilaRepository usuarioFilaRepository;

    public void adicionarUsuarioNaFila(Usuario usuario, Fila fila) {
        List<UsuarioFila> usuariosNaFila = usuarioFilaRepository.findByFilaOrderByPosicao(fila);

        int posicao = usuariosNaFila.size() + 1;

        UsuarioFila usuarioFila = new UsuarioFila();
        usuarioFila.setUsuario(usuario);
        usuarioFila.setFila(fila);
        usuarioFila.setPosicao(posicao);

        usuarioFilaRepository.save(usuarioFila);
    }

    public void removerUsuarioDaFila(Usuario usuario, Fila fila) {
        List<UsuarioFila> usuariosNaFila = usuarioFilaRepository.findByFilaOrderByPosicao(fila);

        for (UsuarioFila usuarioFila : usuariosNaFila) {
            if (usuarioFila.getUsuario().equals(usuario)) {
                usuarioFilaRepository.delete(usuarioFila);

                atualizarPosicoes(usuariosNaFila, usuarioFila.getPosicao());

                break;
            }
        }
    }

    private void atualizarPosicoes(List<UsuarioFila> usuariosNaFila, int posicaoRemovida) {
        for (UsuarioFila usuarioFila : usuariosNaFila) {
            int posicaoAtual = usuarioFila.getPosicao();

            if (posicaoAtual > posicaoRemovida) {
                usuarioFila.setPosicao(posicaoAtual - 1);

                usuarioFilaRepository.save(usuarioFila);
            }
        }
    }
}

