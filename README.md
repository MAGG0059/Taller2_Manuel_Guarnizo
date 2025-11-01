# Taller2_Manuel_Guarnizo

# Receta API

## Descripción del Proyecto
API REST para gestión de recetas culinarias desarrollada con Spring Boot. Permite registrar, consultar, actualizar y eliminar recetas de diferentes tipos (participantes, televidentes, chefs) con filtros por temporada e ingredientes.

## Instalación y Ejecución Local

### Prerrequisitos
- Java 17 o superior
- Maven 3.6+
- Base de datos MongoDB

### Pasos de instalación
1. Clonar el repositorio:
```bash


git clone <https://github.com/MAGG0059/Taller-Evaluativo-Dosw_2025-2>



Configurar la base de datos en application.properties

## properties

spring.data.mongodb.uri=mongodb://localhost:27017/taller2

## Compilar el proyecto:
mvn clean compile

## bash

## mvn clean compile

## Ejecutar la aplicación:

##Para ejecutar swagger:

## mvn spring-boot:run

El swagger estará disponible en:  http://localhost:80/swagger-ui/index.html#/

## Endpoints y Ejemplos

1. Registrar Receta
POST /api/recetas/registrar/{tipo}

Path Variable:

tipo - Tipo de receta (participante, televidente, chef)

Request Body:

json
{
    "titulo": "Pasta Carbonara",
    "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
    "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
    "nombre": "María González",
    "temporada": 5
}
Response:

json
{
    "id": "507f1f77bcf86cd799439011",
    "tipo": "participante",
    "titulo": "Pasta Carbonara",
    "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
    "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
    "nombre": "María González",
    "temporada": 5,
    "fechaRegistro": "2024-01-15T10:30:00Z"
}
2. Obtener Todas las Recetas
GET /api/recetas

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439011",
        "tipo": "participante",
        "titulo": "Pasta Carbonara",
        "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
        "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
        "nombre": "María González",
        "temporada": 5,
        "fechaRegistro": "2024-01-15T10:30:00Z"
    },
    {
        "id": "507f1f77bcf86cd799439012",
        "tipo": "chef",
        "titulo": "Tiramisú",
        "ingredientes": ["queso mascarpone", "café", "galletas", "cacao"],
        "preparacion": "Montar capas de galletas empapadas en café con crema de mascarpone",
        "nombre": "Chef Giovanni",
        "temporada": 3,
        "fechaRegistro": "2024-01-14T15:45:00Z"
    }
]
3. Obtener Receta por ID
GET /api/recetas/{id}

Response (Éxito):

json
{
    "id": "507f1f77bcf86cd799439011",
    "tipo": "participante",
    "titulo": "Pasta Carbonara",
    "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
    "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
    "nombre": "María González",
    "temporada": 5,
    "fechaRegistro": "2024-01-15T10:30:00Z"
}
Response (No encontrado):

http
HTTP/1.1 404 Not Found
4. Obtener Recetas de Participantes
GET /api/recetas/participantes

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439011",
        "tipo": "participante",
        "titulo": "Pasta Carbonara",
        "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
        "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
        "nombre": "María González",
        "temporada": 5,
        "fechaRegistro": "2024-01-15T10:30:00Z"
    }
]
5. Obtener Recetas de Televidentes
GET /api/recetas/televidentes

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439013",
        "tipo": "televidente",
        "titulo": "Ensalada César",
        "ingredientes": ["lechuga", "pollo", "crutones", "salsa césar"],
        "preparacion": "Mezclar todos los ingredientes y añadir la salsa",
        "nombre": "Carlos Ruiz",
        "temporada": 4,
        "fechaRegistro": "2024-01-13T09:20:00Z"
    }
]
6. Obtener Recetas de Chefs
GET /api/recetas/chefs

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439012",
        "tipo": "chef",
        "titulo": "Tiramisú",
        "ingredientes": ["queso mascarpone", "café", "galletas", "cacao"],
        "preparacion": "Montar capas de galletas empapadas en café con crema de mascarpone",
        "nombre": "Chef Giovanni",
        "temporada": 3,
        "fechaRegistro": "2024-01-14T15:45:00Z"
    }
]
7. Obtener Recetas por Temporada
GET /api/recetas/temporada/{temporada}

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439011",
        "tipo": "participante",
        "titulo": "Pasta Carbonara",
        "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
        "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
        "nombre": "María González",
        "temporada": 5,
        "fechaRegistro": "2024-01-15T10:30:00Z"
    }
]
8. Buscar Recetas por Ingrediente
GET /api/recetas/buscar?ingrediente=pasta

Response:

json
[
    {
        "id": "507f1f77bcf86cd799439011",
        "tipo": "participante",
        "titulo": "Pasta Carbonara",
        "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta"],
        "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita",
        "nombre": "María González",
        "temporada": 5,
        "fechaRegistro": "2024-01-15T10:30:00Z"
    }
]
9. Actualizar Receta
PUT /api/recetas/{id}

Request Body:

json
{
    "id": "507f1f77bcf86cd799439011",
    "tipo": "participante",
    "titulo": "Pasta Carbonara Mejorada",
    "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta", "pimienta"],
    "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita y pimienta al gusto",
    "nombre": "María González",
    "temporada": 5,
    "fechaRegistro": "2024-01-15T10:30:00Z"
}
Response:

json
{
    "id": "507f1f77bcf86cd799439011",
    "tipo": "participante",
    "titulo": "Pasta Carbonara Mejorada",
    "ingredientes": ["pasta", "huevo", "queso parmesano", "panceta", "pimienta"],
    "preparacion": "Cocinar la pasta, mezclar con huevo y queso, añadir panceta frita y pimienta al gusto",
    "nombre": "María González",
    "temporada": 5,
    "fechaRegistro": "2024-01-15T10:30:00Z"
}
10. Eliminar Receta
DELETE /api/recetas/{id}

Response:

http
HTTP/1.1 204 No Content



## Swagger UI

La documentación interactiva de la API está disponible en:

## Swagger UI - Azure


http://mi-swagger-app-ghh2f2gdeqdqhyba.chilecentral-01.azurewebsites.net/
http://mi-swagger-app-ghh2f2gdeqdqhyba.chilecentral-01.azurewebsites.net/health
http://mi-swagger-app-ghh2f2gdeqdqhyba.chilecentral-01.azurewebsites.net/test
http://mi-swagger-app-ghh2f2gdeqdqhyba.chilecentral-01.azurewebsites.net/swagger-ui.html