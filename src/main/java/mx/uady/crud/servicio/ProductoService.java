package mx.uady.crud.servicio;

import mx.uady.crud.modelo.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Contrato de la lógica de negocio para la gestión de productos.
 */
public interface ProductoService {

    /**
     * Lista productos de forma paginada, con filtro opcional por nombre.
     *
     * @param busqueda texto a buscar en el nombre (puede ser {@code null} o vacío)
     * @param pagina   número de página, empezando en 0
     * @param tamano   cantidad de registros por página
     * @return página de productos
     */
    Page<Producto> listar(String busqueda, int pagina, int tamano);

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador del producto
     * @return el producto encontrado
     * @throws mx.uady.crud.excepcion.ProductoNoEncontradoException si no existe
     */
    Producto obtenerPorId(Long id);

    /**
     * Crea un producto nuevo o actualiza uno existente.
     *
     * @param producto producto a guardar
     * @return el producto guardado
     */
    Producto guardar(Producto producto);

    /**
     * Elimina un producto.
     *
     * @param id identificador del producto
     * @throws mx.uady.crud.excepcion.ProductoNoEncontradoException si no existe
     */
    void eliminar(Long id);

    /**
     * Elimina varios productos a la vez.
     *
     * @param ids lista de identificadores (puede ser {@code null} o vacía)
     * @return total de registros eliminados
     */
    int eliminarVarios(List<Long> ids);
}
