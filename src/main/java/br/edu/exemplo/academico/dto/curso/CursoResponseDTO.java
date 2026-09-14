package br.edu.exemplo.academico.dto.curso;

public class CursoResponseDTO {
    private Long id;
    private String nome;
    private Integer cargaHoraria;

    public CursoResponseDTO(Long id, String nome, Integer cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }
}