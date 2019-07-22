package Forms;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
import java.sql.SQLException;
import Adapters.ChildWindowAction;
import DataSource.SearchServer;
import DataSource.DeleteServer;

public class ClassForm extends JFrame implements ActionListener {       //管理班级
    private ChildWindowAction windowAction = new ChildWindowAction();
    private String[] columnNames = {"学号", "姓名"};
    private Object[][] students = new Object[30][2];
    private JTextField searchInput = new JTextField();
    private JLabel searchText = new JLabel("输入姓名或学号:");
    private JButton search = new JButton("搜索");
    private JButton add = new JButton("添加学生");
    private JButton delete = new JButton("删除所选学生");
    private JButton change = new JButton("修改所选信息");
    private TableModel tableModel = new DefaultTableModel(students,columnNames);
    private JLabel className = new JLabel("选择班级",JLabel.CENTER);
    private JList classList = new JList();      //班级列表
    private JTable studentTable = new JTable(tableModel);
    private JScrollPane scroll = new JScrollPane(studentTable);
    private JPanel titles = new JPanel();
    private JPanel bottoms = new JPanel();
    private JPanel searchBox = new JPanel();
    public ClassForm(){
        super("课程管理系统");
        init();
        titles.add(className);
        bottoms.add(add);
        bottoms.add(delete);
        bottoms.add(change);
        searchBox.add(searchInput);
        searchBox.add(search);
        getContentPane().add(titles);
        getContentPane().add(classList);
        getContentPane().add(bottoms);
        getContentPane().add(searchBox);
        getContentPane().add(scroll);
        getContentPane().add(searchText);
        pack();
        classList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = classList.getSelectedIndex();
                ListModel<String> listModel = classList.getModel();
                className.setText(listModel.getElementAt(index));
                ClassForm.this.updateData();
            }
        });
        add.addActionListener(this);
        delete.addActionListener(this);
        change.addActionListener(this);
        search.addActionListener(this);
        addWindowListener(windowAction);
        updateData();
    }

    public void init() {
        this.setPreferredSize(new Dimension(1000,600));
        this.setLocation(200,100);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        titles.setLayout(new BorderLayout());
        titles.setSize(1000,100);
        titles.setLocation(0,0);
        className.setFont(new Font("宋体",Font.BOLD, 28));
        classList.setListData(new String[]{"计算机一班", "计算机二班", "软工一班", "软工二班"});
        classList.setSize(100,70);
        classList.setLocation(50,200);
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("宋体", 0, 20));
        scroll.setSize(500,200);
        scroll.setLocation(300,180);
        bottoms.setLayout(new FlowLayout());
        bottoms.setSize(400,100);
        bottoms.setLocation(300,450);
        add.setSize(100,70);
        delete.setSize(100,70);
        change.setSize(100,70);
        searchBox.setLayout(null);
        searchBox.setSize(300,30);
        searchBox.setLocation(350,120);
        searchInput.setSize(150,30);
        search.setSize(70,30);
        search.setLocation(200,0);
        searchText.setLocation(250,120);
        searchText.setSize(100,30);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==add){
            addStudent();
        }else if(e.getSource()==delete){
            deleteStudent();
        }else if(e.getSource()==change){
            changeStudent();
        }else if(e.getSource()==search){
            searchStudent(className.getText(), searchInput.getText());
        }
    }

    public void updateData(){
        SearchServer searchServer = new SearchServer();
        try {
            students = searchServer.getStudentTable(className.getText());
            TableModel tableModel = new DefaultTableModel(students, columnNames);
            studentTable.setModel(tableModel);
            studentTable.setEnabled(true);
            studentTable.updateUI();
            scroll.updateUI();
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
        }
    }

    public void searchStudent(String className, String studentInfo){
        SearchServer searchServer = new SearchServer();
        try {
            students = searchServer.getStudent(className, studentInfo);
            TableModel tableModel = new DefaultTableModel(students, columnNames);
            studentTable.setModel(tableModel);
            studentTable.setEnabled(true);
            studentTable.updateUI();
            scroll.updateUI();
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
        }
    }

    public void addStudent(){
        AddStudentForm addStudentForm = new AddStudentForm();
    }

    public void deleteStudent(){
        int row =  studentTable.getSelectedRow();
        System.out.println(String.valueOf(studentTable.getValueAt(row,1)));
        System.out.println(String.valueOf(studentTable.getValueAt(row,1)).length());
        if(row == -1){
            JOptionPane.showMessageDialog(null, "您还未选择学生", "error",JOptionPane.INFORMATION_MESSAGE);
        }else if(String.valueOf(studentTable.getValueAt(row,1)).equals("null")){
            JOptionPane.showMessageDialog(null, "所选行为空", "error",JOptionPane.INFORMATION_MESSAGE);
        }else{
            DeleteServer deleteServer = new DeleteServer();
            try {
                if(deleteServer.deleteStudent(className.getText() ,String.valueOf(studentTable.getValueAt(row,0)))){
                    JOptionPane.showMessageDialog(null, "删除成功", "error",JOptionPane.INFORMATION_MESSAGE);
                    updateData();
                }
            }catch (ClassNotFoundException ex){
                System.out.println("驱动找不到");
            }catch (SQLException ex){
                System.out.println("SQL操作失误");
            }
        }
    }

    public void changeStudent(){
        int row =  studentTable.getSelectedRow();
        String stuId = String.valueOf(studentTable.getValueAt(row,0));
        String stuName = String.valueOf(studentTable.getValueAt(row,1));
        if(row == -1){
            JOptionPane.showMessageDialog(null, "您还未选择学生", "error",JOptionPane.INFORMATION_MESSAGE);
        }else if(String.valueOf(studentTable.getValueAt(row,1)).equals("null")){
            JOptionPane.showMessageDialog(null, "所选行为空", "error",JOptionPane.INFORMATION_MESSAGE);
        }else{
            ChangeStudentForm changeStudentForm = new ChangeStudentForm(className.getText(),stuId,stuName);
        }
    }
}
