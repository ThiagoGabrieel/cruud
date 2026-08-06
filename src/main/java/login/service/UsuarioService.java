package login.service;

import login.model.Usuario;
import login.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository = new  UsuarioRepository();

    public Usuario cadastrar(String email, String senha) {

        if(repository.jaExisteEmail(email))
            throw new IllegalArgumentException("Email já cadastrado!");

        if(!senha.matches("[0-9]{6}")) {
            throw new IllegalArgumentException("Sua senha precisa conter somente números!");
        }

        Usuario novoUsuario = new Usuario(email, senha);
        repository.salvar(novoUsuario);
        System.out.println("Usuario cadastrado com sucesso!");

        return novoUsuario;
    }

    public Usuario login(String email, String senha) {
        Usuario encontrado = repository.buscarPorEmail(email);

        if (encontrado == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if (!encontrado.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }

        return encontrado;
    }

    public Usuario atualizacaoEmail(long id, String email, String senha) {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(!usuario.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if(repository.jaExisteEmail(email)){
            throw new IllegalArgumentException("Invalido. Esse email já foi cadastrado!");
        }
        usuario.setEmail(email);
        System.out.println("Email atualizado com sucesso!");

        return usuario;
    }

    public Usuario atualizarSenha(long id, String senha, String senhaDigitada) {
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(!usuario.verificarSenha(senha)) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
        if(usuario.verificarSenha(senhaDigitada)) {
            throw new IllegalArgumentException("Sua senha não pode ser igual a anterior");
        }
        usuario.setSenha(senhaDigitada);
        System.out.println("Senha atualizada com sucesso!");

        return usuario;
    }

    public void deletar(long id, String senha){
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado!");
        }
        if(!usuario.verificarSenha(senha)){
            throw new IllegalArgumentException("Senha incorreta!");
        }

        repository.deletarPorId(id);
        System.out.println("Usuario deletado");
    }

    public boolean validarSenha(long id, String senha){
        Usuario usuario = repository.buscarPorId(id);
        if(usuario == null) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        if(!usuario.verificarSenha(senha)){
            throw new IllegalArgumentException("Senha incorreta!");
        }
        return true;
    }
}