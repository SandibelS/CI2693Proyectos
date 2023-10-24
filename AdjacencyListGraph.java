import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

class AdjacencyListGraph<T> implements Graph<T> {
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
        List<T> succesors = new LinkedList<T>();
        if (this.contains(from)) {
            for (node<T> i: graph) {
                if (i.value.equals(from)) {
                    for (T j: i.successorsList){
                        succesors.add(j);
                    }
                    return succesors;
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

        // if (vertices.size() > graph.size()){
        //     return null;
        // }

        // for (T vertex: vertices){
        //     if(!this.graph.contains(vertex)){
        //         return null;
        //     }
        // }

        Graph<T> subgraph = new AdjacencyListGraph<T>();

        for (node<T> i: this.graph){
            if (!vertices.contains(i.value)){
                continue;
            }
            node<T> subgraphNode = new node<T>(i.value);

            for (T succesor: i.successorsList){
                if(vertices.contains(succesor)){
                    subgraphNode.addSuccesor(succesor);
                }
            }
        }
 
        return subgraph;
    }
    public static void main(String[] args) {

        // Se haran pruebas creando un grafo donde los vertices sean enteros no
        // negativos y su relacion con otro vertice sea la de "menor o igual"
        AdjacencyListGraph<Integer> lessThanOrEqual = new AdjacencyListGraph<Integer>();

        // La cantidad de vertices que se genera
        int n = 100;

        // Pruebas: metodo add
        System.out.println("Pruebas: metodo add");  
        for (int i = 0; i < n; i++){
            boolean testAdd1 = lessThanOrEqual.add(i);
            if(!testAdd1){
                System.out.println("Error. vertice = " + i + ". No se logro agregar un elemento que no pertenecia");
            }
        }
        for (int i = 0; i < n; i++){
            boolean testAdd2 = lessThanOrEqual.add(i);
            if(testAdd2){
                System.out.println("Error. vertice = " + i + ". Se agrego un elemento ya existente");
            }
        }
        System.out.println("Fin Pruebas: metodo add");  
        System.out.println();

        // Se comprueba que el tamaño del conjunto de vertices es n
        if (lessThanOrEqual.size() != n){
            System.out.println("Error. |V| = " + lessThanOrEqual.size() + ", cuando deberia ser" + n);
        }
        
        // Pruebas: metodo connect 
        System.out.println("Pruebas: metodo connect"); 
        for (int i = 0; i < n; i++){
            for (int j = i; j < n*2; j++){
                boolean testConnect = lessThanOrEqual.connect(i, j);
                if(!testConnect && j < n){
                    System.out.println("Error. (i , j) = (" + i + ", " + j + ")");
                    System.out.println("No se logro conectar");
                }
                if(testConnect && j >= n){
                    System.out.println("Error. (i , j) = (" + i + ", " + j + ")");
                    System.out.println("Se conectaron vertices que no debian conectarse");
                }
            }
        }
        System.out.println("Fin Pruebas: metodo connect"); 
        System.out.println();

        // Pruebas metodo: contains
        System.out.println("Pruebas: metodo contains");  
        for (int i = 0; i < n*2; i++){
            boolean testContains = lessThanOrEqual.contains(i);
            if(!testContains && i < n){
                System.out.println("Error. i = " + i + ". No se encontro un vertice existente");
            }
            if(testContains && i >= n){
                System.out.println("Error. i = " + i + ". Se encontro un vertice que no existe");
            }
        }
        System.out.println("Fin Pruebas: metodo contains");
        System.out.println();

        // Pruebas metodos: getInwardEdges, getOutwardEdges y getVerticesConnectedTo
        System.out.println("Pruebas: metodos getInwardEdges, getOutwardEdges y getVerticesConnectedTo");
        for (int i = 0; i < n; i++){
            List<Integer> predecesores = lessThanOrEqual.getInwardEdges(i);
            List<Integer> sucesores = lessThanOrEqual.getOutwardEdges(i);
            List<Integer> adyacentes = lessThanOrEqual.getVerticesConnectedTo(i);

            boolean checkPredecesores = true;            
            boolean checkSucesores = true;
            boolean checkadyacentes = true;

            for(Integer p: predecesores){
                if( !(0 <= p && p <= i) ){
                    checkPredecesores = false;
                }
            }
            for(Integer s: sucesores){
                if( !(i <= s && s < n)){
                    checkSucesores = false;
                }
            }
            for(Integer a: adyacentes){
                if( !(0 <= a && a < n)){
                    checkadyacentes = false;
                }
            }

            // System.out.println("predecesores de " + i);  
            // System.out.println(lessThanOrEqual.getInwardEdges(i));  

            // System.out.println("sucesores de " + i);
            // System.out.println(lessThanOrEqual.getOutwardEdges(i));
            // // List<Integer> test = lessThanOrEqual.getOutwardEdges(i);
            // // test.add(999999); //Con esta linea se vio que el metodo anterior no funcionaba
            // // System.out.println("sucesores de " + i + "Luego del cambio");
            // // System.out.println(lessThanOrEqual.getOutwardEdges(i));

            // System.out.println("vertices adyacentes de " + i);
            // System.out.println(lessThanOrEqual.getVerticesConnectedTo(i));          
        }       
        System.out.println("Fin Pruebas: metodos getInwardEdges, getOutwardEdges y getVerticesConnectedTo");
        System.out.println();

        // Pruebas para el metodo remove
        System.out.println("Pruebas: metodo remove");
        for (int i = 10; i < 2*n; i++){
            boolean testRemove = lessThanOrEqual.remove(i);
            if(!testRemove && i < n){
                System.out.println("Error. No logro eliminar un vertice que existe");
            }
            if(testRemove && i >= n){
                System.out.println("Error. Se elimino un vertice que no existe");
            }
        }

        // Se comprueba que el tamaño del conjunto de vertices es 10
        if (lessThanOrEqual.size() != 10){
            System.out.println("Error. |V| = " + lessThanOrEqual.size() + ", cuando deberia ser 10");
        }
        
        // Obtenemos todos los vertices para verificar
        System.out.println("V luego de eliminar n-10 vertices");
        System.out.println(lessThanOrEqual.getAllVertices());
        System.out.println("Fin Pruebas: metodo remove");
        System.out.println();

        // // Pruebas para el metodo disconnect   
        System.out.println("Pruebas: metodo disconnect");
        for (int i = 0; i < 10; i++){
            for (int j = i; j < 20; j++){
                boolean testDis = lessThanOrEqual.disconnect(i, j);
                if(!testDis && j < 10){
                    System.out.println("No se logro desconectar dos vertices que deberian estar conectados");
                }
                if(testDis && j >= 10){
                    System.out.println("Se logro desconectar dos vertices, pero uno de ellos no deberia ni existir");
                }
            }
        }
        System.out.println("Fin Pruebas: metodo disconnect");
        // Seria conveniente hacer un metodo para verificar la cantidad de lados y saber 
        // que estamos usando bien los metodos conectar y desconectar

        // // Pruebas para el metodo subgraph
        

        // Prueba para verificar 0(1) con el metodo size
        AdjacencyListGraph<Integer> graphTest2 = new AdjacencyListGraph<Integer>();
        System.out.println("Se procede a agregar una cantidad bastante alta de vertices");
        for (int i = 0; i < 20000; i++){
            graphTest2.add(i);
        }
        System.out.println("Ahora se procede a calcular el tamaño del conjunto de vertices");
        long start1 = System.nanoTime();
        System.out.println(graphTest2.size());
        long end1 = System.nanoTime();
        System.out.println(end1 - start1);

        
    }
}
