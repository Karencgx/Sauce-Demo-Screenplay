package co.com.udea.calidad.saucedemo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Util {

    public static final String URL_BASE = "https://www.saucedemo.com/";

    private Util() {}

    /** Convierte "Sauce Labs Backpack" en "sauce-labs-backpack" (formato del atributo data-test: solo minúsculas y guiones por espacios). */
    public static String slug(String nombre) {
        return nombre.trim().toLowerCase().replaceAll("\\s+", "-");
    }

    /** Pausa entre pasos en milisegundos. Se cambia con: gradle test -Ppausa.ms=3000 */
    public static long pausaMs() {
        return Long.parseLong(System.getProperty("pausa.ms", "1500"));
    }

    public static void dormir() {
        dormir(pausaMs());
    }

    public static void dormir(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** "Item total: $39.98" -> "$39.98" */
    public static String valorDespuesDeDosPuntos(String texto) {
        int i = texto.indexOf(':');
        return i >= 0 ? texto.substring(i + 1).trim() : texto.trim();
    }

    /** Carpeta de descargas del navegador. Se cambia con: gradle test -Pdescargas.dir=C:\ruta */
    public static Path carpetaDescargas() {
        String personalizada = System.getProperty("descargas.dir");
        return personalizada != null
                ? Paths.get(personalizada)
                : Paths.get(System.getProperty("user.home"), "Downloads");
    }

    /** Nombres (en minúscula) de los .pdf que hay ahora en la carpeta de descargas. */
    public static Set<String> nombresDePdf() {
        Set<String> nombres = new HashSet<>();
        try (Stream<Path> archivos = Files.list(carpetaDescargas())) {
            archivos.map(p -> p.getFileName().toString().toLowerCase())
                    .filter(n -> n.endsWith(".pdf"))
                    .forEach(nombres::add);
        } catch (IOException e) {
            // carpeta ilegible: se devuelve el conjunto vacío
        }
        return nombres;
    }
}