package login.repository;

import login.model.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class UsuarioRepositoryTest {

    private UsuarioRepository repositorio;

    @BeforeEach
    public void setUp() {
        repositorio = new UsuarioRepository();
    }
}
