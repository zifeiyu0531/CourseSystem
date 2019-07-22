package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import Adapters.ChildWindowAction;
import DataSource.ChangeServer;

public class ChangeStudentCourseForm extends JFrame implements ActionListener {
    private String courseName;
    private String oldId;
    private ChildWindowAction windowAction = new ChildWindowAction();
    private JLabel title = new JLabel("修改学生资料", JLabel.CENTER);
    private JLabel studentId = new JLabel();
    private JLabel studentName = new JLabel();
    private JLabel studentGrade = new JLabel();
    private JLabel grade = new JLabel("输入新成绩");
    private JTextField idInput = new JTextField();
    private JButton change = new JButton("修改");
    private JPanel text = new JPanel();
    private JPanel oldData = new JPanel();
    private JPanel items = new JPanel();
    private JPanel itemsInput = new JPanel();
    public ChangeStudentCourseForm(String courseName, String stuId, String stuName, String stuGrade){
        super("课程管理系统");
        init();
        this.courseName = courseName;
        this.oldId = stuId;
        text.add(title);
        studentId.setText("学生学号："+stuId);
        studentName.setText("学生姓名："+stuName);
        studentGrade.setText("学生成绩："+stuGrade);
        oldData.add(studentId);
        oldData.add(studentName);
        oldData.add(studentGrade);
        items.add("South",grade);
        itemsInput.add("South",idInput);
        getContentPane().add(text);
        getContentPane().add(oldData);
        getContentPane().add(items);
        getContentPane().add(itemsInput);
        getContentPane().add(change);
        pack();
        change.addActionListener(this);
        addWindowListener(windowAction);
    }

    public void init() {
        this.setPreferredSize(new Dimension(500,500));
        this.setLocation(500,200);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        text.setLayout(new BorderLayout());
        text.setSize(500,70);
        text.setLocation(0,0);
        title.setFont(new Font("宋体",Font.BOLD, 28));
        oldData.setLayout(new FlowLayout());
        oldData.setSize(200,60);
        oldData.setLocation(150,80);
        items.setLayout(new BorderLayout());
        items.setSize(100,50);
        items.setLocation(150,200);
        itemsInput.setLayout(new BorderLayout());
        itemsInput.setSize(100,50);
        itemsInput.setLocation(270,200);
        change.setSize(70,30);
        change.setLocation(200,350);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==change){
            changeStudent();
        }
    }

    public void changeStudent(){
        ChangeServer changeServer = new ChangeServer();
        try {
            if(changeServer.changeGrade(courseName, oldId, idInput.getText())){
                JOptionPane.showMessageDialog(null, "修改成功！", "success",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new CourseDetailForm(courseName);
            }
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
            JOptionPane.showMessageDialog(null, "该学号已存在！", "error",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}