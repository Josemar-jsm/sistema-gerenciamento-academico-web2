package br.edu.exemplo.academico.service;

import br.edu.exemplo.academico.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final List<Usuario> itens = new ArrayList<>();
    private Long seq = 1L;

    public List<Usuario> listar() {
        return itens;
    }

    public Usuario consultar(Long id) {
        return itens.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Usuario cadastrar(Usuario a) {
        a.setId(seq++);
        itens.add(a);
        return a;
    }

    public Usuario atualizar(Long id, Usuario d) {
        Usuario a = consultar(id);
        if (a == null) return null;
        a.setNome(d.getNome());
        a.setEmail(d.getEmail());
        a.setPerfil(d.getPerfil());
        return a;
    }

    public boolean excluir(Long id) {
        return itens.removeIf(x -> x.getId().equals(id));
    }
}