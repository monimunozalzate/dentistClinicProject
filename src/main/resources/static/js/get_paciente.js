window.addEventListener('load', function () {
    // Ruta y configuracion de fetch
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    }
    // Ejecucion fetch
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        // Recorrer la lista de resultados
        for(paciente of data){
            // Cada fila tendrá un id que luego nos permitirá borrar la fila
            var table = document.getElementById("pacienteTable");
            var pacienteRow = table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            // Boton eliminar
            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                              ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            // Boton modificar
            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                              ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                              paciente.id +
                              '</button>';

            // Armado de fila
            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni.toUpperCase() + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso.toUpperCase() + '</td>' +
                    '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +
                    '<td class=\"td_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
                    '<td class=\"td_numero\">' + paciente.domicilio.numero + '</td>' +
                    '<td class=\"td_localidad\">' + paciente.domicilio.localidad.toUpperCase() + '</td>' +
                    '<td class=\"td_provincia\">' + paciente.domicilio.provincia.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});