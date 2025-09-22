package IAU1;

import java.util.*;

public class Bidireccional {
    public static void resolver(int[][] estadoInicial, int[][] estadoObjetivo) {
        Queue<StatePuzzle> colaInicio = new LinkedList<>();
        Queue<StatePuzzle> colaObjetivo = new LinkedList<>();

        Map<StatePuzzle, StatePuzzle> visitadosInicio = new HashMap<>();
        Map<StatePuzzle, StatePuzzle> visitadosObjetivo = new HashMap<>();

        StatePuzzle inicio = new StatePuzzle(estadoInicial,
                encontrarFilaVacia(estadoInicial),
                encontrarColumnaVacia(estadoInicial), 0, null);

        StatePuzzle objetivo = new StatePuzzle(estadoObjetivo,
                encontrarFilaVacia(estadoObjetivo),
                encontrarColumnaVacia(estadoObjetivo), 0, null);

        colaInicio.add(inicio);
        colaObjetivo.add(objetivo);
        visitadosInicio.put(inicio, null);
        visitadosObjetivo.put(objetivo, null);

        while (!colaInicio.isEmpty() && !colaObjetivo.isEmpty()) {
            StatePuzzle interseccion = expandir(colaInicio, visitadosInicio, visitadosObjetivo);
            if (interseccion != null) {
                imprimirSolucion(interseccion, visitadosInicio, visitadosObjetivo);
                return;
            }

            interseccion = expandir(colaObjetivo, visitadosObjetivo, visitadosInicio);
            if (interseccion != null) {
                imprimirSolucion(interseccion, visitadosInicio, visitadosObjetivo);
                return;
            }
        }
        System.out.println("No se encontró solución.");
    }

    private static StatePuzzle expandir(Queue<StatePuzzle> cola,
                                        Map<StatePuzzle, StatePuzzle> propios,
                                        Map<StatePuzzle, StatePuzzle> opuestos) {
        if (cola.isEmpty()) return null;

        StatePuzzle actual = cola.poll();
        for (StatePuzzle vecino : generarVecinos(actual)) {
            if (!propios.containsKey(vecino)) {
                propios.put(vecino, actual);
                cola.add(vecino);

                if (opuestos.containsKey(vecino)) {
                    return vecino;
                }
            }
        }
        return null;
    }

    private static List<StatePuzzle> generarVecinos(StatePuzzle estado) {
        List<StatePuzzle> vecinos = new ArrayList<>();
        int[] movimientosFila = {-1, 0, 1, 0};
        int[] movimientosColumna = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nuevaFila = estado.filaVacia + movimientosFila[i];
            int nuevaColumna = estado.columnaVacia + movimientosColumna[i];

            if (nuevaFila >= 0 && nuevaFila < 3 && nuevaColumna >= 0 && nuevaColumna < 3) {
                int[][] nuevoTablero = copiarTablero(estado.tablero);
                nuevoTablero[estado.filaVacia][estado.columnaVacia] = nuevoTablero[nuevaFila][nuevaColumna];
                nuevoTablero[nuevaFila][nuevaColumna] = 0;
                vecinos.add(new StatePuzzle(nuevoTablero, nuevaFila, nuevaColumna, estado.nivel + 1, estado));
            }
        }
        return vecinos;
    }

    private static int[][] copiarTablero(int[][] original) {
        int[][] copia = new int[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(original[i], 0, copia[i], 0, 3);
        return copia;
    }

    private static void imprimirSolucion(StatePuzzle interseccion,
                                         Map<StatePuzzle, StatePuzzle> inicio,
                                         Map<StatePuzzle, StatePuzzle> objetivo) {
        List<StatePuzzle> caminoInicio = new ArrayList<>();
        List<StatePuzzle> caminoObjetivo = new ArrayList<>();

        StatePuzzle actual = interseccion;
        while (actual != null) {
            caminoInicio.add(actual);
            actual = inicio.get(actual);
        }
        Collections.reverse(caminoInicio);

        actual = objetivo.get(interseccion);
        while (actual != null) {
            caminoObjetivo.add(actual);
            actual = objetivo.get(actual);
        }

        System.out.println("\nSolución encontrada (Bidireccional):");
        for (StatePuzzle estado : caminoInicio) {
            imprimirTablero(estado.tablero);
            System.out.println();
        }
        for (StatePuzzle estado : caminoObjetivo) {
            imprimirTablero(estado.tablero);
            System.out.println();
        }
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    private static int encontrarFilaVacia(int[][] tablero) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (tablero[i][j] == 0) return i;
        return -1;
    }

    private static int encontrarColumnaVacia(int[][] tablero) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (tablero[i][j] == 0) return j;
        return -1;
    }
}
