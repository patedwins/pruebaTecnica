package ec.gob.seps.base.rest;

import com.pichincha.postgres.repository.ICuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Import(CuentaJpaTest.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuentaJpaTest {
    @Autowired
    private transient ICuentaRepository cuentaRepository;

    @Test
    public void testConsultaBaseDeDatos() {
        // Aquí puedes realizar consultas a través del repositorio y asertar los resultados
        // Por ejemplo:
        int count = cuentaRepository.findAll().size(); // Suponiendo que tu repositorio tiene un método contarElementos()
        assertEquals(5, count); // Suponiendo que esperas 5 elementos en la base de datos
    }
}
