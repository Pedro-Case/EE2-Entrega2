<<<<<<< Updated upstream
public class Cliente extends Thread{
    int nome;
    Assentos assento;

    public Cliente(int nome, Assentos assento) {
        this.nome = nome;
        this.assento = assento;
    }

    public void run(){
        try {
            System.out.println("Cliente " + nome + " entrou no restaurante");
            assento.sentar(this);
            sleep(500);
            assento.atualizando_mesa.lock();
            assento.comeram++;
            assento.sentar_assentos();
            System.out.println(nome + " terminou de comer " + assento.comeram);
            assento.atualizando_mesa.unlock();

        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
=======
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
>>>>>>> Stashed changes
}