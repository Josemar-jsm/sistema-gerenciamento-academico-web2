package br.edu.exemplo.academico.controller;

import br.edu.exemplo.academico.dto.usuario.UsuarioRequestDTO;
import br.edu.exemplo.academico.dto.usuario.UsuarioResponseDTO;
import br.edu.exemplo.academico.model.Usuario;
import br.edu.exemplo.academico.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> consultar(@PathVariable Long id) {
        Usuario a = service.consultar(id);
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.cadastrar(toModel(r))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO r) {
        Usuario a = service.atualizar(id, toModel(r));
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Usuario toModel(UsuarioRequestDTO d) {
        return new Usuario(null, d.getNome(), d.getEmail(), d.getPerfil());
    }

    private UsuarioResponseDTO toResponse(Usuario a) {
        return new UsuarioResponseDTO(a.getId(), a.getNome(), a.getEmail(), a.getPerfil());
    }
}