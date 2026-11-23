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

/*
 Controlador web del CRUD de productos. Recibe las peticiones HTTP,
 invoca al servicio y selecciona la vista a mostrar.
*/
@Controller
public class ProductoController {

    // Cantidad de registros por pagina en el listado.
    private static final int TAMANO_PAGINA = 5;

    private final ProductoService servicio;

    // Inyecta el servicio de productos.
    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    // Redirige la raiz al listado de productos.
    @GetMapping("/")
    public String inicio() {
        return "redirect:/productos";
    }

    // Muestra el listado paginado de productos, con busqueda opcional por nombre.
    @GetMapping("/productos")
    public String listar(@RequestParam(required = false) String q,
                         @RequestParam(defaultValue = "0") int page,
                         Model modelo) {
        Page<Producto> pagina = servicio.listar(q, page, TAMANO_PAGINA);
        modelo.addAttribute("pagina", pagina);
        modelo.addAttribute("q", q);
        return "lista";
    }

    // Muestra el formulario vacio para crear un producto.
    @GetMapping("/productos/nuevo")
    public String nuevo(Model modelo) {
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("titulo", "Nuevo producto");
        return "formulario";
    }

    // Muestra el formulario cargado con los datos de un producto para editarlo.
    @GetMapping("/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerPorId(id));
        modelo.addAttribute("titulo", "Editar producto");
        return "formulario";
    }

    /*
     Guarda un producto nuevo o actualiza uno existente, validando los datos.
     Si hay errores regresa al formulario; si no, redirige al listado con mensaje de exito.
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
            servicio.obtenerPorId(producto.getId()); // lanza excepcion si ya no existe
        }
        servicio.guardar(producto);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Producto creado correctamente" : "Producto actualizado correctamente");
        return "redirect:/productos";
    }
}