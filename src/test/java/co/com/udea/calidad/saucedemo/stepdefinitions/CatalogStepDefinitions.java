package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.questions.ElDetalleDelProducto;
import co.com.udea.calidad.saucedemo.questions.ElPrecioDelProductoEnElCatalogo;
import co.com.udea.calidad.saucedemo.questions.ElPrimerProductoDelCatalogo;
import co.com.udea.calidad.saucedemo.questions.LaCantidadDeProductosDelCatalogo;
import co.com.udea.calidad.saucedemo.tasks.AbrirDetalleDelProducto;
import co.com.udea.calidad.saucedemo.tasks.AgregarDesdeElDetalle;
import co.com.udea.calidad.saucedemo.tasks.OrdenarCatalogo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/** HU-03, HU-04 and the price checks of HU-09 / HU-10: catalog and product detail. */
public class CatalogStepDefinitions {

    @Then("^the catalog shows (\\d+) products$")
    public void theCatalogShowsProducts(int amount) {
        theActorInTheSpotlight().should(seeThat(LaCantidadDeProductosDelCatalogo.actual(), equalTo(amount)));
    }

    @When("^the customer sorts the catalog by \"([^\"]*)\"$")
    public void theCustomerSortsTheCatalog(String criterion) {
        theActorInTheSpotlight().attemptsTo(OrdenarCatalogo.por(criterion));
    }

    @Then("^the first product in the catalog is \"([^\"]*)\"$")
    public void theFirstProductIs(String name) {
        theActorInTheSpotlight().should(seeThat(ElPrimerProductoDelCatalogo.actual(), equalTo(name)));
    }

    @When("^the customer opens the details of the product \"([^\"]*)\"$")
    public void theCustomerOpensTheDetails(String name) {
        theActorInTheSpotlight().attemptsTo(AbrirDetalleDelProducto.de(name));
    }

    @Then("^the details show the name \"([^\"]*)\"$")
    public void theDetailsShowTheName(String name) {
        theActorInTheSpotlight().should(seeThat(ElDetalleDelProducto.nombre(), equalTo(name)));
    }

    @Then("^the details show the price \"([^\"]*)\"$")
    public void theDetailsShowThePrice(String price) {
        theActorInTheSpotlight().should(seeThat(ElDetalleDelProducto.precio(), equalTo(price)));
    }

    @When("^the customer adds the product to the cart from the details$")
    public void theCustomerAddsFromTheDetails() {
        theActorInTheSpotlight().attemptsTo(AgregarDesdeElDetalle.alCarrito());
    }

    @Then("^the catalog price of \"([^\"]*)\" is \"([^\"]*)\"$")
    public void theCatalogPriceOf(String product, String price) {
        theActorInTheSpotlight().should(seeThat(ElPrecioDelProductoEnElCatalogo.de(product), equalTo(price)));
    }
}
