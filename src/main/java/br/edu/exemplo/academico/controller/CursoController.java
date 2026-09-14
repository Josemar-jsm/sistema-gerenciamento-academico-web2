package br.edu.exemplo.academico.controller;

import br.edu.exemplo.academico.dto.curso.CursoRequestDTO;
import br.edu.exemplo.academico.dto.curso.CursoResponseDTO;
import br.edu.exemplo.academico.model.Curso;
import br.edu.exemplo.academico.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> consultar(@PathVariable Long id) {
        Curso a = service.consultar(id);
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> cadastrar(@Valid @RequestBody CursoRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.cadastrar(toModel(r))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequestDTO r) {
        Curso a = service.atualizar(id, toModel(r));
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Curso toModel(CursoRequestDTO d) {
        return new Curso(null, d.getNome(), d.getCargaHoraria());
    }

    private CursoResponseDTO toResponse(Curso a) {
        return new CursoResponseDTO(a.getId(), a.getNome(), a.getCargaHoraria());
    }
}