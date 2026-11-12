# SauceDemo Screenplay

Automatización de pruebas E2E sobre la tienda de práctica [SauceDemo](https://www.saucedemo.com/) con el patrón **Screenplay**, **SerenityBDD**, **Cucumber** (escenarios en español) y **Gradle**.

Proyecto del taller *Automatización de pruebas con scaffolding of ScreenPlay Architecture* — Calidad de Software 2026-2, Universidad de Antioquia.

**Integrantes:** _(nombres del equipo)_
**Docente:** Robinson Coronado G.

---

## Qué se prueba

| Feature | Qué cubre |
|---|---|
| `autenticacion.feature` | Login exitoso, login rechazado (usuario bloqueado, clave incorrecta, usuario o clave vacíos), cierre de sesión, acceso directo al catálogo sin sesión |
| `carrito_de_compras.feature` | Agregar y eliminar productos, carrito nuevo vacío, contador del ícono, vaciar el carrito, conservar productos al seguir comprando |
| `catalogo_de_productos.feature` | Listado de 6 productos, 4 criterios de ordenamiento, detalle de un producto, agregar desde el detalle |
| `checkout.feature` | Compra completa, generación del PDF de la orden, totales (subtotal, impuesto, total), datos de envío incompletos, cancelar, carrito vacío tras comprar, compra y cierre de sesión |
| `usuarios.feature` | Los 6 usuarios de SauceDemo: login, compra completa, precios del catálogo y productos que fallan con perfiles defectuosos |

### Resultado esperado

El proyecto tiene **47 casos de prueba**:

- **40 deben pasar.**
- **7 deben fallar a propósito.** Están etiquetados como `@defectos-conocidos` y documentan defectos que SauceDemo trae en sus usuarios de práctica:

| Usuario | Defecto que detecta la prueba |
|---|---|
| `visual_user` | Los precios del catálogo no coinciden con los publicados |
| `problem_user` | No puede agregar al carrito la camiseta Bolt, la chaqueta Fleece ni la camiseta roja; el formulario de checkout cambia las letras escritas |
| `error_user` | Permite continuar el checkout sin apellido y el botón Finish no completa la orden |

`locked_out_user` se prueba como camino de excepción (login rechazado).

---

## Arquitectura (Screenplay)

```
src/main/java/co/com/udea/calidad/saucedemo
├── userinterfaces/   Targets: dónde está cada elemento de cada página
├── interactions/     Acciones mínimas: ClicSeguro, EscribirTexto, Pausar, DiligenciarEnvio, ...
├── tasks/            Acciones de negocio: IniciarSesion, AgregarProductos, IniciarCheckout, ...
├── questions/        Consultas a la pantalla: ElContadorDelCarrito, ElMensajeDeError, ...
└── utils/            Utilidades (URL base, pausas, carpeta de descargas)

src/test
├── java/.../stepdefinitions/   StepDefinition: une cada frase Gherkin con tasks y questions
├── java/.../runners/           Runner (CucumberWithSerenity)
└── resources/
    ├── features/saucedemo/     Escenarios en español (Gherkin)
    └── serenity.conf           Configuración del navegador y de Serenity
```

Flujo de una prueba: el **feature** describe el escenario → el **StepDefinition** traduce cada frase → el actor **Juan** ejecuta **tasks** (hechas de **interactions**) sobre los **targets** → una **question** consulta la pantalla y se compara con lo esperado.

---

## Requisitos

- JDK 21
- Gradle (el proyecto no incluye wrapper)
- Google Chrome instalado (si no lo hay, Selenium descarga una copia de prueba que puede ser menos estable)
- Conexión a internet

---

## Cómo ejecutar

Suite completa (genera el reporte):

```bash
gradle clean test aggregate
```

Solo las pruebas que deben pasar / solo los defectos conocidos:

```bash
gradle clean test aggregate -PsinDefectos
gradle clean test aggregate -PsoloDefectos
```

Un escenario puntual por nombre:

```bash
gradle clean test aggregate "-Dcucumber.filter.name=Generar el PDF"
```

### Opciones

| Opción | Para qué sirve | Valor por defecto |
|---|---|---|
| `-Ppausa.ms=3000` | Pausa visible entre pasos, en milisegundos (útil para demostrar) | `1500` |
| `-Pdescargas.dir=<ruta>` | Carpeta donde Chrome guarda las descargas (la prueba del PDF la revisa) | `~/Downloads` |

> **Nota:** `clean` borra el reporte anterior. Si quieres conservar el de una corrida, copia `target/site/serenity` a otra carpeta antes de volver a ejecutar.

---

## Reporte

Al terminar, abre:

```
target/site/serenity/index.html
```

El reporte muestra cada escenario con sus pasos y capturas de pantalla. En la pestaña **Tags** puedes filtrar los defectos conocidos.

---

## Cómo se generó el esqueleto

El proyecto se creó con el plugin [ScreenplayArchitecture](https://github.com/jumosqu12/ScreenplayArchitecture):

```bash
gradle screenPlayArchitecture --projectName=SauceDemoScreenplay --groupId=co.com.udea.calidad --principalPackage=saucedemo --type=UX
```

---

## Notas

- SauceDemo es un sitio público de práctica; las credenciales son las publicadas por el propio sitio (`secret_sauce`).
- Ocasionalmente Chrome se cierra a mitad de una corrida (`invalid session id`). No es un fallo de la prueba: se repite el escenario afectado.
