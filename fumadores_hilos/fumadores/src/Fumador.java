public class Fumador extends Thread {

    private String ingredientePropio;
    private Estanco estanco;

    public Fumador(String nombre, String ingredientePropio, Estanco estanco) {

        this.ingredientePropio = ingredientePropio;
        this.estanco = estanco;
    }

    @Override
    public void run() {
        try {
            while (true) {
                estanco.cogerIngredientes(ingredientePropio);

                System.out.println(getName() + " empieza a fumar...");
                long tiempo = 3000 + (long) (Math.random() * 3000);
                Thread.sleep(tiempo);
                System.out.println(getName() + " termina de fumar.");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrumpido.");
        }
    }
}
