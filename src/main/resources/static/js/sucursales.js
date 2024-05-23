$(document).ready(function() {
        $('#modalEditarSucursal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Botón que activó el modal
            var id = button.data('id'); // Extraer el ID de los datos atributo
            var modal = $(this);
            modal.find('.modal-body #editId').val(id); //Buscar y Inyectar el ID en el campo oculto

            // Aquí realizamos una petición AJAX para obtener los datos de la categoría
            $.ajax({
                url: "/sucursales/" + id,
                method: "GET",
                success: function(sucursal) {// el objecto Sucursal traido como respuesta de la solicitud
                    modal.find('.modal-body #editNombreSucursal').val(sucursal.nombre); // Llenar el campo de nombre con el valor recibido
                    modal.find('.modal-body #editTelefono').val(sucursal.telefono);
                    modal.find('.modal-body #editDirrecion').val(sucursal.dirrecion);
                },
                error: function(xhr, status, error) {
                    console.error("Error al obtener la Sucursal :", error);
                }
            });
        });
    });