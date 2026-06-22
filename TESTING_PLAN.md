## Pruebas Unitarias y Cobertura de Reglas de Negocio

### Reglas de Negocio Críticas del Servicio de Productos
1. Validación de Subpedido: No se puede crear una preparación si el subpedido asociado no existe.
2. Consulta de Preparación: Debe ser posible obtener una preparación mediante su identificador único.
3. Manejo de Recursos Inexistentes: Si una preparación no existe, el sistema debe lanzar una excepción controlada.
4. Persistencia de Datos: Las preparaciones deben almacenarse, recuperarse y eliminarse correctamente.

### Cobertura Actual
| Regla                  | Estado          | Casos Cubiertos                              |
|------------------------|-----------------|----------------------------------------------|
| 1. Validación de Subpedido | ⚠️ Pendiente    | Evitar tener subpedidos sueltos          |
| 2. Consulta de Preparación   | ✅ Cubierta     |Consulta por ID existente               |
| 3. Manejo de Recursos Inexistentes | ✅ Cubierta | Excepción al consultar una preparación inexistente|
| 4. Persistencia de Datos  |  ✅ Cubierta|crearPreparacion(),obtenerPorId(),eliminar()   |

### Cobertura por Capa
|Capa	|Tipo de Prueba|	Estado    |
|-------|-----------|-------------|
|Controller|	Pruebas de endpoints REST con MockMvc   |✅
|Service	|Pruebas unitarias con Mockito	            |✅
|Repository|	Pruebas de persistencia con DataJpaTest	|✅
|Model    	|Validación de getters, setters, equals y hashCode|	✅

## Ejecución de Pruebas
#### Comando utilizado:
  mvn clean test


### Reflexión y Deuda Técnica
- Riesgo sin probar: No se validan respuestas HTTP de error (404 Not Found, 400 Bad Request) en test pertinentes.
- 1.1)Acción Futura: -Agregar un test que verifique la excepción al crear una preparación con un subpedido inexistente.
- 1.2)Incorporar respuestas HTTP para verificar validaciones de errores (404 Not Found, 400 Bad Request)
