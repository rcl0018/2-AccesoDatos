package tema3;

public class ejemplo4AritmeticError {
    public static void main(String[] args) {

        try {
            int divididendo = 10;
            int divisor = 0;

            int resultado = divididendo / divisor;
            System.out.println(resultado);
        } catch (ArithmeticException e) {
            System.out.println("error Aritmetico.:" + e.getMessage());
            
        }finally{}
    }
}
