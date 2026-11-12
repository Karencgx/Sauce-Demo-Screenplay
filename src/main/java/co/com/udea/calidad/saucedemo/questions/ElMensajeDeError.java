package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.userinterfaces.MensajesPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElMensajeDeError implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return MensajesPage.ERROR.resolveFor(actor).getText().trim();
    }

    public static ElMensajeDeError mostrado() {
        return new ElMensajeDeError();
    }
}
