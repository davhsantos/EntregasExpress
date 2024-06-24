import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Restaurantes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Restaurantes implements Comparable<Restaurantes>,Serializable
{
    private String codigo;
    private String nome;
    private String tipocozinha;
    private double x;
    private double y;
    private List<Prato> pratos;
    
    public Restaurantes(){
        this.codigo = "";
        this.nome = "";
        this.tipocozinha = "";
        this.x = 0.0;
        this.y = 0.0;
        this.pratos = new ArrayList<>();
    }
    
    public Restaurantes(String codigo, String nome, String tipocozinha, double x, double y, List<Prato> pratos){
        this.codigo = codigo;
        this.nome = nome;
        this.tipocozinha = tipocozinha;
        this.x = x;
        this.y = y;
        this.pratos = pratos.stream().collect(Collectors.toList());
    }
        
    public Restaurantes(Restaurantes r){
        this.codigo = r.getCodigo();
        this.nome = r.getNome();
        this.tipocozinha = r.getTipoCozinha();
        this.x = r.getX();
        this.y = r.getY();
        this.pratos = r.getPratos();
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getTipoCozinha(){
        return this.tipocozinha;
    }
    
    public void setTipoCozinha(String tipocozinha){
        this.tipocozinha = tipocozinha;
    }

    public double getX(){
        return this.x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return this.y;
    }

    public void setY(double y){
        this.y = y;
    }
    
    public List<Prato> getPratos(){
        return this.pratos.stream().collect(Collectors.toList());
    }
    
    public void setPratos(List<Prato> pratos){
        this.pratos = pratos;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Restaurantes r = (Restaurantes) o;
        return this.codigo == r.getCodigo() && this.nome == r.getNome() && 
               this.pratos == r.getPratos() && this.x == r.getX() && this.y == r.getY() &&
               this.tipocozinha == r.getTipoCozinha();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nCódigo: ").append(this.codigo);
        sb.append("\nNome: ").append(this.nome);
        sb.append("\nTipo de cozinha: ").append(this.tipocozinha);
        sb.append("\nPosição X: ").append(this.x);
        sb.append("\nPosição Y: ").append(this.y);
        sb.append("\nMenu: ").append(this.pratos.toString());
        return sb.toString();
    }
    
    @Override
    public Restaurantes clone(){
        return new Restaurantes(this);
    }
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + this.codigo.hashCode();
        hash = 31 * hash + this.nome.hashCode();
        hash = 31 * hash + this.tipocozinha.hashCode();
        hash = 31 * hash + (int) this.x;
        hash = 31 * hash + (int) this.y;
        if(pratos == null){
            hash = 31 * hash + 0;
        } else {
            hash = 31 * hash + this.pratos.hashCode();
        }
        return hash;
    }
    
    @Override
    public int compareTo(Restaurantes r){
        return this.codigo.compareTo(r.getCodigo());
    }
}
