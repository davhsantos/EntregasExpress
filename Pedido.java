import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Pedido aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public abstract class Pedido implements Serializable
{
    private static int nrpedido = 0;
    private double x;
    private double y;
    private LocalDateTime data;
    private static double precototal;
    private static double tempo;
     
    public Pedido(){
        this.nrpedido += 1;
        this.x = 0;
        this.y = 0;
        this.data = LocalDateTime.now();
    }
    
    public Pedido(double xx, double yy){
        this.nrpedido += 1;
        this.x = xx;
        this.y = yy;
        this.data = LocalDateTime.now();
    }
    
    public Pedido(Pedido p){
        this.nrpedido = p.getNrPedido();
        this.x = p.getX();
        this.y = p.getY();
        this.data = p.getData();
    }
    
    public int getNrPedido(){
        return this.nrpedido;
    }
    
    public double getX(){
        return this.x;
    }
    
    public void setX(double xx){
        this.x = xx;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void setY(double yy){
        this.y = yy;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
    
    public String getDataFormatada(String modelo){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(modelo);
        String dataformatada = this.data.format(formatador);
        return dataformatada;
    }
    
    public double getPrecoTotal(){
        return this.precototal;
    }
    
    public void setPrecoTotal(double precototal){
        this.precototal = precototal;
    }
    
    public double getTempo(){
        return this.tempo;
    }
    
    public void setTempo(double tempo){
        this.tempo = tempo;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Pedido p = (Pedido) o;
        return this.nrpedido == p.getNrPedido() && this.x == p.getX() && 
               this.y == p.getY() && this.data.equals(p.getData()) &&
               this.precototal == p.getPrecoTotal() && this.tempo == p.getTempo();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNúmero de Pedido: ").append(this.nrpedido);
        sb.append("\nLocalização: ").append(this.x).append(" ").append(this.y);
        sb.append("\nData do Pedido: ").append(this.data);
        sb.append("\nTotal: ").append(this.precototal);
        return sb.toString();
    }
    
    @Override
    public abstract Pedido clone();
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + (int) this.x;
        hash = 31 * hash + (int) this.y;
        if(this.data == null){
            hash = 31 * hash + 0;
        } else {
            hash = 31 * hash + this.data.hashCode();
        }
        hash = 31 * hash + (int) this.precototal;
        hash = 31 * hash + (int) this.tempo;
        
        return hash;
    }
    
    public int compareTo(Pedido p){
        return this.data.compareTo(p.getData());
    }
}
