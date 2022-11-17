public class Client extends User{

    private double commission;
    private int maxAssets;
    private int shareCap;
    private int totalAssets;

    public Client(String name, int amount, double com, int maxAss, int cap){
        super(name, amount);
        this.commission = com;
        this.maxAssets = maxAss;
        this.totalAssets = 0;
        this.shareCap = cap;
    }

    public Client(String name, double com, int maxAss, int cap){
        super(name);
        this.commission = com;
        this.maxAssets = maxAss;
        this.totalAssets = 0;
        this.shareCap = cap;
    }

    public int getTotalAssets(){
        return this.totalAssets;
    }

    public int getShareCap(){
        return this.shareCap;
    }

    public int getMaxAsset(){
        return this.maxAssets;
    }

    public double getCommission(){
        return this.commission;
    }

    public void incrementTotalAsset(){
        this.totalAssets = this.totalAssets +1;
    }

    public void decrementTotalAsset(){
        this.totalAssets = this.totalAssets - 1;
    }

    //RETURNS DETAILS OF THE CLIENT
    public String toString(){
        String sentence = "CLIENT NAME: " + getName() + ", COMMISSION: "+getCommission()+ ", CURRENTLY HAS TOTAL" +
                " ASSETS: "+ maxAssets;
        return sentence;
    }

}
