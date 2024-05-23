$(document).ready(function() {
        $('#modalEditarBodega').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Botón que activó el modal
            var id = button.data('id'); // Extraer el ID de los datos atributo
            var modal = $(this);
            modal.find('.modal-body #editId').val(id); //Buscar y Inyectar el ID en el campo oculto

            // Aquí realizamos una petición AJAX para obtener los datos de la categoría
            $.ajax({
                url: "/bodegas/" + id,
                method: "GET",
                success: function(data) {//data el objecto modulo traido como respuesta de la solicitud
                    modal.find('.modal-body #editNombreBodega').val(data.nombre); // Llenar el campo de nombre con el valor recibido
                    modal.find('.modal-body #editTemperaturaBodega').val(data.temperatura);
                },
                error: function(xhr, status, error) {
                    console.error("Error al obtener la Bodega :", error);
                }
            });
        });
    });