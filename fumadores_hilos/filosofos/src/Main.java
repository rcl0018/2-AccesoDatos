//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Mesa m1 = new Mesa();
        Filosofo f0 = new Filosofo("Socrtates",0,m1);
        Filosofo f1 = new Filosofo("Platon",1,m1);
        Filosofo f2 = new Filosofo("Descartes",2,m1);
        Filosofo f3 = new Filosofo("Miguel",3,m1);
        Filosofo f4 = new Filosofo("Pedro",4,m1);


        f0.start();
        f1.start();
        f2.start();
        f3.start();
        f4.start();

    }
}