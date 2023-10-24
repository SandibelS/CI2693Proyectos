# CI2693. Proyecto I: Grafo. Implementación con listas de adyacencias

Integrantes:
    1910211 - Prieto Tovar, Jesùs Leonardo
    1710614 - Soares Dos Reis, Sandibel Yescenia

## Class AdjancencyListGraph
    Implementación de un grafo dirigido simple por medio de listas de 
    adyacencias
     

### add
    boolean add(T vertex);
    O(|V|)

### connect
    boolean (T from, T to);
    se hace una primera revision en la lista de nodos O(n)
    Se realiza una segunda revision con el elemento to, en el peor de los casos su complejidad es O(n)
    Esto nos lleva a que la complejidad del metodo sea O(n*n)

### disconnect

    boolean disconnect(T from, T to);
    Al tener la misma estructura que connect, la complejidad de este metodo es O(n*n)

### contains
    boolean contains(T vertex);
    Se realiza un recorrido a toda la lista de nodos en el peor caso, su complejidad es O(n)

### getInwardEdges
    List<T> getInwardEdges(T to);
    recibimos un vertice y retornar la lista de predecesores de ese vertice 
    // Complejidad: Se realiza una busqueda en cada nodo y luego por cada nodo se busca si en los sucesores esta to
    // Esto nos lleva a una complejidad de O(n*n)

### getOutwardEdges
    List<T> getOutwardEdges(T from);
    recibimos un vertice y retornamos la lista de sucesores de ese vertice
    // Creo que este metodo puede dar error por como Java maneja las referencias
    // Complijedidad: Con errores en la referencia es O(n*n), si no generada nada es O(n)

### getVerticesConnectedTo
    List<T> getVerticesConnectedTo(T vertex);
        // Metodo que recibe un vertice y retorna los vertices adyacentes
    // Complejidad: O(n*n)

### getAllVertices
    List<T> getAllVertices();
    se retorna todos los vertices
    // Complejidad: O(n)

### remove
    boolean remove(T vertex);
    se recibe un vertice y lo eliminamos del grafo
    // Complejidad: O(n*n)

### size
    int size();
    retorna el la cardinadlidad de vertices que tiene el grafo
    // Complejidad: O(1)/ O(n) en caso que no tenga una cabeza. 

### subgraph
    Graph<T> subgraph(Collection<T> vertices);
    // Metodo que recibe una coleccion de elementos y retorna un subgrafo
    // Complejidad: O(n*n*n)
