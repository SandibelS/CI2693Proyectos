import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

class AdjancencyListGraph<T> implements Graph<T> {

    // Creamos una lista que contendra los vertices, es decir, las instancias de V 
    private List<node<T>> graph;
    private int edgeCardinality;

    public AdjancencyListGraph(){
        this.graph =  new LinkedList<node<T>>();
        edgeCardinality = 0;
    }

    // Creamos una clase vertice que tendra el atributo valor y una lista de arcos con respecto al vertice
    private static class node<T> {
        T value;
        List<T> successorsList = new LinkedList<T>();

        public node(T value){
            this.value = value;
        }

        public void addSuccesor(T succesor){
            this.successorsList.add(succesor); 
        }

        // cambiar para devolver un bool
        public void removeSuccesor(T succesor){
            if (this.size() > 0){
                this.successorsList.remove(succesor);
            }
 
        }

        //Devolver tamaño de la lista enlazada
        public int size() {
            return this.successorsList.size();
        }
    }

    // Metodo agregar vertice (Sin anidamiento)
    // Este metodo posee una complejidad de O(|V|), es decir, es tiempo constante(suponiendo que es una lista circular)
    public boolean add(T vertex){
        // Verifica si el vertice pertenece al conjunto V
        // Si no pertenece crea un vertice con valor vertex y un atributo lista que funcionara como arcos
        // En caso contrario se indica a la persona que ya se encuentra
        
        if(this.contains(vertex)){
            System.out.println("No fue agregado, ya pertenece al conjutno V");
            return false;
        }
        
        node<T> newVertex = new node<T>(vertex);
        this.graph.add(newVertex);
        System.out.println("Agregado vertice de forma exitosa");
        return true;
    }

    // Metodo para conectar dos vertices que pertenescan al conjunto 
    // La complejidad: se hace una primera revision en la lista de nodos O(n)
    // Se realiza una segunda revision con el elemento to, en el peor de los casos su complejidad es O(n)
    // Esto nos lleva a que la complejidad del metodo sea O(n*n)
    public boolean connect(T from, T to) {

        if(!this.contains(from) || !this.contains(to)){
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }
        for (node<T> i: graph) {
            if (i.value.equals(from)) {
                if (i.successorsList.contains(to)) {
                    System.out.println("Estos dos vertices ya se encuentran conectados");
                    return true;
                }
                i.addSuccesor(to);
                this.edgeCardinality++;
                break;
            }
        }
        System.out.println("Arco generado de forma exitosa");
        return true;
    }

    // Metodo que elimina el arco de un par de vertices
    // Complejidad: Al tener la misma estructura que connect, la complejidad de este metodo es O(n*n)
    public boolean disconnect(T from, T to) {

        if(!this.contains(from) || !this.contains(to)){
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }

        for (node<T> i: graph ) {
            if (i.value.equals(from)) {
                if (!i.successorsList.contains(to)) {
                    System.out.println("Estos dos vertices ya se encuentran desconectados");
                    return true;
                }
                i.removeSuccesor(to);
                this.edgeCardinality--;
                break;
            }
        }
        System.out.println("Arco borrado de forma exitosa");
        return true;
    }

    // Metodo contains, verifica si pertenece al conjunto V
    // Complejidad: Se realiza un recorrido a toda la lista de nodos, su complejidad es O(n)
    public boolean contains(T vertex) {
        for (node<T> i: graph) {
            if (i.value.equals(vertex)) {
                // System.out.println("Si pertenece al conjunto");
                return true;
            }
        }
        // System.out.println("No pertenece al conjunto");
        return false;
    }

    // Metodo getInwardEdges, recibimos un vertice y retornar la lista de predecesores de ese vertice 
    // Complejidad: Se realiza una busqueda en cada nodo y luego por cada nodo se busca si en los sucesores esta to
    // Esto nos lleva a una complejidad de O(n*n)
    public List<T> getInwardEdges(T to) {

        if(!this.contains(to)){
            return null;
        }

        List<T> predecessorsList = new LinkedList<>();
        for (node<T> i: graph) {
            // Conteplando la idea que es un grafo simple, sin lazos.
            if (!i.value.equals(to) && i.successorsList.contains(to)) {
                predecessorsList.add(i.value);
            }
        }
        System.out.print("Predecesores de "+ to + ":" + predecessorsList + "\n");
        return predecessorsList;
    }

    // Metodo getOutwardEdges, recibimos un vertice y retornamos la lista de sucesores de ese vertice
    // Creo que este metodo puede dar error por como Java maneja las referencias
    // Complijedidad: Con errores en la referencia es O(n*n), si no generada nada es O(n)
    public List<T> getOutwardEdges(T from) {
        if (this.contains(from)) {
            for (node<T> i: graph) {
                if (i.value.equals(from)) {
                    System.out.print("Sucesores de "+ from + ":" + i.successorsList+ "\n");
                    return i.successorsList;
                }
            }
        }
        return null;
    }

    // Metodo que recibe un vertice y retorna los vertices adyacentes
    // Complejidad: O(n*n)
    public List<T> getVerticesConnectedTo(T vertex){
        if(!this.contains(vertex)){
            return null;
        }
        List<T> neighborList = new LinkedList<T>();
        List<T> predecessorsList = getInwardEdges(vertex);
        List<T> successorsList = getOutwardEdges(vertex);

        for (T v: predecessorsList){
            neighborList.add(v);
        }

        for (T v: successorsList){
            neighborList.add(v);
        }

        return neighborList;
    }

    // Metodo getAllVertices, se retorna todos los vertices
    // Complejidad: O(n)
    public List<T> getAllVertices() {

        List<T> vertexList = new LinkedList<>();
        for (node<T> i: graph) {
             vertexList.add(i.value);
        }

        System.out.print("El conjunto vertices es: " + vertexList + "\n");
        return vertexList;
    }

    // Metodo remove, se recibe un vertice y lo eliminamos del grafo
    // Duda si trabajamos con multigrafo
    // Complejidad: O(n*n)
    public boolean remove(T vertex) {
        
        if(!this.contains(vertex)){
            System.out.println("El vertice no pertenece al conjunto V");
            return false;
        }

        for (node<T> i: graph) {
            if (i.value.equals(vertex)) {
                edgeCardinality -=  i.successorsCount;
                int indexOfVertex = graph.indexOf(i);
                this.graph.remove(indexOfVertex);
                break;
            }
        }
            
        // Eliminacion de los arcos 
        for (node<T> i: graph) {
            if (i.successorsList.contains(vertex)) {
                i.successorsList.remove(vertex);
                edgeCardinality--;
            }
        }
        System.out.println("Vertice eliminado con exito");
        return true;
    }

    // Metodo size, retorna el la cardinadlidad de vertices que tiene el grafo
    // Complejidad: O(1)/ O(n) en caso que no tenga una cabeza. Consultar
    public int size() {
        System.out.print("El tamaño del conjunto vertices es: " + graph.size() + "\n");
        return graph.size();
    }

    // Metodo que recibe una coleccion de elementos y retorna un subgrafo
    // Complejidad: O(n*n*n)
    public Graph<T> subgraph(Collection<T> vertices){
        // Que pasa si el usuario introduce vertices que no estan en el grafo?
        // Si el numero de vertices que nos estan pasando es mayor que el numero
        // de vertices que tiene el grafo, entonces sabemos que el argumento no es 
        // valido

        Graph<T> subgraph = new AdjancencyListGraph<T>();

        for (node<T> i: this.graph){
            if (!vertices.contains(i.value)){
                continue;
            }
            node<T> subgraphNode = new node<T>(i.value);

            // Este for eleva la complejidad del tiempo
            for (T succesor: i.successorsList){
                if(vertices.contains(succesor)){
                    subgraphNode.addSuccesor(succesor);
                }
            }
        }
 
        return subgraph;
    }
    public static void main(String[] args) {
        AdjancencyListGraph<Integer> listaAdyacencias = new AdjancencyListGraph<Integer>();
        // Verificar consistencia del metodo de sucesores
        // Una vez terminado esto, crear un archivo main (volver la clase publica)
        listaAdyacencias.add(14);
        listaAdyacencias.add(20);
        listaAdyacencias.add(150);
        listaAdyacencias.add(130);
        listaAdyacencias.add(50);
        listaAdyacencias.connect(14, 20);
        listaAdyacencias.connect(14, 130);
        listaAdyacencias.connect(20, 150);
        listaAdyacencias.connect(150, 130);
        listaAdyacencias.connect(130, 14);
        listaAdyacencias.connect(50, 14);
        listaAdyacencias.connect(150, 50);
        listaAdyacencias.getInwardEdges(14);
        listaAdyacencias.getOutwardEdges(14);
        listaAdyacencias.remove(14);
        listaAdyacencias.size();
        listaAdyacencias.getAllVertices();
        // vertice x = listaAdyacencias.conjunto_V.get(1);
    }
}
