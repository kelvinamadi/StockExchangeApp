public class NFT extends asset{

    private int uniqueIdentificationCode;
    private String realLifeRepresentation;
    private String category;

    public NFT(String name, double price, int shares, int id, String representation, String category){
        super(name, price, shares);
        this.uniqueIdentificationCode = id;
        this.realLifeRepresentation = representation;
        this.category = category;
    }

    // INCREASES VALUE OF SHARE ATTRIBUTE
    public void shareOnSocialMedia(){
        int share = getShares() + 5;
        setShares(share);
    }

    // CHANGES VALUE OF BUY PRICE
    public void stake(double val){
        double price = getBuyPrice();
        setBuyPrice(val);
    }

    public int getUniqueIdentificationCode() {
        return this.uniqueIdentificationCode;
    }


    public String getRealLifeRepresentation() {
        return this.realLifeRepresentation;
    }

    public void setRealLifeRepresentation(String representation) {
        this.realLifeRepresentation = representation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // CHANGES VALUES OF VARIOUS ATTRIBUTES
    public void swapNFT(String representation, String category, String name, double price){
        setRealLifeRepresentation(representation);
        setCategory(category);
        setName(name);
        setBuyPrice(price);
    }

    // METHOD THAT RETURNS DETAILS OF ASSET(NFT)
    public String toString(){
        String sentence;
        if (getSold())
        {
            sentence = "SOLD ASSET, TYPE: "+ getClass().getName() + " , INVESTED IN: " + getName() +
                    ", UNIQUE IDENTIFICATION CODE: " + this.uniqueIdentificationCode + ", REPRESENTATION: " +this.realLifeRepresentation
                    + " WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, SOLD AT PRICE: "
                    + getSellPrice();
        }
        else{
            sentence = "TYPE: "+ getClass().getName() + ", INVESTED IN: " + getName() +
                    ", UNIQUE IDENTIFICATION CODE: " + this.uniqueIdentificationCode + ", REPRESENTATION: " +this.realLifeRepresentation
                    + " WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, CURRENT POSITION: "
                    + getCurrentPos();
        }
        return sentence;
    }


}
