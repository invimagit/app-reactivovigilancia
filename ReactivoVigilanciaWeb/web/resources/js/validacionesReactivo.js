/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var PF;
var PrimeFaces;
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
PrimeFaces.locales['es'] = {
                closeText: 'Cerrar',
                prevText: 'Anterior',
                nextText: 'Siguiente',
                monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
                dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
                dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
                dayNamesMin: ['D','L','M','M','J','V','S'],
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
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function validarFechas()
{
                console.log ("Entre al script");
                console.log ("************************************************************");
                console.log ("************************************************************");
                if (PF('fechaInWV').getDate() === null || PF('fechaFinWV').getDate() === null)
                {
                    alert ("Debe ingresar el rango de fechas inicial y final del reporte solicitado");
                    return (false);
                }
                else
                {
                    return (true);
                }
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function concentrarFocoFechas()
{
    alert ("Se generará un nuevo reporte");
    jQuery("[id$='fechaInicial']").val("1");
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function abrirVentanaCargueMasivo (paginaMaestroDetalle)
{
        //**************************************************************
        //**************************************************************
        var d = new Date();
        var n = d.getTime();
        
        var params = [
            'resizable=yes',
            'scrollbars=yes',
            'height='+screen.height*0.75,
            'width='+screen.width*0.75,
            'fullscreen=no' // only works in IE, but here for completeness
        ].join(',');

        var paginaApertura = "ventanaEmergente" + n;
        var popup = window.open(paginaMaestroDetalle,paginaApertura,params);
        popup.focus();
        //**************************************************************
        //**************************************************************
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function mostrarAdvertencia(mensaje)
{
    var confirmacion = false;
    confirmacion = confirm(mensaje);
    return (confirmacion);
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function abrirVentanaModificacionSD(idProyecto,idPadre,ruta)
{
        console.log ("paginaMaestroDetalle = " + idPadre);
        var d = new Date();
        var n = d.getTime();
        
        var params = [
            'resizable=yes',
            'scrollbars=yes',
            'height='+screen.height*0.75,
            'width='+screen.width*0.75,
            'fullscreen=no' // only works in IE, but here for completeness
        ].join(',');

        var paginaApertura = "ficha" + n + "-" + idProyecto;
        var popup = window.open(ruta,paginaApertura,params);
        //console.log ("Termine de Recargando pagina");
        popup.focus();
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
function abrirAyuda (paginaReporte)
{
        var d = new Date();
        var n = d.getTime();
        
        var params = [
            'location=yes',
            'directories=yes',
            'status=yes',
            'scrollbars=yes',
            'resizable=yes',
            'height='+screen.height*0.75,
            'width='+screen.width*0.75,
            'fullscreen=no' // only works in IE, but here for completeness
        ].join(',');

        var paginaApertura = "ventanaEmergente" + n;
        //var popup = window.open(paginaReporte,"_blank", "resizable=yes, scrollbars=yes, menubar=yes,toolbar=yes,titlebar=yes");
        //var popup = window.open(paginaReporte,paginaApertura,"resizable=yes, scrollbars=yes, menubar=yes,toolbar=yes,titlebar=yes");
        var popup = window.open(paginaReporte,paginaApertura,params);
        popup.moveTo(0,0);
        popup.focus();
}
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/
/*******************************************************************/


