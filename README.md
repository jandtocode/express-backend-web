# Express Jandtocode - Portal de Transporte

Backend de pruebas pensado para **práctica de QA**.

La idea no es automatizar ni crear robots porque sí, sino **observar el comportamiento de la aplicación, encontrar errores y desarrollar criterio técnico** para decidir qué probar y por qué. La aplicación cumple con su funcionalidad, pero **tiene fallas**, y encontrarlas es parte del ejercicio.

## ¿Qué hace la aplicación?

- Crear usuarios
- Iniciar sesión
- Bienvenida Dashboard (dashboard por defecto)
- Consultar el saldo de la tarjeta
- Realizar recargas, con cálculo de bonos

---

## Instalación

### Requisitos previos

- **Docker** (Docker Desktop en Windows y Mac, Docker Engine con el plugin Compose en Linux)
- **Java 21**
- Un IDE (IntelliJ IDEA u otro)

PostgreSQL **no** hay que instalarlo: corre dentro de Docker.

### 1. Crear el archivo `.env`

El `.env` no viene en el repositorio, así que **es obligatorio crearlo**. Debe quedar en la raíz del proyecto, junto a `compose.yaml`:

```env
DB_USERNAME=customer
DB_PASSWORD=abc123
DB_NAME=expressDB
DB_HOST=localhost
DB_PORT=5432
```

> Estos valores son solo de práctica. No los uses en un entorno real.

Si ya tienes un PostgreSQL usando el puerto 5432, cambia `DB_PORT` (por ejemplo a `5433`).

### 2. Levantar la base de datos con Docker

El archivo `compose.yaml` existe únicamente para levantar PostgreSQL, porque no está instalado en la máquina. **La base de datos queda vacía**: las tablas y los datos de prueba se cargan en el paso siguiente.

Desde la raíz del proyecto (el comando es igual en Windows, Mac y Linux):

```bash
docker compose up -d
```

> En Windows y Mac, Docker Desktop debe estar abierto. En Linux puede requerir `sudo`. Si tu versión es antigua, el comando es `docker-compose up -d`.

Para comprobar que está corriendo:

```bash
docker ps
```

Debe aparecer el contenedor `postgres-express`.

Otros comandos útiles:

```bash
docker compose down        # apaga la base de datos (conserva los datos)
docker compose down -v     # apaga y BORRA los datos (queda vacía de nuevo)
```

### 3. Cargar las tablas y los usuarios de prueba

El script `src/main/resources/database/expressDB.sql` crea las tablas y los usuarios de prueba. Ejecútalo con el comando de tu sistema, desde la raíz del proyecto:

**Mac, Linux, Git Bash:**
```bash
docker exec -i postgres-express psql -U customer -d expressDB < src/main/resources/database/expressDB.sql
```

**Windows (CMD):**
```bat
docker exec -i postgres-express psql -U customer -d expressDB < src\main\resources\database\expressDB.sql
```

**Windows (PowerShell):**
```powershell
Get-Content src\main\resources\database\expressDB.sql | docker exec -i postgres-express psql -U customer -d expressDB
```

**Alternativa con cliente gráfico** (DBeaver, pgAdmin o IntelliJ): conéctate con host `localhost`, puerto `5432`, base `expressDB`, usuario `customer`, contraseña `abc123`, y ejecuta el contenido del script.

Para verificar que cargó:

```bash
docker exec -it postgres-express psql -U customer -d expressDB -c "SELECT identification, name FROM user_express;"
```

Debes ver 7 usuarios.

> El script se puede ejecutar las veces que quieras: borra y recrea las tablas. Sirve para **reiniciar los datos** después de tus pruebas.

**Usuarios de prueba** (contraseña de todos: `pass123`):

| Identificación | Escenario |
|---|---|
| 1001, 1002 | Usuarios nuevos (0 recargas) |
| 1003 | 5 recargas |
| 1004 | 10 recargas |
| 1005 | 9 recargas |
| 1006 | Bloqueado |
| 1007 | 2 intentos fallidos |

### 4. Ejecutar la aplicación

Desde tu IDE, ejecuta `ExpressApplication.java`, o desde la terminal:

```bash
./mvnw spring-boot:run        # Mac y Linux
mvnw.cmd spring-boot:run      # Windows
```

La API queda disponible en `http://localhost:8080`.

Para detener la aplicación, presiona `Ctrl + C` en la terminal (también en Mac, donde es la tecla `control` y no `cmd`). Si la ejecutaste desde el IDE, usa el botón de detener.

> Detener la aplicación no apaga la base de datos. Para apagarla, usa `docker compose down` (conserva los datos) o `docker compose down -v` (los borra).

---

## Endpoints

Todas las rutas empiezan con `/api`. La autenticación es por **sesión**: los endpoints del dashboard requieren haber hecho login antes.

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/auth/register` | Registra un usuario nuevo |
| POST | `/api/auth/login` | Inicia sesión |
| GET | `/api/dashboard` | Dashboard por defecto |
| GET | `/api/dashboard/user` | Consulta el saldo del usuario |
| POST | `/api/dashboard/recharge/calculate` | Calcula una recarga y su bono |
| PATCH | `/api/dashboard/recharge/final` | Confirma la recarga calculada |

---

## Más información

La documentación completa (caminos de uso, campos, ejemplos y respuestas de error) está en Swagger. Con la aplicación corriendo:

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **Contrato JSON:** http://localhost:8080/v3/api-docs