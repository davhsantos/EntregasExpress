import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.lang.Exception;
/**
 * Escreva a descrição da classe Main aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main extends Exception
{
    private static Scanner sc = new Scanner(System.in);
    private static Menu menu;
    private static EntregasExpress eExp;
    
    public static void main(String[] args) throws Exception{
        menu = new Menu();
        loading();
        if(eExp == null){
            eExp = new EntregasExpress();
        }
        int op = -1;
        
        do{
            op = menu.mostraOpcoes("Bem-vindo(a) aos Serviços EntregasExpress!", new String[]{"Registo         ","Login           ","Mais requisitado"});
            switch(op){
                case 1: 
                    adicionarUser();
                    break;
                case 2: 
                    logIn();
                    break;
                case 3:
                    transpMaisPedidos();
                    break;
                case 27:
                    adicionaRest();
                    break;
            }
        }while(op != 0);
            
        saving();
    }
    
    private static void transpMaisPedidos(){
        Transportador t = eExp.maisPedidos();
        if(t != null){
            System.out.println("Transportador mais requisitado: ");
            System.out.println(t.getNomeEmpresa());
            System.out.println(t.getEmail());
        } else {
            System.out.println("Não existem transportadores!");
        }
    }
    
    private static void adicionarUser(){
        int op = menu.mostraOpcoes("              Menu de Registo             ", new String[]{"Cliente      ", "Transportador"});
        User user = null;
        switch(op) {
            case 1 : 
                try{
                    user = menu.criaCliente();
                    
                }
                catch(NumberFormatException e) {
                    System.out.println("Dados inválidos");
                }
                break;
            case 2 : 
                try{
                    user = menu.criaTransportador();
                    
                }
                catch(NumberFormatException e) {
                    System.out.println("Dados inválidos");
                }
                break;
        }
         
        if(user != null){
            try{
                eExp.addUser(user.clone());
            }
            catch(EmailExistenteException e){
                System.out.println("Este email já se encontra associado a uma conta!");
            }
        }
    }
    
    private static int clienteOnline(){
        int op = menu.menuUser("                 Menu de Login                  ", new String[]{"Pedir um serviço                    ","Modificar pedido feito              ","Obter histórico de pedidos          ","Modificar a minha informação        "});
        return op;
    }
    
    private static int transportadoraOnline(){
        int op = menu.menuUser(" Está online! Bem-vindo ao menu Transportadora. ", new String[]{"Obter histórico de entregas         ","Obter total faturado num período    ","Modificar informação do veículo     ","Modificar informação da empresa     "});
        return op;
    }
    
    private static void adicionaRest(){
        Prato p1 = new Prato("Arroz de pato",4.5);
        Prato p2 = new Prato("Cozido à Portuguesa",9);
        Prato p3 = new Prato("Alheira com ovo",5);
        Prato p4 = new Prato("Sushi",15);
        Prato p5 = new Prato("Ramen",12);
        Prato p6 = new Prato("Pato à Pequim",7);
        Prato p7 = new Prato("Hamburguer",5.5);
        Prato p8 = new Prato("Cachorro",3.5);
        Prato p9 = new Prato("Chesesteak",6);
        Prato p10 = new Prato("BigMac",6.7);
        Prato p11 = new Prato("Steakhouse",6.9);
        Prato p12 = new Prato("Cheseburguer",2.5);
        Prato p13 = new Prato("Pizza",12.5);
        Prato p14 = new Prato("Lasanha",6.5);
        Prato p15 = new Prato("Carbonara",9);
        Prato p16 = new Prato("Brás de Legumes",6.5);
        Prato p17 = new Prato("Rancho de Legumes",4.5);
        Prato p18 = new Prato("Ratatouille", 15);
        
        List<Prato> l1= new ArrayList<>();
        l1.add(p1);
        l1.add(p2);
        l1.add(p3);
        List<Prato> l2= new ArrayList<>();
        l2.add(p4);
        l2.add(p5);
        l2.add(p6);
        List<Prato> l3= new ArrayList<>();
        l3.add(p7);
        l3.add(p8);
        l3.add(p9);
        List<Prato> l4= new ArrayList<>();
        l4.add(p10);
        l4.add(p11);
        l4.add(p12);
        List<Prato> l5= new ArrayList<>();
        l5.add(p13);
        l5.add(p14);
        l5.add(p15);
        List<Prato> l6= new ArrayList<>();
        l6.add(p16);
        l6.add(p17);
        l6.add(p18);
        
        Restaurantes r1 = new Restaurantes("X1AB23","D'gostar","Portuguesa",3,5,l1);
        Restaurantes r2 = new Restaurantes("X1AC12","Roiyaru","Japonesa",4,2,l2);
        Restaurantes r3 = new Restaurantes("23X1AB","Dan's","Americana",2,6,l3);
        Restaurantes r4 = new Restaurantes("XA21B3","Wendy's","Fast-Food",4,10,l4);
        Restaurantes r5 = new Restaurantes("BX1A23","Luzzo","Italiana",5,16,l5);
        Restaurantes r6 = new Restaurantes("2X1AB3","Gosto Superior","Vegan",2,8,l6);
        
        eExp.addRestaurante(r1);
        eExp.addRestaurante(r2);
        eExp.addRestaurante(r3);
        eExp.addRestaurante(r4);
        eExp.addRestaurante(r5);
        eExp.addRestaurante(r6);
        
        System.out.println("Restaurantes: " + eExp.getRestaurantes());
    }
    
    private static void logIn(){
        ArrayList<String> dados = new ArrayList<>();
        dados = menu.menuLogin();
        
        String email = dados.get(0);
        String password = dados.get(1);
        
        try{
            if(eExp.containsUser(email)){
                User u = eExp.getUser(email);
                if(u.getPassword().equals(password)){
                    String status = "true";
                    do{
                        if(u.getClass().getSimpleName().equals("Cliente")){
                            Cliente c = (Cliente) u;
                            int opLogin = clienteOnline();
                            switch(opLogin){
                                case 1:
                                    fazPedido(c);
                                    break;
                                case 2:
                                    alteraPedido(c);
                                    break;
                                case 3:
                                    Map<LocalDateTime,Pedido> hist = eExp.getHistorico(c.getEmail());
                                    LocalDateTime opDate = menu.mostraPedido("             Pedido a consultar:              ",hist);
                                    if(opDate != null){
                                        System.out.println(eExp.getPedido(c,opDate).toString());
                                    } else {
                                        break;
                                    }
                                case 4:
                                    alteraDadosUser(c);
                                    break;
                                case 0:
                                    status = "false";
                                    break;
                            }
                        }
                        if(u.getClass().getSimpleName().equals("Transportador")){
                            Transportador t = (Transportador) u;
                            int opLogin = transportadoraOnline();
                            switch(opLogin){
                                case 1:
                                    Map<LocalDateTime,Pedido> hist = eExp.getHistorico(t.getEmail());
                                    LocalDateTime opDate = menu.mostraPedido("             Pedido a consultar:              ",hist);
                                    if(opDate != null){
                                        System.out.println(eExp.getPedido(t,opDate).toString());
                                    } else {
                                        break;
                                    }
                                    break;
                                case 2:
                                    String[]datas = menu.insereDatas();
                                    double total = eExp.getPedidosEntreDatas(t,datas[0],datas[1]);
                                    System.out.println("O total faturado entre "+ datas[0] +" e "+ datas[1] + " é: "+ total);
                                    break;
                                case 3:
                                    eExp.getVeiculo(t.getEmail()).toString();
                                    alteraVeiculo(t);
                                    break;
                                case 4:
                                    alteraDadosUser(t);
                                    break;
                                case 0:
                                    status = "false";
                                    break;
                            } 
                        }
                    } while(status.equals("true"));
                } else {
                    throw new DadosInvalidosException("Email ou password inválidos!");
                }
            }
        }
        catch(DadosInvalidosException e){
            System.out.println("Email ou password inválidos!");
        }
    }

    private static void fazPedido(Cliente c){
        Pedido p = criaPedido();
        try{
            Map<String,Transportador> lista = eExp.transpDeTipo(p.getClass().getSimpleName());
            Map<String,List<Double>> tordenados = calculaViagem(c,p,lista);
            String email = menu.mostraTranportadores("         Escolha um transportador         ", tordenados, lista);
            if(p.getClass().getSimpleName().equals("Refeicoes")){
                Refeicoes r = (Refeicoes) p;
                System.out.println("Total do pedido: " + r.getPrecoPedido() + "\nTaxa de entrega:   " + tordenados.get(email).get(1));
            }
            p.setPrecoTotal(tordenados.get(email).get(1));
            p.setTempo(tordenados.get(email).get(0));
            eExp.addPedido(c,p);
            eExp.addPedido(lista.get(email),p);
        }
        catch(TransportadoresIndisponiveisException e){
            System.out.println("Ups...Não existem transportadores disponíveis para esse serviço");
        }
    }
    
    private static Map<String,List<Double>> calculaViagem(Cliente c, Pedido p, Map<String,Transportador> lista) throws TransportadoresIndisponiveisException{
        List<Double> dados = new ArrayList<>();
        Map<String,List<Double>> temp = new HashMap<>();
        switch(p.getClass().getSimpleName()){
            case "Pessoas":
                for(Transportador t : lista.values()){
                    if(eExp.distancia(t.getX(),c.getX(),t.getY(),c.getY())<=t.getKm()){
                        double dtotal = eExp.distancia(t.getX(),p.getX(),t.getY(),p.getY()) + eExp.distancia(c.getX(),p.getX(),c.getY(),p.getY());
                        double eta = eExp.tempoDeViagem(t.getTempoKm(), dtotal);
                        double preco = t.getCusto() * dtotal;
                        dados.add(eta);
                        dados.add(preco);
                        temp.put(t.getEmail(),dados);
                    } else {
                        throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis! \n Por favor tente mais tarde.");
                    }
                }
                break;
            case "Big":
                Big b = (Big) p;
                for(Transportador t : lista.values()){
                    if(eExp.distancia(t.getX(),c.getX(),t.getY(),c.getY())<=t.getKm() && b.getKg()<=t.getPeso()){
                        double dtotal = eExp.distancia(t.getX(),p.getX(),t.getY(),p.getY()) + eExp.distancia(c.getX(),p.getX(),c.getY(),p.getY());
                        double eta = eExp.tempoDeViagem(t.getTempoKm(), dtotal);
                        double preco = t.getCusto() * dtotal;
                        dados.add(eta);
                        dados.add(preco);
                        temp.put(t.getEmail(),dados);
                    } else {
                        throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis! \n Por favor tente mais tarde.");
                    }
                }
                break;
            case "Bus":
                for(Transportador t : lista.values()){
                    Bus bus = (Bus) p;
                    if(eExp.distancia(t.getX(),c.getX(),t.getY(),c.getY())<=t.getKm() && bus.getNrPessoas() <= t.getVeiculo().getLotacao()){
                        double dtotal = eExp.distancia(t.getX(),p.getX(),t.getY(),p.getY()) + eExp.distancia(c.getX(),p.getX(),c.getY(),p.getY());
                        double eta = eExp.tempoDeViagem(t.getTempoKm(), dtotal);
                        double preco = t.getCusto() * dtotal;
                        dados.add(eta);
                        dados.add(preco);
                        temp.put(t.getEmail(),dados);
                    } else {
                        throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis! \n Por favor tente mais tarde.");
                    }
                }
                break;
            case "Urgente":
                for(Transportador t : lista.values()){
                    if(eExp.distancia(t.getX(),c.getX(),t.getY(),c.getY())<=t.getKm()){
                        double dtotal = eExp.distancia(t.getX(),p.getX(),t.getY(),p.getY()) + eExp.distancia(c.getX(),p.getX(),c.getY(),p.getY());
                        double eta = eExp.tempoDeViagem(t.getTempoKm(), dtotal);
                        double preco = t.getCusto() * dtotal;
                        dados.add(eta);
                        dados.add(preco);
                        temp.put(t.getEmail(),dados);
                    } else {
                        throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis! \n Por favor tente mais tarde.");
                    }
                }
                break;
            case "Refeicoes":
                for(Transportador t : lista.values()){
                    if(eExp.distancia(t.getX(),c.getX(),t.getY(),c.getY())<=t.getKm()){
                        double dtotal = eExp.distancia(t.getX(),p.getX(),t.getY(),p.getY()) + eExp.distancia(c.getX(),p.getX(),c.getY(),p.getY());
                        double preco = 0.0;
                        if(dtotal <= 5){
                            preco = 1.8;
                        } else {
                            preco = t.getCusto() * dtotal;
                        }
                        double eta = eExp.tempoDeViagem(t.getTempoKm(), dtotal);
                        dados.add(eta);
                        dados.add(preco);
                        temp.put(t.getEmail(),dados);
                    } else {
                        throw new TransportadoresIndisponiveisException("Não existem transportadores disponíveis! \n Por favor tente mais tarde.");
                    }
                }
        }
        
        return temp;
    }
    
    private static Pedido criaPedido(){
        int op = menu.mostraOpcoes("             Menu de Pedidos              ",new String[]{"Pessoas      ","Big          ","Bus          ","Urgentes     ","Refeições    "});
        Pedido p = null;
        try{
            switch(op){
                case 1: 
                    p = menu.criaPedidoPessoas();
                    break;
                case 2: 
                    p = menu.criaPedidoBig();
                    break;
                case 3: 
                    p = menu.criaPedidoBus();
                    break;
                case 4: 
                    p = menu.criaPedidoUrgentes();
                    break;
                case 5: 
                    int op2 = menu.mostraOpcoes("            O que deseja?             ",new String[]{"Americana    ","Italiana     ","Portuguesa   ","Vegan        ","Fast-Food    ","Japonesa     "});
                    List<Restaurantes> lista = new ArrayList<>();
                    Restaurantes r = new Restaurantes();
                    switch(op2){
                        case 1:
                            lista = eExp.restaurantesPorCozinha().get("Americana");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 2:
                            lista = eExp.restaurantesPorCozinha().get("Italiana");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 3:
                            lista = eExp.restaurantesPorCozinha().get("Portuguesa");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 4:
                            lista = eExp.restaurantesPorCozinha().get("Vegan");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 5:
                            lista = eExp.restaurantesPorCozinha().get("Fast-Food");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 6:
                            lista = eExp.restaurantesPorCozinha().get("Japonesa");
                            r = menu.escolheRestaurante(lista);
                            break;
                        case 0: 
                            break;
                        default: 
                            System.out.println("Opção inválida");
                    }
                    if(r != null){
                        p = menu.criaPedidoRefeicoes(r);
                    }
                    break;
                case 0:
                    break;
                default: 
                    System.out.println("Opção inválida");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Dados Incorretos!");
        }
        return p;
    }
    
    private static void alteraPedido(Cliente c){
        Map<LocalDateTime,Pedido> hist = eExp.getHistorico(c.getEmail());
        LocalDateTime opDate = menu.mostraPedido("             Pedido a alterar:              ",hist);
        Pedido p = eExp.getPedido(c, opDate);
        String tipo = p.getClass().getSimpleName();
        switch(tipo){
            case "Pessoas":
                int op = menu.mostraOpcoes("Campo a alterar",new String[]{"Localização  ","Lotação      ","Crianças     "});
                Pessoas pessoas = (Pessoas) p;
                if(op == 1){
                    System.out.println("\nNovas coordenadas: ");
                    double x = Double.parseDouble(sc.nextLine());
                    double y = Double.parseDouble(sc.nextLine());
                    pessoas.setX(x);
                    pessoas.setY(y);
                    eExp.addPedido(c,pessoas);
                }
                if(op == 2){
                    System.out.println("\nNovas quantidade: ");
                    int x = Integer.parseInt(sc.nextLine());
                    pessoas.setLotacao(x);
                    eExp.addPedido(c,pessoas);
                }
                if(op == 3){
                    System.out.println("\nEstá acompanhado de crianças? ");
                    boolean x = sc.nextBoolean();
                    pessoas.setCriancas(x);
                    eExp.addPedido(c,pessoas);
                }
                break;
            case "Bus":
                op = menu.mostraOpcoes("Campo a alterar",new String[]{"Lotação      "});
                Bus bus = (Bus) p;
                if(op == 1){
                    System.out.println("\nNova quantidade: ");
                    int x = Integer.parseInt(sc.nextLine());
                    bus.setNrPessoas(x);
                    eExp.addPedido(c,bus);
                }
                break;
            case "Big":
                op = menu.mostraOpcoes("Campo a alterar",new String[]{"Peso:        "});
                Big big = (Big) p;
                if(op == 1){
                    System.out.println("\nNovo peso:   ");
                    double x = Double.parseDouble(sc.nextLine());
                    big.setKg(x);
                    eExp.addPedido(c,big);
                }
                break;
            case "Urgentes":
                op = menu.mostraOpcoes("Campo a alterar",new String[]{"Temperatura  "});
                Urgentes urgentes = (Urgentes) p;
                if(op == 1){
                    System.out.println("\nNova temperatura: ");
                    double x = Double.parseDouble(sc.nextLine());
                    urgentes.setTemp(x);
                    eExp.addPedido(c,urgentes);
                }
                break;
        }
    }

    private static void alteraVeiculo(Transportador t){
        Veiculo v = eExp.getVeiculo(t.getEmail());
        int op = menu.mostraOpcoes("Campo a alterar", new String[]{"Modelo:      ","Matrícula:   ","Lotação:     "});
        switch(op){
            case 1:
                System.out.println("\nNovo modelo: ");
                String modelo = sc.nextLine();
                v.setModelo(modelo);
                break;
            case 2:
                System.out.println("\nNova matrícula: ");
                String matricula = sc.nextLine();
                v.setMatricula(matricula);
            case 3:
                System.out.println("\nNova lotação: ");
                int lotacao = Integer.parseInt(sc.nextLine());
                v.setLotacao(lotacao);
                break;
            case 0:
                break;
        }
        eExp.addVeiculo(t.getEmail(), v);       
    }

    private static void alteraDadosUser(User u){
        if(u.getClass().getSimpleName().equals("Cliente")){
            Cliente c = (Cliente) u;
            int op = menu.mostraOpcoes("             Campo a alterar             ", new String[]{"Email             ","Nome              ","Password          ","Morada            ","X                  ","Y                 ",});
            switch(op){
                case 1:
                    System.out.println("\nNovo email: ");
                    String email = sc.nextLine();
                    c.setEmail(email);
                    break;
                case 2:
                    System.out.println("\nNovo nome: ");
                    String nome = sc.nextLine();
                    c.setNome(nome);
                    break;
                case 3:
                    System.out.println("\nNova password: ");
                    String password = sc.nextLine();
                    c.setPassword(password);
                    break;
                case 4:
                    System.out.println("\nNova morada: ");
                    String morada = sc.nextLine();
                    c.setMorada(morada);
                    break;
                case 5:
                    System.out.println("\nNovo X: ");
                    double x = Double.parseDouble(sc.nextLine());
                    c.setY(x);
                    break;
                case 6:
                    System.out.println("\nNovo Y: ");
                    Double y = Double.parseDouble(sc.nextLine());
                    c.setX(y);
                    break;
            }
        }
        if(u.getClass().getSimpleName().equals("Transportador")){
            Transportador t = (Transportador) u;
            int op = menu.mostraOpcoes("             Campo a alterar             ", new String[]{"Email             ","Nome da Empresa   ","Password          ","X                 ","Y                 ","Tipo de transporte","Custo por km      ","Raio de entrega   ","Volume máximo     ","Peso máximo      ","Tempo por km     "});
            switch(op){
                case 1:
                    System.out.println("\nNovo email: ");
                    String email = sc.nextLine();
                    t.setEmail(email);
                    break;
                case 2:
                    System.out.println("\nNovo nome de Empresa: ");
                    String nomeEmp = sc.nextLine();
                    t.setNome(nomeEmp);
                    break;
                case 3:
                    System.out.println("\nNova password: ");
                    String password = sc.nextLine();
                    t.setPassword(password);
                    break;
                case 4:
                    System.out.println("\nNovo X: ");
                    double x = Double.parseDouble(sc.nextLine());
                    t.setY(x);
                    break;
                case 5:
                    System.out.println("\nNovo Y: ");
                    Double y = Double.parseDouble(sc.nextLine());
                    t.setX(y);
                    break;
                case 6:
                    System.out.println("\nNovo tipo de transporte: ");
                    String tipo = sc.nextLine();
                    t.setTipoTransporte(tipo);
                    break;
                case 7:
                    System.out.println("\nNovo custo/km: ");
                    Double custo = Double.parseDouble(sc.nextLine());
                    t.setCusto(custo);
                    break;
                case 8:
                    System.out.println("\nNovo raio de entrega: ");
                    Double raio = Double.parseDouble(sc.nextLine());
                    t.setKm(raio);
                    break;
                case 9:
                    System.out.println("\nNovo volume máximo: ");
                    Double vol = Double.parseDouble(sc.nextLine());
                    t.setVolume(vol);
                    break;
                case 10:
                    System.out.println("\nNovo peso máximo: ");
                    Double peso = Double.parseDouble(sc.nextLine());
                    t.setPeso(peso);
                    break;
                case 11:
                    System.out.println("\nNovo tempo/km: ");
                    Double tempo = Double.parseDouble(sc.nextLine());
                    t.setTempoKm(tempo);
                    break;

            }
        }
        
    }
    
    private static void loading(){
        try {
            carregaEstado("estadoEntregasExpress.obj");
            System.out.println("Carregado com sucesso!");
        } 
        catch(FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado!");
        }
        catch(IOException e) {
            System.out.println("Erro a aceder a ficheiro!");
        }
        catch(ClassNotFoundException e) {
            System.out.println("Classe inexistente");
        }
    }
    
    private static void saving(){
        try {
            eExp.guardaEstado("estadoEntregasExpress.obj");
            System.out.println("Ficheiro guardado!");
        } 
        catch(FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado!");
        }
        catch(IOException e) {
            System.out.println("Erro a aceder a ficheiro!");
        }
    }
    
    private static EntregasExpress carregaEstado(String nomeFicheiro) throws FileNotFoundException,
                                                                            IOException,
                                                                            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        eExp = (EntregasExpress) ois.readObject();
        ois.close();
        return eExp;
    }
}