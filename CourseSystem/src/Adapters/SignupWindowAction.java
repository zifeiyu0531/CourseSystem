package Adapters;

import java.awt.event.*;
import Forms.LoginForm;

public class SignupWindowAction extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        e.getWindow().dispose();
        LoginForm loginForm = new LoginForm();
    }
}