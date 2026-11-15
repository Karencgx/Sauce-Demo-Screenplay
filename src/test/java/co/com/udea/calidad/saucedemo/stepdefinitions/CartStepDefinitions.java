package co.com.udea.calidad.saucedemo.stepdefinitions;

import co.com.udea.calidad.saucedemo.questions.ElContadorDelCarrito;
import co.com.udea.calidad.saucedemo.questions.LosProductosDelCarrito;
import co.com.udea.calidad.saucedemo.tasks.AbrirCarrito;
import co.com.udea.calidad.saucedemo.tasks.AgregarProductos;
import co.com.udea.calidad.saucedemo.tasks.EliminarProducto;
import co.com.udea.calidad.saucedemo.tasks.SeguirComprando;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/** HU-05: shopping cart. Cucumber ignores the Given/When/Then keyword, so each sentence is defined once. */
public class CartStepDefinitions {

    @When("^the customer adds the products \"([^\"]*)\" and \"([^\"]*)\" to the cart$")
    public void theCustomerAddsTwoProducts(String first, String second) {
        theActorInTheSpotlight().attemptsTo(AgregarProductos.alCarrito(first, second));
    }

    @When("^the customer adds the product \"([^\"]*)\" to the cart$")
    public void theCustomerAddsAProduct(String name) {
        theActorInTheSpotlight().attemptsTo(AgregarProductos.alCarrito(name));
    }

    @When("^the customer removes the product \"([^\"]*)\" from the cart$")
    public void theCustomerRemovesAProduct(String name) {
        theActorInTheSpotlight().attemptsTo(EliminarProducto.delCarrito(name));
    }

    @When("^the customer opens the cart$")
    public void theCustomerOpensTheCart() {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
    }

    @When("^the customer goes back to the catalog to keep shopping$")
    public void theCustomerKeepsShopping() {
        theActorInTheSpotlight().attemptsTo(SeguirComprando.enElCatalogo());
    }

    @Then("^the cart shows (\\d+) products?$")
    public void theCartShowsProducts(int amount) {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasSize(amount)));
    }

    @Then("^the cart has no products$")
    public void theCartHasNoProducts() {
        theActorInTheSpotlight().attemptsTo(AbrirCarrito.deCompras());
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasSize(0)));
    }

    @Then("^the product \"([^\"]*)\" is in the cart$")
    public void theProductIsInTheCart(String name) {
        theActorInTheSpotlight().should(seeThat(LosProductosDelCarrito.actuales(), hasItem(name)));
    }

    @Then("^the cart icon shows the number (\\d+)$")
    public void theCartIconShowsTheNumber(int number) {
        theActorInTheSpotlight().should(seeThat(ElContadorDelCarrito.actual(), equalTo(number)));
    }

    @Then("^the cart icon shows no number$")
    public void theCartIconShowsNoNumber() {
        theActorInTheSpotlight().should(seeThat(ElContadorDelCarrito.actual(), equalTo(0)));
    }
}
