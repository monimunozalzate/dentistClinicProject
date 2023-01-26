window.addEventListener('load', function () {
    // Seleccionar formulario para modificar
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();

        // Obtener y formatear datos del formulario

        const formData = {
            turno_id: document.querySelector('#turno_id').value,
             fecha: document.querySelector('#fecha').value,
             pacienteId: document.querySelector('#paciente_id').value,
            odontologoId: document.querySelector('#odontologo_id').value,
        };

        // Ruta y configuracion de fetch
          const url = '/turnos';
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
                    window.location.replace("/get_turno.html");
                })

    })
 });

// Buscar la fila a editar y mostrar los datos actuales
function findBy(id) {
    const url = '/turnos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#fecha').value=turno.fecha;
            document.querySelector('#paciente_id').value=turno.pacienteId;
            document.querySelector('#odontologo_id').value=turno.odontologoId;

   // Mostrar el formulario con los datos obtenidos en la busqueda
        document.querySelector('#div_turno_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}