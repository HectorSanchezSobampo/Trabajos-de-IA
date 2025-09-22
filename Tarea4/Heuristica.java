package IAU1;

public class Heuristica {

    public static int heuristica(int actual, int objetivo, int pasos) {
        int distancia = Math.abs(objetivo - actual); 
        int penalizacion = pasos; 
        return distancia + penalizacion;
    }

    public static void main(String[] args) {
        int inicio = 2;
        int objetivo = 10;

        int actual = inicio;
        int pasos = 0;

        System.out.println("Buscando desde " + inicio + " hasta " + objetivo);
        System.out.print("Camino: " + actual);

        while (actual != objetivo && pasos < 20) {
            pasos++;

            int moverAdelante = actual + 1;
            int moverAtras = actual - 1;

            int hAdelante = heuristica(moverAdelante, objetivo, pasos);
            int hAtras = heuristica(moverAtras, objetivo, pasos);

            if (hAdelante <= hAtras) {
                actual = moverAdelante;
            } else {
                actual = moverAtras;
            }

            System.out.print(" -> " + actual);
        }

        System.out.println("\nMeta alcanzada en " + pasos + " pasos.");
    }
}
