package AI;

                           //INVESTIGACION

/* El algoritmo de Dijkstra es un algoritmo clásico de búsqueda de caminos en grafos.
En IA se utiliza para encontrar la ruta más corta entre un estado inicial y un estado meta,
 algo muy común en problemas de planificación, robótica y juegos.*/

/* -Se parte de un nodo inicial.

   -Se asigna distancia 0 al nodo de inicio y ∞ (infinito) a los demás.

   -Se van visitando los nodos, actualizando la distancia más corta conocida hasta cada uno.

   -Se marca un nodo como "visitado" cuando ya se conoce su distancia mínima.

   -Se repite hasta recorrer todos los nodos.*/

//CODIGO
import java.util.*;


public class tarea3 {
    public static void dij(int[][] grafo, int inicio) {
        int n = grafo.length;  
        int[] distancia = new int[n];  
        boolean[] visitado = new boolean[n];

        // Inicializalizar distancias 
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[inicio] = 0; 

        // Recorrer
        for (int i = 0; i < n - 1; i++) {
            //  el nodo con el camino más corto que no se  haya visitado
            int u = nodoMinimo(distancia, visitado);
            visitado[u] = true; 

           
            for (int v = 0; v < n; v++) {
                // Si existe conexión (grafo[u][v] != 0) y encontramos un camino más corto, lo actualizamos
                if (!visitado[v] && grafo[u][v] != 0 &&
                    distancia[u] != Integer.MAX_VALUE &&
                    distancia[u] + grafo[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + grafo[u][v];
                }
            }
        }

        //  resultados
        System.out.println("Camino más corto desde el nodo inicial:");
        for (int i = 0; i < n; i++) {
            System.out.println("Hasta nodo " + i + " - " + distancia[i]);
        }
    }

    // Método auxiliar este  encuentra el nodo con menor camino que no esté visitado
    private static int nodoMinimo(int[] distancia, boolean[] visitado) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < distancia.length; i++) {
            if (!visitado[i] && distancia[i] <= min) {
                min = distancia[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    
    public static void main(String[] args) {

        int[][] grafo = {
            {0, 2, 4, 0},
            {2, 0, 1, 7},
            {4, 1, 0, 3},
            {0, 7, 3, 0}
        };

        dij(grafo, 0);
    }
}

