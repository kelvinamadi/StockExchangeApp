public class asset {
    private String name;
    private double buyPrice;
    private double sellPrice;
    private boolean sold;
    private String currentPos;
    private int shares;

    public asset(String name, double price, int shares){
        this.name = name;
        this.buyPrice = price;
        this.sellPrice = 0;
        this.sold = false;
        this.currentPos = "Break Even";
        this.shares = shares;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShares(){
        return this.shares;
    }

    public void setShares(int num){
        this.shares = num;
    }

    public double getBuyPrice(){
        return this.buyPrice;
    }

    public void setBuyPrice(double price){
        this.buyPrice = price;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    // WHEN AN ASSET IS SOLD, SELL PRICE IS CHANGE AND SO IS SOLD BOOLEAN
    public void sell(double price){
        this.sellPrice = price;
        this.sold = true;
    }

    public boolean getSold(){
        return this.sold;
    }

    public void changeCurrentPos(String situation){
        this.currentPos = situation;
    }


    public String getCurrentPos() {
        return this.currentPos;
    }


    //RETURNS DETAILS OF THE ASSET
    public String toString(){
        String sentence;
        if (this.sold)
        {
            sentence = "SOLD ASSET, TYPE: "+ getClass().getName() + " , INVESTED IN: " + this.name
                    + " WITH BUY PRICE: "+  this.buyPrice + " WITH " + this.shares + " SHARES, SOLD AT PRICE: "
                    + this.sellPrice;
        }
        else{
            sentence = "TYPE: "+ getClass().getName() + ", INVESTED IN: " + this.name
                    + " WITH BUY PRICE: "+  this.buyPrice + " WITH " + this.shares + " SHARES, CURRENT POSITION: "
                    + this.currentPos;
        }
        return sentence;
    }




}
