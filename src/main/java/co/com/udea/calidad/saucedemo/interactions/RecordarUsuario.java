package co.com.udea.calidad.saucedemo.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

/** Guarda en la memoria del actor con qué usuario inició sesión. */
public class RecordarUsuario implements Interaction {

    private final String usuario;

    public RecordarUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.remember("usuario", usuario);
    }

    public static RecordarUsuario es(String usuario) {
        return Tasks.instrumented(RecordarUsuario.class, usuario);
    }
}
