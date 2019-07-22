package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import Bean.User;
import DataSource.SignupServer;
import Adapters.IndexWindowAction;

public class SignupForm extends JFrame implements ActionListener {
    private IndexWindowAction windowAction = new IndexWindowAction();
    private JLabel text = new JLabel("注册", JLabel.CENTER);
    private JLabel item1 = new JLabel("账号");
    private JLabel item2 = new JLabel("密码");
    private JButton quit = new JButton("退出");
    private JButton signup = new JButton("注册");
    private JTextField name = new JTextField(10);
    private JPasswordField passWord = new JPasswordField(10);
    private JPanel titles = new JPanel();
    private JPanel items = new JPanel();
    private JPanel inputs = new JPanel();
    private JPanel buttons = new JPanel();
    public SignupForm(){
        super("注册");
        init();
        titles.add("South",text);
        items.add("North",item1);
        items.add("South",item2);
        inputs.add("North",name);
        inputs.add("South",passWord);
        buttons.add(signup);
        buttons.add(quit);
        getContentPane().add(titles);
        getContentPane().add(items);
        getContentPane().add(inputs);
        getContentPane().add(buttons);
        pack();
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
        buttons.setSize(300,60);
        buttons.setLayout(new FlowLayout());
        buttons.setLocation(100,250);
        text.setFont(new Font("宋体",Font.BOLD, 30));
        pack();
        quit.addActionListener(this);
        signup.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==quit) {
            this.dispose();
            LoginForm loginForm = new LoginForm();
        } else if(e.getSource()==signup){//注册事件
            signUp(name.getText(),passWord.getText());
        }
    }

    public void signUp(String name, String passWord){
        User user = new User();
        user.setName(name);
        user.setPassWord(passWord);
        SignupServer loginServer = new SignupServer();
        try{
            if(loginServer.getSignup(user)) {
                JOptionPane.showMessageDialog(null, "注册成功！", "注册成功",JOptionPane.INFORMATION_MESSAGE);
                IndexForm indexForm = new IndexForm();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "出错！", "error",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
            JOptionPane.showMessageDialog(null, "用户名已存在！", "error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
