public class Stock extends asset{

    private boolean rightToVote;
    private boolean partner;
    private int ownership;

    public Stock(String name, double price, int shares, boolean right, boolean part, int own){
        super(name, price, shares);
        this.rightToVote = right;
        this.partner = part;
        this.ownership = own;
    }

    public boolean getRightToVote(){
        return this.rightToVote;
    }

    public void setRightToVote(boolean vote){
        this.rightToVote = vote;
    }

    public boolean getPartner(){
        return this.partner;
    }

    public void setPartner(boolean part){
        this.partner = part;
    }

    public int getOwnership(){
        return this.ownership;
    }

    public void setOwnership(int score){
        this.ownership = score;
    }

}
