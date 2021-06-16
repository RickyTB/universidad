+++

+++

<img data-src="/images/hadoop/map-reduce-logo-png.png" class="borderless">

---

## ¿Por qué MapReduce?

<div class="uce-grid" style="grid-template-columns: repeat(3, 1fr)">
	<div class="fragment">
		<img data-src="/images/hadoop/programmer.svg" class="borderless">
		<p>Problema requiere de una gran fuerza computacional</p>
	</div>
	<div class="fragment">
		<img data-src="/images/hadoop/computer-network.jpeg" class="borderless">
		<p>Red de computadoras</p>
	</div>
	<div class="fragment">
		<img data-src="/images/hadoop/equipo-de-trabajo.webp" class="borderless">
		<p>Dividir la tarea de la forma más eficiente</p>
	</div>
</div>

{{% note %}}
Digamos que tienes un problema que requiere una gran potencia computacional para ser resuelto. Por lo tanto consigues varias computadoras y las conectas a todas en una red. Muy pronto te vas a dar cuenta que no es suficiente tener una gran fuerza computacional, tienes, además, que encontrar la forma más eficiente de dividir esa tarea para ser resulta por tu red de computadoras. 
{{% /note %}}

---

#### Definición original de MapReduce 

<img data-src="/images/hadoop/mapreduce-paper.png" class="fragment border">

{{% note %}}
MapReduce nace de la necesidad de varias empresas en los 90s e inicios de los 2000 de resolver ese problema. Su primera aparición fue en un paper de Google del año 2004 donde define MapReduce como un paradigma de programación basado en tres etapas, Map, Shuffle y Reduce. Es un modelo que te permite paralelizar grandes cargas de trabajo para ser procesadas en varias máquinas a la vez.
<br>
El paper define una forma específica de estructurar el problema a resolver para que resulte sencillo distribuir el problema en una gran cantidad de máquinas.
{{% /note %}}

---

## Map

![Map function](/images/hadoop/map-function.png)

Función que se aplica a cada elemento de un conjunto, para transformarlo en otro elemento de un nuevo conjunto.

---

## Shuffle

<img data-src="/images/hadoop/shuffle-arraylist.png" style="height: 50vh">

Función que reordena un arreglo o vector.


--- 

## Reduce

![Reduce function](/images/hadoop/reduce.png)

Función que se aplica a cada elemento de un conjunto para producir un resultado basado en el procesamiento de todos los elementos.

---

![Map and reduce](/images/hadoop/filter-map-reduce.png)

<small>Filter, Map y Reduce en programación funcional</small>

---

## Hadoop MapReduce

![MapReduce en Hadoop](/images/hadoop/MapReduce-esquema.webp)

{{% note %}}
HDFS proporciona la división previa de los datos en bloques que necesita MapReduce para ejecutar. Los resultados del procesamiento se pueden almacenar en el mismo sistema de almacenamiento o bien en una base de datos o sistema externo.<br>
En un trabajo Hadoop MapReduce, se dividen los datos de entrada en fragmentos independientes que son procesados por los mappers en paralelo. A continuación, se ordenan los resultados del map, que son la entrada para los reducers. Generalmente, las entradas y salidas de los trabajos se almacenan en un sistema de ficheros, siendo los nodos de almacenamiento y de cómputo los mismos. También es muy común que la lógica de la aplicación no se pueda descomponer en una única ejecución de MapReduce, por lo que se encadenan varias de estas fases, tratando los resultados de una como entrada para los mappers de la siguiente fase.
{{% /note %}}

---

### Ejemplo: Word Count

Contar las ocurrencias de cada palabra en un conjunto de documentos.

![Word Count](/images/hadoop/mapreduce-wordcount.png)

---

### Implementación en JAVA

- Punto de entrada
- Función MAP
- Función REDUCER

---

#### Punto de Entrada 

```java{1-19|18}
public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = new Job(conf, "Ejemplo Word Count");
   
    job.setJarByClass(WordCount.class);
    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    Path outputPath = new Path(args[1]);
 
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
    outputPath.getFileSystem(conf).delete(outputPath);
    System.exit(job.waitForCompletion(true) ? 0 : 1);
}
```

{{% note %}}
El método main especifica las rutas y los formatos de entrada y de salida y los tipos de los pares claves/valor que se van a utilizar. Por último, con el método waitForCompletion envía el trabajo y monitoriza el progreso.
{{% /note %}}

---

#### Función MAP

```java{|4|10,11|13}
public static class TokenizerMapper
     extends Mapper<Object, Text, Text, IntWritable>{
 
  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();
 
  public void map(Object key, Text value, Context context)
	  throws IOException, InterruptedException {

    StringTokenizer itr = new StringTokenizer(value.toString());
    while (itr.hasMoreTokens()) {
      word.set(itr.nextToken());
      context.write(word, one);
    }

  }
}
```

<small>Emite un par clave-valor de la forma <palabra, 1></small>

{{% note %}}
El mapper procesa una línea del texto, que divide por palabras separadas por espacios, y posteriormente emite un par de clave-valor, siempre de la forma <palabra, 1>.
{{% /note %}}

---

#### Función REDUCE

```java{|8-10}
public static class IntSumReducer 
	extends Reducer<Text,IntWritable,Text,IntWritable> {
 
    private IntWritable result = new IntWritable();
    public void reduce(Text key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
}
```

{{% note %}}
La implementación del reducer se centra solamente en sumar los valores, es decir, las ocurrencias de cada clave. En este ejemplo son las palabras.
{{% /note %}}

---

# Gracias por su atención

![Hadoop](/images/hadoop/hadoop.png)
