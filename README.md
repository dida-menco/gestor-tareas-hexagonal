# 📋 Gestor de Tareas Personales (Arquitectura Hexagonal)

¡Hola! Este proyecto es una aplicación web fullstack para la gestión de tareas personales (crear, listar, completar y eliminar). Ha sido desarrollada aplicando los principios de la **Arquitectura Hexagonal (Puertos y Adaptadores)**, **Código Limpio (Clean Code)** y principios **SOLID**.

---

## 🛠️ Tecnologías Utilizadas

- **Backend:** Java 17, Spring Boot
- **Frontend:** React (con Vite) y Axios
- **Base de Datos:** H2 Database (en memoria)
- **Control de Versiones:** Git y GitHub

---

## 🏗️ Arquitectura del Backend (Hexagonal)

El código fuente del backend está organizado en tres capas estrictamente desacopladas dentro del paquete `com.gestor.backend`:

1. **Dominio (`domain`):** 
   - Contiene el modelo puro del negocio (`Tarea.java`) y los contratos/puertos (`TareaUseCase.java` para entrada y `TareaRepositoryPort.java` para salida). No depende de ningún framework externo.
2. **Aplicación (`application`):** 
   - Implementa los casos de uso del negocio (`TareaService.java`). Coordina las operaciones consumiendo los puertos de entrada y salida mediante **Inversión de Dependencias (DIP)**.
3. **Infraestructura (`infrastructure`):** 
   - Contiene los adaptadores de entrada (`TareaController.java` para la API REST) y los adaptadores de salida (`TareaPersistenceAdapter.java` y `TareaJpaRepository.java` para la persistencia en H2).

---

## 💡 Aplicación de Principios SOLID y Código Limpio

- **Responsabilidad Única (SRP):** El controlador se encarga únicamente de las peticiones HTTP, el servicio administra la lógica de negocio y el adaptador se encarga de la base de datos.
- **Inversión de Dependencias (DIP):** El núcleo del negocio (`TareaService`) depende de la abstracción (`TareaRepositoryPort`), nunca de implementaciones concretas de JPA o H2.
- **Código Limpio:** Métodos y variables autodescriptivos, funciones cortas y separación total entre la entidad de dominio y la entidad JPA.

---

## 🚀 Cómo Ejecutar el Proyecto Localmente

### 1. Clonar el repositorio
```bash
git clone [https://github.com/dida-menco/gestor-tareas-hexagonal.git](https://github.com/dida-menco/gestor-tareas-hexagonal.git)
cd gestor-tareas-hexagonal
