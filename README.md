#  Repositorio de Arquitecturas de Software

##  Integrantes del Equipo

|<img width="200" height="250" alt="image" src="https://github.com/user-attachments/assets/c2d404cf-00ff-4065-9173-5124f7e9aae2" /> |<img width="200" height="250" alt="image" src="https://github.com/user-attachments/assets/4f2d8ee8-470a-480c-a774-388eef8e57b3" />|<img width="200" height="250" alt="image" src="https://github.com/user-attachments/assets/204cb5a4-6d2e-441b-9f76-fbbd3df0c4c5" />|<img width="200" height="250" alt="image" src="https://github.com/user-attachments/assets/540ed299-0a7d-40d4-b08d-5743e6a4dc74" />|
| :---: | :---: | :---: | :---: |
| **Fabrizzio Sebastian<br>España Chi** | **François Bilal<br>Pageau Santos**| **Pedro Jesús<br>Tenorio Cauich** | **Diego Armín<br>Zapata Góngora** |

## CRUD de productos con Spring Boot

Aplicación web para administrar productos (alta, edición, baja, búsqueda y paginación), hecha con Spring Boot 3.3.5, Thymeleaf, Spring Data JPA y MySQL. Proyecto final de la materia Arquitecturas de Software, Universidad Autónoma de Yucatán, Facultad de Matemáticas.

### Funcionalidades

- Listado de productos con ID, nombre, precio y stock, paginado de 5 en 5.
- Búsqueda por nombre (sin distinguir mayúsculas).
- Crear y editar productos con validación de datos.
- Eliminar un producto o varios a la vez con casillas de selección; la selección se conserva al cambiar de página.
- Página de error cuando el producto no existe.

### Requisitos

- Java 17
- MySQL 8 o superior, en ejecución en el puerto 3306
- IntelliJ IDEA (incluye Maven) o Maven 3.9 o superior

### Cómo ejecutarlo

1. Crea la base y carga los datos de prueba, con tu usuario de MySQL:

   ```bash
   mysql -u root -p < sql/crud_db.sql
   ```

2. Abre `src/main/resources/application.properties` y cambia `spring.datasource.password` (y el usuario, si no es `root`) por los de tu MySQL. **No subas esa contraseña al repositorio.**
3. Abre el proyecto en IntelliJ (File → Open, selecciona la carpeta donde está `pom.xml`) y espera a que Maven descargue las dependencias.
4. Ejecuta la clase `CrudApplication` (`src/main/java/mx/uady/crud/CrudApplication.java`).
5. Abre `http://localhost:8080` en el navegador.

### Estructura del proyecto

| Carpeta | Contenido |
|---|---|
| `src/main/java/mx/uady/crud` | Código fuente por capas: `modelo`, `repositorio`, `servicio`, `controlador` y `excepcion` |
| `src/main/resources/templates` | Vistas Thymeleaf (`lista`, `formulario`, `error`) |
| `src/main/resources/static` | Estilos y script de selección |
| `sql` | Script de la base de datos |
| `docs` | Diagramas y checklist de pruebas |

### Documentación Javadoc

Se genera con Maven: `mvn javadoc:javadoc`. El resultado queda en `target/site/apidocs/index.html`.