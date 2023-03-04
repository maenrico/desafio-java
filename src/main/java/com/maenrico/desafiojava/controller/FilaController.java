package com.maenrico.desafiojava.controller;

import com.maenrico.desafiojava.model.Fila;
import com.maenrico.desafiojava.model.Usuario;
import com.maenrico.desafiojava.model.UsuarioFila;
import com.maenrico.desafiojava.repository.FilaRepository;
import com.maenrico.desafiojava.repository.UsuarioFilaRepository;
import com.maenrico.desafiojava.repository.UsuarioRepository;
import com.maenrico.desafiojava.service.FilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fila")
public class FilaController {

    @Autowired
    private FilaService filaService;

    @Autowired
    private FilaRepository filaRepository;

    @Autowired
    private UsuarioFilaRepository usuarioFilaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;



//    @PostMapping("/{idFila}/usuario/{idUsuario}")
//    public void adicionarUsuarioNaFila(@PathVariable Long idFila, @PathVariable Long idUsuario) {
//        Usuario usuario = new Usuario();
//        usuario.setId(idUsuario);
//
//        Fila fila = new Fila();
//        fila.setId(idFila);
//
//        filaService.adicionarUsuarioNaFila(usuario, fila);
//    }

        @GetMapping("/{id}")
        public Fila obterFila(@PathVariable Long id) {
            return filaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fila não encontrada com id " + id));
        }

        @PostMapping
        public Fila criarFila(@RequestBody Fila fila) {
            return filaRepository.save(fila);
        }

        @PutMapping("/{id}")
        public Fila atualizarFila(@PathVariable Long id, @RequestBody Fila filaAtualizada) {
            return filaRepository.findById(id)
                    .map(fila -> {
                        fila.setNome(filaAtualizada.getNome());
                        return filaRepository.save(fila);
                    })
                    .orElseThrow(() -> new RuntimeException("Fila não encontrada com id " + id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> excluirFila(@PathVariable Long id) {
            return filaRepository.findById(id)
                    .map(fila -> {
                        usuarioFilaRepository.deleteByFila(fila);
                        filaRepository.delete(fila);
                        return ResponseEntity.ok().build();
                    })
                    .orElseThrow(() -> new RuntimeException("Fila não encontrada com id " + id));
        }

        @PostMapping("/{idFila}/usuario/{idUsuario}")
        public UsuarioFila adicionarUsuarioNaFila(@PathVariable Long idFila, @PathVariable Long idUsuario) {
            Fila fila = filaRepository.findById(idFila)
                    .orElseThrow(() -> new RuntimeException("Fila não encontrada com id " + idFila));

            Usuario usuario = usuarioFilaRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id " + idUsuario)).getUsuario();

            List<UsuarioFila> usuariosNaFila = usuarioFilaRepository.findByFilaOrderByPosicao(fila);
            int ultimaPosicao = usuariosNaFila.isEmpty() ? 0 : usuariosNaFila.get(usuariosNaFila.size() - 1).getPosicao();

            UsuarioFila usuarioFila = new UsuarioFila(null, usuario, fila, ultimaPosicao + 1);
            return usuarioFilaRepository.save(usuarioFila);
        }

        @DeleteMapping("/{idFila}/usuario/{idUsuario}")
        public ResponseEntity<?> removerUsuarioDaFila(@PathVariable Long idFila, @PathVariable Long idUsuario) {
            Fila fila = filaRepository.findById(idFila)
                    .orElseThrow(() -> new RuntimeException("Fila não encontrada com id " + idFila));

            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id " + idUsuario));

            UsuarioFila usuarioFila = usuarioFilaRepository.findByUsuarioAndFila(usuario, fila)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado na fila com id " + idFila));

            usuarioFilaRepository.delete(usuarioFila);
            return ResponseEntity.ok().build();
        }
    }

