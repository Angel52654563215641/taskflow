# TaskFlow — aplicación web contenerizada

Aplicación académica para crear, consultar, editar, filtrar y eliminar tareas. Tecnologías: Java 21, Spring Boot 3.5.6, PostgreSQL 16, HTML/CSS/JavaScript, Docker y Docker Compose.

## Arquitectura
- `application`: aplicación web + API REST, puerto 8080.
- `database`: PostgreSQL, puerto interno 5432.
- `taskflow_network`: red privada Docker.
- `taskflow_data`: volumen persistente de la base de datos.
- Para publicación: servicio Docker en un host y HTTPS/TLS administrado por el proveedor.

## Ejecutar en Windows
Requisito: Docker Desktop instalado y abierto. No necesitas instalar Java ni PostgreSQL en Windows.

Abre PowerShell dentro de esta carpeta y ejecuta:

```powershell
Copy-Item .env.example .env
notepad .env
docker compose up -d --build
docker compose ps
```

Abre:
- Aplicación: http://localhost:8080
- Salud: http://localhost:8080/api/health
- API: http://localhost:8080/api/tasks

Comandos útiles:

```powershell
docker compose logs -f
docker compose down
docker compose up -d --build
```

**No ejecutes `docker compose down -v`** salvo que quieras borrar también el volumen y las tareas guardadas.

## Variables de entorno
`DB_NAME`, `DB_USER`, `DB_PASSWORD`, `SERVER_PORT`, `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `SPRING_JPA_HIBERNATE_DDL_AUTO`. Los valores de ejemplo son solo locales. En el host usa secretos privados y no subas credenciales a GitHub.

## API REST
- GET `/api/health`
- GET `/api/tasks`
- POST `/api/tasks`
- PUT `/api/tasks/{id}`
- DELETE `/api/tasks/{id}`

Estados: `PENDIENTE`, `EN PROCESO`, `TERMINADA`.

JSON de ejemplo:
```json
{"title":"Preparar reporte","description":"Documentar despliegue","status":"PENDIENTE"}
```

## Publicación en host
El archivo `render.yaml` es una plantilla orientativa. Sube el proyecto a GitHub, crea PostgreSQL y un servicio web Docker en el proveedor, configura URL JDBC, usuario, contraseña y puerto con los valores reales del panel, despliega y valida `/api/health`. Usa el hostname privado de la base de datos; no expongas PostgreSQL a Internet. Comprueba que el proveedor asigne el subdominio y habilite TLS/HTTPS. Los planes y sus límites pueden cambiar, así que verifica las condiciones vigentes antes de crear recursos.

La publicación pública, URL, certificado y capturas todavía deben obtenerse desde una cuenta real. No se deben reportar como completados antes de verificarlos.
