package mx.uady.crud.excepcion;

/**
 * Excepción personalizada que indica que no existe un producto con el
 * identificador especificado.
 */
public class ProductoNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1;

    /**
     * Crea la excepción con un mensaje que incluye el identificador buscado.
     *
     * @param id identificador del producto que no se encontró
     */
    public ProductoNoEncontradoException(Long id) {
        super("No existe un producto con id " + id);
    }
}
