public class Main {
    public static void main(String[] args) {
        Assentos assentos = new Assentos();
        assentos.start();
        for (int i = 0; i < 100; i++) {
            Cliente cliente = new Cliente(i, assentos);
            cliente.start();
        }
    }
}