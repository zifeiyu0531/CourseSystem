package Adapters;

import java.awt.event.*;

public class WindowAction extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}
