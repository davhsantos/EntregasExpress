import java.io.Serializable;
/**
 * Escreva a descrição da classe Big aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Big extends Pedido implements Serializable
{
    private double kg;
    
    public Big(){
        super();
        this.kg = 0;
    }
    
    public Big(double xx, double yy, double kg){
        super(xx,yy);
        this.kg = kg;
    }
    
    public Big(Big b){
        super(b);
        this.kg = b.getKg();
    }
    
    public double getKg(){
        return this.kg;
    }
    
    public void setKg(double kg){
        this.kg = kg;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Big b = (Big) o;
        return this.kg == b.getKg();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nPeso: ").append(this.kg);
        return sb.toString();
    }
    
    public Big clone(){
        return new Big(this);
    }
}
