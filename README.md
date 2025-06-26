# 🏀 Court-Service

**Court-Service** es un microservicio desarrollado en Java con Spring Boot, enfocado en la gestión de canchas de baloncesto a nivel mundial. Utiliza datos geográficos proporcionados por la API de GeoNames para estructurar jerárquicamente los países, regiones y ciudades, y se apoya en la API de Google Places para obtener información detallada sobre las canchas.

## 🚀 Tecnologías utilizadas

- 🧰 **Spring Boot**
- 🌱 **Spring Web**
- 🧾 **Lombok**
- 🍃 **MongoDB**
- 🌍 **GeoNames API**
- 📍 **Google Places API**

## 🧠 Funcionalidad

- Consulta y persistencia de **lugares geográficos** (países, regiones y ciudades) usando GeoNames.
- Obtención y almacenamiento de **canchas de baloncesto** desde Google Places API.
- Asociación de las canchas con el identificador de ciudad correspondiente en la base de datos.
- Base de datos precargada con todos los países y subdivisiones **First Order** y **Second Order** de GeoNames.

## 🗃️ Estructura del proyecto

- `controller/`: Endpoints expuestos del microservicio.
- `service/`: Lógica de negocio para manejo de lugares y canchas.
- `repository/`: Interfaces para persistencia con MongoDB.
- `model/`: Modelos de datos (documentos MongoDB).
- `dto/`: Objetos de transferencia de datos.
- `client/`: Consume la API de geonames para guardar todos los paises, regiones y ciudades.

## 💾 Base de datos

Se utiliza MongoDB para almacenar:

- Jerarquía de lugares geográficos (país → región → ciudad).
- Información de las canchas importadas desde Google Places.
  
Se proveerá un **backup** completo de la base de datos en este repositorio para facilitar la carga inicial de datos.

## 📌 Endpoints principales

> Nota: Para ejecutar correctamente estos endpoints, es necesario configurar las claves de acceso a las APIs externas en el archivo `application.properties`.

| Método | Endpoint                                               | Descripción                                                                 |
|--------|--------------------------------------------------------|-----------------------------------------------------------------            |
| GET    | `/geo/country/{geonameId}`                             | Obtener un pais.                                                            |
| GET    | `/geo/country/all`                                     | Obtener todos los paises registrados.                                       |
| GET    | `/geo/ordersAdministrative/firstOrderOfCountry`        | Obtener todos los firstOrder de un pais con el id del pais.                 |
| GET    | `/geo/ordersAdministrative/secondOrderOfCountry`       | Obtener todas las ciudades de una region con el id de la region y el pais.  |
| GET    | `/locations/cities/{geonameId}`                        | Obtener ciudades bajo un `geonameId`.                                       |

## 🔐 Requisitos previos

- Tener MongoDB en ejecución local o en la nube.

Configurar tus credenciales en `application.properties`:

```properties
  data:
    mongodb:
      uri: ${MONGO_ATLAS_URL}
      database: ${DB_NAME}

  userGeoname: ${USER_GEONAME}
