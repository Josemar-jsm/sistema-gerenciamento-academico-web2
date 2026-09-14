package br.edu.exemplo.academico.controller;

import br.edu.exemplo.academico.dto.aluno.AlunoRequestDTO;
import br.edu.exemplo.academico.dto.aluno.AlunoResponseDTO;
import br.edu.exemplo.academico.model.Aluno;
import br.edu.exemplo.academico.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> consultar(@PathVariable Long id) {
        Aluno a = service.consultar(id);
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.cadastrar(toModel(r))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO r) {
        Aluno a = service.atualizar(id, toModel(r));
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Aluno toModel(AlunoRequestDTO d) {
        return new Aluno(null, d.getNome(), d.getEmail());
    }

    private AlunoResponseDTO toResponse(Aluno a) {
        return new AlunoResponseDTO(a.getId(), a.getNome(), a.getEmail());
    }
}