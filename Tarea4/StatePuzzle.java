package IAU1;


import java.util.Arrays;

public class StatePuzzle {
    int[][] tablero;       // Estado del tablero
    int filaVacia;         // Fila del 0
    int columnaVacia;      // Columna del 0
    int nivel;             // Profundidad o costo acumulado
    StatePuzzle padre;     // Estado anterior (para reconstruir la solución)

    public StatePuzzle(int[][] tablero, int filaVacia, int columnaVacia, int nivel, StatePuzzle padre) {
        this.tablero = tablero;
        this.filaVacia = filaVacia;
        this.columnaVacia = columnaVacia;
        this.nivel = nivel;
        this.padre = padre;
    }

    // Comprueba si este tablero es la solución
    public boolean esSolucion() {
        int[][] solucion = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        return Arrays.deepEquals(this.tablero, solucion);
    }

    // Necesario para que HashSet y HashMap funcionen correctamente
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StatePuzzle)) return false;
        StatePuzzle otro = (StatePuzzle) obj;
        return Arrays.deepEquals(this.tablero, otro.tablero);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.tablero);
    }
}
