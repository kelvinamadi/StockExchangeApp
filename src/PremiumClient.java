public class PremiumClient extends Client{

    private String desired;
    private boolean preference;

    public PremiumClient(String name, int amount, double com, int maxAss, int cap, boolean pref, String option){
        super(name, amount, com, maxAss, cap);
        this.preference = pref;
        this.desired = option;
    }

    public PremiumClient(String name, double com, int maxAss, int cap, boolean pref, String option){
        super(name, com, maxAss, cap);
        this.preference = pref;
        this.desired = option;
    }

    public boolean getPreference(){
        return this.preference;
    }

    public String getDesired(){
        return this.desired;
    }

    public void changeDesired(String asset){
        this.desired = asset;
    }

    public void changePreference(){
        this.preference = !this.preference;
    }

    // RETURNS DETAILS ABOUT THE CLIENT
    public String toString(){
        String sentence = "PREMIUM CLIENT NAME: " + getName() + ", COMMISSION: "+getCommission()+ ", CURRENTLY HAS TOTAL" +
                " ASSETS: "+ getMaxAsset() + ", PREFERENCE IS: "+preference;
        return sentence;
    }
}
