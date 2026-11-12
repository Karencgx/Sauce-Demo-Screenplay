# language: es
Característica: Flujos principales con los distintos usuarios de SauceDemo
  Como equipo de calidad
  Queremos ejecutar los flujos principales con cada tipo de usuario de la tienda
  Para detectar comportamientos que cambian según el perfil

  Antecedentes:
    Dado que el cliente abre la tienda SauceDemo

  Esquema del escenario: Inicio de sesión exitoso con cada usuario válido
    Cuando inicia sesión con el usuario "<usuario>" y la contraseña "secret_sauce"
    Entonces el cliente ve el catálogo de productos
    Y el catálogo muestra 6 productos

    Ejemplos:
      | usuario                 |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  Esquema del escenario: Compra completa con usuarios sin defectos en el flujo
    Dado que el cliente inició sesión con el usuario "<usuario>" y la contraseña "secret_sauce"
    Y que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Y finaliza la compra
    Entonces el sistema muestra el mensaje de confirmación "Thank you for your order!"

    Ejemplos:
      | usuario                 |
      | performance_glitch_user |
      | visual_user             |

  Esquema del escenario: Los precios del catálogo coinciden con los publicados
    Dado que el cliente inició sesión con el usuario "<usuario>" y la contraseña "secret_sauce"
    Entonces el precio de "Sauce Labs Backpack" en el catálogo es "$29.99"
    Y el precio de "Sauce Labs Bike Light" en el catálogo es "$9.99"
    Y el precio de "Sauce Labs Bolt T-Shirt" en el catálogo es "$15.99"
    Y el precio de "Sauce Labs Fleece Jacket" en el catálogo es "$49.99"

    Ejemplos:
      | usuario                 |
      | standard_user           |
      | performance_glitch_user |

  # Defecto observado: el catálogo de visual_user muestra precios distintos a los del carrito
  @defectos-conocidos
  Escenario: Defecto - los precios del catálogo de visual_user no coinciden con los publicados
    Dado que el cliente inició sesión con el usuario "visual_user" y la contraseña "secret_sauce"
    Entonces el precio de "Sauce Labs Backpack" en el catálogo es "$29.99"
    Y el precio de "Sauce Labs Bike Light" en el catálogo es "$9.99"
    Y el precio de "Sauce Labs Bolt T-Shirt" en el catálogo es "$15.99"
    Y el precio de "Sauce Labs Fleece Jacket" en el catálogo es "$49.99"

  Esquema del escenario: Agregar al carrito productos que en otros perfiles fallan
    Dado que el cliente inició sesión con el usuario "<usuario>" y la contraseña "secret_sauce"
    Cuando agrega al carrito el producto "<producto>"
    Entonces el ícono del carrito muestra el número 1

    Ejemplos:
      | usuario       | producto                          |
      | standard_user | Sauce Labs Bolt T-Shirt           |
      | standard_user | Sauce Labs Fleece Jacket          |
      | standard_user | Test.allTheThings() T-Shirt (Red) |

  # Defecto observado: problem_user no puede agregar estos tres productos (Backpack, Bike Light y Onesie sí)
  @defectos-conocidos
  Escenario: Defecto - problem_user no puede agregar la camiseta Bolt al carrito
    Dado que el cliente inició sesión con el usuario "problem_user" y la contraseña "secret_sauce"
    Cuando agrega al carrito el producto "Sauce Labs Bolt T-Shirt"
    Entonces el ícono del carrito muestra el número 1

  @defectos-conocidos
  Escenario: Defecto - problem_user no puede agregar la chaqueta Fleece al carrito
    Dado que el cliente inició sesión con el usuario "problem_user" y la contraseña "secret_sauce"
    Cuando agrega al carrito el producto "Sauce Labs Fleece Jacket"
    Entonces el ícono del carrito muestra el número 1

  @defectos-conocidos
  Escenario: Defecto - problem_user no puede agregar la camiseta roja al carrito
    Dado que el cliente inició sesión con el usuario "problem_user" y la contraseña "secret_sauce"
    Cuando agrega al carrito el producto "Test.allTheThings() T-Shirt (Red)"
    Entonces el ícono del carrito muestra el número 1

  @defectos-conocidos
  Escenario: El checkout de error_user exige el apellido antes de continuar
    Dado que el cliente inició sesión con el usuario "error_user" y la contraseña "secret_sauce"
    Y que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "" y código postal "050001"
    Entonces el sistema muestra el mensaje de error "Error: Last Name is required"

  @defectos-conocidos
  Esquema del escenario: Compra completa con usuarios que fallan en el checkout
    Dado que el cliente inició sesión con el usuario "<usuario>" y la contraseña "secret_sauce"
    Y que agrega al carrito el producto "Sauce Labs Backpack"
    Cuando inicia el checkout con nombre "Juan", apellido "Perez" y código postal "050001"
    Entonces el cliente ve el resumen de la orden
    Cuando finaliza la compra
    Entonces el sistema muestra el mensaje de confirmación "Thank you for your order!"

    Ejemplos:
      | usuario      |
      | problem_user |
      | error_user   |
