import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Transportadores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Transportador extends User implements Serializable
{
    private String nomeEmpresa;
    private String tipotransporte;
    private double custo;
    private double km;
    private double volume;
    private double peso;
    private double tempokm;
    private double x;
    private double y;
    private Veiculo veiculo;
    
    public Transportador(){
        super();
        this.nomeEmpresa = "n/a";
        this.tipotransporte = "n/a";
        this.custo = 0.0;
        this.km = 0.0;
        this.volume = 0.0;
        this.peso = 0.0;
        this.tempokm = 0.0;
        this.x = -1.0;
        this.y = -1.0;
        this.veiculo = new Veiculo();
    }
    
    public Transportador(String e, String p, Map<LocalDateTime,Pedido> hist, String nomeEmpresa, 
                         String tipotransporte, double custo, double km, double volume, double peso, 
                         double tempokm, double x, double y, String modelo, String matricula, int lotacao){
        super(e,p,hist);
        this.nomeEmpresa = nomeEmpresa;
        this.tipotransporte = tipotransporte;
        this.custo = custo;
        this.km = km;
        this.volume = volume;
        this.peso = peso;
        this.tempokm = tempokm;
        this.x = x;
        this.y = y;
        this.veiculo = new Veiculo(modelo, matricula,lotacao);
    }
    
    public Transportador(Transportador t){
        super(t);
        this.nomeEmpresa = t.getNomeEmpresa();
        this.tipotransporte = t.getTipoTransporte();
        this.custo = t.getCusto();
        this.km = t.getKm();
        this.volume = t.getVolume();
        this.peso = t.getPeso();
        this.tempokm = t.getTempoKm();
        this.x = t.getX();
        this.y = t.getY();
        this.veiculo = t.getVeiculo();
    } 
    
    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }
    
    public void setNome(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }
    
    public String getTipoTransporte(){
        return this.tipotransporte;
    }

    public void setTipoTransporte(String tipo){
        this.tipotransporte = tipo;
    }
    
    public double getCusto(){
        return this.custo;
    }
    
    public void setCusto(double custo){
        this.custo = custo;
    }
    
    public double getKm(){
        return this.km;
    }
    
    public void setKm(double km){
        this.km = km;
    }
    
    public double getVolume(){
        return this.volume;
    }
    
    public void setVolume(double volume){
        this.volume = volume;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public void setPeso(double peso){
        this.peso = peso;
    }
    
    public double getTempoKm(){
        return this.tempokm;
    }
    
    public void setTempoKm(double tempokm){
        this.tempokm = tempokm;
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

    public Veiculo getVeiculo(){
        return this.veiculo;
    }

    public void setVeiculo(Veiculo v){
        this.veiculo = v;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nNome da Empresa: ").append(this.nomeEmpresa);
        sb.append("\nTipo de transportes").append(this.tipotransporte);
        sb.append("\nCusto po km: ").append(this.custo);
        sb.append("\nRaio máximo: ").append(this.km);
        sb.append("\nVolume: ").append(this.volume);
        sb.append("\nPeso:").append(this.peso);
        sb.append("\nTempo por km: ").append(this.tempokm);
        sb.append("\nPosição x: ").append(this.x);
        sb.append("\nPosição y: ").append(this.y);
        sb.append("\nVeiculo").append(this.veiculo.toString());
                
        return sb.toString();
    }
    
    public Transportador clone(){
        return new Transportador(this);
    }
    
    public boolean equals(Object t){
        if(t == this) return true;
        if(t == null || t.getClass() != this.getClass()) return false;
        Transportador c = (Transportador) t;
        return super.equals(c) &&
               this.nomeEmpresa.equals(c.getNomeEmpresa()) && 
               this.tipotransporte.equals(c.getTipoTransporte()) &&
               this.custo == c.getCusto() && this.km == c.getKm() &&
               this.volume == c.getVolume() && this.tempokm == c.getTempoKm() &&
               this.peso == c.getPeso() && this.x == c.getX() && 
               this.y == c.getY() && this.veiculo == c.getVeiculo();
    }
}
