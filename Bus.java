import java.io.Serializable;
/**
 * Escreva a descrição da classe Bus aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Bus extends Pedido implements Serializable
{
    private int nrpessoas;
    
    public Bus(){
        super();
        this.nrpessoas = 0;
    }
    
    public Bus(double xx, double yy, int nrpessoas){
        super(xx,yy);
        this.nrpessoas = nrpessoas;
    }
    
    public Bus(Bus b){
        super(b);
        this.nrpessoas = b.getNrPessoas();
    }
    
    public int getNrPessoas(){
        return this.nrpessoas;
    }
    
    public void setNrPessoas(int nrpessoas){
        this.nrpessoas = nrpessoas;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Bus b = (Bus) o;
        return this.nrpessoas == b.getNrPessoas();        
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nNúmero de pessoas: ").append(this.nrpessoas);
        return sb.toString();
    }
    
    public Bus clone(){
        return new Bus(this);
    }
}
