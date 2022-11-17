import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class ClientTrading extends Frame{


    private static int uniqueIdCounter=0;
    private static int nftId=0;

    private static String homeWelcome = "Manage Investments Here";
    private static TextArea infoArea = new TextArea(homeWelcome);


    //PRINTS TEXT TO THE GUI SCREEN
    public static void print(String text){
        infoArea.setText(text);
    }


    //PRINT INVESTMENT
    public static void displayInvestments(Account bank){
        String sentence = "CURRENT ASSETS:" + "\n";
        ArrayList<asset> all = bank.getAllAssets();
        for(asset a : all){
            sentence = sentence + "\n" + a.toString();
        }
        if (all.size()==0){
            sentence = sentence + "NONE";
        }
        print(sentence);
    }

    // PRINT SOLD ASSET
    public static void displaySoldAssets(Account bank){
        String sentence = "YOU'VE SOLD THESE ASSETS: " + "\n";
        ArrayList<asset> all = bank.getSold();
        for(asset a : all){
            sentence = sentence + "\n" + a.toString();
        }
        if (all.size()==0){
            sentence = sentence + "NONE";
        }
        print(sentence);
    }

    //Gets a random number between x and y
    public static int randomNum(int length)
    {
        return ((int) (Math.random()*(length - 0))) + 0;
    }

    //reads the file and return double
    public static double readFile() throws IOException
    {
        String sentence = "";
        BufferedReader reading = new BufferedReader(new FileReader("price.txt"));
        String line;
        while ((line = reading.readLine()) != null)
        {
            sentence = sentence + line +"*";
        }
        sentence = sentence.substring(0, sentence.length() - 1); //removes last character
        String [] arr = sentence.split("\\*");
        reading.close();

        double var;
        int index;

        double[] arr2 = new double[arr.length];

        for(String i : arr){
            var = Double.parseDouble(i);
            index = Arrays.asList(arr).indexOf(i);
            arr2[index]=var;
        }

        return arr2[randomNum(arr2.length)];

    }

    //GETS A RANDOM DOUBLE
    public static double getRandomValue(){
        double var = 0.0;
        try{
            var = readFile();
        }
        catch (Exception f){
            System.out.println("FILE COULD NOT BE READ");
            System.out.println(f);
        }
        return var;
    }


    public ClientTrading(Owner own, int index){


        this.setLayout(new FlowLayout());
        String type = own.getClients().get(index).getClass().getName();
        this.setTitle("INVESTMENTS - "+ own.getClients().get(index)+"("+type+")");

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
                            own.getClients().get(index).getAccount().depositMoney(depositing);
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
                            if (own.getClients().get(index).getAccount().getBalance()>=withdraw){
                                own.getClients().get(index).getAccount().withdrawMoney(withdraw);
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
                print("BALANCE: " + own.getClients().get(index).getAccount().getBalance());
            }
        });


        //VIEW INVESTMENTS(TO STRING), CURRENT ASSETS
        Button reportButt = new Button("VIEW INVESTMENTS");
        this.add(reportButt);
        reportButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Account acc = own.getClients().get(index).getAccount();
                displayInvestments(acc);
            }
        });


        //VIEW NET PROFIT
        Button report = new Button("VIEW NET PROFIT");
        this.add(report);
        report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print("YOUR NET PROFIT IS: "+ own.getClients().get(index).getAccount().getNetProfit());
            }
        });


        //VIEW SOLD ASSETS
        Button b3 = new Button("VIEW SOLD ASSETS");
        this.add(b3);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Account acc = own.getClients().get(index).getAccount();
                displaySoldAssets(acc);
            }
        });

        infoArea.setEditable(false);
        this.add(infoArea);

        //BUY CRYPTO BUTTON
        Button btn1 = new Button("BUY CRYPTO");
        this.add(btn1);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt buyC = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                buyC.add(info1);
                buyC.add(mylabel1);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter shares(INT):");
                buyC.add(info3);
                buyC.add(mylabel3);

                TextField info5 = new TextField(30);
                Label mylabel5 = new Label("Enter rarity(INT):");
                buyC.add(info5);
                buyC.add(mylabel5);

                buyC.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = info1.getText();
                            int share = Integer.parseInt(info3.getText());
                            String id = Integer.toString(uniqueIdCounter);
                            int rare = Integer.parseInt(info5.getText());
                            int cost = (int)Math.round(val*share);
                            if (own.getClients().get(index).getTotalAssets()<own.getClients().get(index).getMaxAsset()){
                                if(own.getClients().get(index).getShareCap()>share){
                                    if (cost<own.getClients().get(index).getAccount().getBalance()){
                                        own.getClients().get(index).getAccount().addAsset(new Crypto(name, val, share, id, rare));
                                        own.getClients().get(index).getAccount().withdrawMoney(cost);
                                        print("NEW ASSET ADDED");
                                        uniqueIdCounter++;
                                        own.getClients().get(index).incrementTotalAsset();
                                    }
                                    else{
                                        print("INSUFFICIENT FUNDS TRANSACTION FAILED");
                                    }
                                }
                                else{
                                    print("YOU CANNOT BUY MORE THAN " + own.getClients().get(index).getShareCap()+ " SHARES.");
                                }
                            }

                            else{
                                print(""+own.getClients().get(index).getClass().getName()+" can only have a maximum of" +
                                        " "+own.getClients().get(index).getMaxAsset()+" Assets.");
                            }


                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                buyC.activate();
            }
        });


        //SELL CRYPTO BUTTON
        Button btn2 = new Button("SELL CRYPTO");
        this.add(btn2);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt sellC = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                sellC.add(info1);
                sellC.add(mylabel1);

                TextField info2 = new TextField(30);
                Label mylabel2 = new Label("Enter blockchain ID:");
                sellC.add(info2);
                sellC.add(mylabel2);

                sellC.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean s = false;
                        try{
                            ArrayList<Crypto> c = new ArrayList<Crypto>();
                            String name = info1.getText();
                            String id = info2.getText();
                            for(asset a : own.getClients().get(index).getAccount().getAllAssets()){
                                if (a.getClass().getName().equals("Crypto")){
                                    c.add((Crypto) a);
                                }
                            }
                            for(Crypto a : c){
                                if ((a.getName().equals(name)) && (a.getBlockchainID().equals(id))){
                                    own.getClients().get(index).getAccount().itemSold(a);
                                    a.sell(val);
                                    double commission = own.getClients().get(index).getCommission();
                                    int paying = (int)Math.round(val*a.getShares()*commission);
                                    int prof = (int)Math.round(val*a.getShares()) - (int)Math.round(a.getBuyPrice()*a.getShares()) - paying;
                                    own.getClients().get(index).getAccount().amendNetProfit(prof);
                                    s = true;
                                    if ((int)Math.round(val*a.getShares())>0) own.getClients().get(index).getAccount().depositMoney((int)Math.round(val*a.getShares()-paying));
                                    own.getAccount().depositMoney(paying);
                                    own.getClients().get(index).decrementTotalAsset();
                                }
                            }
                            if (s){
                                print("ASSET SOLD");
                            }
                            else{
                                print("FAILED TO COMPLETE TRANSACTION");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                sellC.activate();
            }
        });

        //BUY CURRENCY BUTTON
        Button btn3 = new Button("BUY CURRENCY");
        this.add(btn3);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt buyCu = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                buyCu.add(info1);
                buyCu.add(mylabel1);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter shares(INT):");
                buyCu.add(info3);
                buyCu.add(mylabel3);

                TextField info5 = new TextField(30);
                Label mylabel5 = new Label("Enter day:");
                buyCu.add(info5);
                buyCu.add(mylabel5);

                TextField info6 = new TextField(30);
                Label mylabel6 = new Label("Enter company:");
                buyCu.add(info6);
                buyCu.add(mylabel6);

                buyCu.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = info1.getText();
                            int share = Integer.parseInt(info3.getText());
                            String day = info5.getText();
                            String company = info6.getText();
                            int cost = (int)Math.round(val*share);
                            if (own.getClients().get(index).getTotalAssets()<own.getClients().get(index).getMaxAsset()){
                                if(own.getClients().get(index).getShareCap()>share) {
                                    if (cost < own.getClients().get(index).getAccount().getBalance()) {
                                        own.getClients().get(index).getAccount().addAsset(new Currency(name, val, share, day, company));
                                        own.getClients().get(index).getAccount().withdrawMoney(cost);
                                        print("NEW ASSET ADDED");
                                        own.getClients().get(index).incrementTotalAsset();
                                    }
                                    else{
                                        print("INSUFFICIENT FUNDS TRANSACTION FAILED");
                                    }
                                }
                                else{
                                    print("YOU CANNOT BUY MORE THAN " + own.getClients().get(index).getShareCap()+ " SHARES.");
                                }
                            }

                            else{
                                print(""+own.getClients().get(index).getClass().getName()+" can only have a maximum of" +
                                        " "+own.getClients().get(index).getMaxAsset()+" Assets.");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }

                    }
                });
                buyCu.activate();
            }
        });

        //SELL CURRENCY BUTTON
        Button btn4 = new Button("SELL CURRENCY");
        this.add(btn4);
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt sellCu = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                sellCu.add(info1);
                sellCu.add(mylabel1);

                TextField info2 = new TextField(30);
                Label mylabel2 = new Label("Enter company:");
                sellCu.add(info2);
                sellCu.add(mylabel2);

                sellCu.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean s = false;
                        try{
                            ArrayList<Currency> c = new ArrayList<Currency>();
                            String name = info1.getText();
                            String company = info2.getText();
                            for(asset a : own.getClients().get(index).getAccount().getAllAssets()){
                                if (a.getClass().getName().equals("Currency")){
                                    c.add((Currency) a);
                                }
                            }
                            for(Currency a : c){
                                if ((a.getName().equals(name)) && (a.getCompany().equals(company))){
                                    own.getClients().get(index).getAccount().itemSold(a);
                                    a.sell(val);
                                    double commission = own.getClients().get(index).getCommission();
                                    int paying = (int)Math.round(val*a.getShares()*commission);
                                    int prof = (int)Math.round(val*a.getShares()) - (int)Math.round(a.getBuyPrice()*a.getShares()) - paying;
                                    own.getClients().get(index).getAccount().amendNetProfit(prof);
                                    s = true;
                                    if ((int)Math.round(val*a.getShares())>0) own.getClients().get(index).getAccount().depositMoney((int)Math.round(val*a.getShares()-paying));
                                    own.getAccount().depositMoney(paying);
                                    own.getClients().get(index).decrementTotalAsset();
                                }
                            }
                            if (s){
                                print("ASSET SOLD");
                            }
                            else{
                                print("FAILED TO COMPLETE TRANSACTION");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                sellCu.activate();
            }
        });


        //BUY STOCK BUTTON
        Button btn5 = new Button("BUY STOCK");
        this.add(btn5);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt buyS = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                buyS.add(info1);
                buyS.add(mylabel1);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter shares(INT):");
                buyS.add(info3);
                buyS.add(mylabel3);


                buyS.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = info1.getText();
                            int share = Integer.parseInt(info3.getText());
                            int cost = (int)Math.round(val*share);
                            if (own.getClients().get(index).getTotalAssets()<own.getClients().get(index).getMaxAsset()){
                                if(own.getClients().get(index).getShareCap()>share) {
                                    if (cost < own.getClients().get(index).getAccount().getBalance()) {
                                        own.getClients().get(index).getAccount().addAsset(new Stock(name, val, share, true, true, 70));
                                        own.getClients().get(index).getAccount().withdrawMoney(cost);
                                        print("NEW ASSET ADDED");
                                        own.getClients().get(index).incrementTotalAsset();
                                    }
                                    else{
                                        print("INSUFFICIENT FUNDS TRANSACTION FAILED");
                                    }
                                }
                                else{
                                    print("YOU CANNOT BUY MORE THAN " + own.getClients().get(index).getShareCap()+ " SHARES.");
                                }
                            }

                            else{
                                print(""+own.getClients().get(index).getClass().getName()+" can only have a maximum of" +
                                        " "+own.getClients().get(index).getMaxAsset()+" Assets.");
                            }


                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                buyS.activate();
            }
        });


        //SELL STOCK BUTTON
        Button btn6 = new Button("SELL STOCK");
        this.add(btn6);
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt sellS = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                sellS.add(info1);
                sellS.add(mylabel1);

                sellS.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean s = false;
                        try{
                            ArrayList<Stock> c = new ArrayList<Stock>();
                            String name = info1.getText();

                            for(asset a : own.getClients().get(index).getAccount().getAllAssets()){
                                if (a.getClass().getName().equals("Stock")){
                                    c.add((Stock) a);
                                }
                            }
                            for(Stock a : c){
                                if (a.getName().equals(name)){
                                    own.getClients().get(index).getAccount().itemSold(a);
                                    a.sell(val);
                                    double commission = own.getClients().get(index).getCommission();
                                    int paying = (int)Math.round(val*a.getShares()*commission);
                                    int prof = (int)Math.round(val*a.getShares()) - (int)Math.round(a.getBuyPrice()*a.getShares()) - paying;
                                    own.getClients().get(index).getAccount().amendNetProfit(prof);
                                    s = true;
                                    if ((int)Math.round(val*a.getShares())>0) own.getClients().get(index).getAccount().depositMoney((int)Math.round(val*a.getShares()-paying));
                                    own.getAccount().depositMoney(paying);
                                    own.getClients().get(index).decrementTotalAsset();
                                }
                            }
                            if (s){
                                print("ASSET SOLD");
                            }
                            else{
                                print("FAILED TO COMPLETE TRANSACTION");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                sellS.activate();
            }
        });


        //BUY NFT BUTTON
        Button btn7 = new Button("BUY NFT");
        this.add(btn7);
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt buyN = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                buyN.add(info1);
                buyN.add(mylabel1);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter shares(INT):");
                buyN.add(info3);
                buyN.add(mylabel3);

                TextField info4 = new TextField(30);
                Label mylabel4 = new Label("Enter representation:");
                buyN.add(info4);
                buyN.add(mylabel4);

                TextField info5 = new TextField(30);
                Label mylabel5 = new Label("Enter category:");
                buyN.add(info5);
                buyN.add(mylabel5);



                buyN.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = info1.getText();
                            int share = Integer.parseInt(info3.getText());
                            String representation = info4.getText();
                            String cat = info5.getText();
                            int cost = (int)Math.round(val*share);
                            if (own.getClients().get(index).getTotalAssets()<own.getClients().get(index).getMaxAsset()){
                                if(own.getClients().get(index).getShareCap()>share) {
                                    if (cost < own.getClients().get(index).getAccount().getBalance()) {
                                        own.getClients().get(index).getAccount().addAsset(new NFT(name, val, share, nftId, representation, cat));
                                        own.getClients().get(index).getAccount().withdrawMoney(cost);
                                        print("NEW ASSET ADDED");
                                        own.getClients().get(index).incrementTotalAsset();
                                        nftId++;
                                    }
                                    else{
                                        print("INSUFFICIENT FUNDS TRANSACTION FAILED");
                                    }
                                }
                                else{
                                    print("YOU CANNOT BUY MORE THAN " + own.getClients().get(index).getShareCap()+ " SHARES.");
                                }
                            }

                            else{
                                print(""+own.getClients().get(index).getClass().getName()+" can only have a maximum of" +
                                        " "+own.getClients().get(index).getMaxAsset()+" Assets.");
                            }


                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                buyN.activate();
            }
        });

        //SELL NFT BUTTON
        Button btn8 = new Button("SELL NFT");
        this.add(btn8);
        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt sellN = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                sellN.add(info1);
                sellN.add(mylabel1);

                TextField info2 = new TextField(30);
                Label mylabel2 = new Label("Enter id:");
                sellN.add(info2);
                sellN.add(mylabel2);


                sellN.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean s = false;
                        try{
                            ArrayList<NFT> c = new ArrayList<NFT>();
                            String name = info1.getText();
                            int id = Integer.parseInt(info2.getText());
                            for(asset a : own.getClients().get(index).getAccount().getAllAssets()){
                                if (a.getClass().getName().equals("NFT")){
                                    c.add((NFT) a);
                                }
                            }
                            for(NFT a : c){
                                if ((a.getName().equals(name)) && (a.getUniqueIdentificationCode()==id)){
                                    own.getClients().get(index).getAccount().itemSold(a);
                                    a.sell(val);
                                    double commission = own.getClients().get(index).getCommission();
                                    int paying = (int)Math.round(val*a.getShares()*commission);
                                    int prof = (int)Math.round(val*a.getShares()) - (int)Math.round(a.getBuyPrice()*a.getShares()) - paying;
                                    own.getClients().get(index).getAccount().amendNetProfit(prof);
                                    s = true;
                                    if ((int)Math.round(val*a.getShares())>0) own.getClients().get(index).getAccount().depositMoney((int)Math.round(val*a.getShares()-paying));
                                    own.getAccount().depositMoney(paying);
                                    own.getClients().get(index).decrementTotalAsset();
                                }
                            }
                            if (s){
                                print("ASSET SOLD");
                            }
                            else{
                                print("FAILED TO COMPLETE TRANSACTION");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                sellN.activate();
            }
        });

        //BUY COMMODITY BUTTON
        Button btn9 = new Button("BUY COMMODITY");
        this.add(btn9);
        btn9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt buyCo = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                buyCo.add(info1);
                buyCo.add(mylabel1);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter shares(INT):");
                buyCo.add(info3);
                buyCo.add(mylabel3);

                TextField info4 = new TextField(30);
                Label mylabel4 = new Label("Enter source:");
                buyCo.add(info4);
                buyCo.add(mylabel4);

                TextField info5 = new TextField(30);
                Label mylabel5 = new Label("Enter Soft or Hard Commodity:");
                buyCo.add(info5);
                buyCo.add(mylabel5);



                buyCo.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = info1.getText();
                            int share = Integer.parseInt(info3.getText());
                            String source = info4.getText();
                            String type = info5.getText();
                            int cost = (int)Math.round(val*share);
                            if (own.getClients().get(index).getTotalAssets()<own.getClients().get(index).getMaxAsset()){
                                if(own.getClients().get(index).getShareCap()>share) {
                                    if (cost < own.getClients().get(index).getAccount().getBalance()) {
                                        own.getClients().get(index).getAccount().addAsset(new Commodities(name, val, share, source, type));
                                        own.getClients().get(index).getAccount().withdrawMoney(cost);
                                        print("NEW ASSET ADDED");
                                        own.getClients().get(index).incrementTotalAsset();
                                    }
                                    else{
                                        print("INSUFFICIENT FUNDS TRANSACTION FAILED");
                                    }
                                }
                                else{
                                    print("YOU CANNOT BUY MORE THAN " + own.getClients().get(index).getShareCap()+ " SHARES.");
                                }
                            }

                            else{
                                print(""+own.getClients().get(index).getClass().getName()+" can only have a maximum of" +
                                        " "+own.getClients().get(index).getMaxAsset()+" Assets.");
                            }


                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                buyCo.activate();
            }
        });

        //SELL COMMODITY BUTTON
        Button btn10 = new Button("SELL COMMODITY");
        this.add(btn10);
        btn10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt sellCo = new Prompt();
                double val = getRandomValue();
                print("PRICE IS: " + val);
                TextField info1 = new TextField(30);
                Label mylabel1 = new Label("Enter name:");
                sellCo.add(info1);
                sellCo.add(mylabel1);

                TextField info2 = new TextField(30);
                Label mylabel2 = new Label("Enter source:");
                sellCo.add(info2);
                sellCo.add(mylabel2);

                TextField info3 = new TextField(30);
                Label mylabel3 = new Label("Enter type:");
                sellCo.add(info3);
                sellCo.add(mylabel3);


                sellCo.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean s = false;
                        try{
                            ArrayList<Commodities> c = new ArrayList<Commodities>();
                            String name = info1.getText();
                            String source = info2.getText();
                            String type = info3.getText();
                            for(asset a : own.getClients().get(index).getAccount().getAllAssets()){
                                if (a.getClass().getName().equals("Commodities")){
                                    c.add((Commodities) a);
                                }
                            }
                            for(Commodities a : c){
                                if ((a.getName().equals(name)) && (a.getResource().equals(source)) &&
                                        a.getType().equals(type)){
                                    own.getClients().get(index).getAccount().itemSold(a);
                                    a.sell(val);
                                    double commission = own.getClients().get(index).getCommission();
                                    int paying = (int)Math.round(val*a.getShares()*commission);
                                    int prof = (int)Math.round(val*a.getShares()) - (int)Math.round(a.getBuyPrice()*a.getShares()) - paying;
                                    own.getClients().get(index).getAccount().amendNetProfit(prof);
                                    s = true;
                                    if ((int)Math.round(val*a.getShares())>0) own.getClients().get(index).getAccount().depositMoney((int)Math.round(val*a.getShares()-paying));
                                    own.getAccount().depositMoney(paying);
                                    own.getClients().get(index).decrementTotalAsset();
                                }
                            }
                            if (s){
                                print("ASSET SOLD");
                            }
                            else{
                                print("FAILED TO COMPLETE TRANSACTION");
                            }
                        }
                        catch(Exception f){
                            print("TRANSACTION FAILED");
                        }
                    }
                });
                sellCo.activate();
            }
        });


        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);


        this.setSize(500,700);
        this.setVisible(true);
    }
}
