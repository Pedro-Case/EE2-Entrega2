public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        for (int i = 0; i < 100; i++) {
            Cliente cliente = new Cliente(i, mesa);
            cliente.start();
        }
    }
}