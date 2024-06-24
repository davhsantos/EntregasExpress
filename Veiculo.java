import java.io.Serializable;
/**
 * Escreva a descrição da classe Veiculos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo implements Serializable
{
    private String modelo;
    private String matricula;
    private int lotacao;
    
    public Veiculo(){
        this.modelo = "n/a";
        this.matricula = "n/a";
        this.lotacao = 0;
    }
    
    public Veiculo(String modelo, String matricula, int lotacao){
        this.modelo = modelo;
        this.matricula = matricula;
        this.lotacao = lotacao;
    }
    
    public Veiculo(Veiculo v){
        this.modelo = v.getModelo();
        this.matricula = v.getMatricula();
        this.lotacao = v.getLotacao();
    }
    
    public String getModelo(){
        return this.modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public int getLotacao(){
        return this.lotacao;
    }

    public void setLotacao(int lotacao){
        this.lotacao = lotacao;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nModelo: ").append(this.modelo);
        sb.append("\nMatricula: ").append(this.matricula);
        
        return sb.toString();
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Veiculo c = (Veiculo) o;
        return this.modelo.equals(c.getModelo()) && 
               this.matricula.equals(c.getMatricula());
    }
}
