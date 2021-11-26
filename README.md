# Título del Proyecto

_Acá va un párrafo que describa lo que es el proyecto_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

* Abrir terminal
* Colcoarse en el directorio para clonar el proyecto
* Realizar clonado del proyecto

```
$ git clone https://github.com/libgit2/libgit2
```

### Pre-requisitos 📋

* Docker
* maven

### Instalación 🔧

Es necesario generar la imagen con el archivo Dockerfile existente en el proyecto

_Situarce en el directorio del proyecto_

```
 cd ~/Documents/prueba/Challeng 
```

_Ejecutar creacion de imagen_

```
 sudo docker build -t spring-boot:1.0 .
```

_Confirmacion de imagen creada_

```
sudo  docker images
```

_Se debe ver la informacion de la imagen_

```
REPOSITORY    TAG       IMAGE ID       CREATED          SIZE
spring-boot   1.0       328998a956ce   19 minutes ago   216MB
```

_Ejecutar contenedor_

```
sudo docker run -d -p 8080:8080 -t spring-boot:1.0
```

_Finaliza con un ejemplo de cómo obtener datos del sistema o como usarlos para una pequeña demo_

## Ejecutando las pruebas ⚙️

_Realizar peticiones_

Aqui pon tus peticiones **GIL**!!!!!!!!!


## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring-boot](http://www.dropwizard.io/1.0.2/docs/) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SQL_LITE](https://rometools.github.io/rome/) - Base de datos


## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Gilberto Tapia Duarte** - *Programador* 