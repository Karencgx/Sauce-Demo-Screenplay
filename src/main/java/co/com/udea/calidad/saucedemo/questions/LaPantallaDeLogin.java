package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** true si el botón Login está en pantalla. */
public class LaPantallaDeLogin implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return !LoginPage.BOTON_INGRESAR.resolveAllFor(actor).isEmpty();
    }

    public static LaPantallaDeLogin visible() {
        return new LaPantallaDeLogin();
    }
}
