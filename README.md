[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9WoPPVkS)

# [Título del Proyecto: Debe ser descriptivo y reflejar el propósito o la solución]

**Curso:** CS 2031 Desarrollo Basado en Plataforma  
**Entrega:** Semana 9 - Culminación del Backend

## Nombres de los Integrantes
| Nombre Completo                                                               | Código     |
|:------------------------------------------------------------------------------|:-----------|
| Miguel Angel de Santa Maria Villena Bustamante                                | 202510702  |
| Edinson Brando Ricardo Vasquez Mamani                                         | 202510680  |
| John Dayron Blas Huete                                                        | 202510794  |
| Daniel Mateo Dongo Triveño                                                    | 202310506  | 
| Guillermo Arturo Heredia Cadenas                                              | 202410219  |

---

## Índice
---

## Índice / Tabla de Contenidos
- [\[Título del Proyecto: Debe ser descriptivo y reflejar el propósito o la solución\]](#título-del-proyecto-debe-ser-descriptivo-y-reflejar-el-propósito-o-la-solución)
  - [Nombres de los Integrantes](#nombres-de-los-integrantes)
  - [Índice](#índice)
  - [Índice / Tabla de Contenidos](#índice--tabla-de-contenidos)
  - [1. Introducción](#1-introducción)
    - [Contexto](#contexto)
    - [Objetivos del Proyecto](#objetivos-del-proyecto)
  - [2. Identificación del Problema o Necesidad](#2-identificación-del-problema-o-necesidad)
    - [Descripción del Problema](#descripción-del-problema)
    - [Justificación](#justificación)
  - [3. Descripción de la Solución](#3-descripción-de-la-solución)
    - [Funcionalidades Implementadas](#funcionalidades-implementadas)
    - [Tecnologías Utilizadas](#tecnologías-utilizadas)
  - [4. Modelo de Entidades](#4-modelo-de-entidades)
    - [Diagrama de Entidades](#diagrama-de-entidades)
    - [Descripción de Entidades](#descripción-de-entidades)
  - [5. Testing y Manejo de Errores](#5-testing-y-manejo-de-errores)
    - [Niveles de Testing Realizados](#niveles-de-testing-realizados)
    - [Resultados](#resultados)
    - [Manejo de Errores](#manejo-de-errores)
  - [6. Medidas de Seguridad Implementadas](#6-medidas-de-seguridad-implementadas)
    - [Seguridad de Datos](#seguridad-de-datos)
    - [Prevención de Vulnerabilidades](#prevención-de-vulnerabilidades)
  - [7. Eventos y Asincronía](#7-eventos-y-asincronía)
  - [8. GitHub \& Management](#8-github--management)
  - [9. Conclusión](#9-conclusión)
  - [10. Apéndices](#10-apéndices)
    - [Licencia](#licencia)
  - [11. Referencias](#11-referencias)

--

## 1. Introducción

### Contexto
[Describir de forma clara y estructurada el contexto general en el que surge la necesidad o el problema que se va a resolver con la aplicación backend.]

### Objetivos del Proyecto
* **Objetivo General:** [Detallar el objetivo principal del proyecto.]
* **Objetivos Específicos:**
    * [Objetivo específico 1]
    * [Objetivo específico 2]
    * [Objetivo específico 3]

---

## 2. Identificación del Problema o Necesidad

### Descripción del Problema
[Explicar en detalle cuál es el problema técnico o la necesidad del mercado específico que el proyecto busca abordar con su implementación.]

### Justificación
[Argumentar de manera sólida por qué es relevante y prioritario dar solución a este problema o satisfacer esta necesidad identificada en el mercado actual.]

---

## 3. Descripción de la Solución

### Funcionalidades Implementadas
[Listar y describir las funcionalidades principales construidas en el backend, detallando explícitamente cómo cada una de ellas contribuye directamente a mitigar o solucionar el problema identificado.]

### Tecnologías Utilizadas
* **Lenguaje de Programación:** [Ej. Java]
* **Framework Principal:** [Ej. Spring Boot]
* **Persistencia de Datos:** [Ej. Spring Data JPA, Hibernate]
* **Bases de Datos:** [Ej. PostgreSQL, MySQL]
* **APIs Externas / Herramientas:** [Mencionar si integraron servicios de terceros o librerías clave como Lombok]

---

## 4. Modelo de Entidades

### Diagrama de Entidades
[Insertar aquí la imagen o link de tu diagrama de clases o Entidad-Relación de la base de datos]
![Diagrama Entidad-Relación](path/to/diagram.png)

### Descripción de Entidades
[Explicar detalladamente las entidades principales del negocio, detallando sus atributos más representativos y los tipos de relaciones implementadas entre ellas, por ejemplo: @OneToMany, @ManyToOne, @ManyToMany, indicando las configuraciones de cascade types o fetch types optimizados.]

---

## 5. Testing y Manejo de Errores

### Niveles de Testing Realizados
[Describir los niveles de prueba automatizados que se incorporaron al proyecto para asegurar la calidad de la entrega, tales como pruebas unitarias de repositorios con `@DataJpaTest`, de servicios con `Mockito` o pruebas de integración de controladores utilizando `@WebMvcTest`/`@SpringBootTest` con `MockMvc` o `TestContainers`.]

### Resultados
[Resumir cuantitativa o cualitativamente los resultados arrojados por las pruebas, detallando cuáles fueron los principales errores o fallos lógicos encontrados y cómo se corrigieron con éxito.]

### Manejo de Errores
[Explicar en términos generales el diseño de excepciones globales utilizadas a través de un `@ControllerAdvice`/`@RestControllerAdvice` y la importancia que tiene centralizar las excepciones de dominio y de infraestructura para garantizar respuestas estandarizadas al cliente.]

---

## 6. Medidas de Seguridad Implementadas

### Seguridad de Datos
[Explicar detalladamente las técnicas adoptadas para garantizar el resguardo de la información sensible, como el uso de Spring Security para asegurar los endpoints de la aplicación, el algoritmo BCrypt para la encriptación de contraseñas y el estándar de tokens JWT para el proceso de login y gestión de roles.]

### Prevención de Vulnerabilidades
[Describir los mecanismos establecidos de forma nativa en el backend o por configuración manual para prevenir ataques informáticos comunes y fallos de seguridad críticos, como Inyección SQL, Cross-Site Scripting (XSS) y Cross-Site Request Forgery (CSRF).]

---

## 7. Eventos y Asincronía
[Detallar meticulosamente qué eventos del sistema fueron utilizados (ej. registro de usuario, transacciones bancarias), explicar la relevancia de implementarlos bajo la arquitectura orientada a eventos para desacoplar componentes y justificar técnicamente los motivos por los cuales el procesamiento (como envío de correos HTML o notificaciones) debe ser asincrónico mediante `@Async`.]

---

## 8. GitHub & Management
[Describir con precisión la manera en que el equipo organizó el flujo de trabajo utilizando herramientas de gestión como GitHub Projects o GitHub Issues, detallando la asignación de tareas, etiquetas (labels) y cumplimiento de deadlines.]

[Asimismo, detallar el uso de GitHub Actions e ilustrar el flujo de integración continua (CI/CD) implementado de manera particular para compilar, probar o desplegar el backend.]

---

## 9. Conclusión

* **Logros del Proyecto:** [Resumir los hitos alcanzados y el impacto de la solución técnica backend diseñada frente al problema originalmente planteado.]
* **Aprendizajes Clave:** [Reflexionar sobre los conocimientos conceptuales o procedimentales más significativos obtenidos por los integrantes del equipo durante el proceso de desarrollo.]
* **Trabajo Futuro:** [Proponer y sugerir posibles mejoras de software, refactorizaciones arquitectónicas o nuevas extensiones funcionales para el sistema a mediano o largo plazo.]

---

## 10. Apéndices

### Licencia
Este proyecto se distribuye bajo la licencia [Especificar el tipo de licencia, ej. MIT License, Apache License 2.0, etc.].

---

## 11. Referencias
* [Referencia Bibliográfica 1 o Enlace Técnico utilizado]
* [Referencia Bibliográfica 2 o Documentación del API]