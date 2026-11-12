# language: es
Característica: Autenticación en SauceDemo
  Como cliente de la tienda en línea SauceDemo
  Quiero iniciar y cerrar sesión de forma segura
  Para proteger el acceso a mi cuenta y a mis compras

  Antecedentes:
    Dado que el cliente abre la tienda SauceDemo

  Escenario: Ruta feliz - inicio de sesión exitoso
    Cuando inicia sesión con el usuario "standard_user" y la contraseña "secret_sauce"
    Entonces el cliente ve el catálogo de productos
    Y el catálogo muestra 6 productos

  Esquema del escenario: Camino de excepción - inicio de sesión rechazado
    Cuando inicia sesión con el usuario "<usuario>" y la contraseña "<contrasena>"
    Entonces el sistema muestra el mensaje de error "<mensaje>"

    Ejemplos:
      | usuario         | contrasena   | mensaje                                                                   |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | clave_mala   | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |

  Escenario: Cierre de sesión exitoso
    Dado que el cliente inició sesión con el usuario "standard_user" y la contraseña "secret_sauce"
    Cuando cierra sesión desde el menú
    Entonces el cliente regresa a la pantalla de inicio de sesión

  Escenario: Escenario límite - acceso directo al catálogo sin sesión iniciada
    Cuando intenta entrar directamente a la página del catálogo sin iniciar sesión
    Entonces el sistema muestra el mensaje de error "Epic sadface: You can only access '/inventory.html' when you are logged in."
