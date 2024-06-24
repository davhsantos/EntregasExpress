import java.io.Serializable;
/**
 * Escreva a descrição da classe Pessoas aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Pessoas extends Pedido implements Serializable

{
    private int lotacao;
    private boolean criancas;
    
    public Pessoas(){
        super();
        this.lotacao = 0;
        this.criancas = false;
    }
    
    public Pessoas(double xx, double yy, int lot, boolean c){
        super(xx,yy);
        this.lotacao = lot;
        this.criancas = c;
    }
    
    public Pessoas(Pessoas p){
        super(p);
        this.lotacao = p.getLotacao();
        this.criancas = p.getCriancas();
    }
    
    public int getLotacao(){
        return this.lotacao;
    }
    
    public void setLotacao(int lotacao){
        this.lotacao = lotacao;
    }
    
    public boolean getCriancas(){
        return this.criancas;
    }
    
    public void setCriancas(boolean criancas){
        this.criancas = criancas;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Pessoas p = (Pessoas) o;
        return this.lotacao == p.getLotacao() && this.criancas == p.getCriancas();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nLotação: ").append(this.lotacao);
        sb.append("\nCrianças: ").append(this.criancas);
        
        return sb.toString();
    }
    
    public Pessoas clone(){
        return new Pessoas(this);
    }
}
