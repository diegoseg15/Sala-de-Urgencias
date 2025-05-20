# 🏥 Sistema de Triaje de Pacientes - Microservicios en Spring Boot

Este proyecto implementa una **API REST** basada en **microservicios** para la gestión del triaje en una sala de urgencias. Utiliza mensajería con **RabbitMQ** (Topic Exchange) para distribuir pacientes según su gravedad a distintos consumidores.

## 🧩 Microservicios

El sistema se compone de tres microservicios:

- **`urgencias`**: Registro de pacientes (productor de mensajes)
- **`atencion`**: Atención médica (consumidores separados por grado)
- **`supervisor`**: Auditoría de atención (consumidor global)

## 🩺 Entidad Paciente

Cada paciente contiene la siguiente información:

- `dni`: Identificador único
- `nombre`, `apellidos`, `género`
- `grado`: leve, grave, crítico
- `estado`: espera, consulta, alta

## ⚙️ Funcionalidades

- Registro y consulta de pacientes
- CRUD completo vía API REST
- Filtros por DNI, grado y estado
- Envío de pacientes vía RabbitMQ a:
  - `pacientes.leve`
  - `pacientes.grave`
  - `pacientes.critico`
  - `pacientes.*` (supervisor)
- Simulación de tiempo de atención (aleatorio)
- Seguridad con JWT (en endpoints protegidos)
- Documentación generada automáticamente con **OpenAPI**

## 🔄 Comunicación entre servicios

Los servicios se comunican mediante un **exchange de tipo Topic** en RabbitMQ:

```
       +------------+
       | URGENCIAS  |  ---[pacientes.leve]---> ATENCION LEVE
       | (productor)|  ---[pacientes.grave]--> ATENCION GRAVE
       +------------+  ---[pacientes.critico]-> ATENCION CRÍTICA
              |
              +-------[pacientes.*]----------> SUPERVISOR
```

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web, Spring AMQP, Spring Security
- RabbitMQ (Topic Exchange)
- Maven
- OpenAPI / Swagger
- Postman (para pruebas)

## 🚀 Despliegue local

```bash
# Clonar el proyecto
git clone https://github.com/tu-usuario/triage-system.git
cd triage-system

# Lanzar los servicios (uno por uno o con múltiples terminales)
cd urgencias && ./mvnw spring-boot:run
cd ../atencion && ./mvnw spring-boot:run
cd ../supervisor && ./mvnw spring-boot:run
````

También puedes usar los perfiles para facilitar el despliegue individual con `--spring.profiles.active=dev`.

## 📚 Documentación

Cada microservicio incluye documentación Swagger disponible en:

* `http://localhost:8080/swagger-ui.html` (urgencias)
* `http://localhost:8081/swagger-ui.html` (atencion)
* `http://localhost:8082/swagger-ui.html` (supervisor)

## 📂 Estructura del proyecto

```
triage-system/
├── urgencias/
├── atencion/
├── supervisor/
├── README.md
└── .gitignore
```

## ✅ Estado del proyecto

✔ Registro de pacientes
✔ Envío a colas por grado
✔ Simulación de atención
✔ Registro por parte del supervisor
✔ CRUD y filtrado por estado
✔ Documentación y JWT

## 📄 Licencia

Este proyecto está bajo licencia MIT.
