import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 * Escreva a descrição da classe Menu aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Menu
{
    private Scanner sc;
    private String nome;
    private String morada;
    private String password;
    private String email;
    private String nomeEmpresa;
    private String tipotransporte;
    private double custo;
    private double km;
    private double volume;
    private double tempokm;
    private double x;
    private double y;
    private double peso;
    private double temp;
    private int lotacao;
    private boolean criancas;
    private String modelo;
    private String matricula;
    
    public Menu(){
        sc=new Scanner(System.in);
    }
    
    public int mostraOpcoes(String titulo, String [] opcoes){
        System.out.println("____________________________________________________________\n");
        System.out.println("|::::    "+titulo+"    ::::|\n");
        for(int i=0;i<opcoes.length;i++) { 
            System.out.println("|                      "+(1+i)+"-"+ opcoes[i]+"                     |\n");
        }
         
        System.out.println("|                      0-Voltar                            |\n");
        System.out.println("_____________________________________________________________\n");
        int op = Integer.parseInt(sc.nextLine());
        return op;
    }
    
    public String mostraTranportadores(String titulo, Map<String,List<Double>> opcoesT,Map<String,Transportador> lista){
        int i = 1;
        int count = 0;
        String key = "";
        Map<Integer,String> oplist = new HashMap<>();
        System.out.println("____________________________________________________________\n");
        System.out.println("|::::    "+titulo+"    ::::|\n");
        for(Map.Entry<String, List<Double>> entry : opcoesT.entrySet()) { 
            key = entry.getKey();
            System.out.println("|            "+ i +"-"+ lista.get(key).getNomeEmpresa());
            for(Double value : entry.getValue()){
                if(count == 0){
                    System.out.println(" \n   ETA: "+ value);
                }
                if(count == 1){
                    System.out.println(" \n   Preço: "+ value);
                }
                count++;
            }
            oplist.put(i,lista.get(key).getEmail());
            i++;
            count = 0;
        }
         
        System.out.println("|                      0-Voltar                            |\n");
        System.out.println("_____________________________________________________________\n");
        int op = Integer.parseInt(sc.nextLine());
        String email = oplist.get(op);
        return email;
    }
    
    public int menuUser(String titulo, String[] opcoes){
        System.out.println("____________________________________________________________\n");
        System.out.println("|:::: "+titulo+" ::::|\n");
        for(int i=0;i<opcoes.length;i++) { 
            System.out.println("|                  "+(1+i)+"-"+ opcoes[i]+"  |\n");
        }
        System.out.println("|                  0-Logout                                |\n");
        System.out.println("_____________________________________________________________\n");
        int op = Integer.parseInt(sc.nextLine());
        return op;
    }
    
    public LocalDateTime mostraPedido(String titulo,Map<LocalDateTime,Pedido> hist){
        int i = 1;
        LocalDateTime key = null;
        Map<Integer,LocalDateTime> oplist = new HashMap<>();
        System.out.println("____________________________________________________________\n");
        System.out.println("|::::    "+titulo+"    ::::|\n");
        for(Map.Entry<LocalDateTime,Pedido> entry : hist.entrySet()) { 
            key = entry.getKey();
            System.out.println("|            "+ i +"-"+ hist.get(key).getClass().getSimpleName());
            System.out.println("|            "+ hist.get(key).getData());
            oplist.put(i,hist.get(key).getData());
            i++;
        }
         
        System.out.println("|                      0-Voltar                            |\n");
        System.out.println("_____________________________________________________________\n");
        int op = Integer.parseInt(sc.nextLine());
        LocalDateTime data = oplist.get(op);
        return data;
    }
    
    public String[] insereDatas() throws NumberFormatException{
        System.out.println(" ______________________________________________________________ \n");
        System.out.println("|                          Insira as datas:                        |\n");
        System.out.println("        Data 1: ");
        String data1 = sc.nextLine();
        System.out.println("        Data 2: ");
        String data2 = sc.nextLine();
        
        String[] datas = {data1,data2};
        
        return datas;
    }
    
    public ArrayList<String> menuLogin() throws NumberFormatException{
        ArrayList<String> dados = new ArrayList<>();
        String email;
        String password;
        
        System.out.println(" ______________________________________________________________ \n");
        System.out.println("|                          Menu  Login                             |\n");
        System.out.println("        E-mail: ");
        email = sc.nextLine();
        System.out.println("        Palavra-passe: ");
        password = sc.nextLine();   
        
        dados.add(email);
        dados.add(password);
        
        return dados;
    }
  
    public User criaCliente() throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Registo de Clientes                       |\n"); 
                    
        System.out.println("  Nome: ");
        nome = sc.nextLine();
                  
        System.out.println("  Morada: ");
        morada = sc.nextLine(); 
  
        System.out.println("  Email: ");
        email = sc.nextLine();
                    
        System.out.println(" Palavra-Passe: ");
        password = sc.nextLine();
                    
        System.out.println(" X: ");
        x = Integer.parseInt(sc.nextLine());
                    
        System.out.println(" Y: ");
        y = Integer.parseInt(sc.nextLine());
                   
        Map<LocalDateTime,Pedido> hist = new TreeMap<>();
                    
        User user = new Cliente(email,nome,password,morada,hist,x,y);
        
        return user;
    }
    
    public User criaTransportador() throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Registo de Empresas                       |\n"); 
        
        System.out.println("  Nome da Empresa: ");
        nomeEmpresa = sc.nextLine();
  
        System.out.println("  Email: ");
        email = sc.nextLine();
                    
        System.out.println(" Palavra-Passe: ");
        password = sc.nextLine();
        
        System.out.println("  Tipo de Transporte: ");
        tipotransporte = sc.nextLine();
        
        System.out.println("  Custo: ");
        custo = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Raio de Entrega: ");
        km = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Volume: ");
        volume = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Tempo por km: ");
        tempokm = Double.parseDouble(sc.nextLine());
                    
        System.out.println(" X: ");
        x = Integer.parseInt(sc.nextLine());
                    
        System.out.println(" Y: ");
        y = Integer.parseInt(sc.nextLine());
        
        System.out.println("  Modelo do veículo:  ");
        modelo = sc.nextLine();
        
        System.out.println("  Matricula:  ");
        matricula = sc.nextLine();

        System.out.println("  Lotação:  ");
        lotacao = Integer.parseInt(sc.nextLine());
        
        Map<LocalDateTime,Pedido> hist = new TreeMap<>();
                    
        User user = new Transportador(email,password,hist,nomeEmpresa,
                                      tipotransporte,custo,km,volume,peso,tempokm,x,y,modelo,matricula,lotacao);
        
        return user;
    }
    
    public Pedido criaPedidoPessoas() throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Pessoas                       |\n"); 
                    
        System.out.println("  Localização X e Y: ");
        x = Double.parseDouble(sc.nextLine());
        y = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Quantidade de pessoas: ");
        lotacao = Integer.parseInt(sc.nextLine());
        
        System.out.println("  Estão incluidas crianças? ");
        criancas = sc.nextBoolean();
                    
        Pedido pessoas = new Pessoas(x,y,lotacao,criancas);
        
        return pessoas;
    }
    
    public Pedido criaPedidoBus()throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Bus                       |\n"); 
                    
        System.out.println("  Localização X e Y: ");
        x = Double.parseDouble(sc.nextLine());
        y = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Quantidade de pessoas: ");
        lotacao = Integer.parseInt(sc.nextLine());
                    
        Pedido bus = new Bus(x,y,lotacao);
        
        return bus;
    }
    
    public Pedido criaPedidoBig()throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Big                       |\n"); 
                    
        sc.nextLine();
        System.out.println("  Localização X e Y: ");
        x = Double.parseDouble(sc.nextLine());
        y = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Peso do pacote: ");
        peso = Double.parseDouble(sc.nextLine());
                    
        Pedido big = new Big(x,y,peso);
        
        return big;
    }
    
    public Pedido criaPedidoUrgentes()throws NumberFormatException{
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                            Urgentes                       |\n"); 
                    
        System.out.println("  Localização X e Y: ");
        x = Double.parseDouble(sc.nextLine());
        y = Double.parseDouble(sc.nextLine());
        
        System.out.println("  Temperatura: ");
        temp = Double.parseDouble(sc.nextLine());
                    
        Pedido urgentes = new Urgentes(x,y,temp);
        
        return urgentes;
    }
    
    public Restaurantes escolheRestaurante(List<Restaurantes> r){
        int i = 0;
        System.out.println("|               Escolha um restaurante:                   |");
        if(r != null){
            for(Restaurantes res : r){
                System.out.println("                      "+(1+i)+"-"+ res.getNome() +"                         ");
                System.out.println("                   Localização: " + res.getX() + " " + res.getY() + "                  \n");
                i++;
            }
        } else {
            System.out.println("Não existem restaurantes...");
        }
        
        System.out.println("|                      0-Voltar                            |\n");
        System.out.println("_____________________________________________________________\n");
        System.out.println("Indique a sua opção: ");
        int op = Integer.parseInt(sc.nextLine());
        return r.get(op-1);
    }

    public Pedido criaPedidoRefeicoes(Restaurantes r)throws NumberFormatException{
        List<Prato> pratos = r.getPratos();
        List<Prato> carrinho = new ArrayList<>();
        int i = 0;
        int checkout = 1;
        double precopedido = 0;
        System.out.println(" __________________________________________________________________ \n");
        System.out.println("|                           Refeições                       |\n");
        
        for(Prato p : pratos){
            System.out.println("                      "+(1+i)+"-"+ p.getNome() +"                     ");
            System.out.println("                      Preço: " + p.getPreco() + "         \n");
            i++;
        }
        
        while(checkout == 1){
            System.out.println("     Selecione os produtos a adicionar ao pedido      ");
            int op = Integer.parseInt(sc.nextLine());
            carrinho.add(pratos.get(op-1));
            precopedido += pratos.get(op-1).getPreco();
            System.out.println("      Total: " + precopedido + "       ");
            System.out.println("     Continuar(1)    Checkout(0)      ");
            checkout = Integer.parseInt(sc.nextLine());
        }
                      
        Pedido refeicoes = new Refeicoes(carrinho,precopedido);
        
        return refeicoes;
    }
}


