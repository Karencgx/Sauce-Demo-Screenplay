# SauceDemo Screenplay

Automatización de pruebas E2E sobre la tienda de práctica [SauceDemo](https://www.saucedemo.com/) con el patrón **Screenplay**, **SerenityBDD**, **Cucumber** (escenarios en inglés) y **Gradle**.

Proyecto del taller *Automatización de pruebas con scaffolding of ScreenPlay Architecture* — Calidad de Software 2026-2, Universidad de Antioquia.

**Integrantes:** _(nombres del equipo)_
**Docente:** Robinson Coronado G.

---

## Qué se prueba

Cada historia de usuario (HU) es un feature en inglés dentro de `src/test/resources/features/saucedemo/` y sus escenarios son los criterios de aceptación. Cada escenario lleva la etiqueta de su HU (`@HU-01` ... `@HU-10`).

| HU | Historia | Casos |
|---|---|---|
| HU-01 | Iniciar sesión (usuario válido, bloqueado y 4 rechazos) | 6 |
| HU-02 | Cerrar sesión y acceso sin sesión | 2 |
| HU-03 | Ordenar el catálogo (6 productos y 4 criterios) | 5 |
| HU-04 | Ver el detalle de un producto (6 productos) y agregarlo desde el detalle | 8 |
| HU-05 | Gestionar el carrito | 8 |
| HU-06 | Ingresar los datos de envío, totales de la orden y cancelar | 8 |
| HU-07 | Finalizar la compra | 6 |
| HU-08 | Descargar el PDF de la orden | 1 |
| HU-09 | Usar la tienda con cada perfil de usuario | 13 |
| HU-10 | Documentar los defectos de los usuarios de prueba | 7 |

### Resultado esperado

El proyecto tiene **64 casos de prueba**:

- **57 deben pasar.**
- **7 deben fallar a propósito** (HU-10, etiqueta `@known-defects`). Describen el comportamiento correcto, así que cada fallo es un defecto real que SauceDemo trae en sus usuarios de práctica:

| Usuario | Defecto que detecta la prueba |
|---|---|
| `visual_user` | Los precios del catálogo no coinciden con los publicados |
| `problem_user` | No puede agregar al carrito la camiseta Bolt, la chaqueta Fleece ni la camiseta roja; el formulario de checkout cambia las letras escritas |
| `error_user` | Permite continuar el checkout sin apellido y el botón Finish no completa la orden |

### Credenciales

La contraseña no está escrita en los features. Se lee de la variable de entorno `SAUCEDEMO_PASSWORD` o de la opción `-Psaucedemo.password`; si no se define, se usa la contraseña pública de demostración que SauceDemo publica en su propia página de inicio de sesión.

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
├── java/.../stepdefinitions/   Hooks y StepDefinitions por dominio (autenticación, catálogo, carrito, checkout)
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
gradle clean test aggregate -PwithoutDefects
gradle clean test aggregate -PonlyDefects
```

Una sola historia de usuario (por ejemplo, la HU-04):

```bash
gradle clean test aggregate -Phu=HU-04
```

### Opciones

| Opción | Para qué sirve | Valor por defecto |
|---|---|---|
| `-Ppausa.ms=3000` | Pausa visible entre pasos, en milisegundos (útil para demostrar) | `1500` |
| `-Psaucedemo.password=<clave>` | Contraseña de los usuarios (o variable de entorno `SAUCEDEMO_PASSWORD`) | contraseña pública de la demo |
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

- SauceDemo es un sitio público de práctica; las credenciales son las que publica el propio sitio.
- Ocasionalmente Chrome se cierra a mitad de una corrida (`invalid session id`). No es un fallo de la prueba: se repite el escenario afectado.
