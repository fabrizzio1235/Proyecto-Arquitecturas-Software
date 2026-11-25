/**
 * Maneja la selección de registros de la lista.
 * Conserva en sessionStorage los ids marcados para poder seleccionar
 * productos de distintas páginas y borrarlos juntos, habilita el botón
 * "Borrar" cuando hay al menos un registro seleccionado y maneja la
 * casilla "seleccionar todos" de la página actual.
 */
(function () {
    const CLAVE = 'productos.seleccionados';
    const marcarTodos = document.getElementById('marcarTodos');
    const casillas = document.querySelectorAll('.casilla');
    const btnBorrar = document.getElementById('btnBorrarSel');
    const formLista = document.getElementById('formLista');
    const etiquetaBorrar = btnBorrar.querySelector('span');

    let seleccion = new Set(leer());

    /** Lee los ids guardados en sessionStorage (vacío si no hay o si falla). */
    function leer() {
        try {
            const datos = JSON.parse(sessionStorage.getItem(CLAVE));
            return Array.isArray(datos) ? datos : [];
        } catch (e) {
            return [];
        }
    }

    /** Guarda la selección actual en sessionStorage. */
    function persistir() {
        try {
            sessionStorage.setItem(CLAVE, JSON.stringify(Array.from(seleccion)));
        } catch (e) {
            // Sin sessionStorage la selección solo dura mientras no se cambie de página.
        }
    }

    /** Refleja la selección en las casillas, la casilla general y el botón Borrar. */
    function actualizar() {
        casillas.forEach(function (c) { c.checked = seleccion.has(c.value); });
        marcarTodos.checked = casillas.length > 0
            && Array.from(casillas).every(function (c) { return c.checked; });
        btnBorrar.disabled = seleccion.size === 0;
        etiquetaBorrar.textContent = seleccion.size > 0
            ? 'Borrar (' + seleccion.size + ')'
            : 'Borrar';
    }

    marcarTodos.addEventListener('change', function () {
        casillas.forEach(function (c) {
            if (marcarTodos.checked) {
                seleccion.add(c.value);
            } else {
                seleccion.delete(c.value);
            }
        });
        persistir();
        actualizar();
    });

    casillas.forEach(function (c) {
        c.addEventListener('change', function () {
            if (c.checked) {
                seleccion.add(c.value);
            } else {
                seleccion.delete(c.value);
            }
            persistir();
            actualizar();
        });
    });

    // Sustituye la confirmación del botón por una que indica el total seleccionado.
    btnBorrar.onclick = function () {
        const enPagina = Array.from(casillas)
            .filter(function (c) { return c.checked; }).length;
        const otras = seleccion.size - enPagina;
        let mensaje = '¿Eliminar ' + seleccion.size + ' registro(s) seleccionado(s)?';
        if (otras > 0) {
            mensaje += '\n' + otras + ' de ellos están en otras páginas.';
        }
        return confirm(mensaje);
    };

    formLista.addEventListener('submit', function (evento) {
        const boton = evento.submitter;
        if (boton === btnBorrar) {
            // Agrega los ids marcados en otras páginas; los de esta página ya viajan en sus casillas.
            const idsPagina = new Set(Array.from(casillas).map(function (c) { return c.value; }));
            seleccion.forEach(function (id) {
                if (!idsPagina.has(id)) {
                    const oculto = document.createElement('input');
                    oculto.type = 'hidden';
                    oculto.name = 'ids';
                    oculto.value = id;
                    formLista.appendChild(oculto);
                }
            });
            seleccion.clear();
            persistir();
        } else if (boton && boton.classList.contains('eliminar')) {
            // Eliminación individual: ese registro deja de estar seleccionado.
            const fila = boton.closest('tr');
            const casilla = fila ? fila.querySelector('.casilla') : null;
            if (casilla) {
                seleccion.delete(casilla.value);
                persistir();
            }
        }
    });

    // Al mostrar la página (incluido volver con el botón Atrás) se vuelve a leer la selección.
    window.addEventListener('pageshow', function () {
        seleccion = new Set(leer());
        actualizar();
    });

    actualizar();
})();