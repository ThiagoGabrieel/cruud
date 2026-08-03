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
    // creio que esse teste ja cobre o salvar na memoria, buscar email e ver se existe no array
   @Test
    void deveRetornarUsuarioQuandoEmailExistir(){
        repository.salvar(new Usuario("teste@gmail.com","123456"));

        Usuario encontrado = repository.buscarPorEmail("teste@gmail.com");

        assertNotNull(encontrado);
        assertEquals("teste@gmail.com",encontrado.getEmail());
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir(){
        repository.salvar(new Usuario("teste@gmail.com","123456"));
        Usuario segundo = new Usuario("teste@gmail.com","123456");
        repository.salvar(segundo);

        Usuario encontrado = repository.buscarPorId(segundo.getId());

        assertNotNull(encontrado);
        assertEquals(segundo.getId(),encontrado.getId());
    }
}
