package seleniumTests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class util {
    
    public static Map<String, String> params = new HashMap<>();
    static {
        boolean probar_en_servidor_local = true;
        
        if(probar_en_servidor_local){
            params.put("url_base", "http://localhost:8080/ReactivoVigilanciaWeb/");
        }else{
            params.put("url_base", "http://190.216.137.115:8080/ReactivoVigilanciaWeb/");
        }
        
        
        params.put("ubicacion_webdriver.chrome", "C:\\Selenium\\\\chromedriver_win32\\\\chromedriver.exe");
        params.put("ubicacion_webdriver.gecko", "C:\\Selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
        params.put("explorador_para_ejecutar_prueba", "firefox");//firefox o chrome
        
        
        
        
        params.put("usu_invima_usuario", "invsariast");
        params.put("usu_invima_contraseña", "Bendicion123");
        params.put("usu_no_invima_usuario", "a41642149");
        params.put("usu_no_invima_contraseña", "Ac101102");
        
        System.setProperty("webdriver.gecko.driver", params.get("ubicacion_webdriver.gecko"));
        System.setProperty("webdriver.chrome.driver", params.get("ubicacion_webdriver.chrome"));
    }
    
    public static WebDriver getDriver(){
        WebDriver driver;
        if(params.get("explorador_para_ejecutar_prueba").equals("firefox")){
            driver = new FirefoxDriver();
        }else{
            driver = new ChromeDriver();
        }
        return driver;
    }
    
    WebDriver driver;
    
    util(WebDriver driver){
        this.driver = driver;
    }
    
    /**
     * Metodo que retorna un elemento con un delay para visualizar la prueba
     * @param referencia
     * @return 
     */
    public WebElement getElemento (By referencia){
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException ex) {
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return driver.findElement(referencia);
    }
    
    /**
     * Login en la pagina principal de Tecnovigilancia
     * @param usuario
     * @param contraseña 
     */
    public void login(String usuario, String contraseña){
        driver.get(util.params.get("url_base"));
        driver.manage().window().setSize(new Dimension(1055, 692));
        getElemento(By.id("inputUsuario")).click();
        getElemento(By.id("inputUsuario")).clear();
        getElemento(By.id("inputUsuario")).sendKeys(usuario);
        getElemento(By.id("inputPassword")).sendKeys(contraseña);
        getElemento(By.id("btnLogin")).click();
        {
            WebElement element = getElemento(By.linkText("Opciones de Reactivo Vigilancia"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
   }
    
    public static String obtener_consecutivo_reporte(String text_in, String palabra_anterior_a_lo_buscado){
      String palabra_guia = palabra_anterior_a_lo_buscado;
      int posicion_inicial = text_in.indexOf(palabra_guia);
      
      String retorno="";
      try{
        retorno = text_in.substring(posicion_inicial+palabra_guia.length(), posicion_inicial+palabra_guia.length()+14);
      }
      catch(Exception e){
        retorno = text_in.substring(posicion_inicial+palabra_guia.length(), posicion_inicial+palabra_guia.length()+14);  
      }
      
      return retorno.trim();
  }
}
