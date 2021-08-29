#Ejercicio de Http Basic authentication con SpringBoot y base de datos

### Configuración de la base de datos

Esta app utiliza una base de datos PostgreSQL. Antes de continuar:

- Opción 1 (Docker): ejecuta el siguiente comando

```
docker run --name postgres-container --rm -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -e POSTGRES_USER=default -d postgres
```

- Opción 2: [Instala Postgres](https://www.postgresql.org/) y configura la base de datos antes de lanzar la aplicación


## Ejercicio: Configuración de Spring Security y base de datos de usuario

Sigue la página de instrucciones (en Notion) y haz pasar los tests que hay en este repositorio mientras testeas 
manualmente la API con Postman o herramientas similares.

## Referencias

Para aprender más sobre Spring Security: 


- [Video Tutoriales de Javacode 503](https://www.youtube.com/watch?v=0tq2AZ664W0&list=PLxxZ0339925HbzpwFWqSNn1u-J108v22N)
- [Vídeo Tutorial de Java Brains (Inglés)](https://www.youtube.com/watch?v=TNt3GHuayXs)
- 📚 Spring Security In action (Laurentiu Spilca)