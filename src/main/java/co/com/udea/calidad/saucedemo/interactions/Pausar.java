package co.com.udea.calidad.saucedemo.interactions;

import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

/** Pausa configurable para poder observar la prueba paso a paso (-Ppausa.ms=3000). */
public class Pausar implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Util.dormir();
    }

    public static Pausar configurada() {
        return Tasks.instrumented(Pausar.class);
    }
}
