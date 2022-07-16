public class Registro {
    private int id;
	private String cep;
	private char sexo;
	private int idade;
	private String nome;
	private String cpf;
	
	public Registro() {
		this.id = 0;
		this.cep = "";
		this.sexo = ' ';
		this.idade = 0;
		this.nome = "";
		this.cpf = "";
	}
	
	public Registro(int id, String cep, String nome, int idade, char sexo, String cpf) {
		this.id = id;
		this.cep = cep;
		this.sexo = sexo;
		this.idade = idade;
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getCep(){
		return this.cep;
	}

	public int getId(){
		return this.id;
	}

	public String cep(){
		return this.cep;
	}

	public char getSexo(){
		return this.sexo;
	}
	
	public int getIdade(){
		return this.idade;
	}

	public String getNome(){
		return this.nome;
	}

	public String getCpf(){
		return this.cpf;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setCep(String cep){
		this.cep = cep;
	}

	public void setSexo(char sexo){
		this.sexo = sexo;
	}

	public void setIdade(int idade){
		this.idade = idade;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public void setCpf(String cpf){
		this.cpf = cpf;
	}

    @Override
    public String toString(){
        return 
        "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" +
		"Id: " + this.id +
        "\nNome: " + this.nome +
		"\nIdade: " + this.idade +
		"\nSexo: " + this.sexo +
		"\nCpf: " + this.cpf +
		"\nCep: " + this.cep +
		"\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
    }
}
