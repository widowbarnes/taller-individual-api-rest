package ec.edu.espe.tienda.service;

import ec.edu.espe.tienda.exception.ProductoInvalidoException;
import ec.edu.espe.tienda.exception.ProductoNoEncontradoException;
import ec.edu.espe.tienda.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 * Contiene las reglas de negocio y gestiona los productos en memoria.
 * Spring crea una única instancia (@Service) y la inyecta donde se necesite.
 */
@Service
public class ProductoService {

    private final Map<Long, Producto> productos = new ConcurrentSkipListMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public ProductoService() {
        // Datos iniciales de ejemplo
        registrar(new Producto(null, "Laptop", 850.00, 10));
        registrar(new Producto(null, "Mouse inalámbrico", 15.50, 40));
        registrar(new Producto(null, "Teclado mecánico", 60.00, 25));
    }

    public List<Producto> listar() {
        return new ArrayList<>(productos.values());
    }

    public Producto buscarPorId(Long id) {
        Producto producto = productos.get(id);
        if (producto == null) {
            throw new ProductoNoEncontradoException(id);
        }
        return producto;
    }

    public Producto registrar(Producto producto) {
        validar(producto);
        Long nuevoId = secuencia.incrementAndGet();
        Producto nuevo = new Producto(nuevoId, producto.getNombre().trim(),
                producto.getPrecio(), producto.getStock());
        productos.put(nuevoId, nuevo);
        return nuevo;
    }

    public void eliminar(Long id) {
        if (productos.remove(id) == null) {
            throw new ProductoNoEncontradoException(id);
        }
    }

    private void validar(Producto producto) {
        if (producto == null) {
            throw new ProductoInvalidoException("Los datos del producto son obligatorios");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoInvalidoException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }
    }
}
