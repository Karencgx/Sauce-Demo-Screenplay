package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.interactions.ClicSeguro;
import co.com.udea.calidad.saucedemo.interactions.Pausar;
import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductos {

    private AgregarProductos() {}

    /** Por cada producto: clic en Add to cart y espera a que el botón cambie a Remove. */
    public static Performable alCarrito(String... nombres) {
        List<Performable> acciones = new ArrayList<>();
        for (String nombre : nombres) {
            String slug = Util.slug(nombre);
            acciones.add(ClicSeguro.en(ProductsPage.BOTON_AGREGAR.of(slug)));
            acciones.add(WaitUntil.the(ProductsPage.BOTON_ELIMINAR.of(slug), isVisible()).forNoMoreThan(5).seconds());
            acciones.add(Pausar.configurada());
        }
        return Task.where("{0} agrega productos al carrito: " + String.join(", ", nombres),
                acciones.toArray(new Performable[0]));
    }
}
