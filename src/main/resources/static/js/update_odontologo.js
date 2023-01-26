window.addEventListener('load', function () {
    // Seleccionar formulario para modificar
    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        // Obtener y formatear datos del formulario
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,

        };
        // Ruta y configuracion de fetch
        const url = '/odontologos';
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
            window.location.replace("/get_list_odontologo.html");
        })

    })
 });

// Buscar la fila a editar y mostrar los datos actuales
function findBy(id) {
    const url = '/odontologos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        document.querySelector('#odontologo_id').value = odontologo.id;
         document.querySelector('#matricula').value = odontologo.matricula;
        document.querySelector('#nombre').value = odontologo.nombre;
        document.querySelector('#apellido').value = odontologo.apellido;

        // Mostrar el formulario con los datos obtenidos en la busqueda
        document.querySelector('#div_odontologo_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}