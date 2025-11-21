public class Mesa {
    private boolean[] tendores = new boolean[5];

    public Mesa(){

    }

    public synchronized void cogerTenedor(int id) throws InterruptedException{
        int teneDorPropio = id;
        int tenedorIquierdo;
        if (teneDorPropio == 0){
             tenedorIquierdo = 4;
        }else {
            tenedorIquierdo = id-1;
        }

        if (id == 0) {
            while (tendores[tenedorIquierdo] || tendores[teneDorPropio]) {
                wait();
            }
            tendores[tenedorIquierdo] = true;
            tendores[teneDorPropio] = true;
            System.out.println("Filosofo " + id + " ha cogido los tenedores " + tenedorIquierdo + " y " + teneDorPropio);
        }
        else {
            while (tendores[teneDorPropio] || tendores[tenedorIquierdo]) {
                wait();
            }
            tendores[teneDorPropio] = true;
            tendores[tenedorIquierdo] = true;
            System.out.println("Filosofo " + id + " ha cogido los tenedores " + teneDorPropio + " y " + tenedorIquierdo);
        }


    }

    public  synchronized void soltarTenedores(int id){
        int teneDorPropio = id;
        int tenedorIquierdo;
        if (teneDorPropio == 0){
            tenedorIquierdo = 4;
        }else {
            tenedorIquierdo = id-1;
        }

        tendores[teneDorPropio] = false;
        tendores[tenedorIquierdo] = false;

        System.out.println("Filosofo  " + id + "ha soltado los tenedores " + teneDorPropio + " y " + tenedorIquierdo );

        notifyAll();

    }
}
