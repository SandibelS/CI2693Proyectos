import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
    
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

            // for(Integer p: predecesores){
            //     if( !(0 <= p && p <= i) ){
            //         checkPredecesores = false;
            //     }
            // }
            // for(Integer s: sucesores){
            //     if( !(i <= s && s < n)){
            //         checkSucesores = false;
            //     }
            // }
            // for(Integer a: adyacentes){
            //     if( !(0 <= a && a < n)){
            //         checkadyacentes = false;
            //     }
            // // }
            // System.out.println(checkPredecesores);            
            // System.out.println(checkSucesores);
            // System.out.println(checkadyacentes);

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



        // // Pruebas para el metodo subgraph
        System.out.println("Pruebas:subgrafo");

        for (int i = 0; i < 11; i++){
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < i; j++){
                list.add(j);
            }
            System.out.println("i: " + i);
            Graph<Integer> Sub = lessThanOrEqual.subgraph(list);
            System.out.println(Sub.getAllVertices());
            for (Integer j: Sub.getAllVertices()){
                System.out.println("Vertice: " + j + ". Sucesores: ");
                System.out.println(Sub.getOutwardEdges(j));
            }
            
        }

        System.out.println("Fin Pruebas:subgrafo");


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
