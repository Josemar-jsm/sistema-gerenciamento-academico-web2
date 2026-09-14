package br.edu.exemplo.academico.service;

import br.edu.exemplo.academico.model.Aluno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    private final List<Aluno> itens = new ArrayList<>();
    private Long seq = 1L;

    public List<Aluno> listar() {
        return itens;
    }

    public Aluno consultar(Long id) {
        return itens.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Aluno cadastrar(Aluno a) {
        a.setId(seq++);
        itens.add(a);
        return a;
    }

    public Aluno atualizar(Long id, Aluno d) {
        Aluno a = consultar(id);
        if (a == null) return null;
        a.setNome(d.getNome());
        a.setEmail(d.getEmail());
        return a;
    }

    public boolean excluir(Long id) {
        return itens.removeIf(x -> x.getId().equals(id));
    }
}