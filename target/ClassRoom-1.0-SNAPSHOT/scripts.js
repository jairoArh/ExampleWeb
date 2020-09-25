/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getDepartments(){
    var dptos = document.getElementById("departments");
    var xhr = new XMLHttpRequest();
    xhr.open( "GET","Control?option=0",true);
    xhr.onreadystatechange = ()=>{
        if( xhr.readyState == 4 && xhr.status == 200 ){
            const records = JSON.parse( xhr.responseText);
            records.forEach(dpto=>{
                let option = document.createElement("option");
                option.setAttribute("value",dpto.code);
                option.appendChild( document.createTextNode( dpto.name));
                dptos.appendChild( option );
            });
        }
    };
    xhr.send(null);
}

function getTowns(){
    var towns = document.getElementById("towns");
    towns.length = 0;
    const code = document.getElementById("departments").value;
    var xhr = new XMLHttpRequest();
    xhr.open( "GET",`Control?option=1&code=${code}`,true);
    xhr.onreadystatechange = ()=>{
        if( xhr.readyState == 4 && xhr.status == 200 ){
           const records = JSON.parse( xhr.responseText);
           records.forEach(town=>{
               let option = document.createElement("option");
                option.setAttribute("value",town.code);
                option.appendChild( document.createTextNode( town.name));
                towns.appendChild( option );
           })
        }
    };
    xhr.send(null);
}

function findTown(){
    const code = document.getElementById("towns").value;
    var out = document.getElementById("output");
    out.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.open( "GET",`Control?option=2&code=${code}`,true);
    xhr.onreadystatechange = ()=>{
        if( xhr.readyState == 4 && xhr.status == 200 ){
           const town = JSON.parse( xhr.responseText );
           let title = document.createElement("h1");
           title.appendChild( document.createTextNode(`Datos de ${town.name}`));
           out.appendChild( title );
           out.appendChild( document.createTextNode(`Código-->${town.code}`));
           out.appendChild( document.createElement("br")) //Salto de Línea
           out.appendChild( document.createTextNode(`Nombre Departamento-->${town.department}`));
           out.appendChild( document.createElement("br")) //Salto de Línea
           out.appendChild( document.createTextNode(`Nombre Municipio-->${town.name}`));
        }
    };
    xhr.send(null);
}