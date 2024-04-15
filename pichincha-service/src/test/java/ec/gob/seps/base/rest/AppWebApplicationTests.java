package ec.gob.seps.base.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppWebApplicationTests {


    @Test
    public void contextLoads() {
        CuentaJpaTest cuentaTest = new CuentaJpaTest();
        cuentaTest.testConsultaBaseDeDatos();
    }

}
