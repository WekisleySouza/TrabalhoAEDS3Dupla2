import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoRegistros {
    private List<Registro> registros;
	private int numeroDeRegistros = 800000;
    private int caso = 0;
	private int caminhoInicio = 0;
	private int caminhoFim = 0;
	private String path;
	private int acessos = 0;
    private int comparacoes = 0;
    private int trocas = 0;
    private long tempo = 0;

    public ArquivoRegistros(String path, int numeroDeRegistros){
        this.caso = 1;
        this.registros = new ArrayList<Registro>();
        this.numeroDeRegistros = numeroDeRegistros;
        this.path = path;
        this.LerArquivo(this.path, this.registros);
    }

    public ArquivoRegistros(String path, String pathBase, int inicio, int fim){
        this.caso = 2;
        this.registros = new ArrayList<Registro>();
        this.caminhoInicio = inicio;
        this.caminhoFim = fim;
        this.path = path;
        this.criarCaminho(pathBase);
    }

	public ArquivoRegistros(String path, String pathBase, int tamanhoDoCaminho){
        this.registros = new ArrayList<Registro>();
		this.caso = 3;
		this.path = path;
		this.intercalarCaminhos(pathBase);
	}

	
    public int getAcessos() {
		return acessos;
	}

	public int getComparacoes() {
		return comparacoes;
	}

	public int getTrocas() {
		return trocas;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public List<Registro> getRegistros() {
		return registros;
	}
	
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	private void intercalarCaminhos(String pathBase){
		List<List<Registro>> listaRegistros = new ArrayList<List<Registro>>();
		for(int i = 0; i < 8; i++){
			listaRegistros.add(new ArrayList<Registro>());
			listaRegistros.get(i).add(new Registro());
			this.LerArquivo(pathBase + Integer.toString(i + 1) + ".csv", listaRegistros.get(i));
		}
		fazerIntercalacao(listaRegistros);
		this.EscreverArquivo(this.path);
		System.out.println("1");

	}
	
	private void fazerIntercalacao(List<List<Registro>> listaRegistros){
		Registro menor = null;
		int indexMenor = 0;
		for(int i = 0; i < listaRegistros.size(); i++){
			if(i == 0 && listaRegistros.get(i).get(0) != null){
				menor = listaRegistros.get(i).get(0);
				indexMenor = i;
			}else if(Funcoes.comparaRegistros(menor, listaRegistros.get(i).get(0)) == 1 && listaRegistros.get(i).get(0) != null){
				indexMenor = i;
				menor = listaRegistros.get(i).get(0);
			}
		}
		this.registros.add(menor);
		listaRegistros.get(indexMenor).remove(0);
		this.removerListasVazias(listaRegistros);
		if(listaRegistros.size() != 0){
			System.out.println(listaRegistros.size());
			fazerIntercalacao(listaRegistros);
		}
	}

	private void removerListasVazias(List<List<Registro>> listaRegistros){
		for(int i = 0; i < listaRegistros.size(); i++){
			if(this.ListaEVazia(listaRegistros.get(i))){
				listaRegistros.remove(i);
			}
		}
	}

	private boolean ListaEVazia(List<Registro> listaRegistros){
		boolean resposta = true;
		for(Registro registro : listaRegistros){
			if(registro != null){
				resposta = false;
			}
		}
		return resposta;
	}

	private void criarCaminho(String pathBase){
		this.LerArquivo(pathBase, this.registros);
		MergeSort merge = new MergeSort(this.registros);
		this.EscreverArquivo(this.path);
		this.acessos = merge.getAcessos();
		this.comparacoes = merge.getComparacoes();
		this.trocas = merge.getTrocas();
		this.tempo = merge.getTempo();
	}
	
    private void LerArquivo(String path, List<Registro> registros){
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String linha = br.readLine();
			int cont = 0;
			while(linha != null){
				cont++;
				if(cont >= this.caminhoInicio && cont <= this.caminhoFim){
					registros.add(this.converteStringParaRegistro(linha));
				}
				if(cont == 1 && this.caso == 3){
					registros.remove(0);
				}
				if(this.caso == 3 || this.caso == 2){
					registros.add(this.converteStringParaRegistro(linha));
				}
				linha = br.readLine();
			}
		}
		catch(FileNotFoundException erro){
            if(this.caso == 1){
                for(int i = 0; i < this.numeroDeRegistros; i++){
                    this.criaRegistro();
                }
            }
			this.EscreverArquivo(path);
		}
		catch(IOException erro){
			System.out.println("Error: " + erro);
		}
	}

    private void criaRegistro(){
		Registro registro = gera_registro(Funcoes.gera_numero_aleatorio(0, 100));
		this.registros.add(registro);
	}
	
	private void EscreverArquivo(String path){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(Registro linha : this.registros){
				bw.write(this.converteRegistroParaString(linha));
				bw.newLine();
			}
		}
		catch(IOException erro){
			erro.printStackTrace();
		}
	}
	
	private String converteRegistroParaString(Registro registro){
		return (String) "" + registro.getId() + ";" + registro.getCep() + ";" + registro.getNome() + ";" + registro.getIdade() + ";" + 
		registro.getSexo() + ";" + registro.getCpf() + ";";
	}

	private Registro converteStringParaRegistro(String texto){
		String[] registroAtual = texto.split(";");
		int id = Integer.parseInt(registroAtual[0]);
		String cep = registroAtual[1];
		String nome = registroAtual[2];
		int idade = Integer.parseInt(registroAtual[3]);
		char sexo = registroAtual[4].charAt(0);
		String cpf = registroAtual[5];
		return (Registro) new Registro(id, cep, nome, idade, sexo, cpf);
	}

	private Registro gera_registro(int id){
		Registro registro = new Registro();
		registro.setNome(this.gera_nome());
		registro.setIdade(this.gera_idade());
		registro.setSexo(this.gera_sexo());
		registro.setCpf(gera_cpf());
		registro.setCep(this.gera_cep());
		registro.setId(id);
		return registro;
	}

    private String gera_nome(){
		String letras = "abcdefghijklmnopqrstuvwxyza"; 
		String nome = "";
		for(int i = 0; i < 10; i++){
			int numero = Funcoes.gera_numero_aleatorio(0, 26);
			String letra = letras.substring(numero, numero + 1);
			if(i == 0){
				nome += letra.toUpperCase();
			}else{
				nome += letra;
			}
		}
		return nome;
	}
    
    private int gera_idade(){
		return Funcoes.gera_numero_aleatorio(10, 50);
	}

	private char gera_sexo(){
		String letras = "MF";
		int numero = Funcoes.gera_numero_aleatorio(0, 1);
		char sexo = letras.charAt(numero);
		return sexo;
	}

	private String gera_cpf(){
		String cpf = "";
		for(int i = 1; i < 12; i++){
			cpf += Integer.toString(Funcoes.gera_numero_aleatorio(0, 9));
			if(i % 3 == 0 && i != 9){
				cpf += ".";
			}else if(i == 9){
				cpf += "-";
			}
		}
		return cpf;
	}

	public String gera_cep(){
		String cep = "";
		for(int i = 0; i < 8; i++){
			cep += Integer.toString(Funcoes.gera_numero_aleatorio(0, 9));
			if(i == 4){
				cep += "-";
			}
		}
		return cep;
	}
}