package login.service;

import login.model.Usuario;
import login.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
        usuarioRepository = new UsuarioRepository();
    }

    //Testes da area de Cadastro
    @ParameterizedTest
    @ValueSource(strings = {"123pop", "123456", "poppop"})
    //no void deve se escrever ou descrever o que voce espera no teste
    void deveLancarExececaoQuandoSenhaNaoESoNumero(String senha) {
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar("teste@gmail.com", senha);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"123pop", "123456", "poppop"})
    void deveRetornarSeASenhaEValidaSendoSoNumerais(String senha) {
        assertDoesNotThrow(() -> {
            usuarioService.cadastrar("teste@gmail.com", senha);
        });
    }

    //Testes da area de Login
    //esse teste mostra que está jogando o aviso na tela quando a senha é incorreta
    // se o teste passar, é porque ta mostrando. Se não, não está (porque a senha ta certa)
    @Test
    public void deveLancarExcecaoQuandoSenhaDeLoginEIncorreta() {

        usuarioService.cadastrar("teste@gmail.com", "123456");
        assertDoesNotThrow(() -> {
            usuarioService.login("teste@gmail.com", "123000");
        });
    }

    @Test
    public void deveRetornarUsuarioQuandoLoginComSenhaCorreta() {

        usuarioService.cadastrar("teste@gmail.com", "123456");
        Usuario resultado = usuarioService.login("teste@gmail.com", "123456");

        assertTrue(resultado.verificarSenha("123456"));

    }
}