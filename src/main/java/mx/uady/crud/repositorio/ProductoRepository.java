package mx.uady.crud.repositorio;

import mx.uady.crud.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 Repositorio de acceso a datos para la entidad de producto.
 Hereda las operaciones CRUD básicas de JPARepository.
*/
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //El tipo de dado page define una interfaz que representa un fragmento de resultados
    // de una consulta, permitiendo iterar entre paginas en lugar de tener todos los resultados
    // de golpe.

    /*
     Busca productos cuyo nombre contenga el texto indicado. Case insensitive
     Regresa pagina de productos que coinciden con el nombre.
    */

    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
