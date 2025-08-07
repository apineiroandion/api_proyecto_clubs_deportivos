# API de Gestión de Clubes Deportivos

Este proyecto es la API RESTful desarrollada como parte del trabajo de fin de curso. Está diseñada para gestionar clubes deportivos como academias de jiu-jitsu, karate, entre otros. La API permite administrar usuarios, clases, eventos y otros elementos clave de la actividad diaria de un club.

## Tecnologías utilizadas

- **Spring Boot**: Framework principal para el desarrollo de la aplicación.
- **Spring Security**: Gestión de la seguridad de los endpoints.
- **JWT (JSON Web Tokens)**: Sistema de autenticación y autorización.
- **API RESTful**: Diseño basado en principios REST, ideal para integrarse con frontend web o móvil.

## Seguridad

La autenticación se realiza mediante tokens JWT. Los usuarios deben iniciar sesión para obtener un token válido, que luego se utiliza para acceder a los endpoints protegidos.

## Funcionalidades principales

- Registro e inicio de sesión de usuarios
- Gestión de miembros del club
- Control de clases, asistencia y eventos
- Administración de instructores

## Notas

Este backend está preparado para trabajar junto a un frontend desarrollado en React y aplicaciones móviles (Android/iOS).
