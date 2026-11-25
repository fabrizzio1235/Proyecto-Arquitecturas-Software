package mx.uady.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación CRUD de productos. Arranca el contexto
 * de Spring Boot y el servidor web embebido.
 */
@SpringBootApplication
public class CrudApplication {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }
}
