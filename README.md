# Crud-Tareas

Para iniciar la aplicación, se debe contar con Java versión 8 instalado en el equipo. 

Antes de continuar, debe ejecutar en su BD MySQL el script SQL /scripts/crud_tareas.sql

Luego, debe actualizar las siguientes propiedades en /src/main/resources/application.properties: 

* spring.datasource.url 
* spring.datasource.username 
* spring.datasource.password

#### Ejecutar aplicación: 
usar mvn para ejecutar

Windows:

    mvnw.cmd spring-boot:run

Unix:

    mvwn spring-boot:run 

Entrar a http://localhost:8081/swagger-ui.html
