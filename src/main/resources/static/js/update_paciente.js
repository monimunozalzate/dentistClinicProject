window.addEventListener('load', function () {
    // Seleccionar formulario para modificar
    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        // Obtener y formatear datos del formulario
        const formData = {
            paciente_id: document.querySelector('#paciente_id').value,
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
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        // Ejecución fetch
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            window.location.replace("/get_paciente.html");
        })

    })
 });

// Buscar la fila a editar y mostrar los datos actuales
function findBy(id) {
    const url = '/pacientes'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let paciente = data;
        document.querySelector('#paciente_id').value = paciente.id;
        document.querySelector('#nombre').value = paciente.nombre;
        document.querySelector('#apellido').value = paciente.apellido;
        document.querySelector('#dni').value = paciente.dni;
        document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
        document.querySelector('#email').value = paciente.email;
        document.querySelector('#calle').value = paciente.domicilio.calle;
        document.querySelector('#numero').value = paciente.domicilio.numero;
        document.querySelector('#localidad').value = paciente.domicilio.localidad;
        document.querySelector('#provincia').value = paciente.domicilio.provincia;


        // Mostrar el formulario con los datos obtenidos en la busqueda
        document.querySelector('#div_paciente_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}