package mx.uady.crud.controlador;

import mx.uady.crud.modelo.Producto;
import mx.uady.crud.servicio.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador web del CRUD de productos. Recibe las peticiones HTTP,
 * invoca al servicio y selecciona la vista a mostrar.
 */
@Controller
public class ProductoController {

    /** Cantidad de registros por página en el listado. */
    private static final int TAMANO_PAGINA = 5;

    private final ProductoService servicio;

    /**
     * Inyecta el servicio de productos.
     *
     * @param servicio servicio con la lógica de negocio
     */
    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    /**
     * Redirige la raíz al listado de productos.
     *
     * @return redirección a {@code /productos}
     */
    @GetMapping("/")
    public String inicio() {
        return "redirect:/productos";
    }

    /**
     * Muestra el listado paginado de productos, con búsqueda opcional.
     *
     * @param q      texto de búsqueda por nombre (opcional)
     * @param page   número de página (por omisión 0)
     * @param modelo modelo para la vista
     * @return nombre de la vista {@code lista}
     */
    @GetMapping("/productos")
    public String listar(@RequestParam(required = false) String q,
                         @RequestParam(defaultValue = "0") int page,
                         Model modelo) {
        Page<Producto> pagina = servicio.listar(q, page, TAMANO_PAGINA);
        modelo.addAttribute("pagina", pagina);
        modelo.addAttribute("q", q);
        return "lista";
    }
}