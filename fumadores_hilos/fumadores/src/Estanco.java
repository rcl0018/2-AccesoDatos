public class Estanco {

    private String ing1 = null; // primer ingrediente en el mostrador
    private String ing2 = null; // segundo ingrediente en el mostrador
    private boolean hayIngredientes = false;

    // El estanquero pone dos ingredientes
    public synchronized void ponerIngredientes(String i1, String i2) throws InterruptedException {
        // Esperar si el mostrador aún no está vacío
        while (hayIngredientes) {
            wait();
        }

        ing1 = i1;
        ing2 = i2;
        hayIngredientes = true;

        System.out.println("Estanquero pone en el mostrador: " + i1 + " y " + i2);

        notifyAll(); // avisar a fumadores
    }

    // El fumador intenta coger sus dos ingredientes faltantes
    public synchronized void cogerIngredientes(String ingredientePropio) throws InterruptedException {
        // Espera mientras:
        // - No haya ingredientes
        // - O los ingredientes NO sean los que necesita el fumador
        while (!hayIngredientes || !sonParaFumador(ingredientePropio)) {
            wait();
        }

        System.out.println(">>> Fumador con " + ingredientePropio +
                " coge: " + ing1 + " y " + ing2);

        // Vaciamos el mostrador
        hayIngredientes = false;
        ing1 = null;
        ing2 = null;

        notifyAll(); // avisar al estanquero
    }

    // Comprueba si los ingredientes sirven para el fumador
    private boolean sonParaFumador(String ingredientePropio) {

        // Si alguno coincide con el que ya tiene, no sirve
        if (ingredientePropio.equals(ing1) || ingredientePropio.equals(ing2)) {
            return false;
        }
        // Los dos son los que le faltan
        return true;
    }
}
