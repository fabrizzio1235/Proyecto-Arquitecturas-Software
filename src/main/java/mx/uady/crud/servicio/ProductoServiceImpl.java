package mx.uady.crud.servicio;

import mx.uady.crud.excepcion.ProductoNoEncontradoException;
import mx.uady.crud.modelo.Producto;
import mx.uady.crud.repositorio.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación de {@link ProductoService}. Delega el acceso a datos en
 * {@link ProductoRepository}, recibido por el constructor. Los métodos
 * {@code findById}, {@code deleteById} y {@code deleteAllById} los define
 * {@code JpaRepository}.
 */
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repositorio;

    /**
     * Crea el servicio con inyección de dependencias por constructor.
     *
     * @param repositorio repositorio de productos
     */
    public ProductoServiceImpl(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public Page<Producto> listar(String busqueda, int pagina, int tamano) {
        Pageable pageable = PageRequest.of(Math.max(pagina, 0), tamano, Sort.by("id"));
        if (busqueda == null || busqueda.isBlank()) {
            return repositorio.findAll(pageable);
        }
        return repositorio.findByNombreContainingIgnoreCase(busqueda.trim(), pageable);
    }

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public Producto obtenerPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    /** {@inheritDoc} */
    @Override
    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }

    /** {@inheritDoc} */
    @Override
    public void eliminar(Long id) {
        if (!repositorio.existsById(id)) {
            throw new ProductoNoEncontradoException(id);
        }
        repositorio.deleteById(id);
    }

    /** {@inheritDoc} */
    @Override
    public int eliminarVarios(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        repositorio.deleteAllById(ids);
        return ids.size();
    }
}
