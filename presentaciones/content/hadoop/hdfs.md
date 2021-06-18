# HDFS 
(Hadoop Distributed File System)

---
{{% section %}}

### ¿Qué es el Sistema de archivos distribuidos (DFS)?

Consiste en aglomerar, almacenar en un único árbol de directorios de archivos y directorios ubicados en cualquier parte de la red.

{{% note %}}
Consiste en aglomerar, almacenar en un único árbol de directorios de archivos y directorios ubicados en cualquier parte de la red.
Esto quiere decir que un empleado en una empresa puede obtener todos los archivos que se encuentren en la red como si estuvieran en su mismo computador accediendo a ellas por medio de directorios. 

{{% /note %}}

--- 

### Algunas Ventajas 

<ul>
  <li class="fragment">Accesibilidad</li>
  <li class="fragment">Capacidad de incremento</li>
  <li class="fragment">Flexibilidad</li>
</ul>

{{% note%}}
Hay varias ventajas en el uso de estos sistemas, una de ella es su accesibilidad, la capacidad de incremento es favorable (en los sistemas centralizados es más costoso el incremento por q se necesita de cambios de infraestructura en cambio en los sistemas de archivos distribuidos se va aumentado maquinas al cluster)
Es flexible ya que toda la información o datos se mueven en toda la red a los diferentes clientes que se tiene.
Los sistemas de archivos distribuidos tienen una forma de árbol jerárquico por donde se puede direccionar por sus nodos.
Con este antecedente rápido entramos a lo que es 
{{% /note %}}

{{% /section %}}

---

{{% section %}}

#### HDFS (Hadoop Distributed File System)
<ul>
  <li>Componente principal de Hadoop.</li>
  <li>Almacenar datos grandes estructurados, semiestructurados y no estructurados.</li>
  <li>Modelo Maestro/Esclavo</li>
  <li>La información no se almacena únicamente en una maquina si no que es distribuida entre las maquinas.</li>
  <li>Control interno.</li>
</ul>

{{% note %}}
(Hadoop Distributed File System)
Es uno de los componentes principal de Hadoop, a más de map-reduce del cual se hablara posteriormente, HDFS nos permite almacenar datos grandes de todo tipo estructurados, semiestructurados (no tienen un esquema definido, se los maneja mediante etiquetas que permiten agruparlos por jerarquía un ejemplo muy obvio son los correos electrónicos o el estándar abierto JSON – (un formato de intercambio de datos semiestructurados que se utilizan mucho en la transmisión de datos entre aplicaciones web y servidores)), y no estructurados, estos datos pueden ser almacenados en varias máquinas.
Al tener forma de un árbol jerárquico, podemos decir que maneja un modelo Mastro Esclavo, que significa que una maquina es la MASTER y las otras, otros nodos son sus esclavos. Esto se explicará a fondo cuando hablemos de sus componentes. 
Al tener este sistema de archivos distribuidos La información no se almacena únicamente en una maquina si no que es distribuida entre las maquinas o servidores que forman el cluster, HDFS mantiene control internamente es decir que sabe que tamaño tiene los bloques, donde se ubican, etc.la informacion suele dividirse en bloques del mismo tamaño (cuando sobrepasa el limite del bloque) y las distribuye en cada una de la maquinas que forman el cluster. 

{{% /note %}}

---

<ul>
  <li>Uso WORM (una escritoria, varias lecturas)</li>
  <li>Tolerante a fallas.</li>
  <li>Uso de hadware básico.</li>
  <li>Adecuado para aplicaciones con una gran cantidad de datos.</li>
  <li>Sistema jerárquico.</li>
</ul>

{{% note%}}
Usa Worm, esto significa que los archivos se escriben una sola vez y no esta disponible para modificaciones, pero se la puede leer las veces que se necesite, con esto podemos decir q HDFS puede escribir, leer, borrar pero no actualizar los archivos.
Varios artículos señalan que entre algunas de las características de HDFS se encuentran que 
es altamente tolerante a fallas. esto lo detallaremos más adelante. 
se puede implementar hadware de bajo costo el hadware que se requiere para el HDFS es básico nos referimos a que se necesita solo lo esencial para mantener un almacenamiento. 
Es adecuado para aplicaciones con una gran cantidad de datos, esto se acopla a la anterior característica ya que se puede hablar de mientras mas nodos, se puede almacenar mas información de igual forma los archivos ingrsados a HDFS suelen ser muy ser grandes.
El sistema es jerárquico, el usuario crea primero un directorio, dentro de este puede crear, eliminar, mover renombrar ficheros, para poder ejecutar estas acciones HDFS tiene comando de líneas.

{{% /note%}}

{{% /section %}}

---

{{% section %}}
#### COMPONENTES DE HDFS

{{% fragment %}} NameNode {{% /fragment %}}
{{% fragment %}} DataNodes {{% /fragment %}}

{{% fragment %}} 
<img data-src="/images/hdfs/ntfs.png">
{{% /fragment %}}
{{% note %}}
Como se dijo q mantenía un modelo Maestro/Esclavo
HDFS de Hadoop tiene dos componentes principales NameNode y el DataNodes los dos tienen en común que usan Hadware Básico 
{{% /note %}}

---

#### NameNode
<ul>

<img data-src="/images/hdfs/gnulinux-logo.png">
  <li>Maestro del modelo.</li>
  <li>Gestiona los DataNodes.</li>
  <li>Se conoce la descripción de los archivos almacenados.</li>
</ul>

{{% note%}}
El Componente NameNode es único en un sistema de archivos distribuidos es el componente principal, sin este no existirá el sistema como tal.
Tanto NameNode y los DataNodes usan somo Sistema operativo Gnu/Linux la diferencia entre los dos es que NameNode usa software de nameNode y DataNode el software con su mismo nombre.
el NameNode es el nodo principal en el cuales se gestiona todos los dataNodes, es decir que deben pasar por el para acceder a cualquier nodo, de igual forma, este, hace saber el estado de cada uno de los DataNodes, es decir que los dataNodes informan si están habilitado o no (tienen fallas o no), en el caso de tener una falla el NameNode toma las precauciones en caso de necesitar información q abarca el dataNode deshabilitado.
Se conoce la descripción de los archivos almacenados en los dataNodes, me refiero a los tamaños que tiene cada uno, a su ubicación en el bloque a los permisos que se tiene al archivo. 
{{% /note%}}

---

#### DataNodes
<ul>

<img data-src="/images/hdfs/gnulinux-logo.png">
  <li>Esclavos del modelo.</li>
  <li>Guarda la data.</li>
  <li>Peticiones del cliente.</li>
</ul>

{{% /section %}}

{{%note%}}
Puede existir varios de estos, y funcionan como los esclavos del modelo, estos se encargan de guardar la data de los archivos que son pasados por el nameNode, y a su ves se encarga de las peticiones que realiza el cliente.

NOTA:  se puede tener un NameNode secundario, que de igual forma seria un esclavo y se lo usaria en el caso que el Maestro deje de funcionar, evitando así fallos posibles en el sistema.

{{%/note%}}

---

##  ¿Qué son los bloques HDFS?
<ul>
Segmentos de un archivo en HDFS.
</ul>
{{% fragment %}} 
<img data-src="/images/hdfs/bloques.png">
{{% /fragment %}} 

 {{%note%}}
Tenemos que conocer uno de los conceptos básicos e importantes de los HDFS 
¿Qué son los bloques en HDFS?
Conocemos a bloques a los segmentos de un archivo en HDFS.
Como sabemos HDFS puede almacenar archivos de grandes tamaños, al particionar nos permite almacenar una gran cantidad de datos en diferentes nodos, esto sudece de la siguiente manera 
El tamaño típico de un bloque en HDFS es de 64Mb , este puede ser cambiado.
Si tenemos un archivo de 400Mb entonces este se particionará en 6 segmentos de 64 Mb y uno de 16 Mb los cuales serán almacenados en diferentes nodos del mismo cluster.
Cada uno de estos bloques son replicados 3 veces en diferentes nodos, evitando así fallas esto veremos a detalle en la replicación de HDFS.
Lo que nos permite los bloques es poder ingresar todos los datos que se desean almacenar. 

 {{%/note%}}

---

{{% section %}}

{{< slide transition="fade" >}}

## Replicación en HDFS
<ul>
<img data-src="/images/hdfs/replicacion1.PNG">
</ul>

{{%note%}}
Para conocer como funciona la replicación vamos a ver las siguientes graficas, tenemos un cliente nuestro NodeName maestro y una infraestructura de un cluster con dos racks y diferentes nodos
{{%/note%}}

---

{{< slide transition="fade" >}}

<img data-src="/images/hdfs/replicacion2.png">

{{%note%}}
Entonces el cliente quiere almacenar datos y se comunica con el nodo principal, este a su vez verifica que nodos estas disponibles y los permisos que se tiene para poderle dar su respuesta al cliente. 
{{%/note%}}

---

{{< slide transition="fade" >}}

<img data-src="/images/hdfs/replicacion3.png">
{{%note%}}
Al ver que tiene todos los permisos permite comunica al cliente  y le da acceso para el almacenamiento 
{{%/note%}}

---

{{< slide transition="fade" >}}

<img data-src="/images/hdfs/replicacion4.png">
{{%note%}}
El cliente puede ingresar sus datos los cuales es el bloque, y es almacenado en el primer rack en uno de sus nodos,
{{%/note%}}

---

{{< slide transition="fade" >}}

<img data-src="/images/hdfs/replicacion5.png">
{{%note%}}
 de igual forma se realizan sus copias las cuales son las replicaciones. 
En nuestro ejemplo la hacemos dos veces, como vemos las copias hacemos en un rack diferente, pero en el mismo cluster, lo que sucede es que si el dataNode donde se almaceno tiene un fallo automáticamente este se pone en deshabilitado y eso es comunicado al NameNode y este busca a sus copias para hacer uso y que la información este vigente, como por default se hace 3 copias al tener mas racks en un cluster podemos ubicarlos en diferentes. Se puede cambiar el número de copias, pero en el caso de solo poner que nos haga una copia se tiene mas posibilidad de que se pierda la información ósea que se tenga daño en los dos nodos. 
{{%/note%}}

{{% /section %}}

---

{{% section %}}
## Comandos en HDFS
<ul>
Similar a POXIS
</ul>

```toml
[Sintaxis]
$ hdfs dfs <comando> <opciones>
$ ./bin/hadoop dfs <comando> <opciones>
```

<ul>Ejemplo: </ul>

```bash
$ hdfs dfs –ls
$ ./bin/hadoop dfs -ls 
```
{{%note%}}
Los comandos que se usan son similares a los que se usan en la terminal de Linux el   - ls que era para listar, cut cortar, cp para copiar, mkdir para crear directorios, mv mover, etc. 
La sintaxis de los comandos hdfs es la siguiente: 
$ hdfs dfs <comando> <opciones>
En donde para que el sistema operativo le encuentre el comando hdfs debe estar declarada la Variable local
O a su ves puede usar el comando de la siguiente manera 
$ ./bin/hadoop dfs <comando> <opciones>

Si nosotros queremos listar los directorios que se tienen 
(comando)

{{%/note%}}

---
<ul>
Esquema URI como argumento 
<br>
Notación:

```bash
scheme://authority/path
```
scheme HDFS: `hdfs`
<br>
scheme file system: `file`

Ejemplo: 
```bash
hdfs dfs -mv file://sampleData/ejemplo/ejemplo.txt hdfs://ejemplo.com:8000/user/ejemplo/bloque_ejemplo.txt
```
</ul>

{{%note%}}
 Cuando se va a escribir las opciones 
Todos los HDFS usan un esquema URI como argumento o las opciones del comando 
La notación es:  (comando)
el esquema lleva HDFS cuando esta en el HDFS o usa esquema file cuando es un archivo que esta en el sistema local o como se dice en el computador.

{{%/note%}}

---

### Comandos propios de HDFS
<ul>

`put` y `copyFromLocal`: copiar File system local al HDSF (similares)
<br>

`get` y `copyToLocal`: copia de HDSF a file system local (similares)
<br>

`getMerge`: concatena los archivos que se encuentren en un directorio y tengan un mismo formato.
<br>

`setrep`: cambia el número de replicas (por defecto 3)
<br>

Para apagar el HDFS se usa el siguiente comando: 

```bash
$stop -dfs.sh 
```

[Documentación HDFS](https://www.tutorialspoint.com/hadoop/hadoop_hdfs_operations.html)

</ul>

{{% /section %}}

