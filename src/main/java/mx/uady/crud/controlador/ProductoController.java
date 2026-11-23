package mx.uady.crud.controlador;

import jakarta.validation.Valid;
import mx.uady.crud.modelo.Producto;
import mx.uady.crud.servicio.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    /**
     * Muestra el formulario vacío para crear un producto.
     *
     * @param modelo modelo para la vista
     * @return nombre de la vista {@code formulario}
     */
    @GetMapping("/productos/nuevo")
    public String nuevo(Model modelo) {
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("titulo", "Nuevo producto");
        return "formulario";
    }

    /**
     * Muestra el formulario cargado con los datos de un producto para editarlo.
     *
     * @param id     identificador del producto
     * @param modelo modelo para la vista
     * @return nombre de la vista {@code formulario}
     */
    @GetMapping("/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerPorId(id));
        modelo.addAttribute("titulo", "Editar producto");
        return "formulario";
    }

    /**
     * Guarda un producto nuevo o actualiza uno existente, validando los datos.
     *
     * @param producto  producto recibido del formulario
     * @param resultado resultado de la validación
     * @param modelo    modelo para la vista
     * @param flash     atributos que sobreviven a la redirección
     * @return el formulario si hay errores; si no, redirección al listado
     */
    @PostMapping("/productos/guardar")
    public String guardar(@Valid @ModelAttribute("producto") Producto producto,
                          BindingResult resultado,
                          Model modelo,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            modelo.addAttribute("titulo",
                    producto.getId() == null ? "Nuevo producto" : "Editar producto");
            return "formulario";
        }
        boolean esNuevo = producto.getId() == null;
        if (!esNuevo) {
            servicio.obtenerPorId(producto.getId()); // lanza excepción si ya no existe
        }
        servicio.guardar(producto);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Producto creado correctamente" : "Producto actualizado correctamente");
        return "redirect:/productos";
    }

    /**
     * Elimina un producto individual.
     *
     * @param id    identificador del producto
     * @param flash atributos que sobreviven a la redirección
     * @return redirección al listado
     */
    @PostMapping("/productos/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        servicio.eliminar(id);
        flash.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/productos";
    }

    /**
     * Elimina todos los productos marcados con la casilla de verificación.
     *
     * @param ids   identificadores seleccionados (puede venir vacío)
     * @param flash atributos que sobreviven a la redirección
     * @return redirección al listado
     */
    @PostMapping("/productos/eliminar-seleccionados")
    public String eliminarSeleccionados(@RequestParam(required = false) List<Long> ids,
                                        RedirectAttributes flash) {
        int borrados = servicio.eliminarVarios(ids);
        if (borrados == 0) {
            flash.addFlashAttribute("error", "No seleccionaste ningún registro");
        } else {
            flash.addFlashAttribute("mensaje", borrados + " registro(s) eliminado(s)");
        }
        return "redirect:/productos";
    }
}