# CI2693. Proyecto I: Grafo. Implementación con listas de adyacencias

Integrantes:
    1910211 - Prieto Tovar, Jesùs Leonardo
    1710614 - Soares Dos Reis, Sandibel Yescenia
    
Implementación de un grafo dirigido simple por medio de listas de adyacencias.
Para nuestra implementacion usaremos el framework Collection que nos proporciona Java, en específico usaremos la interfaz de List y su apartado de LinkedList en nuestro codigo.

## Class AdjancencyListGraph
    Esta clase crea un objeto(grafo) que poseera dos atributos y una clase para definir los vertices.
    
    Atributos:
        - List<node<T>>graph: Lista enlazada que contiene los vertices(nodo<T>) del grafo.
        - int edfeCardinality: Cardinalidad del conjunto de lados que posee el grafo.
        
    Clase nodo<T>:
        Esta clase crea un objeto que sera nuestro vertice, el cual tendra dos atributos: 
            - T value: el valor del vertice.
            - List<T> successorList: una lista enlazada que contendra los sucesores del vertice. 

Metodos de la clase y sus complejidades:

### add
    boolean add(T vertex);
    
    Como este metodo verifica primero si el vertice pertenece al conjunto, el peor caso ocurre cuando 
    pertenece pero se encuentra de ultimo en la lista, es decir, debe recorrer todos los elementos del conjunto V.  
Complejidad: $O(n)$

### connect
    boolean (T from, T to);
    
    Se hace una primera revision en la lista de nodos para saber si ambos elementos pertenecen, esto tiene un tiempo de O(n).
    Se realiza una primera busqueda para hallar el nodo con valor from, el tiempo de esta operacion es O(n).
    Una vez hayado el nodo se realiza una segunda revision para saber si el parametro to pertenece a la 
    lista de sucesores del nodo, si ambos pertenecen entonces se crea una conexion entre ambos y se aumenta el atributo
    edfeCardinality, esto tiene una complejidad es O(n).
Complejidad: $O(n^2)$

### disconnect
    boolean disconnect(T from, T to);
    
    Al tener la misma estructura que connect, poseen la misca complejidad.
Complejidad: $O(n^2)$

### contains
    boolean contains(T vertex);
    
    Se realiza un recorrido a toda la lista de nodos en el peor caso.
Complejidad: $O(n)$

### getInwardEdges
    List<T> getInwardEdges(T to);
    
    Se realiza una primera busqueda para saber si el vertice pertenece al grafo y en caso de no tenerlo retorna null (tiempo O(n)).
    Si el parametro to pertence entonces se crea una una lista que contendra los predecesores, luego verificamos la lista de 
    sucesores de cada nodo para saber si esta contenido el parametro, en caso que se encuentre agregamos el nodo a la 
    lista de predecesores. 
    
    Hacer un recorrido por la lista de nodos tiene un tiempo de O(n) y chequear su lista de sucesores es otro tiempo O(n).
Complejidad: $O(n^2)$

### getOutwardEdges
    List<T> getOutwardEdges(T from);
    
    Se crea una lista que contendra a los sucesores del vertice, primero se verifica si el vertice pertenece al 
    conjunto de nodos (O(n)), en caso de pertenecer este busca el nodo con ese valor (O(n)) y retorna la lista de sucesores
Complejidad: $O(n^2)$

### getVerticesConnectedTo
    List<T> getVerticesConnectedTo(T vertex);
    
    Se verifica si el vertice pertece al grafo(O(n)), en caso de pertenecer se crean tres listas nuevas, llamadas neighborList,
    predecessorsList y successorsList. Las dos ultimas listas usaran los metodos getInwardEdges y getOutwardEdges 
    respectivamente(O(n^2)), luego procederemos a agregar a neighborList cada elemento perteneciente a las listas 
    que nos retornaron los metodos (O(n)). 

    Los metodos getInwardEdges y getOutwardEdges son las que mayor complejidad tienen en este metodo, entonces getVerticesConnectedTo
    poseerá la misma complejidad.
Complejidad: $O(n^2)$

### getAllVertices
    List<T> getAllVertices();

    Se crea un lista que contrendra los vertices y luego por cada nodo en el grafo se guardara el valor del esta en la lista creada.
Complejidad: $O(n)$

### remove
    boolean remove(T vertex);
    
    Primero se verifica si pertenece el vertice pertenece al grafo, si pertenece procedemos a borrar el nodo que lo contiene 
    y sus conexiones con otros nodos.
    Para borrar el nodo, se revisa cada nodo del grafo, si halla uno con el mismo valor del vertice, entonces se borra del grafo y
    se disminuye el atributo edfeCardinaliy, esto tiene una complejidad de O(n).
    Una vez borrado el nodo, se procede a borrar sus las conexiones, se hace una busqueda a cada elemento del grafo y se revisa 
    si en su lista de sucesores esta contenido el vertice, caso de hacer se elimina y seguimos con siguiente elemento. 
    La primera busqueda tiene un tiempo de O(n) y como se usa el metodo contains() en cada vertice revisado 
    entonces su complejidad es O(n^2). 
Complejidad: $O(n)^2$

### size
    int size();
    
    Retorna el la cardinadlidad de vertices que tiene el grafo 
Complejidad: $O(1)$

### subgraph
    Graph<T> subgraph(Collection<T> vertices);
    Se recibe una colección de vertices para crear un subgrafo. Primero se hace una revision por cada nodo del grafo , segundo 
    se verifica si el nodo pertenece a la coleccion al pertenecer se crea un subnodo que contienen(si no pertence pasamos 
    al siguiente nodo), en tercer lugar se hace una revision a lista de sucesores del nodo para saber si tiene elementos que se 
    encuentren en la colección, es decir, si sucesor pertenece del nodo pertenece al subgrafo agregalo a la lista de
    sucesores del subvertice. 
    Cada paso tiene un tiempo de O(n) y como los dos ultimos pasos estan anidados al primero, obtendriamos un tiempo de O(n^3)
Complejidad: $O(n^3)$
