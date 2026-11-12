package co.com.udea.calidad.saucedemo.questions;

import co.com.udea.calidad.saucedemo.interactions.DescargarPdf;
import co.com.udea.calidad.saucedemo.interactions.MarcarInicioDeDescarga;
import co.com.udea.calidad.saucedemo.utils.Util;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

/**
 * true si aparece un .pdf nuevo en la carpeta de descargas (espera hasta 15 segundos).
 * Solo cuenta archivos modificados después de que el actor pulsó "Generate PDF order".
 */
public class ElPdfDeLaOrden implements Question<Boolean> {

    private static final long ESPERA_MAXIMA_MS = 15_000;

    @Override
    public Boolean answeredBy(Actor actor) {
        Boolean enPestana;
        try {
            enPestana = actor.recall(DescargarPdf.CLAVE_PESTANA);
        } catch (RuntimeException e) {
            enPestana = null;
        }
        if (Boolean.TRUE.equals(enPestana)) {
            return true;
        }
        long inicio = actor.recall(MarcarInicioDeDescarga.CLAVE);
        Set<String> previos = actor.recall(MarcarInicioDeDescarga.PDF_PREVIOS);
        boolean hay = hayPdfNuevo(inicio, previos, ESPERA_MAXIMA_MS);
        if (!hay) {
            System.out.println("PDF: no se encontró un .pdf nuevo en " + Util.carpetaDescargas()
                    + " (si Chrome descarga en otra carpeta, usa -Pdescargas.dir=<ruta>)");
        }
        return hay;
    }

    /** Espera hasta esperaMs a que aparezca un .pdf modificado después de 'inicio'. */
    public static boolean hayPdfNuevo(long inicio, Set<String> previos, long esperaMs) {
        Path carpeta = Util.carpetaDescargas();
        long limite = System.currentTimeMillis() + esperaMs;
        while (System.currentTimeMillis() < limite) {
            try (Stream<Path> archivos = Files.list(carpeta)) {
                // Nuevo = nombre que no existía antes, o fecha posterior al clic (Chrome puede conservar
                // la fecha original del archivo, por eso no basta con comparar fechas).
                boolean existe = archivos.anyMatch(p -> {
                    String nombre = p.getFileName().toString().toLowerCase();
                    return nombre.endsWith(".pdf")
                            && (!previos.contains(nombre) || p.toFile().lastModified() >= inicio - 2000);
                });
                if (existe) {
                    return true;
                }
            } catch (IOException e) {
                // la carpeta no es legible: se reintenta hasta agotar la espera
            }
            Util.dormir(500);
        }
        return false;
    }

    public static ElPdfDeLaOrden descargado() {
        return new ElPdfDeLaOrden();
    }
}