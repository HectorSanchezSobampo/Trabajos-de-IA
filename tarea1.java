package AI;

// ----- Clase Nodo -----
class Nodo {
    String nombre;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }
}

// ----- Clase Arbol -----
class Arbol {
    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    //  arbol vacio
    public boolean vacio() {
        return raiz == null;
    }

    // Insertar en orden alfabético
    public void insertar(String nombre) {
        raiz = insertarRec(raiz, nombre);
    }

    private Nodo insertarRec(Nodo actual, String nombre) {
        if (actual == null) return new Nodo(nombre);

        int cmp = nombre.compareTo(actual.nombre);
        if (cmp < 0) {
            actual.izquierdo = insertarRec(actual.izquierdo, nombre);
        } else if (cmp > 0) {
            actual.derecho = insertarRec(actual.derecho, nombre);
        } // si es igual no se inserta duplicado
        return actual;
    }

    // Buscar nodo por nombre 
    public Nodo buscarNodo(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Nodo buscarRec(Nodo actual, String nombre) {
        if (actual == null || actual.nombre.equals(nombre)) return actual;

        if (nombre.compareTo(actual.nombre) < 0) {
            return buscarRec(actual.izquierdo, nombre);
        } else {
            return buscarRec(actual.derecho, nombre);
        }
    }

    // Imprimir árbol en inorden 
    public void imprimirArbol() {
        imprimirRec(raiz);
        System.out.println();
    }

    private void imprimirRec(Nodo actual) {
        if (actual != null) {
            imprimirRec(actual.izquierdo);
            System.out.print(actual.nombre + " ");
            imprimirRec(actual.derecho);
        }
    }
}

// ----- Clase principal -----
public class tarea1 {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        // Insertar nodos
        arbol.insertar("Hector");
        arbol.insertar("Luis");
        arbol.insertar("Sanchez");
        arbol.insertar("Sobampo");

   
        System.out.println("Arbol en inorden:");
        arbol.imprimirArbol();

    
        System.out.println("¿El arbol está vacío? " + arbol.vacio());

      
        Nodo buscado = arbol.buscarNodo("Juan");
        if (buscado != null) {
            System.out.println("Nodo encontrado: " + buscado.nombre);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }
}

