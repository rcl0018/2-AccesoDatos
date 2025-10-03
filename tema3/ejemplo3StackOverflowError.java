package tema3;

public class ejemplo3StackOverflowError {
    public static void main(String[] args) {
        
recursiveFuction();

    }

    public static void recursiveFuction(){
        recursiveFuction();
    }
}
