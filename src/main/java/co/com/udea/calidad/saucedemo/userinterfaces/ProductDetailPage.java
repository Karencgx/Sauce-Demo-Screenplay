package co.com.udea.calidad.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ProductDetailPage {

    public static final Target NOMBRE = Target.the("nombre del producto en el detalle").locatedBy("[data-test='inventory-item-name']");
    public static final Target PRECIO = Target.the("precio del producto en el detalle").locatedBy("[data-test='inventory-item-price']");
    // En el detalle hay un solo botón Add to cart; el data-test lleva el nombre del producto como sufijo.
    public static final Target AGREGAR = Target.the("botón Add to cart del detalle").locatedBy("[data-test^='add-to-cart']");
    public static final Target ELIMINAR = Target.the("botón Remove del detalle").locatedBy("[data-test^='remove']");
    // Solo existe en la pantalla de detalle: sirve para saber que la página ya cargó.
    public static final Target VOLVER = Target.the("botón Back to products").locatedBy("[data-test='back-to-products']");

    private ProductDetailPage() {}
}
