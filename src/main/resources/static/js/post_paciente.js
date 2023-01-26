window.addEventListener('load', function () {
    // Link formulario
    const formulario = document.querySelector('#add_new_paciente');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
       // Obtener y formatear datos del formulario
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            email: document.querySelector('#email').value,
            domicilio : {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };
        // Ruta y configuracion de fetch
        const url = '/pacientes';
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
                '<div> Paciente agregado </div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                //resetUploadForm();

            })
            .catch(error => {
                // Alerta de error
                let errorAlert = '<div class="alert alert-warning alert-dismissible fade show" role="alert">' +
                '<div> Error! Intente nuevamente </div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //resetUploadForm();
            })
    });


    const resetUploadForm = () => {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#email').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }

});