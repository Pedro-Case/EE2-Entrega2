import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Assentos extends Thread{
    Semaphore assentos_ocupados;
    ReentrantLock atualizando_mesa;
    ReentrantLock contando;
    boolean rodando;
    int comeram;

    public Assentos(){
        this.assentos_ocupados = new Semaphore(5);
        this.atualizando_mesa = new ReentrantLock(true);
        this.contando = new ReentrantLock(true);
        this.rodando = true;
        this.comeram = 0;
    }

    void sentar(Cliente cliente) throws InterruptedException {
        try{
            assentos_ocupados.acquire(1);
            atualizando_mesa.lock();
            System.out.println("Cliente " + cliente.nome + " se sentou\nAssentos disponíveis: " + assentos_ocupados.availablePermits());
        }
        finally {
            atualizando_mesa.unlock();
        }



    }

    void sentar_assentos() {
        if (comeram == 5) {
            System.out.println(comeram);
            atualizando_mesa.lock();
            assentos_ocupados.release(5);
            comeram = 0;
            System.out.println("Todos que terminaram de comer se levantaram");
            System.out.println(assentos_ocupados.availablePermits());
            atualizando_mesa.unlock();
        }

    }

}