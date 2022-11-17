import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewOwner extends Frame{

    private Owner owner;

    private static String homeWelcome = "Welcome to your homepage where you can manage" + "\n"+
            "your investments and those of your clients";
    private static TextArea infoArea = new TextArea(homeWelcome);

    private Panel options;

    //PRINTS TEXT TO THE GUI SCREEN
    public static void print(String text){
        infoArea.setText(text);
    }

    // ADDS A NEW CLIENT ALONG WITH A BUTTON WHICH CAN BE CLICKED THAT OPENS A CLIENT WINDOW
    public void addClient(String name, int amount){
        owner.addClient(new Client(name, amount, 0.2, 10, 50));
        Button btn = new Button(name);
        options.add(btn);
        this.setVisible(true);
        ArrayList<String> c = new ArrayList<String>();
        for(Client cl : owner.getClients()){
            c.add(cl.getName());
        }
        int i = c.indexOf(name);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientTrading(owner, i);
            }
        });
    }

    // ADDS A NEW PREMIUM CLIENT ALONG WITH A BUTTON WHICH CAN BE CLICKED THAT OPENS A WINDOW
    public void addPClient(String name, int amount, boolean pref, String option){
        owner.addClient(new PremiumClient(name, amount, 0.2, 100, 100000, pref, option));
        Button btn = new Button(name + " (P)");
        options.add(btn);
        this.setVisible(true);
        ArrayList<String> c = new ArrayList<String>();
        for(Client cl : owner.getClients()){
            c.add(cl.getName());
        }
        int i = c.indexOf(name);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientTrading(owner, i);
            }
        });
    }

    //PRINTS NEWS
    public static String news(){
        String sentence = Crypto.printNews() + "\n" + Currency.printNews() + "\n" + Commodities.printNews();
        return sentence;
    }


    public NewOwner(String name, int num){

        this.owner = new Owner(name, num);
        this.setLayout(new FlowLayout());
        this.setTitle("HOME - " + name+"(logged in)");

        //DEPOSIT BUTTON
        Button dep = new Button("Deposit");
        this.add(dep);
        dep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt dep = new Prompt();
                TextField amount = new TextField(30);
                Label mylabel = new Label("Enter amount:");
                dep.add(amount);
                dep.add(mylabel);

                dep.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            int depositing = Integer.parseInt(amount.getText());
                            owner.getAccount().depositMoney(depositing);
                            print("Success, amount deposited to account");
                        }
                        catch (Exception f){
                            print("TRANSACTION FAILED");
                        }

                    }
                });
                dep.activate();
            }
        });

        //WITHDRAW BUTTON
        Button withdrawButton = new Button("Withdraw");
        this.add(withdrawButton);
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt wit = new Prompt();

                TextField amount = new TextField(30);
                Label mylabel4 = new Label("Enter amount:");
                wit.add(amount);
                wit.add(mylabel4);

                wit.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            int withdraw = Integer.parseInt(amount.getText());
                            if (owner.getAccount().getBalance()>=withdraw){
                                owner.getAccount().withdrawMoney(withdraw);
                                print("SUCCESSFULLY WITHDREW: "+ withdraw);
                            }
                            else{
                                print("INSUFFICIENT FUNDS");
                            }
                        }
                        catch (Exception f){
                            print("TRANSACTION FAILED");
                        }

                    }
                });
                wit.activate();
            }
        });

        //BALANCE BUTTON
        Button reportButton = new Button("BALANCE");
        this.add(reportButton);
        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print("BALANCE: " + owner.getAccount().getBalance());
            }
        });

        //ADD CLIENT BUTTON
        Button addClientButton = new Button("Add Client");
        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Prompt acp = new Prompt();
                TextField field = new TextField(30);
                Label mylabel = new Label("Enter new Client:");
                acp.add(field);
                acp.add(mylabel);

                TextField amount = new TextField(30);
                Label mylabel2 = new Label("Enter initial deposit amount:");
                acp.add(amount);
                acp.add(mylabel2);
                acp.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = field.getText();
                            int cost = Integer.parseInt(amount.getText());
                            addClient(name, cost);
                        }
                        catch(Exception f){
                            print("FAILED TO ADD CLIENT");
                        }
                    }
                });
                acp.activate();
            }
        });

        this.add(addClientButton);

        //ADD PREMIUM CLIENT BUTTON
        Button addPremium = new Button("Add Premium Client");
        addPremium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Prompt pre = new Prompt();
                TextField field = new TextField(30);
                Label mylabel = new Label("Enter new Premium Client:");
                pre.add(field);
                pre.add(mylabel);

                TextField amount = new TextField(30);
                Label mylabel2 = new Label("Enter initial deposit amount:");
                pre.add(amount);
                pre.add(mylabel2);

                TextField preference = new TextField(30);
                Label mylabel3 = new Label("Enter preferable asset(Leave blank if none):");
                pre.add(preference);
                pre.add(mylabel3);
                pre.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = field.getText();
                            int cost = Integer.parseInt(amount.getText());
                            String pref = preference.getText();
                            boolean bol = false;
                            if (pref.length()>0)
                            {
                                bol = true;
                            }
                            addPClient(name, cost, bol, pref);
                        }
                        catch (Exception f){
                            print("FAILED TO ADD PREMIUM CLIENT");
                        }

                    }
                });
                pre.activate();
            }
        });

        this.add(addPremium);

        //INVESTMENTS BUTTON
        Button invest = new Button("INVESTMENTS");
        this.add(invest);
        invest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OwnerTrading(owner);
            }
        });


        //NEWS FEED BUTTON
        Button news = new Button("NEWS FEED");
        this.add(news);
        news.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print(news());
            }
        });


        infoArea.setEditable(false);
        this.add(infoArea);


        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);

        //this.setSize(800,800);
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        options = new Panel();
        options.setLayout(new GridLayout(0,1));
        options.setVisible(true);
        this.add(options);
    }
}