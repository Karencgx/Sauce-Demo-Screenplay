package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ProductsPage {

    public static final Target TITULO = Target.the("título de la página").locatedBy("[data-test='title']");
    public static final Target PRODUCTOS = Target.the("productos del catálogo").locatedBy("[data-test='inventory-item']");
    public static final Target NOMBRES = Target.the("nombres de los productos").locatedBy("[data-test='inventory-item-name']");
    public static final Target ORDENAMIENTO = Target.the("selector de ordenamiento").locatedBy("[data-test='product-sort-container']");

    // Targets dinámicos: se completan con .of(valor)
    public static final Target BOTON_AGREGAR = Target.the("botón Add to cart de {0}").locatedBy("[data-test='add-to-cart-{0}']");
    public static final Target BOTON_ELIMINAR = Target.the("botón Remove de {0}").locatedBy("[data-test='remove-{0}']");
    public static final Target NOMBRE_PRODUCTO = Target.the("nombre del producto {0}")
            .locatedBy("//div[@data-test='inventory-item-name' and normalize-space()='{0}']");

    public static final Target PRECIO_PRODUCTO = Target.the("precio del producto {0} en el catálogo")
            .locatedBy("//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and normalize-space()='{0}']]"
                    + "//div[@data-test='inventory-item-price']");

    private ProductsPage() {}
}