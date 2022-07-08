document.onkeydown = function(e)
{
    if (e)
        document.onkeypress = function() {
            return true;
        }

    var evt = e ? e : event;
    if (evt.keyCode == 116)
    {
        if (e)
            document.onkeypress = function() {
                return false;
            }
        else
        {
            evt.keyCode = 0;
            evt.returnValue = false;
        }
    }
}



function validarSolorNumeros(event) {
    key = (window.event) ? event.keyCode : event.which;
    return (key <= 13 || (key >= 48 && key <= 57 || key == 46));
}

function validarNumeroIdentificacion(event) {
    key = (window.event) ? event.keyCode : event.which;
    return (key <= 13 || (key >= 48 && key <= 57 || key == 46) || key == 45);
}

function preValidarDecimal(o, event) {
    if (oldValue == '') {
        oldValue = o.value;
        return true;
    } else {
        return false;
    }
}

function validarNumeroDecimal(o, event) {
    key = (window.event) ? event.keyCode : event.which;
    punto = (key == 46 && (o.value != '' && o.value.indexOf('.') == -1));
    return (key <= 13 || (key >= 48 && key <= 57) || punto);
}

function postValidarDecimal(o, numDecimal) {
    if (o.value.indexOf('.') != -1) {
        var decimales = o.value.substring(o.value.indexOf('.') + 1, o.value.length);

        if (decimales.length > numDecimal) {
            o.value = oldValue;
        }
    }
    oldValue = '';
}
var longMinima = true;
function validarLongitudMinimaDefault(object, longitudMinima) {
    if (object.value == '') {
        return true;
    }
    if (object.value.length <= longitudMinima) {
        alert('El campo debe tener mas de ' + longitudMinima + ' caracteres');
        object.value = '';
        object.focus();
        longMinima = false;
        return false;
    }
    longMinima = true;
    return true;
}

function validarLongitudMinima(object, longitudMinima) {
    if (object.value == '') {
        return true;
    }
    if (object.value.length <= longitudMinima) {
        object.value = '';
        object.focus();
        longMinima = false;
        return false;
    }
    longMinima = true;
    return true;
}

function validarPatronEmail(e) {
    tecla = (window.event) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function validarDefault(event) {
    return true;
}

function validarNumerosTelefonicos(event) {
    key = (window.event) ? event.keyCode : event.which;
    return (key <= 13 || (key >= 48 && key <= 57 || key == 46 || key == 40 || key == 41 || key == 45 || key == 43 || key == 32));
}

function validarNombreUsuario(event) {
    key = (window.event) ? event.keyCode : event.which;
    return (key <= 13 || (key >= 48 && key <= 57 || key >= 97 && key <= 122 || key>=65 && key <=90));
}

function teclaEnter(event) {
    key = (window.event) ? event.keyCode : event.which;
    if (key == 13) {
        return true;
    }
    return false;
}

function validarPatron(e) {
    tecla = (window.event) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /\d|(\W?[^\\\\{\+\*\?\_Ã‡Â¿Â¡'=$Â·"!ÂªÂº|~Â½Â¬<>])/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function clickBoton(nombreBoton) {
    var inputs = document.getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].id.search(nombreBoton) != -1) {
            inputs[i].click();
            break;
        }
    }
}

function procMenusItems() {
    document.getElementById('divCargandoFormulario').style.display = '';
    document.getElementById('divContenido').style.display = 'none';
    document.body.style.cursor = 'wait';
}

function getPosLeft(obj) {
    var curLeft = 0;
    if (obj.offsetParent) {
        do {
            curLeft += obj.offsetLeft;
        } while (obj = obj.offsetParent);
    }
    return curLeft;
}

function getPosTop(obj) {
    var curTop = 0;
    if (obj.offsetParent) {
        do {
            curTop += obj.offsetTop;
        } while (obj = obj.offsetParent);
    }
    return curTop;
}

function getDivHelpObject() {
    var obj = document.getElementById("divHelper");
    if (obj == null) {
        obj = document.createElement("span");
        obj.style.position = "absolute";
        obj.style.backgroundColor = "#dde9c1";
        obj.style.fontSize = 12;
        obj.style.fontFamily = "Arial, Helvetica, sans-serif";
        obj.style.border = "#165500 solid 1px";
        obj.style.color = "black";
        obj.setAttribute("id", "divHelper");
        obj.style.display = "none";
        document.body.appendChild(obj);
    }
    return obj;
}

function getDivAsteriscoObligatorio(id) {
    var obj = document.getElementById(id);
    if (obj == null) {
        obj = document.createElement("div");
        obj.setAttribute("id", "divHelper");
        obj.setAttribute("class", "divAyuda");
        obj.style.display = "none";
        document.body.appendChild(obj);
    }
    return obj;
}


function validarEmail(str) {
    if (str == '') {
        return true;
    }
    var at = "@";
    var dot = ".";
    var lat = str.indexOf(at);
    var lstr = str.length;
    var ldot = str.indexOf(dot);
    if (str.indexOf(at) == -1) {
        return false;
    }
    if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
        //alert("1");
        return false;
    }
    if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
        //alert("2");
        return false;
    }

    if (str.indexOf(at, (lat + 1)) != -1) {
        //alert("3");
        return false;
    }

    if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
        //alert("5");
        return false;
    }

    if (str.indexOf(dot, (lat + 2)) == -1) {
        //alert("6");
        return false;
    }

    if (str.indexOf(" ") != -1) {
        //alert("7");
        return false;
    }

    re = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$/
    if (!re.exec(str)) {
        //alert("8");
        return false;
    }
    return true;
}

function procesarPago(entidad, transaccionId) {
    alert(transaccionId);
}

/**
 * Función que inserta el applet de documentos anexoas en la aplicación
 */
var flagApplet = 0;
function insertarAppletDocumentos(requisitos, cdgradicacion) {
    if (flagApplet == 0) {
        //        flagApplet++;
        var _app = navigator.appName;
        var applet = '';
        var protocol = location.protocol;
        var host = location.host;
        var pathname = location.pathname;

        var codebase = protocol + '//' + host + '/' + pathname.substring(0, pathname.indexOf('/', 1));

        if (_app == 'Netscape') {
            applet = '<applet width="650" height="500" codebase="' + codebase + '/applet"' +
                    'code="co.gov.invima.LoadDocumeto" archive="LoadDocumentos.jar,bcmail-jdk15-145.jar,bcprov-jdk15-145.jar,commons-net-2.0.jar,firmarlibs.jar,xmlsec-1.4.2.jar" MAYSCRIPT >' +
                    '<param name="requisitos" value="' + requisitos + '"/>' +
                    '<param name="cdgradicacion" value="' + cdgradicacion + '"/>' +
                    '<param name="ftpUser" value="' + ftpUser + '"/>' +
                    '<param name="ftpPassword" value="' + ftpPassword + '"/>' +
                    '<param name="ftpHost" value="' + ftpHost + '"/>' +
                    '<param name="_whidth" value="650"/>' +
                    '<param name="_height" value="500"/>' +
                    '<param name="tamanoMaximoFile" value="10"/>' +
                    '<param name="tamanoMaximoZip" value="8"/>' +
                    '<param name="extensiones" value="' + extensiones + '"/>' +
                    '</applet>';

            document.getElementById('applet').innerHTML = applet;

        } else if (_app == 'Microsoft Internet Explorer') {
            applet = '<object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" width="650" height="500">' +
                    '<PARAM name="codebase" value="' + codebase + '/applet"/>' +
                    '<PARAM name="code" value="co.gov.invima.LoadDocumeto.class"/>' +
                    '<PARAM name="archive" value="LoadDocumentos.jar,bcmail-jdk15-145.jar,bcprov-jdk15-145.jar,commons-net-2.0.jar,firmarlibs.jar,xmlsec-1.4.2.jar"/>' +
                    '<PARAM name="requisitos" value="' + requisitos + '"/>' +
                    '<PARAM name="cdgradicacion" value="' + cdgradicacion + '"/>' +
                    '<PARAM name="ftpUser" value="' + ftpUser + '"/>' +
                    '<PARAM name="ftpPassword" value="' + ftpPassword + '"/>' +
                    '<PARAM name="ftpHost" value="' + ftpHost + '"/>' +
                    '<PARAM name="_whidth" value="650"/>' +
                    '<PARAM name="_height" value="500"/>' +
                    '<PARAM name="scriptable" value="true">' +
                    '<PARAM name="mayscript" value="true">' +
                    '<PARAM name="tamanoMaximoFile" value="10">' +
                    '<PARAM name="tamanoMaximoZip" value="8">' +
                    '<PARAM name="extensiones" value="' + extensiones + '">' +
                    '</object>';

            document.getElementById('applet').innerHTML = applet;
        } else {
            document.getElementById('applet').innerHTML = 'ERROR: Su navegador no soporte el applet necesario para esta funcionalidad';
        }
    }
}

/**
 * Función llamada por el applet de documentos al finalizar su ejecución.
 */
function postAdjuntarAction(observaciones) {
    document.getElementById('frmSolicitarTramite:txtObservacionTramite').value = observaciones;
    document.getElementById('frmSolicitarTramite:documentosNext').onclick();
    flagApplet++;
}

function isDate(field) {
    if (field.value.length != 10) {
        return warnDate(field);
    } else if (field.value.substring(2, 3) != "/" || field.value.substring(5, 6) != "/") {
        return warnDate(field);
    } else if (isNaN(field.value.substring(0, 2)) || isNaN(field.value.substring(3, 5))) {
        return warnDate(field);
    }

    // Check to see if it is a valid date.
    var day = field.value.substring(0, 2);
    var month = field.value.substring(3, 5);
    var year = field.value.substring(6, 10);
    if (isNaN(year) || parseInt(year) < 1800) {
        return warnDate(field);
    }

    //MRO 05-22-03
    var monthint;
    if (month.substring(0, 1) != '0') {
        monthint = parseInt(month);
    }
    else {
        monthint = parseInt(month.substring(1, 2));
    }
    var dayint;
    if (day.substring(0, 1) != '0') {
        dayint = parseInt(day);
    }
    else {
        dayint = parseInt(day.substring(1, 2));
    }
    var yearint = parseInt(year);

    // Construir un objeto Date con los datos. Si luego hay alguna diferencia entre el objeto y los valores, es que
    // los datos no formaban una fecha valida. Toma en cuenta que el mes en JavaScript empieza con 0 (0 es enero).
    var date = new Date(yearint, monthint - 1, dayint);

    // Validate years less than 2000
    if (date.getYear().length == 2) {
        //MRO 05-22-03
        //if ((date.getYear() + 1900) != year) {
        if ((date.getYear() + 1900) != yearint) {
            return warnDate(field);
        }
    }

    // Validate years greater than or equal to 2000
    if (date.getYear().length == 4) {
        //MRO 05-22-03
        //if (date.getYear() != year) {
        if (date.getYear() != yearint) {
            return warnDate(field);
        }
    }

    //MRO 05-22-03
    //if (((date.getMonth() + 1) != month) ||
    //    (date.getDate() != day)) {
    if (((date.getMonth() + 1) != monthint) ||
            (date.getDate() != dayint)) {
        return warnDate(field);
    }

    return true;
}


function warnDate(theField)				// Funci�n que saca un mensaje, cuando la fecha est� incorrecta
{
    theField.focus();
    alert("Formato de fecha inválida");
    return false
}

function activarFullScreen() {
    //window.name = 'INVIMA';
    var options = 'width=800,height=600,menubar=no,status=no';
    var popup = window.open('pages/indexUsuario.iface', '', options);

    popup.moveTo(0, 0);
    if (document.all) {
        popup.top.window.resizeTo(screen.availWidth, screen.availHeight);
    }
    else if (document.layers || document.getElementById) {
        if (popup.top.window.outerHeight < screen.availHeight || popup.top.window.outerWidth < screen.availWidth) {
            popup.top.window.outerHeight = screen.availHeight;
            popup.top.window.outerWidth = screen.availWidth;
        }
    }
}

function revisarSIC() {
    if (ventanaSIC.closed == true) {
        document.getElementById('frmSolicitarTramite:ticketid').value = '';
        document.getElementById('frmSolicitarTramite:botonPago').onclick();
    } else {
        setTimeout('revisarSIC();', 3000);
    }
}
var ptop;
var pleft
function imprimir_popup() {
    document.getElementById('frmSolicitarTramite:mensajeInicial').style.visibility = 'hidden';
    document.getElementById('mensajeInicial').style.visibility = 'hidden';
    document.getElementById('frmSolicitarTramite:btnAceptar').style.visibility = 'hidden';
    document.getElementById('frmSolicitarTramite:btnImprimir').style.visibility = 'hidden';

    ptop = document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.top;
    pleft = document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.left;
    document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.top = '0.5px';
    document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.left = '0.5px';
    setTimeout("imprimirPopup2();", 5000);
    window.print()
}

function imprimirPopup2() {
    document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.top = ptop;
    document.getElementById('frmSolicitarTramite:panelPopupResumenProceso').style.left = ptop;
    document.getElementById('frmSolicitarTramite:mensajeInicial').style.visibility = 'visible';
    document.getElementById('mensajeInicial').style.visibility = 'visible';
    document.getElementById('frmSolicitarTramite:btnAceptar').style.visibility = 'visible';
    document.getElementById('frmSolicitarTramite:btnImprimir').style.visibility = 'visible';
}


//----------------------------------------------------------O
/*
 * Objeto que contiene las funciones JavaScript usadas por la pestaña principal
 * de la PQRVerbal 
 */
var JsPQRVerbal = function() {
};
//Función limpia los " " en los campos de texto obligatorios
JsPQRVerbal.limpiarEspacios = function() {
};
//----------------------------------------------------------O

//----------------------------------------------------------O
/*
 Objeto JsCrearRequerimientoSJ que contiene las funciones usadas en la pestaña
 CrearRequerimientoSoporteTecnico
 */
var JsCrearRequerimientoST = function() {
};
JsCrearRequerimientoST.requerimientoCreado = function() {
    if (document.getElementById('detsolContenedorPestanas:tabDetSol:detsolSoporteTecnicoForm:crearRequerimientoOcultoCreado').value == 'true') {
        return true;
    }
};
//----------------------------------------------------------O

//----------------------------------------------------------O
/*
 Objeto JsEditarUsuarioPopup que contiene las funciones usadas en la pantalla
 de EditarUsuario
 */
var JsEditarUsuarioPopup = function() {
};
JsEditarUsuarioPopup.usuarioEditado = function() {
    if (document.getElementById('formularioEditarUsuario:formEditarUsuarioPopup:ocultoUsuarioModificado').value == 'true') {
        return true;
    }
};
//----------------------------------------------------------O

//----------------------------------------------------------O
/*
 Definición de localización en español para los calendarios
 */
//----------------------------------------------------------O
PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
    dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Sólo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Fecha actual',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Día',
    allDayText: 'Todo el día'
};

//----------------------------------------------------------O
/*
 Objeto JsAbrirAplicacionEscritorio que contiene las funciones para abrir aplicación
 de escritorio
 */
var JsAbrirAplicacionEscritorio = function() {
};
JsAbrirAplicacionEscritorio.abrirApp = function(xhr, status, args) {
    //alert('ejecutar comercial');
    if ((args.idcuenta != null && args.idcuenta != 'undefined' && args.idcuenta != '') &&
            (args.idusuario != null && args.idusuario != 'undefined' && args.idusuario != '')) {
        WshShell = new ActiveXObject("WScript.Shell");
        var st = 'file://' + args.unidad + args.ruta + '/service.exe ';
        escape(st);
        //alert(st);
        st += args.idcuenta + ' ' + args.idusuario; //+' '+args.unidad+' '+args.ruta;
        //alert(st);
        WshShell.Run(st, 1, false);
        alert('Terminado');
    } else {
        alert('Error al ejecutar Comercial');
    }
};
//----------------------------------------------------------O


//----------------------------------------------------------O
/*
 Objeto JsDescargaArchivoReporte que contiene las funciones para abrir aplicación
 de escritorio
 */
var JsDescargaArchivoReporte = function() {
};
JsDescargaArchivoReporte.descargarArchivoReporte = function(xhr, status, args) {
    /*if (null != args.ticketGenerado && 'undefined' != args.ticketGenerado) {
     $('.botonDescargaReporte').click();
     }*/

    if (null != args.reporteHTML && 'undefined' != args.reporteHTML) {

        alert('Imprimir ticket');

        var reporteHTML = args.reporteHTML;

        var WindowObject = window.open(document.URL, '', 'directories=no,scrollbars=no,toolbar=no,titlebar=no,location=no,status=no,menubar=no,resizable=no,witdh=100,height=200');
        var ticket = reporteHTML.replace(/\+/gi, " ");
        ticket = ticket.replace(/%0A/gi, "<br />");
        ticket = ticket.replace(/%3A/gi, ":");
        WindowObject.document.write('<p style="font-size:9px">' + ticket + '</p>');
        WindowObject.document.close();
        WindowObject.focus();
        WindowObject.print();
        WindowObject.close();


        reporteHTML = unescape(reporteHTML);
        //reporteHTML = reporteHTML.replace(/\+/gi, " ");

        /*
         $("[id$='elCoco']").val(reporteHTML);
         $('.botonImprimirReporte').click();
         */
    }
};
//----------------------------------------------------------O


//----------------------------------------------------------O
/*
 Objeto JsDescargaArchivoReporte que contiene las funciones para abrir aplicación
 de escritorio
 */
var JsImprimirArchivoReporte = function() {
};
JsImprimirArchivoReporte.imprimirArchivoReporte = function() {


    /*
     var DocumentContainer = $('.cocossette');
     var WindowObject = window.open("", "PrintWindow",
     "width=750,height=650,top=50,left=50,toolbars=no,scrollbars=yes,status=no,resizable=yes");
     WindowObject.document.write();
     WindowObject.document.write(DocumentContainer.innerHTML);
     WindowObject.document.close();
     WindowObject.focus();
     WindowObject.print();
     WindowObject.close();
     */

    /*
     factory.printing.header = "";
     factory.printing.footer = "";
     factory.printing.portrait = false;
     factory.printing.leftMargin = 1.0;
     factory.printing.topMargin = 1.0;
     factory.printing.rightMargin = 1.0;
     factory.printing.bottomMargin = 1.0;
     factory.printing.Print(true);
     */




};
//----------------------------------------------------------O


//----------------------------------------------------------O
/*
 Objeto JsDescargaArchivoReporte que contiene las funciones para abrir aplicación
 de escritorio
 */
var JsDescargaArchivoAnexoSoporte = function() {
};
JsDescargaArchivoAnexoSoporte.descargarArchivoAnexo = function(xhr, status, args) {
    if (null != args.archivoAnexoGenerado && 'undefined' != args.archivoAnexoGenerado) {
        $('.botonDescargaAnexoSeleccionado').click();
    }
};
//----------------------------------------------------------O


//----------------------------------------------------------O
/*
 Objeto JsFuncionesCampos que contiene las funciones trabajar sobre eventos
 en campos de los formularios
 */
var JsFuncionesCampos = function() {
};
JsFuncionesCampos.completarCerosNumeroCuenta = function(campo) {
    if (campo != null
            && campo.value != null
            && campo.value != "") {
        var complementoCero = "";
        for (var i = 0; i < (6 - campo.value.length); i++) {
            complementoCero += "0";
        }
        campo.value = complementoCero + campo.value;
    }
};
//----------------------------------------------------------O

var padding_zero = function(x, n) {
    var zeros = repeat("0", n);
    return String(zeros + x).slice(-1 * n)
}

/**
 * PErmite obtner el nombre dle equipo del cliente
 */
function GetComputerName()
{
    try
    {
        var network = new ActiveXObject('WScript.Network');
        // Show a pop up if it works
        return (network.computerName);
    }
    catch (e) {
    }
    //return '';
}

/**
 * Inactivar SeleconeMenu luego de seleccionar
 */
function disableOneMenu() {
    //alert("In Disable One Menu Function...");
    var clickedGroup = document.getElementById('selectTipoDocumento').value;
    alert("Selected Value " + clickedGroup);
    if (clickedGroup == "RUT" || clickedGroup == "NIT") {
        document.getElementById('selectTipoDocumento').disabled = true;
        alert("Location One Menu Disabled...");
    }
}

/**
 * Español de Calendar Primefaces
 */

PrimeFaces.locales['es'] = {
        closeText: 'Cerrar',
        prevText: 'Anterior',
        nextText: 'Siguiente',
        monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
        dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
        dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
        dayNamesMin: ['D','L','M','X','J','V','S'],
        weekHeader: 'Semana',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        timeOnlyTitle: 'Sólo hora',
        timeText: 'Tiempo',
        hourText: 'Hora',
        minuteText: 'Minuto',
        secondText: 'Segundo',
        currentText: 'Fecha actual',
        ampm: false,
        month: 'Mes',
        week: 'Semana',
        day: 'Día',
        allDayText : 'Todo el día'
};