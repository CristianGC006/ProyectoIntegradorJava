public class Metodos {
    public static void main(String[] args) {
        System.out.println("Hola, bienvenido :)");
        try {
            int resultado=10/0;//Genera una exepcion
        } catch (ArithmeticException e){
            System.out.println("No se puede dividir por cero!");
        }


    }

}