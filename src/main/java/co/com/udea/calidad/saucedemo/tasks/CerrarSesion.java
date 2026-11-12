package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.interactions.Pausar;
import co.com.udea.calidad.saucedemo.userinterfaces.MenuPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CerrarSesion {

    private CerrarSesion() {}

    public static Performable desdeElMenu() {
        return Task.where("{0} cierra sesión desde el menú",
                ClicSeguro.en(MenuPage.BOTON_MENU),
                WaitUntil.the(MenuPage.CERRAR_SESION, isVisible()).forNoMoreThan(5).seconds(),
                Pausar.configurada(),
                ClicSeguro.en(MenuPage.CERRAR_SESION));
    }
}
