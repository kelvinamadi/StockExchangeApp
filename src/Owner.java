import java.util.*;

public class Owner extends User {

    private ArrayList<Client> clients;

    public Owner(String name, int amount) {
        super(name, amount);
        this.clients = new ArrayList<Client>();
    }

    public Owner(String name) {
        super(name);
        this.clients = new ArrayList<Client>();
    }

    public int getNumberOfClients(){
        return this.clients.size();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    // ADDS A NEW CLIENT TO THE ARRAYLIST OF TYPE CLIENT
    public void addClient(Client c){
        clients.add(c);
    }


}



