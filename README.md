# Plantilla para proyectos en spring

Arquetipo para la definici&oacute; de proyectos en spring

## Caracter&iacute;sticas

Herramientas y marcos de trabajo

- Gradle
- SpringBoot
- Lombok
- JPA
- Hibernate
- OpenApi
- PMD
- Checkstyle

## Estructura

### xxx-domain

Define las entidades, repositorios, interfaces y clases pojo del proyecto

### xxx-core

Define las implementaciones de las interfaces del proyecto (Se desarrolla toda la l{ogica de negocio del proyecto)

### xxx-service

Permite la exposici&oacute;n de los recursos _REST_ (No se debe agregar lógica del proyecto en los controladores)

#### Levantar el proyecto de forma local

En Windows utilizar gitbash uno de los siguientes comandos.

1. SPRING_PROFILES_ACTIVE=CONSOLA gradle bootRun
2. SPRING_PROFILES_ACTIVE=CONSOLA java - jar myJar.jar

#### Dependencias

### Variables de entorno

Se encuentran definidas en el archivo .env de la raiz del proyecto.
Deben ser seteadas en las variables de entorno del equipo y configuradas correctamente en el archivo values.yaml del directorio ci
para que puedan ser configuradas correctamente por el Rancher

### Versiones

Para un correcto versionado se debe actualizar la version del proyecto en los directorios:

#### Aplicativo

- /build.gradle -> "version = '0.0.0-SNAPSHOT'"
- /ci/helm/values.yaml -> "appVersion: 0.0.0-SNAPSHOT"
- /ci/helm/Chart.yaml -> "appVersion: 0.0.0-SNAPSHOT"
- /ci/docker/dockerfile -> "xxx-service-0.0.0-SNAPSHOT"

#### Chart helm

La version se debe escribir con la palabra "snapshot" o "release" en letras minúsculas y precedidas por el signo mas (+).
Si no se utiliza esta convención no se presentará en los catalogos helm de Rancher para su despliegue.
- /ci/helm/Chart.yaml -> "version: 0.0.0+snapshot"

### Compilación

Para asegurarse de que el proyecto no presenta vulnerabilidades de código se debe ejecutar el comando el el gitbash de windows

./gradlew clean build

Si presenta violaciones de reglas PMD y Checkstyle deben ser corregidas hasta que la aplicación se construya de forma exitosa.
Cada vez que se encuentran violaciones de reglas en el log de ejecución se presenta una url que les llevará al listado de reglas violadas.

### Construir docker
docker build --build-arg "USER_ARG=pato" --build-arg "PASSWORD_ARG=pato123" --build-arg "URL_ARG=jdbc:postgresql://172.19.240.1:5432/pruebabd" -t pato/appweb:pichincha .