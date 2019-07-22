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

public class AddStudentCourseForm extends JFrame implements ActionListener {
    private ChildWindowAction windowAction = new ChildWindowAction();
    private String courseName;
    private JLabel title = new JLabel("添加学生",JLabel.CENTER);
    private JList classList = new JList();
    private JLabel className = new JLabel("选择班级");
    private JLabel name = new JLabel("输入姓名");
    private JLabel id = new JLabel("输入学号");
    private JTextField nameInput = new JTextField();
    private JTextField idInput = new JTextField();
    private JButton add = new JButton("添加");
    private JPanel titles = new JPanel();
    private JPanel items = new JPanel();
    private JPanel itemsInput = new JPanel();
    public AddStudentCourseForm(String courseName){
        super("课程管理系统");
        init();
        this.courseName=courseName;
        titles.add(title);
        items.add("North",name);
        items.add("South",id);
        itemsInput.add("North",nameInput);
        itemsInput.add("South",idInput);
        getContentPane().add(titles);
        getContentPane().add(classList);
        getContentPane().add(className);
        getContentPane().add(items);
        getContentPane().add(itemsInput);
        getContentPane().add(add);
        pack();
        classList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = classList.getSelectedIndex();
                ListModel<String> listModel = classList.getModel();
                className.setText(listModel.getElementAt(index));
            }
        });
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
        classList.setListData(new String[]{"计算机一班", "计算机二班", "软工一班", "软工二班"});
        classList.setSize(100,70);
        classList.setLocation(50,200);
        className.setSize(100,20);
        className.setLocation(50,150);
        items.setLayout(new BorderLayout());
        items.setSize(100,50);
        items.setLocation(200,200);
        itemsInput.setLayout(new BorderLayout());
        itemsInput.setSize(100,50);
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
        if(className.getText().equals("选择班级")){
            JOptionPane.showMessageDialog(null, "请选择班级", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else if(nameInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入姓名", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else if(idInput.getText().length()<=0){
            JOptionPane.showMessageDialog(null, "请输入学号", "fail",JOptionPane.INFORMATION_MESSAGE);
        }else{
            AddServer addServer = new AddServer();
            try {
                if(addServer.insertCourseStudent(className.getText(), nameInput.getText(), idInput.getText(),courseName)){
                    JOptionPane.showMessageDialog(null, "添加成功！", "success",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new CourseDetailForm(courseName);
                }
            }catch (ClassNotFoundException ex){
                System.out.println("驱动找不到");
            }catch (SQLException ex){
                System.out.println("SQL操作失误");
                JOptionPane.showMessageDialog(null, "该学生已存在！", "error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}