package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Limpia el campo y escribe el texto. Verifica que el valor haya quedado en el campo y,
 * si la página lo borró (por ejemplo al terminar de montarse), lo vuelve a escribir.
 */
public class EscribirTexto implements Interaction {

    private static final int INTENTOS = 4;

    private final Target campo;
    private final String texto;

    public EscribirTexto(Target campo, String texto) {
        this.campo = campo;
        this.texto = texto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int intento = 1; intento <= INTENTOS; intento++) {
            WebElementFacade elemento = campo.resolveFor(actor);
            elemento.waitUntilVisible();
            if (texto.isEmpty()) {
                elemento.clear();
            } else {
                elemento.type(texto);
            }
            Util.dormir(300);
            String valor = campo.resolveFor(actor).getValue();
            if ((valor == null ? "" : valor).equals(texto)) {
                return;
            }
        }
    }

    public static EscribirTexto en(Target campo, String texto) {
        return Tasks.instrumented(EscribirTexto.class, campo, texto);
    }
}