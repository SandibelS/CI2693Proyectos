# CI2693. Proyecto I: Grafo. Implementación con listas de adyacencias

Integrantes:
    1910211 Prieto Tovar, Jesùs Leonardo,
    1710614 Soares Dos Reis, Sandibel Yescenia.
    
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
Complejidad: $O(|V|)$

### connect
    boolean (T from, T to);
    
    Se hace una primera revision en la lista de nodos para saber si ambos elementos pertenecen, esto tiene un tiempo de O(|V|).
    Se realiza una primera busqueda para hallar el nodo con valor from, el tiempo de esta operacion es O(|V|).
    Una vez hayado el nodo se realiza una segunda revision para saber si el parametro to pertenece a la 
    lista de sucesores del nodo, en el peor caso el vertice from está conectado con todos los otros vertices,
    incluyendose a si mismo, por tanto se tendria que recorrer la cardinalidad de vertices en su lista de sucesores.
Complejidad: $O(|V|^2)$

### disconnect
    boolean disconnect(T from, T to);
    
    Al tener la misma estructura que connect, poseen la misma complejidad.
Complejidad: $O(|V|^2)$

### contains
    boolean contains(T vertex);
    
    Se realiza un recorrido a toda la lista de nodos en el peor caso.
Complejidad: $O(|V|)$

### getInwardEdges
    List<T> getInwardEdges(T to);
    
    Se realiza una primera busqueda para saber si el vertice pertenece al grafo y en caso de no tenerlo retorna null (tiempo O(|V|)).
    Si el parametro to pertence entonces se crea una una lista que contendra los predecesores, luego verificamos la lista de 
    sucesores de cada nodo para saber si esta contenido el parametro, en caso que se encuentre agregamos el nodo a la lista de predecesores. 
    
    Hacer un recorrido por la lista de nodos tiene un tiempo de O(|V|) y chequear su lista de sucesores es tambien O(|V|).
Complejidad: $O(|V|^2)$

### getOutwardEdges
    List<T> getOutwardEdges(T from);
    
    Se crea una lista que contendra a los sucesores del vertice, primero se verifica si el vertice pertenece al 
    conjunto de nodos (O(|V|)), en caso de pertenecer este busca el nodo con ese valor O(|V|) y retorna una 
    copia de la lista de sucesores.
Complejidad: $O(|V|^2)$

### getVerticesConnectedTo
    List<T> getVerticesConnectedTo(T vertex);
    
    Se verifica si el vertice pertece al grafo(O(|V|)), en caso de pertenecer se crean tres listas nuevas, llamadas neighborList,
    predecessorsList y successorsList. Las dos ultimas listas usaran los metodos getInwardEdges y getOutwardEdges 
    respectivamente(O(|V|^2)), luego procederemos a agregar a neighborList cada elemento perteneciente a las listas 
    que nos retornaron los metodos, esto seria O(|V|). 

    Los metodos getInwardEdges y getOutwardEdges son las que mayor complejidad tienen en este metodo, entonces getVerticesConnectedTo
    poseerá la misma complejidad.
Complejidad: $O(|V|^2)$

### getAllVertices
    List<T> getAllVertices();

    Se crea un lista que contrendra los vertices y luego por cada nodo en el grafo se guardara el valor en la lista creada.
Complejidad: $O(|V|)$

### remove
    boolean remove(T vertex);
    
    Primero se verifica si pertenece el vertice pertenece al grafo, si pertenece procedemos a borrar el nodo que lo contiene 
    y sus conexiones con otros nodos.
    Para borrar el nodo, se revisa cada nodo del grafo, si halla uno con el mismo valor del vertice, entonces se borra del grafo y
    se disminuye el atributo edgeCardinaliy, esto tiene una complejidad de O(|V|).
    Una vez borrado el nodo, se procede a borrar sus conexiones, se hace una busqueda a cada elemento del grafo y se revisa 
    si en su lista de sucesores esta contenido el vertice, caso de hacer se elimina y seguimos con siguiente elemento. 
    La primera busqueda tiene un tiempo de O(|V|) y como se usa el metodo contains() en cada vertice revisado 
    entonces su complejidad es O(n^2). 
Complejidad: $O(|V|^2)$

### size
    int size()
    
    Retorna el la cardinadlidad de vertices que tiene el grafo 
Complejidad: $O(1)$

### subgraph
    Graph<T> subgraph(Collection<T> vertices)

    Se recibe una colección de vertices para crear un subgrafo. Si la coleccion de vertices es mayor 
    que la cantidad de vertices del grafo o se pasa un vertice que no esta en el grafo, se retorna null.
    De esta forma se asegura que el grafo que se esta pasando cumpla con las condiciones de subgrafo.

    Revisar que todos los vertices que se pasaron estan en el grafo (luego de revisar el tamaño de la coleccion) en el peor caso toma O(|V|), cuando el usuario quiere un clone del grafo actual.

    Luego de verificar la pertenencia y agregar los vertices al subgrafo, se obtienen los sucesores de cada vertice de la collecion, se recorren estos vertices y si alguno esta en la collecion, entonces se conectan los vertices en el subgrafo, esto seria O(|V|^3).

Complejidad: $O(|V|^3)$
