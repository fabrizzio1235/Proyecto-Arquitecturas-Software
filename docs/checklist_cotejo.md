# Checklist de cotejo y pruebas del proyecto CRUD

## Parte A. Criterios de evaluación (15 pts lista de cotejo + 5 pts rúbrica + hasta 4 pts extra)

| Tipo | Criterio | Puntos | Evidencia que debe existir | Revisa | Estado    |
|---|---|---|---|---|-----------|
| Lista de cotejo | Descripción de las funcionalidades (objetivo principal y lista de funcionalidades) | 4 | PDF sección 1 | Pedro (Int. 1) | Pendiente |
| Lista de cotejo | Descripción de los componentes con todos sus elementos: nombre, descripción, dependencias, interfaces de salida y de entrada, artefactos y diagrama de componentes | 4 | PDF sección 2 | Francois (Int. 4) | Pendiente |
| Lista de cotejo | Secuencias de los servicios que se proveen y su descripción (nombre, descripción y diagrama UML de cada una) | 4 | PDF sección 4 | Diego y Fabrizzio (Int. 2 y 3) | Pendiente |
| Lista de cotejo | Ejemplo de uso: enlace a un video de YouTube con la ejecución | 3 | PDF sección 5 | Francois (Int. 4) | Pendiente |
| Rúbrica | Cubrir las funcionalidades pactadas (listado al iniciar, casilla por registro, editar y eliminar por registro, botones superiores de crear y borrar seleccionados) | 3 | Demo en vivo y video | Todos | Pendiente |
| Rúbrica | Manejo de al menos una excepción en el componente | 2 | `ProductoNoEncontradoException` y `DataAccessException`; se muestran en la demo abriendo `/productos/editar/9999` | Fabrizzio (Int. 3) | Pendiente |
| Extra | Paginación | 2 | Demo, video y PDF sección 1 | Francois (Int. 4) | Pendiente |
| Extra | Búsqueda | 2 | Demo, video y PDF sección 1 | Francois (Int. 4) | Pendiente |

## Requisitos de forma

- Tabla en base de datos MySQL con al menos 3 campos: 1 identificador único (`id`), 1 campo de texto (`nombre`) y al menos 1 campo numérico (`precio`, `stock`).
- Fichas de clase en PDF (sección 3) con: nombre, descripción, dependencias con otras clases (asociación, nombre y para qué sirve), atributos (nombre, tipo, visibilidad, valor por omisión y descripción), funciones (nombre, argumentos con tipo, valor de retorno, visibilidad, servicio que implementa si es pública con componente e interfaz de salida, y descripción) y el diagrama de clases.
- Código fuente con comentarios Javadoc **y** la documentación Javadoc generada, dentro del ZIP.
- Archivo ZIP llamado `Apellido1Apellido2Apellido3Apellido4.ZIP`.
- Entrega grupal, fecha límite lunes 30 de noviembre de 2026 a las 14:00 (meta interna del equipo: viernes 27).
- Presentar la aplicación funcionando en la sesión destinada para ello.

## Parte B. Pruebas funcionales (integración T-25 y verificación final T-56)

Antes de probar: la tabla `productos` debe tener al menos 7 registros (se cargan con `sql/crud_db.sql`, T-12).

| ID | Prueba | Pasos | Resultado esperado | Estado    |
|---|---|---|---|-----------|
| P-01 | Arranque | Abrir `http://localhost:8080` | Redirige a `/productos` y muestra el listado | Cumple    |
| P-02 | Campos de la tabla | Revisar las columnas | Se ven ID, nombre, precio y stock | Cumple    |
| P-03 | Casilla por registro | Revisar cada fila | Cada registro tiene su casilla | Cumple    |
| P-04 | Crear válido | "Nuevo producto", llenar los 3 campos, Guardar | Vuelve al listado con el mensaje "Producto creado correctamente" y aparece el registro | Cumple    |
| P-05 | Crear con datos inválidos | Enviar con nombre vacío; luego precio negativo; luego stock negativo; luego nombre de más de 100 caracteres | Se queda en el formulario con el mensaje de error junto al campo y no se guarda nada | Cumple    |
| P-06 | Editar | Pulsar el lápiz, cambiar el nombre, Guardar | Mensaje "Producto actualizado correctamente" y el cambio se ve en la lista | Cumple    |
| P-07 | Editar inexistente | Abrir `/productos/editar/9999` | Página de error con el mensaje "No existe un producto con id 9999" | Cumple    |
| P-08 | Eliminar individual | Pulsar el bote, cancelar; repetir y aceptar | Al cancelar no pasa nada; al aceptar se elimina y aparece el mensaje de éxito | Cumple    |
| P-09 | Eliminar ya borrado | Abrir el listado en dos pestañas, borrar el registro en una y luego en la otra | La segunda muestra la página de error (id inexistente) | Cumple    |
| P-10 | Borrar seleccionados | Marcar 2 registros y pulsar "Borrar" | Se eliminan los 2 y aparece "2 registro(s) eliminado(s)" | Cumple    |
| P-11 | Seleccionar todos | Marcar la casilla del encabezado | Se marcan todas las de la página | Cumple    |
| P-12 | Botón "Borrar" sin selección | Entrar sin marcar nada | El botón está deshabilitado; al marcar uno se habilita | Cumple    |
| P-13 | Búsqueda | Buscar "mouse", luego "MOUSE", luego "zzz" | Las dos primeras devuelven el mismo resultado; la tercera muestra "No hay registros para mostrar" | Cumple    |
| P-14 | Paginación | Con más de 5 registros, pulsar "Siguiente", un número y "Anterior" | Cambia de página, la página actual aparece resaltada y el conteo "Mostrando X de Y" es correcto | Cumple    |
| P-15 | Búsqueda con paginación | Buscar un texto con más de 5 coincidencias y cambiar de página | Se conserva el texto buscado al cambiar de página | Cumple    |
| P-16 | Acentos y eñes | Crear "Teclado ergonómico niño" | Se guarda y se ve igual en el listado | Cumple    |
| P-17 | Mensajes de éxito | Después de crear, editar y borrar, recargar la página | El mensaje aparece solo una vez, no se repite al recargar | Cumple    |
| P-18 | Instalación limpia (T-56) | En otra computadora, abrir el ZIP final en IntelliJ y ejecutar `CrudApplication` | Arranca y se ve el listado | Pendiente |
| P-19 | Selección entre páginas (SG-02) | Marcar un producto en la página 1, pasar a la 2 y regresar | La casilla sigue marcada y el botón dice “Borrar (1)” | Cumple    |
| P-20 | Borrar de varias páginas (SG-02) | Marcar uno en la página 1 y otro en la 2 y pulsar Borrar | La confirmación indica el total; al aceptar se eliminan los dos | Cumple    |
| P-21 | Eliminar individual con selección guardada (SG-02) | Marcar uno y eliminar otro con su bote individual | El marcado sigue marcado y no se elimina | Cumple    |
| P-22 | Selección vacía tras borrar (SG-02) | Después de borrar, volver al listado | No queda ninguna casilla marcada y el botón Borrar está deshabilitado | Cumple    |
| P-23 | Selección por pestaña (SG-02) | Marcar uno y abrir `/productos` en otra pestaña | La otra pestaña no muestra nada seleccionado | Cumple    |
| P-24 | Regresión de la selección (SG-02) | Repetir P-11 y P-12 | Seleccionar todos marca solo la página actual y el botón se habilita y deshabilita bien | Cumple    |