public class Cronometrado {
    protected Cronometro cronometro = new Cronometro();
    
    // Retorna tempo cronometrado
    public long getTempo() { 
        return cronometro.getTempo(); 
    }
}