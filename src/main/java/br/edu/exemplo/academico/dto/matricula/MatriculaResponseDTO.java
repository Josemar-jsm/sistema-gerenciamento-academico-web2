package br.edu.exemplo.academico.dto.matricula;

public class MatriculaResponseDTO {
    private Long id;
    private Long alunoId;
    private Long cursoId;
    private String status;

    public MatriculaResponseDTO(Long id, Long alunoId, Long cursoId, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public String getStatus() {
        return status;
    }
}