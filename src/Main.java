
import java.awt.*;
import java.awt.event.*;


//MAIN FILE
public class Main extends Frame {

    private static String homeWelcome = "Welcome to Investment Trading App" + "\n"+
            "Trade on Crypto,NFT, Forex, Commodities, Stocks and GROW YOUR WEALTH";
    private static TextArea infoArea = new TextArea(homeWelcome);

    private Panel options;

    public static void print(String text){
        infoArea.setText(text);
    }

    public Main(){

        this.setLayout(new FlowLayout());
        this.setTitle("KAMADI'S EXCHANGE");


        infoArea.setEditable(false);
        this.add(infoArea);


        WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc);

        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        options = new Panel();
        options.setLayout(new GridLayout(0,1));
        options.setVisible(true);
        this.add(options);


        //LOGIN BUTTON WHICH ALLOWS USER TO CREATE ACCOUNT AS LONG AS THEY ENTER CORRECT TYPE DETAILS
        Button btn2 = new Button("LOGIN");
        options.add(btn2);
        this.setVisible(true);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prompt login = new Prompt();
                TextField field = new TextField(30);
                Label mylabel = new Label("Enter name:");
                login.add(field);
                login.add(mylabel);

                TextField field1 = new TextField(30);
                Label mylabel1 = new Label("Enter initial deposit:");
                login.add(field1);
                login.add(mylabel1);


                login.addSubmitListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String name = field.getText();
                            String amount = field1.getText();
                            if (amount.length()==0) {
                                dispose();
                                new NewOwner(name, 0);
                            }
                            else{
                                dispose();
                                new NewOwner(name, Integer.parseInt(amount));
                            }
                        }
                        catch (Exception f){
                            System.out.println("ERROR, DIDN'T ENTER INTEGER VALUE FOR INITIAL DEPOSIT");
                        }

                    }
                });
                login.activate();
            }
        });

    }



    public static void main(String[] args){
    new Main();
    }
}
