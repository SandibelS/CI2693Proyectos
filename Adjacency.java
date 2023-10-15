import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

class AdjancencyListGraph<T> {
    // Creamos una lista que contendra los vertices, es decir, las instancias de V 
    List<vertice<T>> conjunto_V = new LinkedList<vertice<T>>();
    List<T> V = new LinkedList<T>();
    private int cardinalidad_V = 0;
    private int cardinalidad_E = 0;

    // Creamos una clase vertice que tendra el atributo valor y una lista de arcos con respecto al vertice
    static class vertice<T> {
        T valor;
        int sucesores = 0;
        List<T> arcos = new LinkedList<T>();
    }
    // Metodo agregar vertice
    boolean add(T vertex){
        // Verifica si el vertice pertenece al conjunto V
        // Si no pertenece crea un vertice con valor vertex y un atributo lista que funcionara como arcos
        // En caso contrario se indica a la persona que ya se encuentra  
        if(!this.contains(vertex)) {
            vertice<T> vertice = new vertice<T>();
            vertice.valor = vertex;
            this.conjunto_V.add(vertice);
            V.add(vertex);
            cardinalidad_V++;
            System.out.println("Agregado vertice de forma exitosa");
            return true;
        } else {
            System.out.println("No fue agregado, ya pertenece al conjutno V");
            return false;
        }
    }
    // Metodo para conectar dos vertices que pertenescan al conjunto 
    boolean connect(T from, T to) {
        if (this.contains(from) && this.contains(to)) {
            for (vertice<T> i: conjunto_V ) {
                if (i.valor.equals(from)) {
                    i.arcos.add(to);
                    i.sucesores++;
                    this.cardinalidad_E++;
                    break;
                }
            }
            System.out.println("Arco generado de forma exitosa");
            return true;
        } else {
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }
        
    }
    // Metodo que elimina el arco de un par de vertices
    boolean disconnect(T from, T to) {
        if (this.contains(from) && this.contains(to)) {
            for (vertice<T> i: conjunto_V ) {
                if (i.valor.equals(from)) {
                    i.arcos.remove(to);
                    i.sucesores--;
                    this.cardinalidad_E--;
                    break;
                }
            }
            System.out.println("Arco borrado de forma exitosa");
            return true;
        } else {
            System.out.println("Alguno de los vertices no pertenece al conjunto");
            return false;
        }
    }
    // Metodo contains, verifica si pertenece al conjunto V
    boolean contains(T vertex) {
        for (vertice<T> i: conjunto_V) {
            if (i.valor.equals(vertex)) {
                // System.out.println("Si pertenece al conjunto");
                return true;
            }
        }
        // System.out.println("No pertenece al conjunto");
        return false;
    }
    // Metodo getInwardEdges, recibimos un vertice y retornar la lista de predecesores de ese vertice 
    List<T> getInwardEdges(T to) {
        List<T> referencia_V = new LinkedList<>();
        if (this.contains(to)) {
            for (vertice<T> i: conjunto_V) {
                if (!i.valor.equals(to) && i.arcos.contains(to)) {
                    referencia_V.add(i.valor);
                }
            }
            System.out.print("Predecesores de "+ to + ":" + referencia_V + "\n");
            return referencia_V;
        } else {
            return null;
        }
    }
    // Metodo getOutwardEdges, recibimos un vertice y retornamos la lista de sucesores de ese vertice
    List<T> getOutwardEdges(T from) {
        if (this.contains(from)) {
            for (vertice<T> i: conjunto_V) {
                if (i.valor.equals(from)) {
                    System.out.print("Sucesores de "+ from + ":" + i.arcos+ "\n");
                    return i.arcos;
                }
            }
        }
        return null;
    }
    // Metodo getAllVertices, se retorna todos los vertices
    List<T> getAllVertices() {
        System.out.print("El conjunto vertices es: " + V + "\n");
        return V;
    }
    // Metodo remove, se recibe un vertice y lo eliminamos del grafo 
    boolean remove(T vertex) {
        if (this.contains(vertex)) {
            // Eliminacion del vertice 
            for (vertice<T> i: conjunto_V) {
                if (i.valor.equals(vertex)) {
                    cardinalidad_E = cardinalidad_E - i.sucesores;
                    cardinalidad_V--;
                    int posicionV = conjunto_V.indexOf(i);
                    conjunto_V.remove(posicionV);
                    V.remove(vertex);
                    break;
                }
            }
            // Eliminacion de los arcos 
            for (vertice<T> i: conjunto_V) {
                if (i.arcos.contains(vertex)) {
                    i.arcos.remove(vertex);
                    cardinalidad_E--;
                }
            }
            System.out.println("Vertice eliminado con exito");
            return true;
        }
        System.out.println("El vertice no pertenece al conjunto V");
        return false;
    }
    // Metodo size, retorna el la cardinadlidad de vertices que tiene el grafo
    int size() {
        System.out.print("El tamaño del conjunto vertices es: " + cardinalidad_V + "\n");
        return cardinalidad_V;
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
        System.out.println(listaAdyacencias.cardinalidad_V);
        System.out.println(listaAdyacencias.cardinalidad_E);
    }
}