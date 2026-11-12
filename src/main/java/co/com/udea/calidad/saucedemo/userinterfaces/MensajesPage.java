package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Mensajes de error: el sitio usa el mismo data-test en el login y en el checkout. */
public class MensajesPage {

    public static final Target ERROR = Target.the("mensaje de error").locatedBy("[data-test='error']");

    private MensajesPage() {}
}
