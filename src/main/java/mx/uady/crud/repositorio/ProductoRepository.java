package mx.uady.crud.repositorio;

import mx.uady.crud.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad {@link Producto}.
 * Hereda las operaciones CRUD básicas de {@link JpaRepository}.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca productos cuyo nombre contenga el texto indicado, sin distinguir
     * mayúsculas de minúsculas. El tipo {@link Page} representa un fragmento
     * de los resultados, lo que permite recorrerlos por páginas.
     *
     * @param nombre   texto que debe contener el nombre
     * @param pageable datos de paginación (número y tamaño de página, orden)
     * @return página de productos que coinciden con el nombre
     */
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
