package br.edu.exemplo.academico.service;

import br.edu.exemplo.academico.model.Matricula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {
    private final List<Matricula> itens = new ArrayList<>();
    private Long seq = 1L;

    public List<Matricula> listar() {
        return itens;
    }

    public Matricula consultar(Long id) {
        return itens.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Matricula cadastrar(Matricula a) {
        a.setId(seq++);
        itens.add(a);
        return a;
    }

    public Matricula atualizar(Long id, Matricula d) {
        Matricula a = consultar(id);
        if (a == null) return null;
        a.setAlunoId(d.getAlunoId());
        a.setCursoId(d.getCursoId());
        a.setStatus(d.getStatus());
        return a;
    }

    public boolean excluir(Long id) {
        return itens.removeIf(x -> x.getId().equals(id));
    }
}