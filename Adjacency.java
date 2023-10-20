import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

class AdjancencyListGraph<T> implements Graph<T> {

    // Creamos una lista que contendra los vertices, es decir, las instancias de V 
    private List<node<T>> graph;

    // Esta lista puede generar errores en la consistencia del programa
    private List<T> vertices = new LinkedList<T>();

    // Creo que vertexCardinality es innecesaria;
    private int vertexCardinality;
    private int edgeCardinality;

    // Por convencion se usa un constructor en especial cuando uno de los 
    // atributos se inicializa con una funcion.
    public AdjancencyListGraph(){
        this.graph =  new LinkedList<node<T>>();
        vertexCardinality = 0;
        edgeCardinality = 0;
    }

    // Creamos una clase vertice que tendra el atributo valor y una lista de arcos con respecto al vertice
    // Esta clase en realidad es la reprsentacion de un nodo de lista enlazada, los metodos 
    // agregados ayudan a la simplificacion del codigo en el futuro
    private static class node<T> {
        T value;
        int successorsCount = 0;
        List<T> successorsList = new LinkedList<T>();

        public node(T value){
            this.value = value;
        }

        public void addSuccesor(T succesor){
            this.successorsList.add(succesor);
            successorsCount++; 
        }

        // cambiar para devolver un bool
        public void removeSuccesor(T succesor){
            if (successorsCount > 0){
                this.successorsList.remove(succesor);
                successorsCount--;
            }
 
        }
    }

    // Metodo agregar vertice (Sin anidamiento)
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
        vertices.add(vertex);
        vertexCardinality++;
        System.out.println("Agregado vertice de forma exitosa");
        return true;
    }

    // Metodo para conectar dos vertices que pertenescan al conjunto 
    public boolean connect(T from, T to) {

        if(!this.contains(from) || !this.contains(to)){
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }
        for (node<T> i: graph ) {
            if (i.value.equals(from)) {
                i.addSuccesor(to);
                this.edgeCardinality++;
                break;
            }
        }
        System.out.println("Arco generado de forma exitosa");
        return true;
    }
    // Metodo que elimina el arco de un par de vertices
    public boolean disconnect(T from, T to) {

        if(!this.contains(from) || !this.contains(to)){
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }

        for (node<T> i: graph ) {
            if (i.value.equals(from)) {
                i.removeSuccesor(to);
                this.edgeCardinality--;
                break;
            }
        }
        System.out.println("Arco borrado de forma exitosa");
        return true;
    }

    // Metodo contains, verifica si pertenece al conjunto V
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
    public List<T> getInwardEdges(T to) {

        if(!this.contains(to)){
            return null;
        }

        List<T> predecessorsList = new LinkedList<>();
        for (node<T> i: graph) {
            if (!i.value.equals(to) && i.successorsList.contains(to)) {
                predecessorsList.add(i.value);
            }
        }
        System.out.print("Predecesores de "+ to + ":" + predecessorsList + "\n");
        return predecessorsList;
    }

    // Metodo getOutwardEdges, recibimos un vertice y retornamos la lista de sucesores de ese vertice
    // Creo que este metodo puede dar error por como Java maneja las referencias
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

    public List<T> getVerticesConnectedTo(T vertex){
        if(!this.contains(vertex)){
            return null;
        }
        List<T> neighborList = new LinkedList<T>();
        List<T> predecessorsList = getInwardEdges(vertex);
        List<T> successorsList = getOutwardEdges(vertex);

        if(predecessorsList.equals(null) || successorsList.equals(null)){
            return null;
        }

        for (T v: predecessorsList){
            neighborList.add(v);
        }

        for (T v: successorsList){
            neighborList.add(v);
        }

        return neighborList;
    }

    // Metodo getAllVertices, se retorna todos los vertices
    public List<T> getAllVertices() {

        // List<T> vertexList = new LinkedList<>();
        // for (node<T> i: graph){
        //     vertexList.add(i.value);
        // }
        // return vertexList;

        System.out.print("El conjunto vertices es: " + vertices + "\n");
        return vertices;
    }

    // Metodo remove, se recibe un vertice y lo eliminamos del grafo 
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
                this.vertices.remove(vertex);
                this.vertexCardinality--;
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
    public int size() {
        System.out.print("El tamaño del conjunto vertices es: " + vertexCardinality + "\n");
        // podemos simplemente usar graph.size(), creo que es 0(1) y todo;
        return vertexCardinality;
    }

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
        System.out.println(listaAdyacencias.vertexCardinality);
        System.out.println(listaAdyacencias.edgeCardinality);
    }
}