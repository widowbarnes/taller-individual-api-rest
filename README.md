# API REST de Productos – Taller Individual 1.1

Programación Avanzada – NRC 36759 – Universidad de las Fuerzas Armadas ESPE

API REST con Spring Boot (Spring Web) para la gestión básica de productos de una tienda.
Los datos se guardan en memoria (sin base de datos), por lo que se reinician al detener la aplicación.

## Requisitos
- JDK 17 o superior
- Maven 3.9+ (o el Maven integrado del IDE)

## Ejecución
```bash
mvn spring-boot:run
```
La API queda disponible en `http://localhost:8080`.

## Endpoints
| Método | Ruta | Descripción | Éxito |
|--------|------|-------------|-------|
| GET | `/api/productos` | Lista todos los productos | 200 |
| GET | `/api/productos/{id}` | Consulta un producto | 200 / 404 |
| POST | `/api/productos` | Registra un producto | 201 / 400 |
| DELETE | `/api/productos/{id}` | Elimina un producto | 204 / 404 |

Ejemplo de cuerpo para `POST`:
```json
{ "nombre": "Monitor 24 pulgadas", "precio": 180.0, "stock": 12 }
```

## Reglas de negocio
- `precio` debe ser mayor a cero.
- `stock` no puede ser negativo.
- `nombre` es obligatorio.
- Consultar o eliminar un id inexistente genera `ProductoNoEncontradoException` (404).

## Estructura
```
controller/  ProductoController          -> capa HTTP
service/     ProductoService              -> reglas de negocio y almacenamiento en memoria
model/       Producto                     -> modelo de datos
exception/   ProductoNoEncontradoException, ProductoInvalidoException,
             ErrorResponse, GlobalExceptionHandler (@RestControllerAdvice)
```
