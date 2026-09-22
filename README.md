# Express Jandtocode - Portal de Transporte

API REST para gestión de transporte con autenticación de usuarios.

## Requisitos Previos

- Docker y Docker Compose instalados
- Java 21+
- IntelliJ IDEA (o tu IDE preferido)
- PostgreSQL (vía Docker)

## Configuración Inicial

### 1. Levantar PostgreSQL con Docker

```bash
docker-compose up -d
```

Esto crea el contenedor `postgres-express` con la BD `expressDB`.

### 2. Ejecutar la Aplicación

Abre el proyecto en IntelliJ y ejecuta:

```bash
mvn spring-boot:run
```

O simplemente ejecuta `ExpressApplication.java` desde el IDE.

La API estará disponible en: `http://localhost:8080`

---

## Uso del Proyecto

### Primera Vez

1. `docker-compose up -d` - Levanta PostgreSQL
2. Ejecuta la app desde IntelliJ
3. Prueba los endpoints en Postman

### Cambios en Código

1. Realiza los cambios
2. Ejecuta la app nuevamente desde IntelliJ
3. Los cambios en la BD se aplican automáticamente

### Si Algo Falla o Quieres Empezar de Cero

Elimina el volumen y crea todo nuevamente:

```bash
docker-compose down -v
docker-compose up -d
```

Luego ejecuta la app en IntelliJ.

---

## Endpoints

### Login

**POST** `/auth/login`

Request:
```json
{
  "identification": "123456789",
  "password": "password123"
}
```

Response (exitoso):
```json
{
  "success": true,
  "message": "Login exitoso",
  "name": "Juan",
  "lastName": "Pérez"
}
```

## Documentación API

Swagger disponible en: `http://localhost:8080/swagger-ui.html`