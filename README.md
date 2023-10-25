# CI2693. Proyecto I: Grafo. Implementación con listas de adyacencias

Integrantes:
    1910211 Prieto Tovar, Jesùs Leonardo,
    1710614 Soares Dos Reis, Sandibel Yescenia.
    
Implementación de un grafo dirigido simple por medio de listas de adyacencias usando la definición de lista de adyacencias del libro GRAFOS Y ALGORITMOS (Oscar Meza H, Maruja Ortega F.). Esta representación tiene como ventaja principal el manejo dinámico de la memoria, lo que permite una implementación más flexible de las operaciones agregar vértices y eliminar lados.
Para nuestra implementación usaremos el framework Collection que nos proporciona Java, en específico usaremos la interfaz de List y su apartado de LinkedList en nuestro código.

## Class AdjancencyListGraph
    Esta clase crea un objeto(grafo) que poseerá dos atributos y una clase para definir los vértices.
    
    Atributos:
        - List<node<T>>graph: Lista enlazada que contiene los vértices(nodo<T>) del grafo.
        - int edfeCardinality: Cardinalidad del conjunto de lados que posee el grafo.
        
    Clase nodo<T>:
        Esta clase crea un objeto que será nuestro vértice, el cual tendrá dos atributos: 
            - T value: el valor del vertice.
            - List<T> successorList: una lista enlazada que contendrá los sucesores del vértice 

Metodos de la clase y sus complejidades:

### add
    boolean add(T vertex);
    
Primero se verifica si el vértice ya pertenece al grafo por medio del método contains(), el peor caso de este método es O(|V|). 
Luego, si el vértice no está en el grafo, se crea un nodo con el vértice como valor y se agrega al grafo, esto es O(1).

Complejidad: $O(|V|)$

### connect
    boolean (T from, T to);
    
Se revisa si ambos elementos pertenecen al grafo por medio del metodo contains(), esto tiene un tiempo de O(|V|).
Si ambos vértices estan en el grafo, se realiza una primera busqueda para hallar el nodo con valor from, en el peor caso toma O(|V|).
Una vez hallado el nodo se realiza una segunda revisión para saber si el parámetro to pertenece a la lista de sucesores del nodo, 
de esta forma no se vuelve a agregar el vértice to. El peor caso ocurre cuando el vértice from está conectado con todos los 
otros vértices, incluyéndose a sí mismo, por tanto se tendría que recorrer la cardinalidad de vértices en su lista de sucesores.

Complejidad: $O(|V|^2)$

### disconnect
    boolean disconnect(T from, T to);
    
Al tener la misma estructura que connect, poseen la misma complejidad.

Complejidad: $O(|V|^2)$

### contains
    boolean contains(T vertex);
    
Si el vértice pasado no esta en el grafo, se realiza un recorrido a toda la lista de nodos.

Complejidad: $O(|V|)$

### getInwardEdges
    List<T> getInwardEdges(T to);
    
Se realiza una primera busqueda para saber si el vértice pertenece al grafo y en caso de no tenerlo retorna null (tiempo O(|V|)).
Si el parámetro to pertence entonces se crea una una lista que contendra los predecesores, luego verificamos la lista de 
sucesores de cada nodo para saber si esta contenido el parámetro, en caso de que se encuentre agregamos el valor del nodo a la lista de predecesores. 
    
Hacer un recorrido por la lista de nodos tiene un tiempo de O(|V|) y chequear su lista de sucesores es también O(|V|) en el peor caso.

Complejidad: $O(|V|^2)$

### getOutwardEdges
    List<T> getOutwardEdges(T from);
    
Se crea una lista que contendrá a los sucesores del vértice, primero se verifica si el vértice pertenece al 
conjunto de nodos (O(|V|)), en caso de pertenecer este busca el nodo con ese valor O(|V|) y retorna una 
copia de la lista de sucesores, de esta forma no se pasa la lista de sucesores por referencia.

Complejidad: $O(|V|^2)$

### getVerticesConnectedTo
    List<T> getVerticesConnectedTo(T vertex);
    
Se verifica si el vértice pertece al grafo(O(|V|)), en caso de pertenecer se crean tres listas nuevas, llamadas neighborList,
predecessorsList y successorsList. Las dos últimas listas usaran los metodos getInwardEdges y getOutwardEdges 
respectivamente(O(|V|^2)), luego procederemos a agregar a neighborList cada elemento perteneciente a las listas 
que nos retornaron los métodos, esto sería O(|V|). 

Los métodos getInwardEdges y getOutwardEdges son las que mayor complejidad tienen en este método, entonces getVerticesConnectedTo
poseerá la misma complejidad.

Complejidad: $O(|V|^2)$

### getAllVertices
    List<T> getAllVertices();

Se crea un lista que contrendrá los vértices y luego por cada nodo en el grafo se guardara el valor en la lista creada.

Complejidad: $O(|V|)$

### remove
    boolean remove(T vertex);
    
Primero se verifica si el vértice pertenece al grafo, si pertenece procedemos a borrar el nodo que lo contiene 
y sus conexiones con otros nodos.
    
Para borrar el nodo, se revisa cada nodo del grafo, si halla uno con el mismo valor del vértice, entonces se borra del grafo y
se disminuye el atributo edgeCardinaliy, esto tiene una complejidad de O(|V|).
    
Una vez borrado el nodo, se procede a borrar sus conexiones, se hace una búsqueda a cada elemento del grafo y se revisa 
si en su lista de sucesores este contenido el vértice, en caso de hacerlo se elimina y seguimos con siguiente elemento. 
    
La primera búsqueda tiene un tiempo de O(|V|) y como se usa el método contains() en cada vértice revisado 
entonces su complejidad es O(|V|^2). 

Complejidad: $O(|V|^2)$

### size
    int size()
    
Retorna la cardinalidad de vértices que tiene el grafo usando el método size() de LinkedList, el cual toma un tiempo de O(1).

Complejidad: $O(1)$

### subgraph
    Graph<T> subgraph(Collection<T> vertices)

Se recibe una colección de vértices para crear un subgrafo. Si la colección de vértices es mayor 
que la cantidad de vértices del grafo o se pasa un vertice que no esta en el grafo, se retorna null.
De esta forma se asegura que el grafo que se esta retornando cumpla con las condiciones de subgrafo.

Revisar que todos los vértices que se pasaron estan en el grafo (luego de revisar el tamaño de la colección) en el peor caso toma O(|V|), 
cuando el usuario quiere un clone del grafo actual.

Luego de verificar la pertenencia y agregar los vértices al subgrafo, se obtienen los sucesores de cada vértice de la collección, 
se recorren estos sucesores y si alguno esta en la collección, entonces se conectan los vértices en el subgrafo, esto seria O(|V|^3).

Complejidad: $O(|V|^3)$
