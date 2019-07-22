package Adapters;

import java.awt.event.*;

public class ChildWindowAction extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        e.getWindow().dispose();
    }
}