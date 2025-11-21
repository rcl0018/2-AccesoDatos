public class Estanquero extends Thread {

    private Estanco estanco;

    public Estanquero(Estanco estanco) {

        this.estanco = estanco;
    }

    @Override
    public void run() {

        String[][] combinaciones = {
                {"PAPEL", "TABACO"},
                {"PAPEL", "CERILLA"},
                {"TABACO", "CERILLA"}
        };

        try {
            while (true) {
                int idx = (int) (Math.random() * combinaciones.length);

                String i1 = combinaciones[idx][0];
                String i2 = combinaciones[idx][1];

                estanco.ponerIngredientes(i1, i2);
            }
        } catch (InterruptedException e) {
            System.out.println("Estanquero interrumpido.");
        }
    }
}

