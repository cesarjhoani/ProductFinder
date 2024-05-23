$(document).ready(function() {
        $('#modalEditarModulo').on('show.bs.modal', function (event) {
            console.log("Modal se está mostrando...");
            var button = $(event.relatedTarget); // Botón que activó el modal
            var id = button.data('id'); // Extraer el ID de los datos atributo
            console.log("ID de modulo:", id); // Verificar si se está obteniendo el ID correctamente
            var modal = $(this);
            modal.find('.modal-body #editId').val(id); //Buscar y Inyectar el ID en el campo oculto

            // Aquí realizamos una petición AJAX para obtener los datos de la categoría
            $.ajax({
                url: "/modulos/" + id,
                method: "GET",
                success: function(data) {//data el objecto modulo traido como respuesta de la solicitud
                    modal.find('.modal-body #editNombreModulo').val(data.nombre); // Llenar el campo de nombre con el valor recibido
                },
                error: function(xhr, status, error) {
                    console.error("Error al obtener la categoría:", error);
                }
            });
        });
    });