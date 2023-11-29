# T3
## Integrantes
|Nombre|ROL|
|:--|--|
|Vicente Lineros | 202073610-3 |
|Matías Contreras| 201904613-6 |

## Aspectos formales

Desarrollamos una aplicacion sencilla en Java que implementa
los requerimientos descritos en el enunciado.

Para el desarrollo se utilizo TDD permitienod agilizar
el trabajo y disminuir los errores. En primer lugar se desarrollaron los tests y posteriormente las distintas funcionalidades que debían pasar estos tests. Una vez completado una funcionalidad todos los tests desarrollados anteriormente deberían continuar siendo exitosos, de lo contrario haciamos las revisiones necesarias.

Finalmente, existe una suite de pruebas sobre algunos de los metodos
desarrollados, que se podrian incluir en un workflow de CI para 
disminuir los errores de integracion en grupos de trabajo mas grandes al trabajar con repositorios git.

## Cómo utilizar

### Programa

    java src/main/java/inf/junit/Main.java

### Tests

    mvn test
