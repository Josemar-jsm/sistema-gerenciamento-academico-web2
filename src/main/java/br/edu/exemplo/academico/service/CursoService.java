package br.edu.exemplo.academico.service;

import br.edu.exemplo.academico.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {
    private final List<Curso> itens = new ArrayList<>();
    private Long seq = 1L;

    public List<Curso> listar() {
        return itens;
    }

    public Curso consultar(Long id) {
        return itens.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Curso cadastrar(Curso a) {
        a.setId(seq++);
        itens.add(a);
        return a;
    }

    public Curso atualizar(Long id, Curso d) {
        Curso a = consultar(id);
        if (a == null) return null;
        a.setNome(d.getNome());
        a.setCargaHoraria(d.getCargaHoraria());
        return a;
    }

    public boolean excluir(Long id) {
        return itens.removeIf(x -> x.getId().equals(id));
    }
}