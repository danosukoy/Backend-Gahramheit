[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9WoPPVkS)

# Gahramheit - Plataforma Social de Gestión y Resumen Anual de Anime

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

## Índice / Tabla de Contenidos
- [Gahramheit - Plataforma Social de Gestión y Resumen Anual de Anime](#gahramheit---plataforma-social-de-gestión-y-resumen-anual-de-anime)
  - [Nombres de los Integrantes](#nombres-de-los-integrantes)
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
    - [1. User (Usuario)](#1-user-usuario)
    - [2. Anime](#2-anime)
    - [3. Genre (Género)](#3-genre-género)
    - [4. Episode (Episodio)](#4-episode-episodio)
    - [5. Review (Reseña)](#5-review-reseña)
    - [6. UserAnimeList (Lista de Seguimiento del Usuario)](#6-useranimelist-lista-de-seguimiento-del-usuario)
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

---

## 1. Introducción

### Contexto
El consumo de contenido multimedia, específicamente el anime, ha experimentado un crecimiento exponencial a nivel global en los últimos años. Sin embargo, a medida que el catálogo de series disponibles se expande en múltiples servicios de streaming, los consumidores encuentran dificultades crecientes para mantener un registro organizado e histórico de sus hábitos de visualización. En este contexto, surge la necesidad de una plataforma que centralice el seguimiento de series y capítulos, convirtiéndose en el ecosistema principal del usuario para interactuar con la comunidad otaku.

### Objetivos del Proyecto
* **Objetivo General:** Desarrollar una plataforma backend robusta, escalable y segura para la gestión social de anime, permitiendo catalogar consumos individuales y automatizar un "Yearly Recap".
* **Objetivos Específicos:**
    * Implementar una arquitectura limpia en capas utilizando Spring Boot que integre servicios de persistencia y consumo de APIs de terceros de manera eficiente
    * Diseñar un algoritmo de procesamiento de datos para la generación asíncrona de los "Yearly Recap" sin degradar la performance del servidor.
    * Garantizar la integridad de los datos de los usuarios mediante la incorporación de estrictas medidas de seguridad, validaciones de entrada de datos y control de accesos basados en roles.

---

## 2. Identificación del Problema o Necesidad

### Descripción del Problema
Los fanáticos del anime o mangas se enfrentan al problema recurrente de perder el rastro exacto de las series que consumen, los episodios vistos y las fechas de emisión. Actualmente, no existe una herramienta unificada en el mercado que aborde este seguimiento desde una perspectiva emocional y visual, privando a los usuarios de una forma interactiva de recapitular, revivir y compartir su comportamiento de consumo anual (al estilo de las aplicaciones líderes de streaming de música) con su círculo social o comunidades digitales.

### Justificación
Solucionar esta problemática es sumamente relevante porque el software no solo funciona como un organizador funcional de tareas de visualización, sino que añade un valor social e identitario fundamental para el usuario. Al proveer métricas personalizadas y espacios de debate centralizados, se mitiga la fragmentación de la información de consumo y se fomenta la retención de los usuarios en una plataforma que celebra sus hábitos culturales de entretenimiento a través de mecánicas de gamificación y análisis de datos.

---

## 3. Descripción de la Solución

### Funcionalidades Implementadas
La plataforma backend de **Gahramheit** posee los servicios necesarios para soportar las siguientes capacidades operativas, las cuales componen el núcleo de nuestro MVP:
1. **Búsqueda de Anime y Catálogo:** Sincronización en tiempo real con catálogos externos para consultar fichas técnicas, sinopsis, casas animadoras y actores de doblaje.
2. **Seguimiento Dinámico (Watchlist):** Gestión individualizada del progreso de visualización, permitiendo marcar episodios específicos como vistos y categorizar el estado de la serie en la lista personal del usuario.
3. **Sistema de Calificación y Feedback (Rating):** Persistencia de puntuaciones numéricas y comentarios escritos sobre series completas o episodios de forma independiente.
4. **Foros de Discusión Comunitarios:** Habilitación de espacios de interacción social segmentados por serie, permitiendo la comunicación directa entre los usuarios de la plataforma.
5. **Generador Automatizado de "Wrap" (Yearly Recap):** Algoritmo analítico encargado de agrupar hábitos de consumo, calcular tiempos totales de visualización y deducir géneros favoritos a fin de año.
6. **Sistema de Recompensas y Logros:** Gamificación integrada que otorga insignias de mérito (ej. "Maestro del Shonen") al completar metas del perfil.

### Tecnologías Utilizadas
* **Lenguaje de Programación:** Java 21
* **Framework Principal:** Spring Boot (Spring Web, Spring Security)
* **Persistencia de Datos:** Spring Data JPA con Hibernate
* **Bases de Datos:** Neon (PostgreSQL)
* **APIs Externas:** [Jikan API](https://jikan.moe/) (API REST gratuita basada en MyAnimeList)
* **Herramientas:** Lombok, Validation, ModelMapper 

---

## 4. Modelo de Entidades

### Diagrama de Entidades

![Diagrama Entidad-Relación](imgs/d_e_r.png)

### Descripción de Entidades
---

### 1. User (Usuario)
Representa a los usuarios registrados en la plataforma. 
* **Atributos clave:** `id` (Autoincremental), `username` (Único), `email` (Único) y `password`.
* **Relaciones:**
    * `@OneToMany` con `Review`: Un usuario puede escribir múltiples reseñas. Configurado con `CascadeType.ALL` y `orphanRemoval = true` para asegurar que si un usuario es eliminado, sus reseñas asociadas se borren de forma automática.
    * `@OneToMany` con `UserAnimeList`: Relación con la lista personalizada de animes del usuario. Cuenta con la misma configuración de cascada completa (`CascadeType.ALL`).
* **Optimización:** Se aplican las anotaciones `@ToString.Exclude` y `@EqualsAndHashCode.Exclude` de Lombok en las colecciones para prevenir excepciones de recursividad infinita (`StackOverflowError`) durante la serialización o logs.

### 2. Anime
Es la entidad central del catálogo de la aplicación.
* **Atributos clave:** `id`, `title` (Obligatorio), `malId` (ID externo de MyAnimeList), `episodesCount` e `imageUrl`.
* **Relaciones:**
    * `@OneToMany` con `Episode`: Un anime contiene una lista ordenada de episodios. Configurado en cascada estricta (`CascadeType.ALL, orphanRemoval = true`).
    * `@OneToMany` con `Review`: Mapea todas las opiniones asignadas a este anime específico.
    * `@OneToMany` con `UserAnimeList`: Mapea la presencia de este anime en las distintas colecciones de los usuarios.
    * `@ManyToMany` con `Genre`: Relación bidireccional que vincula los animes con sus respectivos géneros a través de la tabla intermedia explícita `anime_genre` mediante `@JoinTable`.

### 3. Genre (Género)
Clasificación temática para los animes del catálogo.
* **Atributos clave:** `id`, `name` (Obligatorio y único, ej: "Action", "Sci-Fi").
* **Relaciones:**
    * `@ManyToMany(mappedBy = "genres")`: Relación bidireccional inversa con la entidad `Anime`. Al utilizar `mappedBy`, se establece que la entidad `Anime` es la dueña de la relación, evitando la duplicidad de tablas intermedias en la base de datos.

### 4. Episode (Episodio)
Instancia que pertenece a un anime específico.
* **Atributos clave:** `id`, `episodeNumber` (Obligatorio) y `title`.
* **Relaciones:**
    * `@ManyToOne` con `Anime`: Relación obligatoria (`nullable = false`) hacia su anime contenedor.
* **Optimización:** Configurado con `fetch = FetchType.LAZY`. Esto evita la carga innecesaria del objeto `Anime` completo en memoria cada vez que se realiza una consulta de episodios independientes (evitando el problema de rendimiento $N+1$).

### 5. Review (Reseña)
Almacena las evaluaciones y comentarios de los usuarios sobre los animes.
* **Atributos clave:** `id`, `score` (Puntaje numérico), `comment` (Mapeado como tipo `text` en BD para admitir textos largos) y `createdAt` (Fecha de creación).
* **Relaciones:**
    * `@ManyToOne` con `User`: Vincula la reseña con su autor.
    * `@ManyToOne` con `Anime`: Vincula la reseña con el anime evaluado.
* **Optimización:** Ambas relaciones `@ManyToOne` están configuradas con `fetch = FetchType.LAZY` para optimizar las consultas estructuradas a la base de datos.

### 6. UserAnimeList (Lista de Seguimiento del Usuario)
Entidad intermedia que gestiona la relación de muchos a muchos con atributos adicionales entre un `User` y un `Anime` (rompiendo la relación clásica N:M en dos relaciones 1:N).
* **Atributos clave:** `status` (Mapeado como un Enum de tipo String: `WATCHING`, `COMPLETED`, `DROPPED`) y `currentEpisode` (Progreso actual del usuario).
* **Clave Primaria Compuesta:** Implementa la anotación `@EmbeddedId` apuntando a la clase embebible `UserAnimeListId` (la cual contiene los campos `userId` y `animeId` e implementa `Serializable`).
* **Relaciones y Mapeo:**
    * `@ManyToOne` con `User` y `@ManyToOne` con `Anime`.
    * Utiliza `@MapsId("userId")` y `@MapsId("animeId")` para vincular los atributos de la clave compuesta directamente con las entidades externas correspondientes, asegurando consistencia de datos a nivel físico.
* **Optimización:** Ambas uniones están optimizadas con `fetch = FetchType.LAZY` para evitar sobrecargas al interactuar con las listas de reproducción.

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
### Flujo de Trabajo y Gestión del Proyecto
Para garantizar un trbajo coordinado, ordenado, transparente, el equipo eligió por **Github Issues** con una estrategía de ramificación estrucuturada.
* **Ramificación Individual (`feat/`):** El desarrollo se organizó mediante donde cada integrante trabajó de forma aislada y segura en su propia rama. Para ello, se crearon ramas específicas vinculadas a cada integrante la nomenclatura estricta:
  `feat/{nombre_de_integrante}`
* **Pull Requests:** Una vez que un integrante culminaba el desarrollo en su respectiva rama `feat/`, abría un *Pull Request* (PR) hacia la rama principal `main`. Cada PR fue sometido a una revisión por pares (*Code Review*) para asegurar el cumplimiento de la arquitectura en capas, las validaciones de datos y las buenas prácticas de Spring Boot antes de autorizar su integración final.

### Integración Continua (CI/CD) con GitHub Actions
---

## 9. Conclusión

* **Logros del Proyecto:** Se logró construir un motor backend robusto que resuelve con éxito el problema de la dispersión de datos en los aficionados al anime, ofreciendo una plataforma unificada para el tracking diario y la generación interactiva de recaps históricos.
  
* **Aprendizajes Clave:** El desarrollo del sistema permitió al equipo comprender la importancia práctica del desacoplamiento arquitectónico mediante capas lógicas bien definidas, así como la correcta estructuración y optimización de relaciones complejas en JPA para evitar fallas lógicas comunes.
  
* **Trabajo Futuro:** Como extensiones del sistema para próximas versiones de software, se plantea la incorporación de búsquedas predictivas optimizadas con Elasticsearch y la implementación de mecanismos avanzados de almacenamiento en caché distribuidos a través de un servidor Redis independiente para blindar por completo las consultas externas de la API de Jikan.
---

## 10. Apéndices

### Licencia
Este proyecto se distribuye bajo los términos y directrices de la licencia **MIT License**, permitiendo el uso y modificación libre del software con fines educativos.

---

## 11. Referencias
* *Spring Boot Reference Documentation.* Spring IO. Documentación Oficial del Framework.
* *Jikan API - Open-Source MyAnimeList API.* jikan.moe
* *Jakarta Bean Validation Specification.* jakarta.ee