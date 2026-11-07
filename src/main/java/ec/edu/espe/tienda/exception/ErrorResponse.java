package ec.edu.espe.tienda.exception;

import java.time.LocalDateTime;

/** Estructura común de las respuestas de error de la API. */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje,
        String ruta) {
}
