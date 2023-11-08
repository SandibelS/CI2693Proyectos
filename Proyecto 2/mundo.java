import java.io.File;
import java.io.FileReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class mundo {
    
    static LinkedList<mon> lecturaCSV(Scanner sc) {
        LinkedList<mon> lista = new LinkedList<mon>();
        sc.useDelimiter(",|(\\n)|(\r\n)");

        while(sc.hasNextLine()) {
            String nombre = sc.next();
            String nivel = sc.next();
            String atributo = sc.next();
            String poder = sc.next();

            if (!contains(nombre, lista)) {

                if (elements(atributo) && level(nivel) && power(poder)) {
                    int n = Integer.parseInt(nivel);
                    int p = Integer.parseInt(poder);
                    mon monstruo = new mon(nombre, n, atributo, p);
                    lista.add(monstruo);
                } else {
                    System.out.println("Alguno de los valores es incorrecto");
                }
               
            } 
        }
        sc.close();
        return lista;
    }
    
    static boolean contains(String nombre , LinkedList<mon> lista) {
        for(mon i: lista) {
            if (i.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    static boolean elements(String elemento) {
        String elementos[] = {"AGUA", "FUEGO", "VIENTO", "TIERRA", "LUZ", "OSCURIDAD", "DIVINO"};
        for (String i: elementos) {
            if (i.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    static boolean level(String nivel) {
        int n = Integer.parseInt(nivel);
        if (n <= 12 && 1 <= n) {
            return true;
        }
        return false;
    }

    static boolean power(String poder) {
        int p = Integer.parseInt(poder) % 50;
        if (p == 0) {
            return true;
        } else {
           return false; 
        }
    }

    static class Baraja {
        private LinkedList<mon> vertices;

        public Baraja(Scanner sc) {
            this.vertices = lecturaCSV(sc);
        }
        
        public LinkedList<mon> getBaraja() {
            return this.vertices;
        }

        public LinkedList<mon> sucesores(mon vertice) {
            LinkedList<mon> sucesores = new LinkedList<mon>();
            for(mon i: this.vertices) {
                if (!i.equals(vertice) && conexiones(vertice, i)) {
                    sucesores.add(i);
                }
            }
            return sucesores;
        }

        static boolean conexiones(mon inicio, mon ultimo) {
            if (inicio.getNivel().equals(ultimo.getNivel()) || inicio.getPoder().equals(ultimo.getPoder()) || inicio.getAtributo().equals(ultimo.getAtributo())) {
                return true;
            } else {
                return false;
            }
        }

        public void chiquito() {
            Stack<String> terna = new Stack<String>();
            for(mon i: this.vertices) {
                terna.push(i.getNombre());
                chiquitoR(1, i, terna);
                terna.pop();
            }
        }

        public void chiquitoR(int indicador, mon vertice, Stack<String> terna) {
            if (indicador != 2) {
                for(mon i: sucesores(vertice)) {
                    terna.push(i.getNombre());
                    chiquitoR(2, i, terna); 
                    terna.pop();
                }

            } else {
                for(mon i: sucesores(vertice)) {
                    terna.push(i.getNombre());
                    System.out.println(terna.toString());
                    terna.pop();
                }
            }
            
        }
    }

    public static void main(String[] args) {

        try {

            File file = new File("deck.csv");
            Scanner sc = new Scanner(file); 
            Baraja mazo = new Baraja(sc);
            mazo.chiquito();

        } catch (Exception e) {
            System.out.print("mal");
        }
    }
}

