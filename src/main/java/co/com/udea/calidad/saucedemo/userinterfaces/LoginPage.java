package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target USUARIO = Target.the("campo usuario").locatedBy("[data-test='username']");
    public static final Target CLAVE = Target.the("campo contraseña").locatedBy("[data-test='password']");
    public static final Target BOTON_INGRESAR = Target.the("botón Login").locatedBy("[data-test='login-button']");

    private LoginPage() {}
}
