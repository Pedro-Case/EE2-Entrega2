public class Cliente extends Thread{
    int nome;
    Mesa mesa;

    public Cliente(int nome, Mesa mesa) {
        this.nome = nome;
        this.mesa = mesa;
    }

    public void run(){
        try {
            System.out.println("Cliente " + nome + " entrou no restaurante");
            mesa.sentar(this);
            sleep(500);
            mesa.atualizando_mesa.lock();
            mesa.comeram++;
            System.out.println(nome + " terminou de comer. Faltam " + (5 - mesa.comeram) + " clientes terminarem de comer");
            mesa.levantar();
            mesa.atualizando_mesa.unlock();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}