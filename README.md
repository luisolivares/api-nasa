# Proyecto API REST - NASA

Este proyecto es un **microservicio Spring Boot 3** que consume la API pública de la NASA ([https://api.nasa.gov/](https://api.nasa.gov/)) para obtener la **Astronomy Picture of the Day (APOD)**.  
Se implementa el patrón **Circuit Breaker** mediante **Resilience4j** para manejar fallos en la conexión con APIs externas, garantizando resiliencia y alta disponibilidad.

---

## 📌 Tecnologías y herramientas utilizadas

- **Java 17**
- **Spring Boot 3**
- **Circuit Breaker** con **Resilience4j**
- **Swagger/OpenAPI** para documentación de endpoints
- **Actuator + Prometheus** para monitoreo y métricas
- **Docker** para contenerización
- API externa: [NASA API](https://api.nasa.gov/)

---

## 🗂 Controladores principales

### 1. NasaController
- **Ruta:** `/api/v1/nasa/apod`
- **Descripción:** Obtiene la Astronomy Picture of the Day (APOD) desde la API de la NASA.
- **Parámetros:**
  - `date` (opcional): Fecha específica (YYYY-MM-DD)
  - `start_date` (opcional): Fecha inicio para rango (YYYY-MM-DD)
  - `end_date` (opcional): Fecha fin para rango (YYYY-MM-DD)
  - `count` (opcional): Número de resultados aleatorios
  - `thumbs` (opcional): Incluir miniaturas de videos (true/false)
  - `api_key` (obligatorio): Clave de API de la NASA
- **Respuesta:** JSON con información de la imagen astronómica (`ApodResponse`)

**Ejemplo de JSON de respuesta:**
```json
{
  "date": "2025-10-05",
  "title": "La Vía Láctea desde Chile",
  "explanation": "Una vista espectacular de la Vía Láctea desde el Observatorio Paranal...",
  "url": "https://apod.nasa.gov/apod/image/2510/milkyway_chile.jpg",
  "hdurl": "https://apod.nasa.gov/apod/image/2510/milkyway_hd.jpg",
  "media_type": "image",
  "service_version": "v1"
}
```

---

### 2. HealthzController
- **Ruta:** `/api/v1/healthz`
- **Descripción:** Indica la disponibilidad y estado operativo del API REST.
- **Respuesta:**
```json
{
  "API": "nasa",
  "Descripción": "Proyecto API REST donde nos conectaremos a la API REST externa de la NASA",
  "Project version": "0.0.1-SNAPSHOT",
  "Java version": "17"
}
```

---

## 🚀 Levantar el proyecto

### 1️⃣ Levantar localmente (con Maven)

1. Compilar el proyecto:
```bash
mvn clean install
```

2. Ejecutar el proyecto:
```bash
mvn spring-boot:run
```

El servicio estará disponible en:  
```
http://localhost:8080
```

- Swagger UI:  
```
http://localhost:8080/swagger-ui.html
```

- Endpoint de healthz:  
```
http://localhost:8080/api/v1/healthz
```

---

### 2️⃣ Levantar con Docker

1. Construir la imagen Docker:
```bash
docker build -t nasa-api:1.0 .
```

2. Ejecutar el contenedor:
```bash
docker run -d -p 8080:8080 --name nasa-api-container nasa-api:1.0
```

3. Verificar contenedor en ejecución:
```bash
docker ps
```

---

### 3️⃣ Detener el contenedor Docker

```bash
docker stop nasa-api-container
docker rm nasa-api-container
```

---

## 📊 Monitoreo y métricas

- **Spring Boot Actuator** expone los endpoints:
  - `/actuator/health`
  - `/actuator/metrics`
  - `/actuator/circuitbreakers`
  - `/actuator/prometheus` (para integración con Prometheus)

- **Circuit Breaker**:
  - Configurado con Resilience4j para proteger la comunicación con la API de NASA.
  - Parametrizable desde `application.properties`:
    ```properties
    resilience4j.circuitbreaker.instances.nasaApi.slidingWindowSize=10
    resilience4j.circuitbreaker.instances.nasaApi.failureRateThreshold=50
    resilience4j.circuitbreaker.instances.nasaApi.waitDurationInOpenState=5s
    ```

---

## 📝 Notas adicionales

- Este proyecto es **extensible** para consumir otras APIs de la NASA o cualquier API externa.
- La documentación Swagger genera automáticamente ejemplos de **request y response JSON** gracias a las anotaciones `@Schema` en las clases `ApodRequest` y `ApodResponse`.
- Se recomienda configurar una **API Key válida** de [https://api.nasa.gov/](https://api.nasa.gov/) para pruebas reales.

---

## 📋 Ejemplos de uso con cURL

**Obtener APOD de un día específico:**
```bash
curl -X GET "http://localhost:8080/api/v1/nasa/apod?date=2025-10-05&api_key=DEMO_KEY" -H "accept: application/json"
```

**Obtener múltiples resultados aleatorios:**
```bash
curl -X GET "http://localhost:8080/api/v1/nasa/apod?count=3&api_key=DEMO_KEY" -H "accept: application/json"
```

**Ver estado del API:**
```bash
curl -X GET "http://localhost:8080/api/v1/healthz" -H "accept: application/json"
```

