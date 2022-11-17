import java.util.*;

public class User{

    private String name;
    private Account account;

    public User(String name, int amount){
        this.name = name;
        this.account = new Account(amount);
    }

    public User(String name){
        this.name = name;
        this.account = new Account();
    }


    public String getName(){
        return this.name;
    }

    public Account getAccount(){
        return this.account;
    }

}
