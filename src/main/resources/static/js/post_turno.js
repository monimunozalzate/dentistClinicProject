window.addEventListener('load', function () {
    // Link formulario
    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
       // Obtener y formatear datos del formulario
        const formData = {
        fechaTurno: document.querySelector('#fechaTurno').value,
           pacienteId: document.querySelector('#id_paciente').value,
            odontologoId: document.querySelector('#id_odontologo').value,

        };
        // Ruta y configuracion de fetch
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        // Ejecución fetch
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 // Alerta de exito
                let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<div> Turno agregado </div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                //resetUploadForm();

            })
            .catch(error => {
                // Alerta de error
                let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                '<div> Error! Intente nuevamente :)</div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //resetUploadForm();
            })
    });


    const resetUploadForm = () => {
    document.querySelector('#fechaTurno').value = "";
        document.querySelector('#id_paciente').value = "";
        document.querySelector('#id_odontologo').value = "";

    }

});
