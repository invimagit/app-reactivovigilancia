package seleniumTests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pruebaFRIARH {

    private WebDriver driver;
    WebDriverWait wait;
    util u;
    String id_usuario;

  @Before
  public void setUp() {
    driver = util.getDriver();
    wait = new WebDriverWait(driver, 18);
    u = new util(driver);    
    
    Date date = new Date();  
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(date);
    id_usuario = ""+(date.getYear()+1900)+
            (date.getMonth()+1)+date.getDate()+calendar.get(Calendar.HOUR_OF_DAY)+
            calendar.get(Calendar.MINUTE);
  }
  
  @After
  public void tearDown() {
    try{TimeUnit.MILLISECONDS.sleep(5000L);} catch(Exception e){}
    //driver.quit();
  }
  
  @Test
  public void prueba_principal() {
        String id_friarh = crearMonitoreo("HURTO",true);
        crearFRIARH("HURTO",true,id_friarh);
        crearFRIARH("ALERTA",false,"");
  }
  
  
  
  public String crearMonitoreo(String tipo_reporte, boolean completo){
      u.login(util.params.get("usu_invima_usuario"),util.params.get("usu_invima_contraseña"));
      driver.get(util.params.get("url_base")+"faces/pages/reporteSeguridad.xhtml");
      try{TimeUnit.MILLISECONDS.sleep(2000L);} catch(Exception e){}
      
      u.getElemento(By.id("frmSeguridad:btn_moniotoreo")).click();
      try{TimeUnit.MILLISECONDS.sleep(1000L);} catch(Exception e){}
      u.getElemento(By.id("frmSeguridad:btn_nuevo_monitoreo")).click();
      
      u.getElemento(By.id("frmSeguridad:monitoreo_fuente_label")).click();
      if(completo){
        u.getElemento(By.id("frmSeguridad:monitoreo_fuente_4")).click();  
        u.getElemento(By.id("frmSeguridad:inputagenciaSanitaria")).click();  
        u.getElemento(By.id("frmSeguridad:inputagenciaSanitaria")).sendKeys("Agencia Sanitaria");  
      }
      else{
        u.getElemento(By.id("frmSeguridad:monitoreo_fuente_1")).click();
      }
      
      u.getElemento(By.id("frmSeguridad:inputdetalles")).click();
      u.getElemento(By.id("frmSeguridad:inputdetalles")).sendKeys("Detalles");
      u.getElemento(By.id("frmSeguridad:monitoreo_aplica_label")).click();
      u.getElemento(By.id("frmSeguridad:monitoreo_aplica_1")).click();
      
      u.getElemento(By.id("frmSeguridad:monitoreo_tipo_label")).click();
      if(tipo_reporte.equals("ALERTA")){  
        u.getElemento(By.id("frmSeguridad:monitoreo_tipo_1")).click();
      }else{
        u.getElemento(By.id("frmSeguridad:monitoreo_tipo_2")).click();  
      }
      
      u.getElemento(By.id("frmSeguridad:monitereo_funcionario_label")).click();
      u.getElemento(By.id("frmSeguridad:monitereo_funcionario_1")).click();
      u.getElemento(By.id("frmSeguridad:inputpaginaWeb")).click();
      u.getElemento(By.id("frmSeguridad:inputpaginaWeb")).sendKeys("http://localhost:8080/ReactivoVigilanciaWeb");
      
      u.getElemento(By.id("frmSeguridad:btnGuardarMonitoreo")).click();   
      
      wait.until(ExpectedConditions.elementToBeClickable(By.id("frmSeguridad:lblMensaje")));
      String Friarh = u.getElemento(By.id("frmSeguridad:lblMensaje")).getText();
      System.out.println(Friarh);
      u.getElemento(By.id("frmSeguridad:btnMensaje")).click();
      u.getElemento(By.id("barra_horizontal:salir")).click();
      
      return util.obtener_consecutivo_reporte(Friarh, "FRIARH:").trim();
  }
          
          
  public void crearFRIARH(String tipo_reporte, boolean completo, String id_monitoreo){
        u.login(util.params.get("usu_invima_usuario"),util.params.get("usu_invima_contraseña"));
        driver.get(util.params.get("url_base")+"faces/pages/reporteSeguridad.xhtml");
        try{TimeUnit.MILLISECONDS.sleep(2000L);} catch(Exception e){}
        
        u.getElemento(By.id("frmSeguridad:btn_friarh")).click();
        try{TimeUnit.MILLISECONDS.sleep(1000L);} catch(Exception e){}
        
        if(id_monitoreo.equals("")){
            u.getElemento(By.id("frmSeguridad:btn_nuevo_reporte")).click();
        }else{
            u.getElemento(By.id("frmSeguridad:friarh_id")).click();
            u.getElemento(By.id("frmSeguridad:friarh_id")).sendKeys(id_monitoreo);
            u.getElemento(By.id("frmSeguridad:friarh_id")).click();
            u.getElemento(By.id("frmSeguridad:btnBuscar")).click();
            try{TimeUnit.MILLISECONDS.sleep(2000L);} catch(Exception e){}
        }

        if(id_monitoreo.equals("")){
            u.getElemento(By.id("frmSeguridad:listaTipoNotificacion_label")).click();
            if(tipo_reporte.equals("HURTO")){
                u.getElemento(By.id("frmSeguridad:listaTipoNotificacion_2")).click();
            }
            else{
                u.getElemento(By.id("frmSeguridad:listaTipoNotificacion_1")).click();
            }
        }
        
        u.getElemento(By.id("frmSeguridad:listaTiponotificante_label")).click();
        if(completo){
            u.getElemento(By.id("frmSeguridad:listaTiponotificante_4")).click();
            u.getElemento(By.id("frmSeguridad:inputOtroNotificante")).click();
            u.getElemento(By.id("frmSeguridad:inputOtroNotificante")).sendKeys("Otro notificante");
        }else{
            u.getElemento(By.id("frmSeguridad:listaTiponotificante_1")).click();
        }
        
        if(id_monitoreo.equals("")){
            u.getElemento(By.id("frmSeguridad:listaTipoFuente_label")).click();
            if(completo){
                u.getElemento(By.id("frmSeguridad:listaTipoFuente_4")).click();
                u.getElemento(By.id("frmSeguridad:inputNombreAgencia")).click();
                u.getElemento(By.id("frmSeguridad:inputNombreAgencia")).sendKeys("Nombre Agencia Sanitaria");
            }else{
                u.getElemento(By.id("frmSeguridad:listaTipoFuente_1")).click();
            }
        }
        
        u.getElemento(By.id("frmSeguridad:listaRiesgo_label")).click();
        u.getElemento(By.id("frmSeguridad:listaRiesgo_1")).click();
        u.getElemento(By.id("frmSeguridad:inputReferencia")).click();
        u.getElemento(By.id("frmSeguridad:inputReferencia")).sendKeys("referencia");
        u.getElemento(By.id("frmSeguridad:inputRS")).click();
        u.getElemento(By.id("frmSeguridad:inputRS")).sendKeys("RS-001");
        u.getElemento(By.id("frmSeguridad:inputMarca")).click();
        u.getElemento(By.id("frmSeguridad:inputMarca")).sendKeys("Marca");
        u.getElemento(By.id("frmSeguridad:inputNombreReactivo")).click();
        u.getElemento(By.id("frmSeguridad:inputNombreReactivo")).sendKeys("Nombre del reactivo");
        u.getElemento(By.id("frmSeguridad:inputLote")).click();
        u.getElemento(By.id("frmSeguridad:inputLote")).sendKeys("Lote1");
        
        if (tipo_reporte.equals("HURTO")) {
          u.getElemento(By.id("frmSeguridad:listaDptoR_label")).click();
          u.getElemento(By.id("frmSeguridad:listaDptoR_5")).click();
          u.getElemento(By.id("frmSeguridad:listaMpioR_label")).click();
          u.getElemento(By.id("frmSeguridad:listaMpioR_1")).click();
          u.getElemento(By.id("frmSeguridad:inputFechaHurto_input")).click();
          u.getElemento(By.id("frmSeguridad:inputFechaHurto_input")).sendKeys("2020-06-17");
          u.getElemento(By.id("frmSeguridad:listaSiNoDenuncia_label")).click();
          u.getElemento(By.id("frmSeguridad:listaSiNoDenuncia_1")).click();
          u.getElemento(By.id("frmSeguridad:listaSiNoHurtoAutoriza_label")).click();
          u.getElemento(By.id("frmSeguridad:listaSiNoHurtoAutoriza_1")).click();
          u.getElemento(By.id("frmSeguridad:inputRelatohurto")).click();
          u.getElemento(By.id("frmSeguridad:inputRelatohurto")).sendKeys("Realice una breve descripción de los hechos del hurto del(los) Reactivos de Diagnóstico In Vitro");

          u.getElemento(By.id("frmSeguridad:btnAgregarF2")).click();

          u.getElemento(By.xpath("//div[@id='frmSeguridad:tablaFriarh02:0:row_editor_3']/span")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputNomReactivoF2")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputNomReactivoF2")).sendKeys("Nombre");
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputRegSanF2")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputRegSanF2")).sendKeys("rg-001");
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputReferenciaF2")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputReferenciaF2")).sendKeys("referencia1");
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputLoteF2")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputLoteF2")).sendKeys("Lote1");
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputMarcaF2")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputMarcaF2")).sendKeys("Marca1");
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputCantidadF1")).click();
          u.getElemento(By.id("frmSeguridad:tablaFriarh02:0:inputCantidadF1")).sendKeys("1");
          u.getElemento(By.xpath("//div[@id='frmSeguridad:tablaFriarh02:0:row_editor_3']/span[2]")).click();
        }else{
            u.getElemento(By.id("frmSeguridad:listaEstados_label")).click();
            if(completo){
                u.getElemento(By.id("frmSeguridad:listaEstados_8")).click();
                u.getElemento(By.id("frmSeguridad:inputCualOtro")).click();
                u.getElemento(By.id("frmSeguridad:inputCualOtro")).sendKeys("Otro estado");
            }else{
                u.getElemento(By.id("frmSeguridad:listaEstados_1")).click();
            }
            u.getElemento(By.id("frmSeguridad:inputDescribeProblema")).click();
            u.getElemento(By.id("frmSeguridad:inputDescribeProblema")).sendKeys("Describa el problema presentado con Reactivo de Diagnóstico In Vitro");
            
            u.getElemento(By.id("frmSeguridad:inputPosibleCausa_label")).click();
            u.getElemento(By.id("frmSeguridad:inputPosibleCausa_1")).click();
            
            u.getElemento(By.id("frmSeguridad:inputMedidasCorrectivas")).click();
            u.getElemento(By.id("frmSeguridad:inputMedidasCorrectivas")).sendKeys("Describa las medidas correctivas y/o preventivas tomadas sobre el Reactivo de Diagnóstico In Vitro");
            
            u.getElemento(By.id("frmSeguridad:listaSiNoEventosAdversos_label")).click();
            if(completo){
                u.getElemento(By.id("frmSeguridad:listaSiNoEventosAdversos_1")).click();
                u.getElemento(By.id("frmSeguridad:inputCuantosEventos")).click();
                u.getElemento(By.id("frmSeguridad:inputCuantosEventos")).sendKeys("1");
                u.getElemento(By.id("frmSeguridad:inputEventoAdverso")).click();
                u.getElemento(By.id("frmSeguridad:inputEventoAdverso")).sendKeys("En caso afirmativo, realice una breve descripción de evento adverso o Incidente presentado");
            }else{
                u.getElemento(By.id("frmSeguridad:listaSiNoEventosAdversos_2")).click();
            }
            
            u.getElemento(By.id("frmSeguridad:btnAgregar")).click();
            //clic lapiz
            
            u.getElemento(By.xpath("//div[@id='frmSeguridad:tablaFriarh01:0:row_editor_f1']/span")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomClienteF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomClienteF1")).sendKeys("Nombre");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputDireccionF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputDireccionF1")).sendKeys("Cll 13 #13-13");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:listaDptoTabla_label")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:listaDptoTabla_4")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:listaMpioTabla_label")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:listaMpioTabla_0")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputTelefonoF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputTelefonoF1")).sendKeys("2222222");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomContactoF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomContactoF1")).sendKeys("Contacto");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomReactivoF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputNomReactivoF1")).sendKeys("Reactivo");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputRegSanF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputRegSanF1")).sendKeys("rs-001");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputRefF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputRefF1")).sendKeys("Referencia1");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputLoteF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputLoteF1")).sendKeys("Lote1");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputMarcaF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputMarcaF1")).sendKeys("Marca1");
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputCantidadF1")).click();
            u.getElemento(By.id("frmSeguridad:tablaFriarh01:0:inputCantidadF1")).sendKeys("1");
            u.getElemento(By.xpath("//div[@id='frmSeguridad:tablaFriarh01:0:row_editor_f1']/span[2]")).click();
        }
        
        
        u.getElemento(By.id("frmSeguridad:seguimiento_observaciones")).click();
        u.getElemento(By.id("frmSeguridad:seguimiento_observaciones")).sendKeys("Observaciones");
        
        u.getElemento(By.id("frmSeguridad:btnAceptar")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("frmSeguridad:lblMensaje")));
        System.out.println(u.getElemento(By.id("frmSeguridad:lblMensaje")).getText());
        u.getElemento(By.id("frmSeguridad:btnMensaje")).click();

        
        u.getElemento(By.id("barra_horizontal:salir")).click();
    }
  
   
}
