public class Filosofo extends Thread{

    private String nombre;
    private int id;
    private Mesa m1;

    public Filosofo(String nombre,int id, Mesa m1){
        this.nombre = nombre;
        this.id = id;
        this.m1 = m1;

    }

    public void pensar() throws InterruptedException {
        System.out.println("Filosofo " + nombre + "esta pensando");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
    }

    public void comer() throws InterruptedException {
        System.out.println("Filosofo " + nombre + "esta comiedno");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
    }

    public void run(){
        try {
            while (true) {
                pensar();
                m1.cogerTenedor(id);
                comer();
                m1.soltarTenedores(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
