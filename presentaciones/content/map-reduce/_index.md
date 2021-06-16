+++
title = "MapReduce y HDFS | Minería de Datos"
outputs = ["Reveal"]
subject_name = "Minería de Datos"
+++

# Hadoop: HDFS y MapReduce
Ricardo Baquero - Samantha Huaca

---

## Arquitectura de Hadoop

![Hadoop architecture](/images/hadoop/Hadoop-Architecture1.jpg)

{{% note %}}
Hadoop tiene una topología maestro-esclavo. En esta topología, tenemos un nodo maestro y varios nodos esclavos. La función del nodo maestro es asignar una tarea a varios nodos esclavos y administrar recursos. Los nodos esclavos hacen el cálculo real. Los nodos esclavos almacenan los datos reales, mientras que en los maestros tenemos metadatos. Esto significa que almacena datos sobre datos. 
<br>
La arquitectura de Hadoop está compuesta de tres principales capas:
<ul>
	<li>HDFS (Hadoop Distributed File System)</li>
	<li>Yarn</li>
	<li>MapReduce</li>
</ul>
{{% /note %}}