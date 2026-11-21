package mx.uady.crud.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/*
 Entidad que representa un registro de la tabla {@code productos}.
 Contiene un identificador único, un campo de texto (nombre) y campos numéricos (precio y stock).
*/
@Entity
@Table(name = "productos")
public class Producto {

    // Identificador único autoincremental.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del producto (campo de texto).
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    // Precio unitario (campo numérico decimal).
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // Existencias disponibles (campo numérico entero).
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    // Constructor vacío requerido por JPA.
    public Producto() {
    }

    /*
     Crea un producto con todos sus datos.
     @param id     identificador (puede ser {@code null} si es nuevo)
     @param nombre nombre del producto
     @param precio precio unitario
     @param stock  existencias disponibles
    */
    public Producto(Long id, String nombre, BigDecimal precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // @return el identificador del producto
    public Long getId() { return id; }

    // @param id nuevo identificador
    public void setId(Long id) { this.id = id; }

    // @return el nombre del producto
    public String getNombre() { return nombre; }

    // @param nombre nuevo nombre
    public void setNombre(String nombre) { this.nombre = nombre; }

    // @return el precio unitario
    public BigDecimal getPrecio() { return precio; }

    // @param precio nuevo precio
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    // @return las existencias disponibles
    public Integer getStock() { return stock; }

    // @param stock nuevas existencias
    public void setStock(Integer stock) { this.stock = stock; }
}