package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.Student;

public class ChangeServer{
    private String PASSWORD="root";
    public boolean changeStudent(String className,String studentId, String studentName, String oldId) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="update "+className+" set studentid = '"+studentId+"', studentname = '"+studentName+"' where studentid = '"+oldId+"'";
        String sql2="update student set id = '"+studentId+"', name = '"+studentName+"' where id = '"+oldId+"'";
        System.out.println(sql);
        System.out.println(sql2);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        statement.executeUpdate(sql2);
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }

    public boolean changeCourse(String name, String teacherName, String time, String location, String oldName) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="update course set name = '"+name+"', teachername = '"+teacherName+"', time = '"+time+"', location = '"+location+"' where name = '"+oldName+"'";
        System.out.println(sql);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        if(!name.equals(oldName)){
            String sql2="drop table "+oldName;
            String sql3="create table "+name+" (studentid varchar(15), studentname varchar(5), class varchar(10), grade int(3))";
            System.out.println(sql2);
            System.out.println(sql3);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
        }
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }

    public boolean changeGrade(String courseName,String studentId, String newGrade) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="update "+courseName+" set grade = '"+newGrade+"' where studentid = '"+studentId+"'";
        System.out.println(sql);
        //准备statement
        Statement statement=conn.createStatement();
        statement.executeUpdate(sql);
        System.out.println("2");
        //处理ResultSet
        statement.close();
        conn.close();
        return true;
    }
}