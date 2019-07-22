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

public class CourseForm extends JFrame implements ActionListener {       //管理班级
    private ChildWindowAction windowAction = new ChildWindowAction();
    private String[] columnNames = {"课程名", "任课老师", "上课时间", "上课地点"};
    private Object[][] courses = new Object[30][4];
    private JTextField searchInput = new JTextField();
    private JLabel searchText = new JLabel("输入课程名:");
    private JButton search = new JButton("搜索");
    private JButton add = new JButton("添加课程");
    private JButton delete = new JButton("删除所选课程");
    private JButton change = new JButton("修改所选课程");
    private TableModel tableModel = new DefaultTableModel(courses,columnNames);
    private JLabel className = new JLabel("课程管理",JLabel.CENTER);
    private JLabel tip = new JLabel("点击查看课程详情");
    private JList classList = new JList();      //课程列表
    private JTable studentTable = new JTable(tableModel);
    private JScrollPane scroll = new JScrollPane(studentTable);
    private JScrollPane scroll2 = new JScrollPane(classList);
    private JPanel titles = new JPanel();
    private JPanel bottoms = new JPanel();
    private JPanel searchBox = new JPanel();
    public CourseForm(){
        super("课程管理系统");
        init();
        titles.add(className);
        bottoms.add(add);
        bottoms.add(delete);
        bottoms.add(change);
        searchBox.add(searchInput);
        searchBox.add(search);
        getContentPane().add(titles);
        getContentPane().add(bottoms);
        getContentPane().add(searchBox);
        getContentPane().add(scroll);
        getContentPane().add(scroll2);
        getContentPane().add(searchText);
        getContentPane().add(tip);
        pack();
        classList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = classList.getSelectedIndex();
                ListModel<String> listModel = classList.getModel();
                if(e.getValueIsAdjusting()){
                    CourseDetailForm courseDetailForm=new CourseDetailForm(listModel.getElementAt(index));
                }

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
        scroll2.setSize(130,70);
        scroll2.setLocation(50,200);
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("宋体", 0, 20));
        scroll.setSize(700,200);
        scroll.setLocation(200,180);
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
        tip.setLocation(40,150);
        tip.setSize(120,30);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==add){
            addCourse();
        }else if(e.getSource()==delete){
            deleteCourse();
        }else if(e.getSource()==change){
            changeCourse();
        }else if(e.getSource()==search){
            searchCourse(searchInput.getText());
        }
    }

    public void updateData(){
        SearchServer searchServer = new SearchServer();
        try {
            courses = searchServer.getCourseTable();
            TableModel tableModel = new DefaultTableModel(courses, columnNames);
            studentTable.setModel(tableModel);
            studentTable.setEnabled(true);
            studentTable.updateUI();
            scroll.updateUI();
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
        }
        try {
            String[] students = searchServer.getCourses();
            classList.setListData(students);
            classList.setEnabled(true);
            classList.updateUI();
            scroll.updateUI();
        }catch (ClassNotFoundException ex){
            System.out.println("驱动找不到");
        }catch (SQLException ex){
            System.out.println("SQL操作失误");
        }
    }

    public void searchCourse(String courseName){
        SearchServer searchServer = new SearchServer();
        try {
            courses = searchServer.getCourse(courseName);
            TableModel tableModel = new DefaultTableModel(courses, columnNames);
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

    public void addCourse(){
        AddCourseForm addCourseFormd = new AddCourseForm();
        this.dispose();
    }

    public void deleteCourse(){
        int row =  studentTable.getSelectedRow();
        System.out.println(String.valueOf(studentTable.getValueAt(row,1)));
        System.out.println(String.valueOf(studentTable.getValueAt(row,1)).length());
        if(row == -1){
            JOptionPane.showMessageDialog(null, "您还未选择课程", "error",JOptionPane.INFORMATION_MESSAGE);
        }else if(String.valueOf(studentTable.getValueAt(row,1)).equals("null")){
            JOptionPane.showMessageDialog(null, "所选行为空", "error",JOptionPane.INFORMATION_MESSAGE);
        }else{
            DeleteServer deleteServer = new DeleteServer();
            try {
                if(deleteServer.deleteCourse(String.valueOf(studentTable.getValueAt(row,0)))){
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

    public void changeCourse(){
        int row =  studentTable.getSelectedRow();
        String name = String.valueOf(studentTable.getValueAt(row,0));
        String teacherName = String.valueOf(studentTable.getValueAt(row,1));
        String time = String.valueOf(studentTable.getValueAt(row,2));
        String location = String.valueOf(studentTable.getValueAt(row,3));
        if(row == -1){
            JOptionPane.showMessageDialog(null, "您还未选择课程", "error",JOptionPane.INFORMATION_MESSAGE);
        }else if(String.valueOf(studentTable.getValueAt(row,1)).equals("null")){
            JOptionPane.showMessageDialog(null, "所选行为空", "error",JOptionPane.INFORMATION_MESSAGE);
        }else{
            ChangeCourseForm changeCourseForm = new ChangeCourseForm(name,teacherName,time,location);
            this.dispose();
        }
    }
}