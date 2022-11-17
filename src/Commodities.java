public class Commodities extends asset implements NewsFeed{

    private String resource;
    private String type;

    public Commodities(String name, double price, int shares, String source, String type){
        super(name, price, shares);
        this.resource = source;
        this.type = type;
    }

    // METHOD THAT HALVES THE BUY PRICE ATTRIBUTE
    public void meltDown(){
        double price = getBuyPrice()/2;
        setBuyPrice(price);
    }

    // METHOD THAT CHANGES RESOURCE, TYPE AND BUY PRICE ATTRIBUTES
    public void exchangeLivestock(String source, String type, double price){
        this.resource = source;
        this.type = type;
        setBuyPrice(price);
    }

    // METHOD THAT CHANGES THE BUY PRICE AND SHARE ATTRIBUTES
    public void rentEnergy(){
        double price = getBuyPrice()/4;
        int share = getShares() + 4;
        setBuyPrice(price);
        setShares(share);
    }

    public String getResource() {
        return resource;
    }

    public String getType() {
        return type;
    }

    //RETURNS DETAILS OF THE ASSET(COMMODITY)
    public String toString(){
        String sentence;
        if (this.getSold())
        {
            sentence = "SOLD ASSET, TYPE: "+ getClass().getName() + " , INVESTED IN: " + getName()
                    + ", RESOURCE: " + this.resource + ", TYPE: "+ this.type + "COMMODITY, WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, SOLD AT PRICE: "
                    + getSellPrice();
        }
        else{
            sentence = "TYPE: " + getClass().getName() + ", INVESTED IN: " + getName()
                    + ", RESOURCE: " + this.resource + ", TYPE: "+ this.type + "COMMODITY, WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, CURRENT POSITION: "
                    + getCurrentPos();
        }
        return sentence;
    }

    public static String printNews(){
        String sentence = "NEED GROWS FOR NATURAL RESOURCES BECAUSE OF UKRAINE-RUSSIA WAR";
        return sentence;
    }

    public String printTaxLiability(){
        String sentence = "TAX LIABILITY";
        return sentence;
    }

}
