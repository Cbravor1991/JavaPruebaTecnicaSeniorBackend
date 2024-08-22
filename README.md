# Prueba Técnica - BackEnd Senior (Java)

## Enunciado

Desarrollar una API RESTful en Java que gestione una librería digital. La API debe permitir la creación, consulta, actualización y eliminación de libros y autores. Además, debe proporcionar la capacidad de buscar libros por diferentes criterios y realizar operaciones avanzadas como la clasificación y paginación de resultados.

## Requisitos

### Entidades

#### Libro (Book):
- **ID**: UUID
- **Título**: String
- **Autor**: Author
- **ISBN**: String (único)
- **Fecha de publicación**: LocalDate
- **Número de páginas**: int
- **Género**: Enum (Fiction, Non-Fiction, Science, History, etc.)

#### Autor (Author):
- **ID**: UUID
- **Nombre completo**: String
- **Fecha de nacimiento**: LocalDate
- **Nacionalidad**: String

### Funcionalidades de la API

#### Libros:
- Crear un nuevo libro.
- Consultar un libro por su ID.
- Actualizar un libro existente.
- Eliminar un libro.
- Listar todos los libros con soporte para paginación y clasificación por título, fecha de publicación, y número de páginas.
- Buscar libros por título, autor, género y rango de fechas de publicación.

#### Autores:
- Crear un nuevo autor.
- Consultar un autor por su ID.
- Actualizar un autor existente.
- Eliminar un autor.
- Listar todos los autores.

### Requisitos Técnicos

- **Framework**: Utilizar Spring Boot.
- **Persistencia**: Utilizar una base de datos relacional (PostgreSQL, MySQL, o similar). Implementar JPA/Hibernate para el acceso a datos.
- **Pruebas**: Escribir pruebas unitarias y de integración utilizando JUnit y/o Mockito.
- **Documentación**: Documentar la API utilizando Swagger/OpenAPI.
- **Seguridad**: Implementar seguridad básica con autenticación JWT para proteger ciertos endpoints (opcional).
- **Manejo de Errores**: Implementar manejo de excepciones y respuestas de error apropiadas para la API.
- **Deploy**: La solución debe ser capaz de ejecutarse localmente utilizando Docker.

### Extras (Opcional)
- Implementar un endpoint para importar libros en masa a partir de un archivo CSV.
- Implementar un sistema de caché para optimizar las consultas a la base de datos.
- Añadir validaciones adicionales a los modelos (por ejemplo, verificar el formato del ISBN).

## Entrega
Subir el código a un repositorio en GitHub/GitLab con un `README.md` que explique cómo ejecutar la aplicación, incluyendo los comandos para configurar y ejecutar la base de datos.

Incluir instrucciones claras sobre cómo ejecutar las pruebas.

## Evaluación
La evaluación se centrará en la calidad del código, la adherencia a los principios SOLID, el uso de buenas prácticas en el desarrollo de APIs, la cobertura de pruebas, y la documentación. Además, se valorará la implementación de las funcionalidades extras y la eficiencia de las soluciones propuestas.

## Tiempo de Entrega
El tiempo para realizar esta prueba es de 5 días.

## Desarrollado hasta el momento

- **Modelos**: Se crearon los modelos `Author` y `Book` utilizando JPA, lo que permite la creación automática de las tablas en la base de datos especificada en `application.properties`.

- **Repositorios**: Se implementaron los repositorios correspondientes, los cuales simplifican las operaciones de persistencia y recuperación de datos.

- **Servicios**: Se diseñaron las interfaces de servicios y se implementaro las mismas, actuando como una capa intermedia que separa la lógica de negocio entre los controladores y los repositorios mediante encapsulamiento.

- **Tests**: Se realizaron pruebas para verificar que las implementaciones del servicio interactúen correctamente con los repositorios.

