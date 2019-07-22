package Forms;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import Adapters.ChildWindowAction;
import DataSource.DeleteServer;
import DataSource.SearchServer;

public class CourseDetailForm extends JFrame implements ActionListener {
    private ChildWindowAction windowAction = new ChildWindowAction();
    private String name;
    private JLabel title = new JLabel("", JLabel.CENTER);
    private String[] columnNames = {"学号", "姓名", "班级", "成绩"};
    private Object[][] students = new Object[30][4];
    private JTextField searchInput = new JTextField();
    private TableModel tableModel = new DefaultTableModel(students,columnNames);
    private JTable studentTable = new JTable(tableModel);
    private JLabel searchText = new JLabel("输入学号:");
    private JButton search = new JButton("搜索");
    private JButton add = new JButton("添加学生");
    private JButton delete = new JButton("删除所选学生");
    private JButton change = new JButton("修改所选学生成绩");
    private JScrollPane scroll = new JScrollPane(studentTable);
    private JPanel text = new JPanel();
    private JPanel bottoms = new JPanel();
    private JPanel searchBox = new JPanel();
    public CourseDetailForm(String name){
        super("课程管理系统");
        init();
        this.name = name;
        title.setText(name);
        text.add(title);
        bottoms.add(add);
        bottoms.add(delete);
        bottoms.add(change);
        searchBox.add(searchInput);
        searchBox.add(search);
        getContentPane().add(text);
        getContentPane().add(bottoms);
        getContentPane().add(searchBox);
        getContentPane().add(scroll);
        getContentPane().add(searchText);
        pack();
        addWindowListener(windowAction);
        add.addActionListener(this);
        delete.addActionListener(this);
        change.addActionListener(this);
        search.addActionListener(this);
        updateData();
    }

    public void init() {
        this.setPreferredSize(new Dimension(700,600));
        this.setLocation(500,200);
        this.setResizable(false);
        setVisible(true);
        setLayout(null);
        text.setLayout(new BorderLayout());
        text.setSize(700,60);
        text.setLocation(0,50);
        title.setFont(new Font("宋体",Font.BOLD, 28));
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("宋体", 0, 20));
        scroll.setSize(500,200);
        scroll.setLocation(100,180);
        bottoms.setLayout(new FlowLayout());
        bottoms.setSize(400,100);
        bottoms.setLocation(150,450);
        add.setSize(100,70);
        delete.setSize(100,70);
        change.setSize(100,70);
        searchBox.setLayout(null);
        searchBox.setSize(300,30);
        searchBox.setLocation(250,120);
        searchInput.setSize(150,30);
        search.setSize(70,30);
        search.setLocation(200,0);
        searchText.setLocation(150,120);
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
            searchStudent(searchInput.getText());
        }
    }

    public void updateData(){
        SearchServer searchServer = new SearchServer();
        try {
            students = searchServer.getCourseDetail(name);
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

    public void searchStudent(String id){
        SearchServer searchServer = new SearchServer();
        try {
            students = searchServer.getStudentDetail(id, name);
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
        AddStudentCourseForm addStudentCourseForm = new AddStudentCourseForm(name);
        this.dispose();
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
                if(deleteServer.deleteCourseStudent(name ,String.valueOf(studentTable.getValueAt(row,0)))){
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
        String stuGrade = String.valueOf(studentTable.getValueAt(row,3));
        if(row == -1){
            JOptionPane.showMessageDialog(null, "您还未选择学生", "error",JOptionPane.INFORMATION_MESSAGE);
        }else if(String.valueOf(studentTable.getValueAt(row,1)).equals("null")){
            JOptionPane.showMessageDialog(null, "所选行为空", "error",JOptionPane.INFORMATION_MESSAGE);
        }else{
            ChangeStudentCourseForm changeStudentCourseForm = new ChangeStudentCourseForm(name,stuId,stuName,stuGrade);
            this.dispose();
        }
    }
}
