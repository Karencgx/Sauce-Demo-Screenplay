package co.com.udea.calidad.saucedemo.tasks;

import co.com.udea.calidad.saucedemo.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class OrdenarCatalogo {

    private OrdenarCatalogo() {}

    public static Performable por(String criterio) {
        return Task.where("{0} ordena el catálogo por " + criterio,
                SelectFromOptions.byVisibleText(criterio).from(ProductsPage.ORDENAMIENTO));
    }
}
