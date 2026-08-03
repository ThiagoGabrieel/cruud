package login.repository;

import login.model.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioRepositoryTest {

    private UsuarioRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new UsuarioRepository();
    }

   @Test
    public void deveNaoLancarNullSeEmailForEncontrado(){
        repository.salvar(new Usuario("teste@gmail.com","123456"));
        Usuario encontrado = repository.buscarPorEmail("teste@gmail.com");
        assertNotNull(encontrado);
    }
}
