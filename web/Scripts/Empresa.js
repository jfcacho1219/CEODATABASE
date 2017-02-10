function ValidarNIT()
{
    var SMLV = ((document.getElementById("txtNIT").value));
    //var letra = SMLV.substr(9,9);
    document.getElementById("txtNIT").value = SMLV;
    if(document.getElementById("txtNIT").value === 'NaN')
    {
        alert("El NIT SOLO CONTIENE ");
        document.getElementById("txtNIT").value = "";
    }

}
//LLENA DE ACUERDO A LOS PARAMETROS EL TAMAÑO DE LA EMPRESA
function AutollenarTamano()
{
    var Validar = false;
    var Empleados = document.getElementById("txtEmpleados").value;
    if(Empleados<11)
    {
        Validar = true;
        document.getElementById("txtTamano").value = "Micro";
    }
    if(Empleados>=11 && Empleados<51)
    {
        Validar = true;
        document.getElementById("txtTamano").value = "Pequeña";
    }
    if(Empleados>=51 && Empleados<201)
    {
        Validar = true;
        document.getElementById("txtTamano").value = "Mediana";
    }
    if(Empleados>=201)
    {
        Validar = true;
        document.getElementById("txtTamano").value = "Grande";
    }
    var Salario = document.getElementById("txtTamano").value;
    if(!Validar) alert("Valor Incorrecto");
}
//COMPLETA EL VALOR EN TERMINOS DE SALARIOS MÍNIMOS
function AutollenarSMLV()
{
    var SMLV = Math.round(((document.getElementById("txtSostenimientoPesos").value)/689454),10);
    var texto = document.getElementById("txtSostenimientoSMLV");
    texto.value=SMLV;
    if(texto.value === "NaN")
    {
        alert(document.getElementById("txtSostenimientoPesos").value +" no es un número")
        texto.value=" ";
    }
}
//Commpleta el campo de dirección de planta por el mismo de la administrativa
 function AutoLlenarDireccion()
{
    document.getElementById("txtDireccionAdministrativa").value = document.getElementById("txtDireccionPlanta").value;   
}
//COMPLETA EL CAMPO DE MUNICIPIO PLANTA Y ADMINISTRATIVO CON EL MISMO VALOR
function AutollenarMunicipio()
{
    document.getElementById("txtMunicipioAdministracion").value = document.getElementById("txtMunicipioPlanta").value;
}
//VALIDA QUE NINGÚN CAMPO ESTÉ VACÍO
function Validar()
{
    var NIT = document.getElementById("txtNIT").value;
    var Nombre = document.getElementById("txtNombre").value;
    var Sociedad = document.getElementById("txtSociedad").value;
    var AnoCreacion = document.getElementById("txtAnoCreacion").value;
    var AnoAfiliacion = document.getElementById("txtAnoAfiliacion").value;
    var SectorProductivo = document.getElementById("txtSectorProductivo").value;
    var NumeroEmpleados = document.getElementById("txtEmpleados").value;
    var Tamano = document.getElementById("txtTamano").value;
    var SostenimientoPesos = document.getElementById("txtSostenimientoPesos").value;
    var SostenimientoSalarios = document.getElementById("txtSostenimientoSMLV").value;
    var Descripcion = document.getElementById("txtDescripcion").value;
    var DireccionPlanta = document.getElementById("txtDireccionPlanta").value;
    var DireccionAdministrativa = document.getElementById("txtDireccionAdministrativa").value;
    var MunicipioPlanta = document.getElementById("txtMunicipioPlanta").value;
    var MunicipioAdministrativa = document.getElementById("txtMunicipioAdministracion").value;

    if( NIT === "" || Nombre ==="" || Sociedad === "" ||
            SectorProductivo ==="" || NumeroEmpleados ==="" || Tamano === "" || 
            SostenimientoPesos === "" || SostenimientoSalarios === "" || Descripcion ===""
            || DireccionPlanta ==="" || DireccionAdministrativa === "" || MunicipioPlanta === ""
            || MunicipioAdministrativa ==="")
    {
        alert("No es posible completar el registro, hay uno o más campos vacíos");
        document.getElementById("btnRegistrar").hidden = "hidden";

    }
    else
    {
        alert("Campos Correctos");
        document.getElementById("btnRegistrar").hidden = false;
        document.getElementById("btnValidar").hidden = "hidden";
    }

}
//PERMITE SOLO SELECCIONAR UN CAMPO
function Deseleccionar(ID)
{
    var radio = document.getElementById(ID);
    radio.checked = false;
}
