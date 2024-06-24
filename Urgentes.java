import java.io.Serializable;
/**
 * Escreva a descrição da classe Urgentes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Urgentes extends Pedido implements Serializable
{
    private double temp;
    
    public Urgentes(){
        super();
        this.temp = 0;
    }
    
    public Urgentes(double xx, double yy, double temp){
        super(xx, yy);
        this.temp = temp;
    }
    
    public Urgentes(Urgentes u){
        super(u);
        this.temp = u.getTemp();    
    }
    
    public double getTemp(){
        return this.temp;
    }
    
    public void setTemp(double temp){
        this.temp = temp;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Urgentes u = (Urgentes) o;
        return this.temp == u.getTemp();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nTemperatura de conservação: ").append(this.temp);
        return sb.toString();
    }
    
    public Urgentes clone(){
        return new Urgentes(this);
    }
}
