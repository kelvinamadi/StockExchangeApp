public class Crypto extends asset implements NewsFeed{

    private String blockchainID;
    private int rarity;

    public Crypto(String name, double price, int shares, String id, int rare) {
        super(name, price, shares);
        this.blockchainID = id;
        this.rarity = rare;
    }

    public int getRarity(){
        return this.rarity;
    }

    public String getBlockchainID(){
        return this.blockchainID;
    }

    // METHOD THAT INCREASES THE VALUE OF THE SHARES ATTRIBUTE
    public void mimeCrypto(){
        int share = getShares() + 1;
        setShares(share);

    }

    // METHOD THAT DECREASES THE VALUE OF THE BUY PRICE ATTRIBUTE
    public void yieldFarm(){
        double price = getBuyPrice() - 0.01;
        setBuyPrice(price);
    }

    //RETURNS DETAILS OF THE ASSET(CRYPTO)
    public String toString(){
        String sentence;
        if (this.getSold())
        {
            sentence = "SOLD ASSET, TYPE: "+ getClass().getName() + " , INVESTED IN: " + getName() + ", BLOCKCHAIN ID: " + this.blockchainID
                    + " WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getSold() + " SHARES, SOLD AT PRICE: "
                    + getSellPrice();
        }
        else{
            sentence = "TYPE: "+ getClass().getName() + ", INVESTED IN: " + getName() + ", BLOCKCHAIN ID: " + this.blockchainID
                    + " WITH BUY PRICE: "+  getBuyPrice()+ " WITH " + getShares() + " SHARES, CURRENT POSITION: "
                    + getCurrentPos();
        }
        return sentence;
    }

    public static String printNews(){
        String sentence = "BUY BITCOIN NOW! BUY ETH NOW, BOTH GROWING EXPONENTIALLY";
        return sentence;
    }

    public String printTaxLiability(){
        String sentence = "NO TAX LIABILITY";
        return sentence;
    }

}
