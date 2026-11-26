# Guía para completar la evidencia

## 1. Ejecución local
1. Instala Docker Desktop para Windows y ábrelo.
2. Descomprime el ZIP.
3. Abre PowerShell dentro de la carpeta `TaskFlow_Proyecto`.
4. Ejecuta `docker compose up -d --build`.
5. Revisa `docker compose ps`.
6. Abre `http://localhost:8080` y `http://localhost:8080/api/health`.
7. Crea, edita y elimina tareas. Guarda capturas de pantalla.

## 2. Persistencia
Ejecuta `docker compose down` y luego `docker compose up -d`. Comprueba que las tareas sigan guardadas. No uses `down -v`, porque elimina los datos.

## 3. Publicación
Sube el código a GitHub. Crea PostgreSQL y un servicio Docker en el proveedor. Configura las variables de entorno con las credenciales reales del panel. Despliega, abre la URL pública, valida `/api/health` y comprueba HTTPS en el navegador.

## 4. Evidencias que debes agregar al reporte
- Docker Desktop con los dos servicios activos.
- Resultado de `docker compose ps`.
- Interfaz con tareas de ejemplo.
- Pruebas de crear, editar y eliminar.
- Logs de conexión.
- Panel del host con el servicio publicado.
- URL pública y respuesta de `/api/health`.
- Certificado HTTPS válido.
- Prueba de persistencia después de reiniciar.

No afirmes que la aplicación está publicada ni que HTTPS está verificado hasta contar con esas evidencias.
