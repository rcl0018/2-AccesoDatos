//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Estanco estanco = new Estanco();

        // Crear hilos con herencia
        Estanquero estanquero = new Estanquero(estanco);

        Fumador fumadorPapel   = new Fumador("Fumador PAPEL", "PAPEL", estanco);
        Fumador fumadorTabaco  = new Fumador("Fumador TABACO", "TABACO", estanco);
        Fumador fumadorCerilla = new Fumador("Fumador CERILLA", "CERILLA", estanco);

        // Arrancamos los hilos
        estanquero.start();
        fumadorPapel.start();
        fumadorTabaco.start();
        fumadorCerilla.start();
    }
}

/*

Mesa --> clase
cogerTenedores()
soltarTenedores()

Filosofo --> hilo
Main -->

 */