package IAU1;


public class Main {
    public static void main(String[] args) {
        // Estado inicial 
        int[][] estadoInicial = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8}
        };

        // Estado objetivo 
        int[][] estadoObjetivo = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        System.out.println("=== Búsqueda en Anchura  ===");
        Anchura.resolver(estadoInicial);

        System.out.println("\n=== Búsqueda en Profundidad  ===");
        Profundidad.resolver(estadoInicial);

        System.out.println("\n=== Búsqueda de Costo Uniforme  ===");
        CostoUniforme.resolver(estadoInicial);

        System.out.println("\n=== Búsqueda Bidireccional ===");
        Bidireccional.resolver(estadoInicial, estadoObjetivo);
    }
}
