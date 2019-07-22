package Adapters;

import java.awt.event.*;
import Forms.LoginForm;

public class IndexWindowAction extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        e.getWindow().dispose();
        LoginForm loginForm = new LoginForm();
    }
}