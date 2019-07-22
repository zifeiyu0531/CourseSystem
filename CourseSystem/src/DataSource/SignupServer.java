package DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;
import Bean.User;

public class SignupServer{
    private String PASSWORD="root";
    public boolean getSignup(User user) throws ClassNotFoundException, SQLException{
        String URL="jdbc:mysql://localhost:3306/coursesystem?useUnicode=true&characterEncoding=UTF-8";
        String USER="root";
        //加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //获得数据库的连接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //通过数据库的连接操作数据库
        String sql="insert into user values ('"+user.getName()+"','"+ user.getPassWord()+"')";
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