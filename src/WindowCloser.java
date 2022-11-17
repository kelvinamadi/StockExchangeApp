// WINDOW CLOSER, CLOSES THE VARIOUS GUI WINDOWS
import java.awt.event.*;
class WindowCloser extends WindowAdapter{
    public void windowClosing(WindowEvent evt) {
	System.exit(0);
    }
}
