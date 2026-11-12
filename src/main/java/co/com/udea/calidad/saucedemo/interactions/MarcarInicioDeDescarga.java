package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

/** Guarda la hora y los PDF que ya existen, para distinguir el PDF nuevo de descargas anteriores. */
public class MarcarInicioDeDescarga implements Interaction {

    public static final String CLAVE = "inicioDescarga";
    public static final String PDF_PREVIOS = "pdfPrevios";

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.remember(CLAVE, System.currentTimeMillis());
        actor.remember(PDF_PREVIOS, Util.nombresDePdf());
    }

    public static MarcarInicioDeDescarga ahora() {
        return Tasks.instrumented(MarcarInicioDeDescarga.class);
    }
}