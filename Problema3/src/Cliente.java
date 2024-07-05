import java.util.NoSuchElementException;

class Cliente extends Thread{
    int nome;
    Barbeiro barbeiro;
    static int counter = 0;
    boolean cabelo_cortado;
    public Cliente(int nome, Barbeiro barbeiro) {
        this.nome = nome;
        this.barbeiro = barbeiro;
    }


    public void run() {
        barbeiro.novo_cliente.lock();
        if (barbeiro.fila.size() <= barbeiro.n_cadeiras){
            if (barbeiro.fila.isEmpty()){
                System.out.println("O cliente " + nome + " foi direto para a cadeira do barbeiro.");
            }
            else{
                System.out.println("O cliente " + nome + " encontrou um lugar na fila.\nTamanho da fila: "+ barbeiro.fila.size());
            }
            barbeiro.fila.add(this);
            counter++;
            barbeiro.novo_cliente.unlock();
            while (!cabelo_cortado){
                try {
                    barbeiro.cortando_cabelo.lock();
                    if (barbeiro.dormindo && barbeiro.fila.getFirst() == this) {
                        barbeiro.interrupt();
                        barbeiro.dormindo = false;
                        System.out.println("O cliente " + nome + " acordou o barbeiro.");
                    }
                }
                catch (NoSuchElementException e){
                    this.interrupt();
                }
                finally {
                    barbeiro.cortando_cabelo.unlock();
                }
            }
        }
        else {
            System.out.println("O cliente " + nome + " não conseguiu um lugar e foi direto para casa.");
            barbeiro.novo_cliente.unlock();
        }
    }
}
