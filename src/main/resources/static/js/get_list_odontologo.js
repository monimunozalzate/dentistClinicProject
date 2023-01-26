window.addEventListener('load', function () {
    // Ruta y configuracion de fetch
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }
    // Ejecucion fetch
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        // Recorrer la lista de resultados
        for(odontologo of data){
            // Cada fila tendrá un id que luego nos permitirá borrar la fila
            var table = document.getElementById("odontologoTable");
            var odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            // Boton eliminar
            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                              ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            // Boton modificar
            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                              ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                              odontologo.id +
                              '</button>';

            // Armado de fila
            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});