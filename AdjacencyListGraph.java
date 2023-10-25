import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

public class AdjacencyListGraph<T> implements Graph<T> {
    // Se declaran una lista de nodos, los cuales representaran un vertice 
    // junto a sus sucesores, y un entero que llevara la cantidad de arcos
    // del grafo
    private List<node<T>> graph; 
    private int edgeCardinality;

    public AdjacencyListGraph(){
        this.graph =  new LinkedList<node<T>>();
        edgeCardinality = 0;
    }

    // Se crea la clase node para poder representar un vertice y su respectiva 
    // lista de sucesores. Tambien se agregan los metodos addSuccesor y 
    // removeSuccesor para facilitar su manejo
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
            if (this.successorsListSize() > 0){
                this.successorsList.remove(succesor);
            }
 
        }

        //Devolver tamaño de la lista enlazada
        public int successorsListSize() {
            return this.successorsList.size();
        }
    }

    public boolean add(T vertex){
        // Si el grafo ya contiene el vertice no se hace nada y se retorna false
        if(this.contains(vertex)){
            return false;
        }
        
        // Si no se posee el vertice, se crea una nueva instancia de un nodo
        // y se agrega
        node<T> newVertex = new node<T>(vertex);
        this.graph.add(newVertex);
        return true;
    }

    public boolean connect(T from, T to) {
        if(!this.contains(from) || !this.contains(to)){
            return false;
        }
        for (node<T> i: this.graph) {
            if (i.value.equals(from)) {
                if (i.successorsList.contains(to)) {
                    return true;
                }
                i.addSuccesor(to);
                this.edgeCardinality++;
                break;
            }
        }
        return true;
    }

    public boolean disconnect(T from, T to) {

        if(!this.contains(from) || !this.contains(to)){
            return false;
        }

        for (node<T> i: graph ) {
            if (i.value.equals(from)) {
                if (!i.successorsList.contains(to)) {
                    return true;
                }
                i.removeSuccesor(to);
                this.edgeCardinality--;
                break;
            }
        }
        return true;
    }

    public boolean contains(T vertex) {
        for (node<T> i: graph) {
            if (i.value.equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    public List<T> getInwardEdges(T to) {

        if(!this.contains(to)){
            return null;
        }

        List<T> predecessorsList = new LinkedList<>();
        for (node<T> i: graph) {
            if (i.successorsList.contains(to)) {
                predecessorsList.add(i.value);
            }
        }
        return predecessorsList;
    }

    public List<T> getOutwardEdges(T from) {
        if (!this.contains(from)) {
            return null;
        }
        
        List<T> succesors = new LinkedList<T>();
        for (node<T> i: graph) {
            if (i.value.equals(from)) {
                for (T j: i.successorsList){
                    succesors.add(j);
                }
                break;
            }
        }  
        return succesors;     
    }

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

    public List<T> getAllVertices() {

        List<T> vertexList = new LinkedList<>();
        for (node<T> i: graph) {
             vertexList.add(i.value);
        }

        return vertexList;
    }

    public boolean remove(T vertex) {
        
        if(!this.contains(vertex)){
            return false;
        }

        for (node<T> i: graph) {
            if (i.value.equals(vertex)) {
                edgeCardinality -=  i.successorsListSize();
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
        return true;
    }

    public int size() {
        return graph.size();
    }

    public Graph<T> subgraph(Collection<T> vertices){

        Graph<T> subgraph = new AdjacencyListGraph<T>();

        for (node<T> i: graph){
            if (!vertices.contains(i.value)){
                continue;
            }
            subgraph.add(i.value);
        }

        for (node<T> i: graph){
            if (!vertices.contains(i.value)){
                continue;
            }
            for (T succesor: i.successorsList){
                if(vertices.contains(succesor)){
                    subgraph.connect(i.value, succesor);
                }
            }
        }

        return subgraph;
    }
}
