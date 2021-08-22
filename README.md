# Trivias App (API)
Trivias es una aplicación de preguntas sobre diferentes temáticas. Registrate con un nombre de usuario y contraseña para entrar a la aplicación, seleccionar una categoría y contestar 20 preguntas aleatorias.
Esta api es consumida por la aplicación cliente Angular que puedes consultar [aquí](https://github.com/AlejandroCN/trivias-frontend "aquí").
## Comenzando 🚀
La aplicación tiene una pre-configuración de 2 perfiles: desarrollo y producción, dichas configuraciones están establecidas en los archivos application-dev.properties y application-prod.properties respectivamente, no es necesario que modifiques ninguno de estos dos archivos a menos que desees cambiar el comportamiento por defecto de la aplicación.

Debes crear un archivo con las variables de ambiente para ambos perfiles que por defecto está excluído del repositorio (ver archivo .gitignore), el archivo es apikeys.properties, debes ubicarlo en el mismo directorio donde está el application.properties.

Dentro de apikeys.properties debes establecer lo siguiente para los perfiles de desarrollo y producción:
- Credenciales para la conexión a la base de datos
- El dominio de la aplicación cliente (para configuración de CSRF)

### Ejemplo de apikeys.properties
```java
dev.db_name=mydb
dev.db_username=john
dev.db_password=doe
dev.db_host=localhost
dev.client_host=http://localhost:4200

prod.db_name=db-prod
prod.db_username=user-prod
prod.db_password=user-prod1
prod.db_host=dominio-servidor-produccion.com
prod.client_host=https://dominio-aplicacion-frontend.com
```
### Base de datos
Usa el script trivias.sql ubicado en la raíz de este proyecto para generar la base de datos en MySQL 5.7, si deseas cambiar la implementación puedes usar el siguiente diagrama entidad - relación como referencia.
**Nota que el script trivias.sql contiene una inserción de un usuario con rol administrador y una contraseña codificada de ejemplo que puedes cambiar siempre y cuando esta sea codificada con un hash bcrypt con un cost factor=10**.

[![Diagrama ER](https://drive.google.com/uc?id=1cuzHhoNV02XgjbtD5UUyl9OxY1QRXdl7 "Diagrama ER")](https://drive.google.com/uc?id=1cuzHhoNV02XgjbtD5UUyl9OxY1QRXdl7")
### Pre-requisitos 📋

* [Spring Boot](https://spring.io/projects/spring-boot "Spring Boot")
* [MySQL](https://dev.mysql.com/downloads/mysql/5.7.html "MySQL")
* [Maven](https://maven.apache.org/ "Maven")

### Instalación 🔧
Usando el maven wrapper incluido al generar el proyecto con Spring Initializr:
```
./mvnw spring-boot:run
```
La ruta base por defecto en modo desarrollo es: http://localhost:8080
## Despliegue 📦
Una vez que hayas configurado correctamente las variables de ambiente para producción en el archivo apikeys.properties puedes construir el empaquetado jar con el siguiente comando:
En la raíz del proyecto ejecuta:
```
mvn -Pprod clean install
```
Puedes encontrar el archivo jar en el directorio ./target/.

## Construido con 🛠️

* [Spring Boot 2.4.2](https://spring.io/projects/spring-boot "Spring Boot 2.4.2")
* [MySQL 5.7](https://dev.mysql.com/downloads/mysql/5.7.html "MySQL 5.7")