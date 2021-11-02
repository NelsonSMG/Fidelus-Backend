# Fidelus - Backend

Backend para la segunda parcial de Arquitectura Web

### Pasos

Clonar el proyecto

Correr los siguientes comandos

Para instalar dependencias 

```mvn clean install```

Para generar war

```mvn clean package```

Levantar con Wildfly

    Confirmar de que en el archivo standalone.xml se encuentran los datos correctos para la conexón del datasource.
    Limpiar las carpetas standalone/deployments, standalone/data y standalone/tmp
    Copiar el war generado por el package realizado en el paso anterior al directorio standalone/deployments
    Ejecutar el script de inicio del wildfly ```sh standalone.sh```

La aplicación estará disponible en **http://localhost:8080/fidelus**
