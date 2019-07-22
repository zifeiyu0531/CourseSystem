package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import Adapters.IndexWindowAction;

public class IndexForm extends JFrame implements ActionListener {
    private IndexWindowAction windowAction = new IndexWindowAction();
    private JLabel title = new JLabel("欢迎使用课程管理系统", JLabel.CENTER);
    private JButton toCourse = new JButton("管理课程");
    private JButton toClass = new JButton("管理班级");
    private JPanel text = new JPanel();
    private JPanel buttons = new JPanel();
    public IndexForm(){
        super("课程管理系统");
        init();
        text.add(title);
        buttons.add("North",toCourse);
        buttons.add("South",toClass);
        getContentPane().add(text);
        getContentPane().add(buttons);
        pack();
        toCourse.addActionListener(this);
        toClass.addActionListener(this);
        addWindowListener(windowAction);
    }

    public void init() {
        this.setPreferredSize(new Dimension(500,500));
        this.setLocation(500,200);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        text.setLayout(new BorderLayout());
        text.setSize(500,100);
        text.setLocation(0,50);
        buttons.setLayout(new BorderLayout());
        buttons.setSize(100,100);
        buttons.setLocation(190,200);
        title.setFont(new Font("宋体",Font.BOLD, 28));
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==toCourse){
            CourseForm courseForm = new CourseForm();
        }else if(e.getSource()==toClass){
            ClassForm classForm = new ClassForm();
        }
    }
}
