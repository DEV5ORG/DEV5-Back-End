# DEV5 - Back-End

## 🧑‍💻 Equipo de Desarrollo
- Douglas Conejo Cascante  
- Elizabeth Sequeira Suárez  
- Steven Montoya Calderón  
- Andrés Quesada Arias  
- Diego Fiatt Vargas  

## 📌 Descripción del Proyecto
Organizar un evento es un proceso fragmentado y poco eficiente. Actualmente, los usuarios deben buscar, contactar y coordinar con múltiples proveedores de manera independiente, lo que genera una gran inversión de tiempo y esfuerzo. Además, la falta de una plataforma centralizada dificulta la comparación de precios, disponibilidad y calidad del servicio, lo que puede llevar a experiencias insatisfactorias o a sobrecostos inesperados.

La solución propuesta es una aplicación que centraliza todos los servicios necesarios para la organización de eventos. A través de esta plataforma, los usuarios podrán buscar lugares según su presupuesto y número de invitados, reservar espacios con fecha y hora específica, y contratar servicios como comida y entretenimiento.

---

## ⚙️ Tecnologías Utilizadas

- **Lenguaje:** Java  
- **Framework:** Spring Boot  
- **Base de Datos:** P4Admin  
- **Gestión de dependencias:** Maven  
- **Control de versiones:** Git

---

## 🏗️ Arquitectura del Sistema

![Diagrama de Arquitectura](https://github.com/user-attachments/assets/099ea7bf-ab1f-46db-bbb8-1b3740ccb04e)

---

## 🗃️ Diseño de la Base de Datos

![Base de Datos](https://github.com/user-attachments/assets/ed6c6cb6-002d-4a3d-a1b1-f40e750a1a20)

---

## ✅ Requisitos Iniciales

### Funcionales

#### 👤 Gestión de Usuarios y Autenticación
- Registro, inicio de sesión y recuperación de contraseña.
- Gestión de perfil de usuario.

#### 📅 Gestión de Eventos
- Crear eventos con detalles como nombre, fecha, lugar, servicios, imágenes, descripción y categoría.
- Filtrado y búsqueda de eventos.
- Vista detallada para clientes y administradores.

#### 📆 Sistema de Reservas
- Selección de fecha y hora.
- Validación de disponibilidad.
- Confirmaciones, historial y carrito de reservas.
- Generación y compartición de invitaciones tras el pago.

### No Funcionales

#### 🚀 Rendimiento
- Carga inicial < 3 segundos.
- Búsqueda < 2 segundos.
- Soporte para 1000 usuarios concurrentes.

#### 🔐 Seguridad
- Contraseñas con hash seguro.
- Rutas protegidas.

#### 🎯 Usabilidad y Compatibilidad
- Cualquier sistema operativo.
- Compatible con iOS 13+ y Android 8.0+.
---
## 🚀 Instalación y configuración

Sigue estos pasos para configurar y ejecutar la aplicación en tu entorno:

### 1️⃣ Clonar el repositorio

### 2️⃣ Instalar Spring Boot

- Seleccionamos la opción que marca la flecha de color amarillo

 #### Opción 1️⃣   
  
![image](https://github.com/user-attachments/assets/d0b71394-aab8-4407-b401-97e203bc4c88)

- Agregamos la opción de Spring boot

![image](https://github.com/user-attachments/assets/ffbdafff-c692-4199-9e97-7d30f0c2821a)



![image](https://github.com/user-attachments/assets/70813463-762e-4f0a-8766-9b76830ffb5a)

- Agregamos los datos como salen en la imagen, y por último seleccionamos Apply

  ![image](https://github.com/user-attachments/assets/6fa46c69-1299-4ec2-aa6a-2433fe1469cf)

 #### Opción 2️⃣ 

 - Nos vamos al pom.xml de nuestro proyecto clonado o descargado

![image](https://github.com/user-attachments/assets/f3e1863b-f0a6-4a01-acc6-741faaed7329)

- Seleccionamos click derecho en el editor de código
- Escogemos la opción de Maven
- Seleccionamos la opción Sync Project para descargar las dependencias e instalar Spring Boot

![image](https://github.com/user-attachments/assets/d73ae5df-b65f-4807-be43-8f8b99015243)

### 3️⃣ Configurar el entorno de Base de datos 

- Instalamos el motor de la base de datos [Descargar aquí](https://www.pgadmin.org/download/)
  
- Configuramos el application.properties, aca hay un ejemplo
```sh
spring.application.name=Nombre_de_Organizacion
spring.datasource.url=jdbc:postgresql://localhost:8000/Tu_Base_De_Datos_En_P4Admin
spring.datasource.username= Tu_Username
spring.datasource.password=Tu_Contraseña
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```




