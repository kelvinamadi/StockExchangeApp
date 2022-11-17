import java.util.*;

public class Account {
    private ArrayList<asset> allAssets;
    private int netProfit;
    private ArrayList<asset> sold;
    private int balance;


    public Account(int amount){
        this.allAssets = new ArrayList<asset>();
        this.netProfit = 0;
        this.sold = new ArrayList<asset>();
        this.balance = amount;
    }

    public Account() {
        this.allAssets = new ArrayList<asset>();
        this.netProfit = 0;
        this.sold = new ArrayList<asset>();
        this.balance = 0;

    }

    public void depositMoney(int amount){
        this.balance = this.balance + amount;
    }

    public void withdrawMoney(int amount ){
        this.balance = this.balance - amount;
    }

    public int getBalance(){
        return this.balance;
    }

    public int getNetProfit(){
        return this.netProfit;
    }

    public void amendNetProfit(int profit){
        this.netProfit = this.netProfit + profit;
    }

    // METHOD TO RETURN ASSETS SOLD
    public ArrayList<asset> getSold(){
        return this.sold;
    }

    // METHOD TO RETURN ASSETS
    public ArrayList<asset> getAllAssets(){
        return this.allAssets;
    }

    //NEED A METHOD TO ADD ASSET
    public void addAsset(asset asset){
        this.allAssets.add(asset);
    }

    //NEED A METHOD TO ADD ASSET
    public void addSold(asset asset){
        this.sold.add(asset);
    }

    // REMOVE ASSETS FROM ASSET ARRAYLIST IF SOLD AND ADD IT TO SOLD LIST
    public void itemSold(asset asset){
        int index = this.allAssets.indexOf(asset);
        asset itemS = this.allAssets.get(index);
        this.allAssets.remove(index);
        addSold(itemS);
    }

}
