package mx.uady.crud.excepcion;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Manejador global de excepciones. Convierte los errores en una vista
 * amigable en lugar de mostrar la traza al usuario.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja el caso de un producto inexistente.
     *
     * @param ex     excepción lanzada
     * @param modelo modelo para la vista
     * @return vista {@code error}
     */
    @ExceptionHandler(ProductoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productoNoEncontrado(ProductoNoEncontradoException ex, Model modelo) {
        modelo.addAttribute("mensaje", ex.getMessage());
        return "error";
    }

    /**
     * Maneja errores de acceso a la base de datos (conexión, integridad, etc.).
     *
     * @param ex     excepción lanzada
     * @param modelo modelo para la vista
     * @return vista {@code error}
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String errorBaseDatos(DataAccessException ex, Model modelo) {
        modelo.addAttribute("mensaje",
                "Ocurrió un error al acceder a la base de datos. Intenta de nuevo más tarde.");
        return "error";
    }
}