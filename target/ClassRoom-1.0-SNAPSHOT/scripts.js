/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * _____________________________________________________________________________
 * FUNCIONES PARA TRAER DATOS DESDE ARCHIVOS json - Cambiar en el HTML la llamada
 * a las funciones pertinentes
 * _____________________________________________________________________________
 */

/**
 * Función que realiza petición al servidor para traer los Departamentos del archivo JSON
 * @returns {undefined}
 */

function getDepartments() {
    var dptos = document.getElementById("departments");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "Control?option=0", true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const records = JSON.parse(xhr.responseText);
            records.forEach(dpto => {
                let option = document.createElement("option");
                option.setAttribute("value", dpto.code);
                option.appendChild(document.createTextNode(dpto.name));
                dptos.appendChild(option);
            });
        }
    };
    xhr.send(null);
}

/**
 * Función que realiza petición al servidor para traer los Municipios de un Departamento del archivo JSON
 * @returns {undefined}
 */

function getTowns() {
    var towns = document.getElementById("towns");
    towns.length = 0;
    const code = document.getElementById("departments").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", `Control?option=1&code=${code}`, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const records = JSON.parse(xhr.responseText);
            records.forEach(town => {
                let option = document.createElement("option");
                option.setAttribute("value", town.code);
                option.appendChild(document.createTextNode(town.name));
                towns.appendChild(option);
            })
        }
    };
    xhr.send(null);
}

/**
 * Función que realiza una petición al servidor para consultar un municipio por código
 * @returns {undefined}
 */

function findTown() {
    const code = document.getElementById("towns").value;
    var out = document.getElementById("output");
    out.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.open("GET", `Control?option=2&code=${code}`, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const town = JSON.parse(xhr.responseText);
            let title = document.createElement("h1");
            title.appendChild(document.createTextNode(`Datos de ${town.name}`));
            out.appendChild(title);
            out.appendChild(document.createTextNode(`Código-->${town.code}`));
            out.appendChild(document.createElement("br")) //Salto de Línea
            out.appendChild(document.createTextNode(`Nombre Departamento-->${town.department}`));
            out.appendChild(document.createElement("br")) //Salto de Línea
            out.appendChild(document.createTextNode(`Nombre Municipio-->${town.name}`));
        }
    };
    xhr.send(null);
}

/**
 * _____________________________________________________________________________
 * FUNCIONES PARA TRAER DATOS DESDE UNA BASE DE DATOS mysql - cambiar en el HTML
 * las llamadas a las funciones correspondientes.
 * _____________________________________________________________________________
 */

/**
 * Función que realiza una petición AJAX al servidor para traer los Departamentos de una tabla de la Base de Datos
 * @returns {undefined}
 */

function loadDepartments() {
    var dptos = document.getElementById("departments");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ControllDB?option=0", true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const records = JSON.parse(xhr.responseText);
            records.forEach(dpto => {
                let option = document.createElement("option");
                option.setAttribute("value", dpto.code);
                option.appendChild(document.createTextNode(dpto.name));
                dptos.appendChild(option);
            });
        }
    };
    xhr.send(null);
}

/**
 * Función que realiza una petición AJAX al servidor para traer los Municipios de un Departamento de una tabla de la Base de Datos
 * @returns {undefined}
 */

function loadTowns() {
    var towns = document.getElementById("towns");
    towns.length = 0;
    const code = document.getElementById("departments").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", `ControllDB?option=1&code=${code}`, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const records = JSON.parse(xhr.responseText);
            records.forEach(town => {
                let option = document.createElement("option");
                option.setAttribute("value", town.code);
                option.appendChild(document.createTextNode(town.name));
                towns.appendChild(option);
            })
        }
    };
    xhr.send(null);
}

/**
 * Función que permite a través de una petición AJAX, buscar un municipio y visualizar sus datos
 * @returns {undefined}
 */

function searchTown() {
    const code = document.getElementById("towns").value;
    var out = document.getElementById("output");
    out.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.open("GET", `ControllDB?option=2&code=${code}`, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const town = JSON.parse(xhr.responseText);
            let title = document.createElement("h1");
            title.appendChild(document.createTextNode(`Datos de ${town.name}`));
            out.appendChild(title);
            out.appendChild(document.createTextNode(`Código-->${town.code}`));
            out.appendChild(document.createElement("br")) //Salto de Línea
            out.appendChild(document.createTextNode(`Nombre Departamento-->${town.department}`));
            out.appendChild(document.createElement("br")) //Salto de Línea
            out.appendChild(document.createTextNode(`Nombre Municipio-->${town.name}`));
        }
    };
    xhr.send(null);
}

function showDepartments(){
    var dptos = document.getElementById("dptos");
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "Control?option=0", true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const records = JSON.parse(xhr.responseText);
            records.forEach(dpto => {
                let option = document.createElement("option");
                option.setAttribute("value", dpto.code);
                option.appendChild(document.createTextNode(dpto.name));
                dptos.appendChild(option);
            });
        }
    };
    xhr.send(null);
}

/**
 * _____________________________________________________________________________
 * FUNCIONES PARA INTEARCTUAR CON EL SERVIDOR A TRAVÉS DE AJAX, PARA EL CRUD DE MUNICIPIOS
 * _____________________________________________________________________________
 */

/**
 * Función que permite adicionar un municipio a la base de datos.
 * @returns {undefined}
 */

function addTown() {
    event.preventDefault();
    const code = document.getElementById("code").value;
    const dpto = document.getElementById("dptos").value;
    const name = document.getElementById("name").value;
    var tblBody = document.getElementById("tblBody");
    tblBody.innerHTML = "";
    if (/^[0-9]{1,6}$/.test(code) && /^[a-zA-Z\s]+$/.test(name) && dpto != "0") {
        const varSend = `code=${code}&dpto=${dpto}&name=${name}`;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "ControllDB", true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (!JSON.parse(xhr.responseText)) {
                    alert("!!Código de Municipio ya Registrado!!");
                } else {
                    const towns = JSON.parse(xhr.responseText);
                    for( town of towns ) {
                        var row = document.createElement("tr");
                        var col = document.createElement("td");
                        col.appendChild(document.createTextNode(town.code));
                        row.appendChild(col);

                        col = document.createElement("td");
                        col.appendChild(document.createTextNode(town.name));
                        row.appendChild(col);

                        tblBody.appendChild(row);
                        
                        //Limpiar los campos
                        document.getElementById("code").value = "";
                        document.getElementById("dptos").value = 0;
                        document.getElementById("name").value = "";
                    };
                }
            }
        };
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(varSend);
    } else {
        alert("Revisar Campos")
    }
}

/**
 * Función que permite buscar un municipio, visualiza los datos si existe.
 * @returns {undefined}
 */

function findTownDB() {
    event.preventDefault();
    document.getElementById("tblBody").innerHTML = "";
    const code = document.getElementById("code").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", `ControllDB?option=3&code=${code}`, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            if (!JSON.parse(xhr.responseText)) {
                alert("!!Municipio NO Registrado!!");
            }else{
                const town = JSON.parse(xhr.responseText);
                document.getElementById("dptos").value = town.department;
                document.getElementById("name").value = town.name;
            }
        }
    };
    xhr.send(null);
}

function eraseTownDB(){
    const code = document.getElementById("code").value;
    if( /^[0-9]{1,6}$/.test(code) ){
        var xhr = new XMLHttpRequest();
        const code = document.getElementById("code").value;
        xhr.open("GET",`ControllDB?option=4&code=${code}`,true);
        xhr.onreadystatechange = ()=>{
            if( xhr.readyState == 4 && xhr.status == 200 ){
                if( JSON.parse( xhr.responseText ) ){
                    alert("Se Eliminó el Municipio");
                }else{
                    alert("!!Algo Salió Mal, no fue posible Eliminar!!");
                }
            }
        };
        xhr.send(null);
    }else{
        alert("!!Entrada Inválida!!");
    }
}