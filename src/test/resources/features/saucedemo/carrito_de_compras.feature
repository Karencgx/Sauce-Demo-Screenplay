# language: es
Característica: Carrito de compras en SauceDemo
  Como cliente de la tienda en línea SauceDemo
  Quiero agregar y eliminar productos de mi carrito
  Para validar que el carrito refleja correctamente mi selección

  Antecedentes:
    Dado que el cliente abre la tienda SauceDemo
    Y el cliente inició sesión con el usuario "standard_user" y la contraseña "secret_sauce"

  Escenario: Ruta feliz - agregar y eliminar productos del carrito
    Cuando agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Y elimina del carrito el producto "Sauce Labs Bike Light"
    Entonces el carrito muestra 1 producto
    Y el producto "Sauce Labs Backpack" aparece en el carrito

  Escenario: Un carrito nuevo no tiene productos
    Cuando abre su carrito de compras
    Entonces el carrito no muestra ningún producto

  Escenario: El contador del carrito se actualiza al agregar productos
    Cuando agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Entonces el ícono del carrito muestra el número 2

  Escenario: Escenario límite - vaciar el carrito por completo
    Cuando agrega al carrito los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
    Y elimina del carrito el producto "Sauce Labs Backpack"
    Y elimina del carrito el producto "Sauce Labs Bike Light"
    Entonces el carrito no muestra ningún producto

  Escenario: Los productos se conservan al seguir comprando
    Cuando agrega al carrito el producto "Sauce Labs Backpack"
    Y abre su carrito de compras
    Y regresa al catálogo para seguir comprando
    Y agrega al carrito el producto "Sauce Labs Onesie"
    Entonces el carrito muestra 2 productos
    Y el producto "Sauce Labs Backpack" aparece en el carrito
    Y el producto "Sauce Labs Onesie" aparece en el carrito
