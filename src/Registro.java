import java.util.ArrayList;
import java.util.List;

public class Registro {
    private int id;
	private String cep;
	private char sexo;
	private int idade;
	private String nome;
	private String cpf;
	
	// Construtor
	public Registro() {
		this.id = 0;
		this.cep = "";
		this.sexo = ' ';
		this.idade = 0;
		this.nome = "";
		this.cpf = "";
	}

	// Compara dois registros
    public static int comparaRegistros(Registro a, Registro b){
        int[][] paresDeAtributos = {
            {a.getCepComoInt(), b.getCepComoInt()},
            {a.getSexoAsciiNumber(), b.getSexoAsciiNumber()},
            {a.getIdade(), b.getIdade()},
            {a.getNomeComparandoCom(b), b.getNomeComparandoCom(a)}
        };

        for(int[] par : paresDeAtributos){
            if(par[0]<par[1]){
                return -1;
            }
            if(par[0]>par[1]){
                return 1;
            }
        }
        return 0;
    }

	// Retorna cep como inteiro
    public int getCepComoInt(){
        return Integer.parseInt(getCep().replace("-", ""));
    }

	// Retorna sexo de acordo com a tabela ascci
    public int getSexoAsciiNumber(){
        return (int)getSexo();
    }

	// Obtém um número de acordo com qual nome é menor (-2 --> primeiro é menor; 0 iguais; 2 --> O do parâmetro é menor)
    public int getNomeComparandoCom(Registro outro){
        return getNome().compareTo(outro.getNome());
    }

	//Preenche o registro com dados aleatórios
	public void preencherAleatorio(){
		setIdade(Gerar.idade());
		setSexo(Gerar.sexo());
		setNome(Gerar.nomeCompleto(this.sexo));
		setCpf(Gerar.cpf());
		setCep(Gerar.cep());
	}

	// Transforma string em registro
	static Registro fromCSV(String csvString){
		String[] atributos = csvString.split(";");
		Registro registro = new Registro();
		registro.setId(Integer.parseInt(atributos[0]));
		registro.setCep(atributos[1]);
		registro.setNome(atributos[2]);
		registro.setIdade(Integer.parseInt(atributos[3]));
		registro.setSexo(atributos[4].charAt(0));
		registro.setCpf(atributos[5]);
		return registro;
	}

	// Transforma uma lista de strings em uma lista de registros
	static List<Registro> manyFromCSV(String[] linhas){
		List<Registro> registros = new ArrayList<Registro>();
		for (String linha : linhas){
			registros.add(fromCSV(linha));
		}
		return registros;
	}

	// Retorna registro em forma de string
	public String toCSV(){
		return String.join( ";", 
		String.valueOf(getId()), 
		getCep(), 
		getNome(), 
		String.valueOf(getIdade()), 
		String.valueOf(getSexo()), 
		getCpf());
	}
	
	// SETTERS E GETTERS 
	public String getCep(){	return this.cep; }
	public void setCep(String cep){	this.cep = cep;	}

	public int getId(){	return this.id;	}
	public void setId(int id){ this.id = id; }

	public char getSexo(){ return this.sexo; }
	public void setSexo(char sexo){	this.sexo = sexo; }
	
	public int getIdade(){ return this.idade; }
	public void setIdade(int idade){ this.idade = idade; }

	public String getNome(){ return this.nome; }
	public void setNome(String nome){ this.nome = nome; }

	public String getCpf(){	return this.cpf; }
	public void setCpf(String cpf){	this.cpf = cpf;	}
}
