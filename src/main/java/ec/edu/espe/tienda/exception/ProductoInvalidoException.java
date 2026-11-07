package ec.edu.espe.tienda.exception;

/** Se lanza cuando los datos de un producto incumplen una regla de negocio. */
public class ProductoInvalidoException extends RuntimeException {

    public ProductoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
