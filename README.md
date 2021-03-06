# Google book wishlist

_Proyecto enfocado en la creacon de lista de libros provenientes de un API expuesta por google._

## Comenzando 馃殌

_Estas instrucciones te permitir谩n obtener una copia del proyecto en funcionamiento en tu m谩quina local para prop贸sitos de desarrollo y pruebas._

* Abrir terminal
* Colcoarse en el directorio para clonar el proyecto
* Realizar clonado del proyecto

```
$ git clone https://github.com/Gilheroes/ChallengeML.git
```

### Pre-requisitos 馃搵

* Docker
* Docker-Compose
* maven

### Instalaci贸n 馃敡

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

* -d -> Es utilizado para realizar la ejecuci贸n en background


_*Notas:*_

* Si desea ejecutar el contenedor en primera estancia remover -d, para salir de la ejecucion utilizar ctrl+c.
* Si se desean ver los logs del contenedor utilice la siguiente instrucci贸n (Para finalizar la ejecuci贸n seria ctrl+c):
```
sudo docker-compose logs -f
```

## Explicaci贸n docker-compose 馃摝

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

## Ejecutando las pruebas 鈿欙笍

_Realizar peticiones_

Se agrega coleccion de peticiones json en /src/main/resources/getToken.postman_collection.json


## Construido con 馃洜锔?

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring-boot](https://spring.io/projects/spring-boot) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SQL_LITE](https://www.sqlite.org/docs.html) - Base de datos


## Autores 鉁掞笍

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Gilberto Tapia Duarte** - *Programador* 