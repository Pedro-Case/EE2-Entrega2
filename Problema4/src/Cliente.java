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
}