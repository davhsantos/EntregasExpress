import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * Escreva a descrição da classe Refeições aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Refeicoes extends Pedido implements Serializable
{
    private List<Prato> carrinho;
    private double precopedido;
    
    public Refeicoes(){
        this.carrinho = new ArrayList<>();
        this.precopedido = 0.0;
    }

    public Refeicoes(List<Prato> carrinho, double precopedido)
    {
        this.carrinho = carrinho.stream().collect(Collectors.toList());
        this.precopedido = precopedido;
    }
    
    public Refeicoes(Refeicoes r){
        this.carrinho=r.getCarrinho();
        this.precopedido = r.getPrecoPedido();
    }
     
    public List<Prato> getCarrinho(){
        return this.carrinho.stream().collect(Collectors.toList());
    }
    
    public void setCarrinho(List<Prato> carrinho){
        this.carrinho=carrinho;
    }
    
    public double getPrecoPedido(){
        return this.precopedido;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nCarrinho: ").append(this.carrinho.toString());
        sb.append("\nPreço da Comida").append(this.precopedido);
        return sb.toString();
    }
    
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;
     
        Refeicoes t=(Refeicoes) o;
        return this.carrinho==t.getCarrinho() &&  this.precopedido == t.getPrecoPedido();  
    }
  
    public Refeicoes clone(){
        return new Refeicoes(this);
    } 
     
    public int hashCode(){
        int hash=7;
        hash = 31 * hash + this.carrinho.hashCode();
        hash = 31 * hash + (int) this.precopedido;
        return hash;
    }
 
}
