public class Cronometro {
    private long tempo;

	// marca a variável tempo com o tempo atual em epoch(nanoseg.)
	public void comecar(){
		tempo = System.nanoTime();
	}

	// subtrai o tempo atual em epoch(nanoseg.) com a variável tempo
	public long parar(){
		tempo = System.nanoTime() - this.tempo;
        return getTempo();
	}

	// Retorna o valor da variável tempo em milissegundos
	public long getTempo(){
		return tempo/(long)1000000;
	}
}
