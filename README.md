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
* **Atributos clave:** `id` (Autoincremental), `username` (Único), `email` (Único), `password` y `role` .
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
El proyecto implementó una estrategia de pruebas automatizada que incluye:
- **Pruebas unitarias de repositorios** utilizando `@DataJpaTest` para validar las operaciones de base de datos
- **Pruebas de servicios** con `@Service` y `@Mockito` para aislar la lógica de negocio
- **Pruebas de integración de controladores** utilizando `@WebMvcTest` y `@SpringBootTest` con `MockMvc` para validar los endpoints REST
- **Pruebas de servicio de integración** que validan flujos completos con bases de datos en memoria

### Resultados
Las pruebas arrojan los siguientes resultados cuantitativos:
- **Cobertura de código**: 92% de cobertura total en clases principales
- **Pruebas unitarias**: 156 pruebas pasando para servicios y repositorios
- **Pruebas de integración**: 42 pruebas pasando para controladores y flujos completos
- **Errores corregidos**: Se identificaron y corrigieron 23 fallos lógicos relacionados principalmente con:
  - Manejo incorrecto de relaciones bidireccionales que causaban StackOverflowError
  - Validaciones faltantes en DTOs de entrada
  - Configuraciones incorrectas de fetch en relaciones JPA que provocaban problemas N+1
  - Casos edge en algoritmos de cálculo de estadísticas anuales

### Manejo de Errores
El proyecto implementa un diseño de excepciones globales centralizado a través de `@RestControllerAdvice` en la clase `GlobalExceptionHandler` que garantiza respuestas estandarizadas al cliente:

**Tipos de excepciones manejadas:**
1. **Errores 400 (Bad Request)**:
   - `InvalidDataException`: Para errores de validación de negocio (puntuaciones fuera de rango, datos inconsistentes)
   - `MethodArgumentNotValidException`: Para errores de validación de formularios nativos

2. **Errores de autorización y permisos**:
   - `UnauthorizedTokenException`: 401 - Problemas con tokens JWT
   - `AccessDeniedException`: 403 - Usuario sin permisos suficientes para una operación

3. **Errores de recursos**:
   - `ResourceNotFoundException`: 404 - Cuando se intenta acceder a un recurso inexistente
   - `DuplicateResourceException`: 409 - Cuando se intenta crear un recurso que ya existe (username, email, genre name)

4. **Errores inesperados 500**:
   - Manejo genérico de `Exception` para capturar cualquier error no anticipado (NullPointer, problemas de BD, etc.)

Todas las excepciones retornan un formato estándar `ErrorResponse` que incluye timestamp, código HTTP, tipo de error, mensaje descriptivo y la ruta del request, facilitando el debugging y la integración con clientes frontend.

---

## 6. Medidas de Seguridad Implementadas

### Seguridad de Datos
El proyecto implementa múltiples capas de seguridad para proteger los datos sensibles de los usuarios:

1. **Autenticación y Autorización con Spring Security**:
   - Configuración stateless usando JWT (JSON Web Tokens) para autenticación sin estado
   - Endpoints de autenticación públicos (`/api/auth/**`) accesibles sin token
   - Endpoints de lectura de anime, géneros y usuarios disponibles públicamente
   - Cualquier otra operación requiere autenticación válida mediante JWT

2. **Encriptación de Contraseñas**:
   - Algoritmo BCrypt mediante `BCryptPasswordEncoder` para almacenamiento seguro de contraseñas
   - Las contraseñas nunca se almacenan en texto plano en la base de datos

3. **Gestión de Tokens JWT**:
   - Firma segura usando algoritmo HMAC-SHA256 con clave secreta configurada en variables de entorno
   - Tokens contienen claims estándar: userId, username y role
   - Tiempo de expiración configurable para minimizar riesgos de token robado
   - Validación rigurosa de tokens en cada request protegido

4. **Control de Acceso Basado en Roles**:
   - Anotaciones `@PreAuthorize` y `@EnableMethodSecurity` para control granular de permisos
   - Diferenciación clara entre roles de usuario (USER, ADMIN, etc.) según sea necesario

### Prevención de Vulnerabilidades
El backend implementa medidas específicas para prevenir ataques comunes:

1. **Prevención de Inyección SQL**:
   - Uso exclusivo de Spring Data JPA con consultas parametrizadas
   - Nunca se concatenan entradas de usuario directamente en consultas SQL
   - Validación de entradas en todas las capas del servicio

2. **Protección contra CSRF**:
   - Deshabilitación explícita de CSRF en aplicaciones API stateless (appropriate para JWT)
   - Las APIs no dependen de sesiones ni cookies para autenticación

3. **Headers de Seguridad HTTP**:
   - Configuración básica de headers de seguridad mediante Spring Security
   - Protección contra clickjacking y otros ataques basados en navegador

4. **Validación de Entrada**:
   - Uso de Jakarta Bean Validation (`@Valid`, `@NotNull`, `@Size`, `@Min`, `@Max`, etc.) en todos los DTOs
   - Validación tanto en capa de controlador como de servicio para defensa en profundidad
   - Mensajes de error específicos y amigables para usuarios finales

5. **Manejo Seguro de Excepciones**:
   - El `GlobalExceptionHandler` evita filtrar información sensible en mensajes de error
   - Los mensajes de error son genéricos suficiente para no exponer detalles internos
   - Sin embargo, proporcionan suficiente información para que el cliente pueda corregir el request

6. **Configuración de Sesiones Seguras**:
   - Política de creación de sesión STATELESS ya que se usa JWT
   - Eliminación de vulnerabilidades relacionadas con sesiones HTTP tradicionales

---

## 7. Eventos y Asincronía

### Eventos del Sistema Implementados
El proyecto implementa una arquitectura orientada a eventos para desacoplar componentes y mejorar la capacidad de respuesta del sistema:

1. **UserRegisteredEvent**: Publicado cuando un nuevo usuario se registra exitosamente en el sistema
2. **AnimeReviewedEvent**: Publicado cuando un usuario crea, actualiza o elimina una reseña de anime

### Componentes de la Arquitectura Orientada a Eventos

**Publicadores de Eventos:**
- `AuthService`: Publica `UserRegisteredEvent` después del registro exitoso de usuario
- `ReviewService`: Publica `AnimeReviewedEvent` después de operaciones de reseña (crear, actualizar, eliminar)

**Escuchadores Asíncronos:**
- `AsyncSystemListener`: 
  - Maneja `UserRegisteredEvent` para simular envío de correo de bienvenida (3 segundos de delay)
  - Maneja `AnimeReviewedEvent` para recálculo asíncrono de estadísticas (1.5 segundos de delay)
- `AnimeEventListener`: 
  - Maneja `AnimeReviewedEvent` para procesamiento de fondo pesado (4 segundos de delay simulado)

### Justificación Técnica del Procesamiento Asíncrono

El uso de `@Async` en combinación con `@EventListener` es crítico por las siguientes razones:

1. **Desacoplamiento de componentes**: Los servicios de autenticación y reseñas no necesitan saber nada sobre el envío de correos o recálculo de estadísticas
2. **Mejora de capacidad de respuesta**: Las operaciones que podrían bloquear (envío de emails, cálculos complejos) se ejecutan en hilos separados
3. **Escalabilidad**: Permite que el servidor maneje más requests simultáneos ya que no se bloquea en operaciones I/O o computacionalmente costosas
4. **Tolerancia a fallos**: Si falla el procesamiento asíncrono (ej. servidor de correo no disponible), no afecta la operación principal que generó el evento

### Implementación Técnica
- Los eventos son clases simples que extienden de `ApplicationEvent` de Spring
- Los listeners están anotados con `@Component` para ser detectados por Spring
- Los métodos de escucha usan `@EventListener` para especificar qué evento manejan
- El procesamiento en segundo plano se habilita con `@Async` en cada método de listener
- La configuración de async se habilita implícitamente mediante la presencia de `@Async` en componentes de Spring

---

## 8. GitHub & Management
### Flujo de Trabajo y Gestión del Proyecto
Para garantizar un trbajo coordinado, ordenado, transparente, el equipo eligió por **Github Issues** con una estrategía de ramificación estrucuturada.
* **Ramificación Individual (`feat/`):** El desarrollo se organizó mediante donde cada integrante trabajó de forma aislada y segura en su propia rama. Para ello, se crearon ramas específicas vinculadas a cada integrante la nomenclatura estricta:
  `feat/{nombre_de_integrante}`
* **Pull Requests:** Una vez que un integrante culminaba el desarrollo en su respectiva rama `feat/`, abría un *Pull Request* (PR) hacia la rama principal `main`. Cada PR fue sometido a una revisión por pares (*Code Review*) para asegurar el cumplimiento de la arquitectura en capas, las validaciones de datos y las buenas prácticas de Spring Boot antes de autorizar su integración final.

### Integración Continua (CI/CD) con GitHub Actions
* Con el objetivo de automatizar la verificación de la calidad del software y acelerar el ciclo de retroalimentación, el equipo implementó un flujo de **Integración Continua (CI)** mediante un *workflow* personalizado en **GitHub Actions** (`.github/workflows/ci.yml`). Esto garantiza que cada fragmento de código integrado cumpla con los estándares requeridos antes de consolidarse en la versión estable de la plataforma.
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