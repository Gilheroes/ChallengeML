# Google book wishlist

_Proyecto enfocado en la creacon de lista de libros provenientes de un API expuesta por google._

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

* Abrir terminal
* Colcoarse en el directorio para clonar el proyecto
* Realizar clonado del proyecto

```
$ git clone https://github.com/Gilheroes/ChallengeML.git
```

### Pre-requisitos 📋

* Docker
* Docker-Compose
* maven

### Instalación 🔧

El unico requisiro es ejecutar el archivo docker-compose.yml, para lo cual son necesarios los siguientes pasos:

_Situarce en el directorio del proyecto_

```
 cd ~/Documents/prueba/Challeng 
```

_Ejecutar el siguiente comando:_

```
 sudo docker-compose up -d
```

Donde: 

* -d -> Es utilizado para realizar la ejecución en background


_*Notas:*_

* Si desea ejecutar el contenedor en primera estancia remover -d, para salir de la ejecucion utilizar ctrl+c.
* Si se desean ver los logs del contenedor utilice la siguiente instrucción (Para finalizar la ejecución seria ctrl+c):
```
sudo docker-compose logs -f
```

## Explicación docker-compose 📦

_La estructura del archivo docker-compose.yml es la siguiente_

```
version: '3.3'
services:
    project-app:
      build: ./
      image: spring-boot:1.0
      restart: always
      container_name: project-app
      ports:
        - 8080:8080
      volumes: 
        - /var/run/docker.sock:/var/run/docker.sock
      network_mode: bridge
```

Donde:

* **project-app** - Es el nombre que le asignamos a nuestro servicio.
* **build** - Indica la ubicacion del archivo Dockerfile desde donde se creara la imagen.
* **container_name** - Es el nombre que le asignamos a nuestro contenedor.
* **ports** - Los puertos que seran utilizados interna y externamente

_Nota:_ Si su puerto 8080 se encuentra ocupado usted puede modificar el puerto externo para que el servicio de levante en otro puerto sin afectar su funcionalidad. Ejemplo:

```
ports:
- 8081:8080
```

_Debera cambiar el puerto en las peticiones para las pruebas_

## Ejecutando las pruebas ⚙️

_Realizar peticiones_

Se agrega coleccion de peticiones json en /src/main/resources/getToken.postman_collection.json


## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring-boot](https://spring.io/projects/spring-boot) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SQL_LITE](https://www.sqlite.org/docs.html) - Base de datos


## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Gilberto Tapia Duarte** - *Programador* 