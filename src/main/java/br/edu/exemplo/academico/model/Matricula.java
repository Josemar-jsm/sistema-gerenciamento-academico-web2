package br.edu.exemplo.academico.model;

public class Matricula {
    private Long id;
    private Long alunoId;
    private Long cursoId;
    private String status;

    public Matricula() {
    }

    public Matricula(Long id, Long alunoId, Long cursoId, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}