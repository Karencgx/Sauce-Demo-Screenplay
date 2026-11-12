package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.interactions.EscribirTexto;
import co.com.udea.calidad.saucedemo.interactions.Pausar;
import co.com.udea.calidad.saucedemo.interactions.RecordarUsuario;
import co.com.udea.calidad.saucedemo.userinterfaces.LoginPage;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion {

    private IniciarSesion() {}

    public static Performable con(String usuario, String clave) {
        return Task.where("{0} inicia sesión con el usuario '" + usuario + "'",
                RecordarUsuario.es(usuario),
                EscribirTexto.en(LoginPage.USUARIO, usuario),
                Pausar.configurada(),
                EscribirTexto.en(LoginPage.CLAVE, clave),
                Pausar.configurada(),
                ClicSeguro.en(LoginPage.BOTON_INGRESAR));
    }

    /** Inicia sesión y espera a que el catálogo termine de cargar antes de seguir. */
    public static Performable conExito(String usuario, String clave) {
        return Task.where("{0} inicia sesión y espera el catálogo",
                con(usuario, clave),
                WaitUntil.the(ProductsPage.ORDENAMIENTO, isVisible()).forNoMoreThan(10).seconds());
    }
}