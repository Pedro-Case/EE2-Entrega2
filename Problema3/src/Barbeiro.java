import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

class Barbeiro extends Thread{
    boolean terminou;
    boolean dormindo;
    ReentrantLock cortando_cabelo;
    ReentrantLock novo_cliente;
    int n_cadeiras;
    ArrayList<Cliente> fila;

    public Barbeiro(int n_cadeiras){
        this.terminou = false;
        this.cortando_cabelo = new ReentrantLock();
        this.novo_cliente = new ReentrantLock(true);
        this.n_cadeiras = n_cadeiras;
        this.fila = new ArrayList<>();
        this.dormindo = false;
    }
    void cortar_cabelo(){
        try{
            cortando_cabelo.lock();
            Cliente cliente = fila.removeFirst();
            cliente.cabelo_cortado = true;
            System.out.println("Barbeiro cortou o cabelo do cliente " + cliente.nome);
        }
        finally {
            cortando_cabelo.unlock();
        }

    }

    public void run(){
        while(!terminou){
            if (!fila.isEmpty()){
                cortar_cabelo();
            }
            else {
                try {
                    System.out.println("Barbeiro foi dormir já que não tinha ninguém para atender" + terminou);
                    dormindo = true;
                    sleep(10000);
                }
                catch (InterruptedException e) {}
            }
        }

    }
}
