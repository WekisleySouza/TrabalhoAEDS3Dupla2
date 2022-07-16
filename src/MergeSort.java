import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    private List<Registro> registrosMerge;
    private int acessos = 0;
    private int comparacoes = 0;
    private int trocas = 0;
    private long tempo = 0;
    
    public MergeSort(List<Registro> listaRegistros){
        this.registrosMerge = new ArrayList<Registro>(listaRegistros);
        this.tempo = Funcoes.pegaTempo();
        this.performaOrdenacao(listaRegistros, this.registrosMerge, 0, listaRegistros.size() - 1);
        this.tempo = Funcoes.pegaTempo() - this.tempo;
    }
    
    public int getTrocas() {
        return trocas;
    }

    public void setTrocas(int trocas) {
        this.trocas = trocas;
    }


    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }
    
    public int getAcessos() {
        return acessos;
    }
    
    public void setAcessos(int acessos) {
        this.acessos = acessos;
    }
    
    public int getComparacoes() {
        return comparacoes;
    }
    
    public void setComparacoes(int comparacoes) {
        this.comparacoes = comparacoes;
    }

    // Ordena o vetor usando MergeSort
    private void performaOrdenacao(List<Registro> registros, List<Registro> registrosMerge, int inicio, int fim) {
        if(inicio < fim) {
            this.comparacoes++;
            int meio = (inicio + fim) / 2;
            performaOrdenacao(registros, registrosMerge, inicio, meio);
            performaOrdenacao(registros, registrosMerge, meio + 1, fim);
            merge(registros, registrosMerge, inicio, meio, fim);
        }
    }

    // Método que realiza as trocas dp MergeSort.
    public void merge(List<Registro> registros, List<Registro> registrosMerge, int inicio, int meio, int fim) {
        for(int k = inicio; k <= fim; k++) {
            registrosMerge.set(k, registros.get(k));
            acessos++;
        }
        int i = inicio;
        int j = meio + 1;
        
        for(int k = inicio; k <= fim; k++) {
            if(i > meio) {
                registros.set(k, registrosMerge.get(j++));
                this.acessos++;
                this.trocas++;
                this.comparacoes++;

            }else if (j > fim){
                registros.set(k, registrosMerge.get(i++));
                this.acessos++;
                this.trocas++;
                this.comparacoes += 2;

            }else if(Funcoes.comparaRegistros(registrosMerge.get(i), registrosMerge.get(j)) == -1) {
                registros.set(k, registrosMerge.get(i++));
                this.trocas++;
                this.acessos += 3;
                this.comparacoes += 3;
            }else {
                registros.set(k, registrosMerge.get(j++));
                this.trocas++;
                this.acessos += 3;
                this.comparacoes += 3;
            }
        }
    }
}