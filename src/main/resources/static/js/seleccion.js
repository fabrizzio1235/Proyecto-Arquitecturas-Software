(function () {
    const marcarTodos = document.getElementById('marcarTodos');
    const casillas = document.querySelectorAll('.casilla');
    const btnBorrar = document.getElementById('btnBorrarSel');

    function actualizarBoton() {
        const hayMarcadas = Array.from(casillas).some(function (c) { return c.checked; });
        btnBorrar.disabled = !hayMarcadas;
    }

    marcarTodos.addEventListener('change', function () {
        casillas.forEach(function (c) { c.checked = marcarTodos.checked; });
        actualizarBoton();
    });

    casillas.forEach(function (c) {
        c.addEventListener('change', function () {
            marcarTodos.checked = Array.from(casillas).every(function (x) { return x.checked; });
            actualizarBoton();
        });
    });
})();
