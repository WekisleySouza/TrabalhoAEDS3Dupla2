import java.io.File;

public class Arquivos {
    private int numeroDeRegistros = 0;
    private int acessosTotal = 0;
    private int comparacoesTotal = 0;
    private int trocasTotal = 0;
    private long tempoTotal = 0;
    private long[] tempoCaminhos = new long[8];
    private long[] acessosCaminhos = new long[8];
    private long[] trocasCaminhos = new long[8];
    private long[] comparacoesCaminhos = new long[8];

    
    public Arquivos(int numeroDeRegistros){
        this.deletarArquivo("RegistrosPrimeiraEtapa//RegistroInicial.csv");
        for(int i = 1; i < 9; i++){
            this.deletarArquivo("RegistrosSegundaEtapa//Caminho_" + i + ".csv");
        }
        this.deletarArquivo("RegistrosTerceiraEtapa//RegistroFinal.csv");

        if(numeroDeRegistros % 8 == 0){
            this.numeroDeRegistros = numeroDeRegistros;
            this.criarArquivoComRegistros();
        }else{
            System.out.println("Este programa utiliza a intercalação balançeada de 8 caminhos. Favor, escolher um número válido para o teste!");
        }
    }
    
    public long[] getTempoCaminhos() {
        return tempoCaminhos;
    }

    public long[] getAcessosCaminhos() {
        return acessosCaminhos;
    }

    public long[] getTrocasCaminhos() {
        return trocasCaminhos;
    }

    public long[] getComparacoesCaminhos() {
        return comparacoesCaminhos;
    }

    public int getAcessosTotal() {
        return acessosTotal;
    }

    public int getComparacoesTotal() {
        return comparacoesTotal;
    }

    public int getTrocasTotal() {
        return trocasTotal;
    }

    public long getTempoTotal() {
        return tempoTotal;
    }

    private void criarArquivoComRegistros(){
        new ArquivoRegistros("RegistrosPrimeiraEtapa//RegistroInicial.csv", this.numeroDeRegistros);
    }

    public void comecarOrdenacao(){
        this.ordenarSeparandoEmCaminhos();
        this.fazerIntercalacao();
    }

    private void ordenarSeparandoEmCaminhos(){
        int tamanhoCaminhos = this.numeroDeRegistros / 8;
        int tamanhoCaminhosFinal = tamanhoCaminhos;
        int tamanhoCaminhosInicio = 1;
        for(int i = 0; i < 8; i++){
            ArquivoRegistros arquivo = new ArquivoRegistros("RegistrosSegundaEtapa//Caminho_" + (i + 1) + ".csv", "RegistrosPrimeiraEtapa//RegistroInicial.csv", tamanhoCaminhosInicio, tamanhoCaminhosFinal);
            tamanhoCaminhosFinal += tamanhoCaminhos;
            tamanhoCaminhosInicio += tamanhoCaminhos;
            this.acessosCaminhos[i] = arquivo.getAcessos();
            this.comparacoesCaminhos[i] = arquivo.getComparacoes();
            this.trocasCaminhos[i] = arquivo.getTrocas();
            this.tempoCaminhos[i] = arquivo.getTempo();
        }
    }

    private void fazerIntercalacao(){
        int tamanhoCaminhos = this.numeroDeRegistros / 8;
        ArquivoRegistros arquivo = new ArquivoRegistros("RegistrosTerceiraEtapa//RegistroFinal.csv", "RegistrosSegundaEtapa//Caminho_", tamanhoCaminhos);
    }

    private void deletarArquivo(String path){
        File arquivo = new File(path);
        arquivo.delete();
    }
}