package login.repository;

import java.util.ArrayList;
import login.model.Usuario;

public class UsuarioRepository {

    //sem banco de dados com SQL no momento
    private ArrayList<Usuario> lista = new ArrayList<>();

    // Metodo para salvar. Não usa SET porque não vai retornar alg
    public void salvar(Usuario novoUsuario){
        lista.add(novoUsuario);
    }

    // Nao usa void porque void não retorna algo. Aqui queremos que retorne
    public Usuario buscarPorId(long id){
        for(Usuario user: lista){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public Usuario buscarPorEmail(String email){
        for(Usuario user: lista){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public boolean jaExisteEmail(String email){
        for(Usuario user: lista){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean atualizarEmail(long id, String email){
        Usuario achado = buscarPorId(id);
        if(achado != null){
            achado.setEmail(email);
            return true;
        }
        return false;
    }

    public boolean atualizarSenha(long id, String senha){
        Usuario achado = buscarPorId(id);
        if(achado != null){
            achado.setSenha(senha);
            return true;
        }
        return false;
    }

    public boolean deletarPorId(long id){
        Usuario achado = buscarPorId(id);

        if(achado != null){
            lista.remove(achado);
            return true;
        }
        return false;
    }
}

