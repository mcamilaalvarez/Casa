# CasaElectrica

## Descripción

CasaElectrica es un proyecto desarrollado en Spring Boot que permite la gestión de artículos eléctricos, incluyendo la administración de fábricas y la carga de imágenes para cada artículo.

## Tecnologías Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Thymeleaf (si aplica)
* Git & GitHub

## Instalación y Configuración

### Prerrequisitos

* Tener Java 17 instalado.
* Tener Maven instalado.
* Tener una base de datos PostgreSQL configurada.

### Pasos para ejecutar el proyecto

1.  Clonar el repositorio:

    ```bash
    git clone https://github.com/mcamilaalvarez/CasaElectrica.git
    cd CasaElectrica
    ```

2.  Configurar el archivo `application.properties` con los datos de la base de datos:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/casa_electrica
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  Compilar y ejecutar el proyecto:

    ```bash
    mvn spring-boot:run
    ```

## Funcionalidades

* Gestión de artículos: Crear, modificar, listar y eliminar artículos.
* Administración de fábricas: Relacionar artículos con fábricas.
* Carga de imágenes: Subir imágenes a cada artículo.
* API REST: Exposición de endpoints para la interacción con la aplicación.


## Autor

‍ Maria Camila Alvarez
