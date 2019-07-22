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
import DataSource.ChangeServer;

public class ChangeCourseForm extends JFrame implements ActionListener {
    private ChildWindowAction windowAction = new ChildWindowAction();
    private String oldName;
    private JLabel title = new JLabel("修改课程",JLabel.CENTER);
    private JLabel name = new JLabel();
    private JLabel teacherName = new JLabel();
    private JLabel time = new JLabel();
    private JLabel location = new JLabel();
    private JLabel newname = new JLabel("输入新课程名");
    private JLabel newteacherName = new JLabel("输入新任课老师");
    private JLabel newtime = new JLabel("输入新上课时间");
    private JLabel newlocation = new JLabel("输入新上课地点");
    private JTextField nameInput = new JTextField();
    private JTextField teacherNameInput = new JTextField();
    private JTextField timeInput = new JTextField();
    private JTextField locationInput = new JTextField();
    private JButton add = new JButton("修改");
    private JPanel titles = new JPanel();
    private JPanel oldData = new JPanel();
    private JPanel items = new JPanel();
    private JPanel itemsInput = new JPanel();
    public ChangeCourseForm(String name, String teacherName, String time, String location){
        super("课程管理系统");
        init();
        oldName = name;
        this.name.setText(name);
        this.teacherName.setText(teacherName);
        this.time.setText(time);
        this.location.setText(location);
        oldData.add(this.name);
        oldData.add(this.teacherName);
        oldData.add(this.time);
        oldData.add(this.location);
        titles.add(title);
        items.add(newname);
        items.add(newteacherName);
        items.add(newtime);
        items.add(newlocation);
        itemsInput.add(nameInput);
        itemsInput.add(teacherNameInput);
        itemsInput.add(timeInput);
        itemsInput.add(locationInput);
        getContentPane().add(titles);
        getContentPane().add(oldData);
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
        oldData.setLayout(new FlowLayout());
        oldData.setSize(200,100);
        oldData.setLocation(150,100);
        items.setLayout(new GridLayout(4,1,10,10));
        items.setSize(100,100);
        items.setLocation(100,200);
        itemsInput.setLayout(new GridLayout(4,1,10,10));
        itemsInput.setSize(100,100);
        itemsInput.setLocation(270,200);
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
            ChangeServer changeServer = new ChangeServer();
            try {
                if(changeServer.changeCourse(nameInput.getText(), teacherNameInput.getText(), timeInput.getText(), locationInput.getText(), oldName)){
                    JOptionPane.showMessageDialog(null, "修改成功！", "success",JOptionPane.INFORMATION_MESSAGE);
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