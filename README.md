# Fidelus - Backend

Backend para la segunda parcial de Arquitectura Web

El front-end puede encontrarse aquí **https://github.com/NelsonSMG/Parcial1-ARQ-WEB**

### Pasos

Crear la base de datos ```fidelizacion``` en postgres

Una vez dentro, ejecutar el script ```scriptbasededatos.sql``` para la creacion de tablas y demás

Clonar el proyecto

Correr los siguientes comandos

Para instalar dependencias:

```mvn clean install```

Para generar war:

```mvn clean package```

Levantar con Wildfly:

- Confirmar de que en el archivo standalone.xml se encuentran los datos correctos para la conexón a la base de datos.
- Limpiar las carpetas standalone/deployments, standalone/data y standalone/tmp
- Copiar el war generado por el package realizado en el paso anterior al directorio standalone/deployments
- Ubicarse en la carpeta bin del Wildfly dentro de línea de comando y ejecutar el script de inicio del wildfly ```sh standalone.sh```

La aplicación estará disponible en **http://localhost:8080/fidelus**
