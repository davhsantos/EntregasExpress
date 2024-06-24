import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
import java.lang.Exception;

public class EntregasExpress extends Exception implements Serializable
{
    private Map <String,User> user; 
    private Map<String,Restaurantes> rest;
     
    public EntregasExpress(){
        this.user = new HashMap<>();
        this.rest = new HashMap<>();
    }
    
    public EntregasExpress(Map<String,User> user,Map<String,Restaurantes> rest){
        this.user = user;
        this.rest = rest;
    }
    
    public EntregasExpress(EntregasExpress eExp){
        this.user = eExp.getUsers();
        this.rest = eExp.getRestaurantes();
    }
    
    public Transportador maisPedidos(){
        long maiornumero = 0;
        String email = "";
        for(User u : this.user.values()){
            if(u instanceof Transportador){
                Transportador t = (Transportador) u;
                long tpedidos = t.getHistorico().values().stream().count();
                if(tpedidos > maiornumero){
                    maiornumero = tpedidos;
                    email = t.getEmail();
                }
            }
        }
        Transportador t = (Transportador) getUser(email);
        return t;
    }
    
    public void addUser(User u) throws EmailExistenteException{
        try{
            if(containsUser(u.getEmail())){
                throw new EmailExistenteException("Esse email já se encontra registado");
            } else if(u != null){
                this.user.put(u.getEmail(),u.clone());
            }
        } catch(DadosInvalidosException e){
            System.out.println("Dados inexistentes!");
        }
    }
    
    public Map<String,User> getUsers(){
        return this.user.values().stream().collect(Collectors.toMap((u) -> u.getEmail(),(u) -> u.clone()));
    }
    
    public Map<String,Restaurantes> getRestaurantes(){
        return this.rest.values().stream().collect(Collectors.toMap((r)->r.getCodigo(),(r)->r.clone()));
    }
    
    public void addRestaurante(Restaurantes r){
        if(r != null){
            rest.put(r.getCodigo(),r.clone());
        }
    }
    
    public Map<String,List<Restaurantes>> restaurantesPorCozinha(){
        Map<String,List<Restaurantes>> rest = new HashMap<>();
        for(Restaurantes r : this.rest.values()){
            String tipocozinha = r.getTipoCozinha();
            List<Restaurantes> lista = rest.getOrDefault(tipocozinha, new ArrayList<>());
            lista.add(r);
            rest.put(tipocozinha,lista);
        }
        return rest;
    }
    
    public User getUser(String email){
        return this.user.get(email);
    }
    
    public boolean containsUser(String email) throws DadosInvalidosException{
        boolean contains = false;
        if(this.user.containsKey(email)){
            contains = true;
        } else {
            throw new DadosInvalidosException("Email inválido");
        }
        return contains;
    }
    
    public Map<LocalDateTime,Pedido> getHistorico(String email){
        return this.user.get(email).getHistorico();
    }
    
    public Pedido getPedido(User u,LocalDateTime data){
        Pedido p = getHistorico(u.getEmail()).get(data);
        return p;
    }
    
    public double getPedidosEntreDatas(Transportador t, String d1, String d2){
        String modelo = "dd-MM-yyyy";
        return getHistorico(t.getEmail()).values().stream()
                             .filter((d)->d.getDataFormatada(modelo).compareTo(d1)>0 
                                        && d.getDataFormatada(modelo).compareTo(d2)<0)
                             .mapToDouble((e)->e.getPrecoTotal()).sum();
    }
    
    public Map<String,Transportador> transpDeTipo(String tipo) throws TransportadoresIndisponiveisException{
        Map<String,Transportador> transpDeTipo = this.user.values().stream()
                                                                   .filter((u) -> u instanceof Transportador)
                                                                   .map((t) -> (Transportador) t)
                                                                   .filter((t) -> t.getTipoTransporte().equals(tipo))
                                                                   .collect(Collectors.toMap((t)->t.getEmail(),(t)->t.clone()));
                                                                   
        if(transpDeTipo == null){
            throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis!");
        }
                                                                   
        return transpDeTipo;
    }
    
    public void addPedido(User u, Pedido p){
        if(u != null && p != null){
            u.addHistorico(p);
            this.user.put(u.getEmail(),u.clone());
        }
    }

    public Veiculo getVeiculo(String email){
        Transportador t = (Transportador)getUser(email);
        return t.getVeiculo();
    }

    public void addVeiculo(String email,Veiculo v){
        Transportador t = (Transportador) getUser(email);
        t.setVeiculo(v);
    }
    
    public double distancia(double x1, double x2, double y1, double y2){
        double distancia = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        return distancia;
    }
    
    public double tempoDeViagem(double KmPorHora,double distancia){
        double tempo = (distancia/KmPorHora);
        return tempo;
    }
    
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
}