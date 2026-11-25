package mx.uady.crud.servicio;

import mx.uady.crud.modelo.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

// Contrato de la lógica de negocio para la gestión de productos.
public interface ProductoService {

    // Lista productos de forma paginada, con filtro opcional por nombre.
    //Se define el numero de la pagina y el tamaño de cada pagina

    Page<Producto> listar(String busqueda, int pagina, int tamano);

    // Obtiene un producto por su identificador.

    Producto obtenerPorId(Long id);

    // Crea un producto nuevo o actualiza uno existente.

    Producto guardar(Producto producto);

    // Elimina un producto. Lanza la excepcion personalizada si no se encuentra

    void eliminar(Long id);

    // Elimina varios productos a la vez. Utiliza una lista de ID's y devuelve el total
    // de registros eliminados

    int eliminarVarios(List<Long> ids);
}
