window.addEventListener('load', function () {
    // Ruta y configuracion de fetch
    const url = '/turnos';
    const settings = {
        method: 'GET'
    }
    // Ejecucion fetch
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        // Recorrer la lista de resultados
        for(turno of data){
            // Cada fila tendrá un id que luego nos permitirá borrar la fila
            var table = document.getElementById("turnoTable");
            var turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            // Boton eliminar
            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                              ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            // Boton modificar
            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                              ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                              turno.id +
                              '</button>';

            // Armado de fila
                    turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td class=\"td_odontologo_id">' + turno.pacienteId + '</td>' +
                    '<td class=\"td_paciente_id\">' + turno.odontologoId + '</td>' +
                    '<td>' + deleteButton + '</td>';
           };
 })

});


