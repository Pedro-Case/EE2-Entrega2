import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Barbeiro barbeiro = new Barbeiro(10);
        barbeiro.start();
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int n = 0; n < 100; n++) {
            Cliente cliente = new Cliente(n, barbeiro);
            cliente.start();
            clientes.add(cliente);
        }
        for (Cliente cliente : clientes) {
            cliente.join();
        }
        System.out.println("Fim do expediente");
        barbeiro.terminou = true;
        barbeiro.interrupt();

    }
}