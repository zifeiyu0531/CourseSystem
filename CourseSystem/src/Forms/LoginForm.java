package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import Adapters.IndexWindowAction;
import Adapters.WindowAction;
import Bean.User;
import DataSource.LoginServer;

public class LoginForm extends JFrame implements ActionListener{    //登录界面
    private WindowAction windowAction = new WindowAction();
    private JLabel title = new JLabel("学生课程管理系统", JLabel.CENTER);
    private JLabel text = new JLabel("登录", JLabel.CENTER);
    private JLabel item1 = new JLabel("账号");
    private JLabel item2 = new JLabel("密码");
    private JLabel default1 = new JLabel("(默认：123)");
    private JLabel default2 = new JLabel("(默认：123)");
    private JButton login = new JButton("登录");
    private JButton quit = new JButton("退出");
    private JButton signup = new JButton("注册");
    private JTextField name = new JTextField(10);
    private JPasswordField passWord = new JPasswordField(10);
    private JPanel titles = new JPanel();
    private JPanel items = new JPanel();
    private JPanel inputs = new JPanel();
    private JPanel defaults = new JPanel();
    private JPanel buttons = new JPanel();
    public LoginForm(){
        super("学生课程管理系统");
        init();
        titles.add("North",title);
        titles.add("South",text);
        items.add("North",item1);
        items.add("South",item2);
        inputs.add("North",name);
        inputs.add("South",passWord);
        defaults.add("North",default1);
        defaults.add("South",default2);
        buttons.add(login);
        buttons.add(signup);
        buttons.add(quit);
        getContentPane().add(titles);
        getContentPane().add(items);
        getContentPane().add(inputs);
        getContentPane().add(defaults);
        getContentPane().add(buttons);
        pack();
        login.addActionListener(this);
        quit.addActionListener(this);
        signup.addActionListener(this);
        addWindowListener(windowAction);
    }

    public void init(){
        this.setPreferredSize(new Dimension(500,500));
        this.setLocation(500,200);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        titles.setSize(500,70);
        titles.setLayout(new BorderLayout());
        titles.setLocation(0,50);
        items.setSize(50,60);
        items.setLayout(new BorderLayout());
        items.setLocation(160,160);
        inputs.setSize(100,60);
        inputs.setLayout(new BorderLayout());
        inputs.setLocation(200,160);
        defaults.setSize(100,60);
        defaults.setLayout(new BorderLayout());
        defaults.setLocation(330,160);
        buttons.setSize(300,60);
        buttons.setLayout(new FlowLayout());
        buttons.setLocation(100,250);
        title.setFont(new Font("宋体",Font.BOLD, 26));
        text.setFont(new Font("宋体",Font.BOLD, 16));
        default1.setForeground(Color.gray);
        default2.setForeground(Color.gray);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==quit) {
            System.exit(0);
        } else if(e.getSource()==login){//登录事件
            logIn(name.getText(),passWord.getText());
        } else if(e.getSource()==signup){//注册事件
            SignupForm signupForm = new SignupForm();
            this.dispose();
        }
    }

    public void logIn(String name, String passWord){
        User user = new User();
        user.setName(name);
        user.setPassWord(passWord);
        LoginServer loginServer = new LoginServer();
        try{
            if(loginServer.getLogin(user)) {
                IndexForm indexForm = new IndexForm();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "登录失败，请检查账号密码！", "error",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
        }
    }
}
