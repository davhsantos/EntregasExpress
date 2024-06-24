import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Escreva a descrição da classe User aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public abstract class User implements Serializable
{
    private String email;
    private String password;
    private Map<LocalDateTime,Pedido> historico;
    
    public User(){
        this.email = "n/a";
        this.password = "n/a";
        this.historico = new TreeMap<>();
    }
    
    public User(String email, String password, Map<LocalDateTime,Pedido> hist){
        this.email = email;
        this.password = password;
        this.historico = hist.values().stream().collect(Collectors.toMap((t) -> t.getData(),(t) -> t));
    }
    
    public User(User u){
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.historico = u.getHistorico();
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Map<LocalDateTime,Pedido> getHistorico(){
        return this.historico.values().stream().collect(Collectors.toMap((e) -> e.getData(),(e) -> e.clone()));
    }
    
    public void addHistorico(Pedido p){
        this.historico.put(p.getData(),p.clone());
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nEmail: ").append(this.email);
        sb.append("\nPassword: ").append(this.password);
        sb.append("\nHistórico: ").append(this.historico.toString());
        
        return sb.toString();
    }
    
    public abstract double getX();
    public abstract double getY();
    public abstract User clone();
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + this.getEmail().hashCode();
        hash = 31 * hash + this.getPassword().hashCode();
        
        return hash;
    }
    
    public int compareTo(User u){
        return this.email.compareTo(u.getEmail());
    }
}
