public class Currency extends asset{

    private boolean marketOpen;
    private String day;
    private String company;

    public Currency(String name, double price, int shares, String day, String company){
        super(name, price, shares);
        this.marketOpen = true;
        this.day = day;
        this.company = company;
    }

    public void closeOrOpenMarket(){
        this.marketOpen = !this.marketOpen;
    }

    public boolean getMarketOpen(){
        return this.marketOpen;
    }

    public String getDay(){
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCompany() {
        return company;
    }


    //RETURNS DETAILS OF THE ASSET(CURRENCY)
    public String toString(){
        String sentence;
        if (getSold())
        {
            sentence = "SOLD ASSET, TYPE: "+ getClass().getName() + " , INVESTED IN: " + getName() + ", FROM COMPANY: " + this.company
                    + " WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, SOLD AT PRICE: "
                    + getSellPrice();
        }
        else{
            sentence = "TYPE: "+ getClass().getName() + ", INVESTED IN: " + getName() + ", FROM COMPANY: " + this.company
                    + " WITH BUY PRICE: "+  getBuyPrice() + " WITH " + getShares() + " SHARES, CURRENT POSITION: "
                    + getCurrentPos();
        }
        return sentence;
    }

    public static String printNews(){
        String sentence = "RUSSIA RUBLE DRASTICALLY FALLS IN VALUE POST THE SANCTIONS" + "\n"+
                " IMPOSED ON UK GOVERNMENT AND USA GOVERNMENT";
        return sentence;
    }

    public String printTaxLiability(){
        String sentence = "TAX LIABILITY";
        return sentence;
    }


}
