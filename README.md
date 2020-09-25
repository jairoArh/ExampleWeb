# Ejemplo de Desarrollo Web.
Selects dependientes en una App con Java Enterprise Edition JEE, usando AJAX para realizar la peticiones asíncronas al Servidor.

- Editor: NetBeans 11.3
- Versión de Java: JDK 1.8
- Gestor de Dependencias: Maven.

En el Servlet `Control.java` se usa la siguiente línea de código para traer la ruta absoluta al directorio del proyecto y a partir de ahí poder establecer la ruta a los archivos JSON.

```java
String path = getServletContext().getRealPath("/")+"resources/files/";
```

