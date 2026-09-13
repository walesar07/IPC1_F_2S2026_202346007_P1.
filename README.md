# Sistema de Gestión de Refugio Animal y Adopciones

- **Curso:** Introducción a la Programación y Computación 1 - Sección "F"[cite: 2]
- **Estudiante:** Valeska Jael Aparicio[cite: 2]
- **Carné:** 202346007[cite: 2]
- **Fecha de entrega:** 12/09/2026[cite: 2]

---

## 📌 Descripción General del Sistema
Aplicación de escritorio desarrollada en Java con interfaz gráfica en Java Swing (programada por código), diseñada para optimizar y controlar las operaciones de un centro de rescate animal mediante arreglos estáticos, matrices y persistencia en archivos de texto/CSV[cite: 1, 2].

## 🚀 Funcionalidades Principales

1. **Módulo de Autenticación:** 
   - Control de acceso local mediante un sistema de inicio de sesión con credenciales de usuario administrador o auxiliar cargadas desde archivo[cite: 1, 2].

2. **Módulo de Animales Rescatados:** 
   - Registro, búsqueda, actualización de estado clínico/salud y eliminación lógica de perros y gatos ingresados al refugio[cite: 1, 2]. Incluye validación estricta para evitar códigos duplicados.

3. **Módulo de Adoptantes:** 
   - Gestión completa de los registros de posibles adoptantes (DPI, nombre, teléfono, dirección y correo electrónico) almacenados de forma estructurada[cite: 1, 2].

4. **Módulo de Solicitudes y Adopciones:** 
   - Creación y administración de solicitudes de adopción asociando a un adoptante con un animal, permitiendo actualizar dinámicamente sus estados (*Pendiente*, *Aprobada*, *Rechazada*)[cite: 1, 2].

5. **Módulo de Rescates Urgentes:** 
   - Registro de reportes ciudadanos de animales en situación de calle, asignación de niveles de prioridad y seguimiento de atención de casos[cite: 1, 2].

6. **Panel de Ubicaciones (Matriz del Refugio):** 
   - Representación visual e interactiva basada en una matriz bidimensional (`EspacioRefugio[][]`) donde las filas representan áreas y las columnas representan jaulas, permitiendo verificar disponibilidad y asignar o liberar espacios de forma controlada[cite: 1, 2].

7. **Generación de Reportes HTML:** 
   - Exportación automática de reportes web con estilos básicos y marcas de tiempo para animales, adoptantes, solicitudes, rescates y ocupación del refugio[cite: 1, 2].

---
Puedes consultar la documentación detallada, diagramas de arquitectura y bitácora de pruebas en el archivo PDF adjunto en este repositorio[cite: 1, 2].
