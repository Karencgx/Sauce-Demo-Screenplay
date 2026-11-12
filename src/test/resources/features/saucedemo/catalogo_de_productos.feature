# language: es
Característica: Catálogo de productos en SauceDemo
  Como cliente de la tienda en línea SauceDemo
  Quiero ordenar y consultar los productos del catálogo
  Para encontrar fácilmente lo que quiero comprar

  Antecedentes:
    Dado que el cliente abre la tienda SauceDemo
    Y el cliente inició sesión con el usuario "standard_user" y la contraseña "secret_sauce"

  Escenario: El catálogo muestra todos los productos disponibles
    Entonces el catálogo muestra 6 productos

  Esquema del escenario: Ordenamiento del catálogo
    Cuando ordena el catálogo por "<criterio>"
    Entonces el primer producto del catálogo es "<primer_producto>"

    Ejemplos:
      | criterio            | primer_producto                   |
      | Name (A to Z)       | Sauce Labs Backpack               |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |
      | Price (low to high) | Sauce Labs Onesie                 |
      | Price (high to low) | Sauce Labs Fleece Jacket          |

  Escenario: Consultar el detalle de un producto
    Cuando abre el detalle del producto "Sauce Labs Backpack"
    Entonces el detalle muestra el nombre "Sauce Labs Backpack"
    Y el detalle muestra el precio "$29.99"

  Escenario: Agregar un producto desde su página de detalle
    Cuando abre el detalle del producto "Sauce Labs Backpack"
    Y agrega al carrito el producto desde el detalle
    Entonces el ícono del carrito muestra el número 1
