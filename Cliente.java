import java.util.Map;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente extends User implements Serializable
{
    private String nome;
    private String morada;
    private double x;
    private double y;
    
    public Cliente(){
        super();
        this.nome = "n/a";
        this.morada = "n/a";
        this.x = -1.0;
        this.y = -1.0;
        
    }
    
    public Cliente(String e, String nome, String p, String morada, Map<LocalDateTime,Pedido> hist, double x, double y){
        super(e,p,hist);
        this.nome = nome;
        this.morada = morada;
        this.x = x;
        this.y = y;
    }
    
    public Cliente(Cliente c){
        super(c);
        this.nome = c.getNome();
        this.morada = c.getMorada();
        this.x = c.getX();
        this.y = c.getY();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getMorada(){
        return this.morada;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public Cliente clone(){
        return new Cliente(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nNome: ").append(this.nome);
        sb.append("\nMorada: ").append(this.morada);
        sb.append("\nPosição x: ").append(this.x);
        sb.append("\nPosição y: ").append(this.y);
        
        return sb.toString();
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Cliente c = (Cliente) o;
        return super.equals(c) && this.x == c.getX() && this.y == c.getY();
    }
}
