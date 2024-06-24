import java.io.Serializable;
/**
 * Escreva a descrição da classe Prato aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Prato implements Serializable
{
    private String nome;
    private double preco;
    
    public Prato()
    {
        this.nome = "";
        this.preco = 0.0;
    }
    
    public Prato(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }
    
    public Prato(Prato p){
        this.nome = p.getNome();
        this.preco = p.getPreco();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setPreco(double preco){
        this.preco = preco;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Prato p = (Prato) o;
        return this.nome == p.getNome() && this.preco == p.getPreco();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ").append(this.nome);
        sb.append("\nPreço: ").append(this.preco);
        return sb.toString();
    }
    
    @Override
    public Prato clone(){
        return new Prato(this);
    }
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + (int) this.preco;
        hash = 31 * hash + this.nome.hashCode();
        
        return hash;
    }
    
    public int compareTo(Prato p){
        return this.nome.compareTo(p.getNome());
    }
}
