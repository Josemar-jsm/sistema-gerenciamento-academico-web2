package br.edu.exemplo.academico.controller;

import br.edu.exemplo.academico.dto.matricula.MatriculaRequestDTO;
import br.edu.exemplo.academico.dto.matricula.MatriculaResponseDTO;
import br.edu.exemplo.academico.model.Matricula;
import br.edu.exemplo.academico.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> consultar(@PathVariable Long id) {
        Matricula a = service.consultar(id);
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> cadastrar(@Valid @RequestBody MatriculaRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.cadastrar(toModel(r))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MatriculaRequestDTO r) {
        Matricula a = service.atualizar(id, toModel(r));
        return a == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Matricula toModel(MatriculaRequestDTO d) {
        return new Matricula(null, d.getAlunoId(), d.getCursoId(), d.getStatus());
    }

    private MatriculaResponseDTO toResponse(Matricula a) {
        return new MatriculaResponseDTO(a.getId(), a.getAlunoId(), a.getCursoId(), a.getStatus());
    }
}