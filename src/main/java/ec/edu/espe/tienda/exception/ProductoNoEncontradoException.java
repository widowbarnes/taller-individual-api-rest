package ec.edu.espe.tienda.exception;

/** Se lanza cuando se consulta o elimina un producto cuyo id no existe. */
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("No se encontró el producto con id " + id);
    }
}
