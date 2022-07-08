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

public class creacionUsuarios {

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
    driver.quit();
  }
  
  @Test
  /**
   * En la prueba se crea un usuario externo y se aprueba para que pueda entrar a la aplicacion
   */
  public void prueba_principal() {
        creacionUsuario();
        verificacion_de_usuario_creado();
  }
  
  
  public void creacionUsuario() {
      
    driver.get(util.params.get("url_base")+"faces/ReactivoUsuarioInternet.xhtml");
    driver.manage().window().setSize(new Dimension(1055, 703));
    
    u.getElemento(By.id("frmUsuariosInternet:inputCedula")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputCedula")).sendKeys(id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputNombreApelllidos")).sendKeys("Usuario "+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputTelefonoPersonal")).sendKeys("1111111");
    u.getElemento(By.id("frmUsuariosInternet:inputCorreoPersonal")).sendKeys("jandradem@invima.gov.co");
    u.getElemento(By.id("frmUsuariosInternet:inputNit")).sendKeys(id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputNombreRazonSocial")).sendKeys("Empresa Usuario "+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:listaPais_label")).click();
    u.getElemento(By.id("frmUsuariosInternet:listaPais_42")).click();
    u.getElemento(By.id("frmUsuariosInternet:listaDpto_label")).click();
    u.getElemento(By.id("frmUsuariosInternet:listaDpto_5")).click();
    u.getElemento(By.id("frmUsuariosInternet:listaMpio_label")).click();
    u.getElemento(By.id("frmUsuariosInternet:listaMpio_1")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputDireccion")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputDireccion")).sendKeys("Calle 14 55-30 Local 3");
    u.getElemento(By.id("frmUsuariosInternet:inputTelefonoOrg")).sendKeys("1111111");
    u.getElemento(By.id("frmUsuariosInternet:inputCorreoCorp")).sendKeys("jonattanandrade@hotmail.com");
    u.getElemento(By.id("frmUsuariosInternet:inputCargoDesempenado")).sendKeys("Presidente");

    u.getElemento(By.id("frmUsuariosInternet:inputUsuario")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputUsuario")).sendKeys("Usuario"+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputPassword")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputPassword")).sendKeys("Usuario"+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputConfirmPassword")).sendKeys("Usuario"+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputPregunta")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputPregunta")).sendKeys("Usuario"+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:inputRespuesta")).click();
    u.getElemento(By.id("frmUsuariosInternet:inputRespuesta")).sendKeys("Usuario"+id_usuario);
    
    System.out.println("Usuario"+id_usuario);
    u.getElemento(By.id("frmUsuariosInternet:btnAceptar")).click();
    
    wait.until(ExpectedConditions.elementToBeClickable(By.id("frmUsuariosInternet:btnMensajeResultado")));
    try{TimeUnit.MILLISECONDS.sleep(1000L);} catch(Exception e){}
    u.getElemento(By.id("frmUsuariosInternet:btnMensajeResultado")).click();

  }
  
  public void verificacion_de_usuario_creado(){
        u.login(util.params.get("usu_invima_usuario"),util.params.get("usu_invima_contraseña"));
        driver.get(util.params.get("url_base")+"faces/pages/activar.xhtml");
        try{TimeUnit.MILLISECONDS.sleep(2000L);} catch(Exception e){}
        
        u.getElemento(By.id("frmActivarUsuariosInternet:listaCriterio_label")).click();
        u.getElemento(By.id("frmActivarUsuariosInternet:listaCriterio_1")).click();
        
        
        u.getElemento(By.id("frmActivarUsuariosInternet:inputCedulaNombre")).click();
        u.getElemento(By.id("frmActivarUsuariosInternet:inputCedulaNombre")).sendKeys(id_usuario);

        u.getElemento(By.id("frmActivarUsuariosInternet:btnBuscar")).click();

        
        
        
        u.getElemento(By.id("frmActivarUsuariosInternet:listaCedulaNombre_label")).click();
        u.getElemento(By.id("frmActivarUsuariosInternet:listaCedulaNombre_1")).click();
        
        u.getElemento(By.id("frmActivarUsuariosInternet:listaEstado_label")).click();
        u.getElemento(By.id("frmActivarUsuariosInternet:listaEstado_2")).click();
        
        u.getElemento(By.id("frmActivarUsuariosInternet:listaRol_label")).click();
        u.getElemento(By.id("frmActivarUsuariosInternet:listaRol_1")).click();

        u.getElemento(By.id("frmActivarUsuariosInternet:btnAceptar")).click();
        
        u.getElemento(By.id("barra_horizontal:salir")).click();
        try{TimeUnit.MILLISECONDS.sleep(3000L);} catch(Exception e){}
        u.login("Usuario"+id_usuario,"Usuario"+id_usuario);
    }
  
   
}
