package Forms;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import Adapters.ChildWindowAction;
import DataSource.AddServer;

public class AddCourseForm extends JFrame implements ActionListener {
    private ChildWindowAction windowAction = new ChildWindowAction();
    private JLabel title = new JLabel("添加课程",JLabel.CENTER);
    private JLabel name = new JLabel("输入课程名");
    private JLabel teacherName = new JLabel("输入任课老师");
    private JLabel time = new JLabel("输入上课时间");
    private JLabel location = new JLabel("输入上课地点");
    private JTextField nameInput = new JTextField();
    private JTextField teacherNameInput = new JTextField();
    private JTextField timeInput = new JTextField();
    private JTextField locationInput = new JTextField();
    private JButton add = new JButton("添加");
    private JPanel titles = new JPanel();
    private JPanel items = new JPanel();
    private JPanel itemsInput = new JPanel();
    public AddCourseForm(){
        super("课程管理系统");
        init();
        titles.add(title);
        items.add(name);
        items.add(teacherName);
        items.add(time);
        items.add(location);
        itemsInput.add(nameInput);
        itemsInput.add(teacherNameInput);
        itemsInput.add(timeInput);
        itemsInput.add(locationInput);
        getContentPane().add(titles);
        getContentPane().add(items);
        getContentPane().add(itemsInput);
        getContentPane().add(add);
        pack();
        add.addActionListener(this);
        addWindowListener(windowAction);
    }

    public void init() {
        this.setPreferredSize(new Dimension(500,500));
        this.setLocation(500,200);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        titles.setLayout(new BorderLayout());
        titles.setSize(500,100);
        titles.setLocation(0,0);
        title.setFont(new Font("宋体",Font.BOLD, 28));
        items.setLayout(new GridLayout(4,1,10,10));
        items.setSize(100,100);
        items.setLocation(100,150);
        itemsInput.setLayout(new GridLayout(4,1,10,10));
        itemsInput.setSize(100,100);
        itemsInput.setLocation(270,150);
        add.setSize(70,30);
        add.setLocation(200,350);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==add){
            addStudent();
        }
    }

    public void addStudent(){
        if(nameInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入课程名", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else if(teacherNameInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入任课老师名", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else if(timeInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入上课时间", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else if(locationInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入上课地点", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else{
            AddServer addServer = new AddServer();
            try {
                if(addServer.insertCourse(nameInput.getText(), teacherNameInput.getText(), timeInput.getText(), locationInput.getText())){
                    JOptionPane.showMessageDialog(null, "添加成功！", "success",JOptionPane.INFORMATION_MESSAGE);
                    new CourseForm();
                    this.dispose();
                }
            }catch (ClassNotFoundException ex){
                System.out.println("驱动找不到");
            }catch (SQLException ex){
                System.out.println("SQL操作失误");
                JOptionPane.showMessageDialog(null, "该课程已存在或课程命名出错！", "error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}