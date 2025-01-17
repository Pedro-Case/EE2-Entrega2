import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Mesa {
    Semaphore assentos_ocupados;
    ReentrantLock atualizando_mesa;
    ReentrantLock contando;
    boolean rodando;
    int comeram;

    public Mesa(){
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
            System.out.println("Cliente " + cliente.nome + " se sentou");
        }
        finally {
            atualizando_mesa.unlock();
        }
    }

    void levantar() {
        if (comeram == 5) {
            atualizando_mesa.lock();
            assentos_ocupados.release(5);
            comeram = 0;
            System.out.println("Todos da mesa terminaram de comer, então se levantaram e sairam.");
            atualizando_mesa.unlock();
        }
    }
}