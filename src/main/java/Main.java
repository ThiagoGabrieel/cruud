import login.model.Usuario;
import login.service.UsuarioService;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static UsuarioService service = new UsuarioService();
    static Usuario usuario;


    public static void main(String[] args){

        while (true){
            System.out.println("---------------------");
            System.out.println("[1] - CADASTRAR");
            System.out.println("[2] - LOGIN");
            System.out.println("[3] - ATUALIZAR EMAIL");
            System.out.println("[4] - ATUALIZAR SENHA");
            System.out.println("[5] - DELETAR CONTA");
            System.out.println("[6] - SAIR");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine();
            System.out.println("---------------------");

            switch (opcao){

                case "1": cadastrar(); break;
                case "2": login(); break;
                case "3": atualizarEmail(); break;
                case "4": atualizarSenha(); break;
                case "5": deletar(); break;
                case "6": {
                    System.out.print("Encerrando...");
                    sc.close();
                    return;
                }
                default: System.out.println("Opção inválida");
            }
        }
    }

    static void cadastrar(){
        System.out.println("----- CADASTRO -----");

        try{

            System.out.print("Crie um email: ");
            String email = sc.nextLine();

            System.out.print("Crie uma senha(min. 6 digitos): ");
            String senha = sc.nextLine();

            Usuario novoUsuario = service.cadastrar(email, senha);

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    static void login(){

        int maxTentativas = 3;
        int tentativas = 0;

        while(tentativas < maxTentativas) {

            System.out.println("----- LOGIN -----");

            try {
                System.out.print("Digite seu email: ");
                String emailDigitado = sc.nextLine();

                System.out.print("Digite sua senha: ");
                String senhaDigitada = sc.nextLine();

                usuario = service.login(emailDigitado, senhaDigitada);

                System.out.println("Bem vindo!");
                return;

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                tentativas ++;
            }
            if (tentativas == maxTentativas) {
                System.out.println("Máxima de tentativas atingidas!");
            }
        }
    }

    static void atualizarEmail() {
        System.out.println("----- ATUALIZAR EMAIL -----");

        try {
            System.out.print("Primeiramente digite sua senha por segurança:");
            String senhaDigitada = sc.nextLine();

            System.out.print("Digite o email novo:");
            String email = sc.nextLine();

            service.atualizacaoEmail(usuario.getId(), email, senhaDigitada);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void atualizarSenha() {
        System.out.println("----- ATUALIZAR SENHA -----");

        try {
            System.out.print("Digite sua senha atual:");
            String senhaDigitada = sc.nextLine();

            System.out.print("Digite sua nova senha(min. 6 digitos):");
            String senha = sc.nextLine();

            service.atualizarSenha(usuario.getId(), senhaDigitada, senha);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    static void deletar() {
        System.out.println("----- DELETAR CONTA -----");

        try {
            System.out.print("Digite sua senha para deletar: ");
            String senha = sc.nextLine();

            System.out.print("Deseja realmente deletar sua conta? (sim/nao): ");
            String resposta = sc.nextLine();

            if (resposta.equals("sim")) {
                service.deletar(usuario.getId(),senha);
                System.out.println("Conta deletada com Sucesso!!");
            } else{
                System.out.println("Cancelando....");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

