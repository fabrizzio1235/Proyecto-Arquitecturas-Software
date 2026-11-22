package mx.uady.crud.excepcion;

public class ProductoNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID= 1;
    //Excepcion personalizada que indica si un producto no se encontró bajo el id especificado
    public ProductoNoEncontradoException(Long id) {
        super("No existe un producto con id " + id);
    }
}
