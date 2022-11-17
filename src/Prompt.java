import java.awt.*;
import java.awt.event.*;
public class Prompt extends Frame{

	// PROMPT CLASS WHICH BRINGS A POP THAT ALLOWS USER TO ENTER INFORMATION
    private Button submit;
    public Prompt(){	
	this.setLayout(new GridLayout(0,2));
	submit = new Button("Enter");
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent evt) {
		    ((Frame)(evt.getSource())).dispose();
		}
	    });	
    }

    public void addSubmitListener(ActionListener listener){
	submit.addActionListener(listener);
    }    

    public void activate(){	
        this.add(submit);
	this.pack(); //
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }



}
