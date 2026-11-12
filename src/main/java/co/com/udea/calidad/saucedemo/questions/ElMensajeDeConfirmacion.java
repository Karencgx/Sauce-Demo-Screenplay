package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElMensajeDeConfirmacion implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return CheckoutPage.CONFIRMACION.resolveFor(actor).getText().trim();
    }

    public static ElMensajeDeConfirmacion deLaOrden() {
        return new ElMensajeDeConfirmacion();
    }
}
