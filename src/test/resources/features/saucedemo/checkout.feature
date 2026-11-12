# language: es
Característica: Checkout y finalización de la orden en SauceDemo
  Como cliente de la tienda en línea SauceDemo
  Quiero completar la compra de los productos de mi carrito
  Para recibir la confirmación de mi orden

  Antecedentes:
    Dado que el cliente abre la tienda SauceDemo
    Y el cliente inició sesión con el usuario "standard_user" y la contraseña "secret_sauce"

  Escenario: Ruta feliz - compra completa hasta la confirmación de la orden
    Dado que agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y finaliza la compra
    Entonces el sistema muestra el mensaje de confirmación "Thank you for your order!"

  Escenario: Generar el PDF de la orden después de finalizar la compra
    Dado que agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y finaliza la compra
    Y genera el PDF de la orden
    Entonces el PDF de la orden se descarga en el equipo

  Escenario: Resumen de la orden con los totales correctos
    Dado que agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Entonces el resumen de la orden muestra el subtotal "$39.98"
    Y el resumen de la orden muestra el impuesto "$3.20"
    Y el resumen de la orden muestra el total "$43.18"

  Esquema del escenario: Camino de excepción - datos de envío incompletos
    Dado que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "<nombre>", apellido "<apellido>" y código postal "<codigo>"
    Entonces el sistema muestra el mensaje de error "<mensaje>"

    Ejemplos:
      | nombre | apellido | codigo | mensaje                        |
      |        | Perez    | 050001 | Error: First Name is required  |
      | Juan   |          | 050001 | Error: Last Name is required   |
      | Juan   | Perez    |        | Error: Postal Code is required |

  Escenario: Cancelar el checkout conserva los productos del carrito
    Dado que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y cancela la compra
    Entonces el carrito muestra 1 producto
    Y el producto "Sauce Labs Backpack" aparece en el carrito

  Escenario: Después de finalizar la compra el carrito queda vacío
    Dado que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y finaliza la compra
    Y regresa al catálogo desde la confirmación
    Entonces el ícono del carrito no muestra ningún número

  Escenario: Compra completa y cierre de sesión
    Dado que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y finaliza la compra
    Y regresa al catálogo desde la confirmación
    Y cierra sesión desde el menú
    Entonces el cliente regresa a la pantalla de inicio de sesión
