package login.model;

public class Usuario {
    private static long proximoId = 1L;
    private long id;
    private String email;
    private String senha;

    //construtor
    public Usuario (String email, String senha){
        setEmail(email);
        setSenha(senha);
        this.id = proximoId;
        proximoId++;
    }

    // Getter

    public long getId() { return id; }
    public String getEmail() { return email; }

    // Setter com válidação
    public void setEmail (String email){
        if(email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            throw new IllegalArgumentException("Seu email inválido");
        this.email = email;
    }

    public void setSenha(String senha){
        if(senha == null || !senha.matches("[0-9]{6}")) {
            throw new IllegalArgumentException("Sua senha precisa conter somente Digitos!");
        }
        this.senha = senha;
    }

    public boolean verificarEmail(String emailDigitado){
        return this.email.equals(emailDigitado);
    }
    public boolean verificarSenha(String senhaDigitada){
        return this.senha.equals(senhaDigitada);
    }
}


