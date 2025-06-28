FROM eclipse-temurin:21.0.7_6-jdk
LABEL authors="MrDaniel"


# CREAMOS DIRECTORIO EN EL QUE ESTARA EL CONTENEDOR
WORKDIR /root

COPY pom.xml /root
COPY .mvn /root/.mvn
COPY mvnw /root

# DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

# COPIAR EL CODIGO AL CONTENEDOR
COPY src /root/src

# DskipTests es para skipear las pruebas unitarias
RUN ./mvnw clean install -DskipTests

# SIRVE PARA EJECUTAR COMANDOS CUANDO SE INICIE EL CONTENEDOR
ENTRYPOINT ["java", "-jar", "/root/target/court-service-0.0.1-SNAPSHOT.jar"]