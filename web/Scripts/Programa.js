function Activartxt()
{
    var list = document.getElementById("txtPrograma").value;
    if(list === "OTRO")
    {
        document.getElementById("txtPrograma").hidden = "hidden";
        document.getElementById("labelOriginal").hidden = "hidden";
        document.getElementById("txtPrograma2").hidden = false;
        document.getElementById("txtDescripcion").hidden = false;
        document.getElementById("labelCual").hidden = false;
        document.getElementById("labelCual2").hidden = false;
    }  
}

function Deseleccionar(ID)
{
    var radio = document.getElementById(ID);
    radio.checked = false;
}

function Validar()
{
    if (document.getElementById("RadioEliminar").checked===true) {
        alert("Si oprimes el botón \"Confirmar Edición\"  se eliminará y no se podrá recuperar");
    }
}