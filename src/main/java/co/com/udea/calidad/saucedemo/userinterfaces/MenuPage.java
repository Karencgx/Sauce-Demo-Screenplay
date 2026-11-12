package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class MenuPage {

    public static final Target BOTON_MENU = Target.the("botón del menú").locatedBy("#react-burger-menu-btn");
    public static final Target CERRAR_SESION = Target.the("opción Logout").locatedBy("[data-test='logout-sidebar-link']");

    private MenuPage() {}
}
