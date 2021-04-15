package jdbc;

import org.junit.*;

import java.sql.*;

/**
 * 注解@Ignore表示暂时不测试
 *
 * @user 郑超
 * @date 2021/4/14
 */
public class JDBCBasics {

    private static final String URL = "jdbc:mysql://localhost:3309/jt_db?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static String sql = "";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * 在所有测试方法前执行一次，例如：创建数据连接
     */
    @BeforeClass
    public static void testInitialize() throws ClassNotFoundException, SQLException {
        System.out.println("initialize...");
        // 注册数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取数据库连接
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        // 获取传输器
        statement = connection.createStatement();
    }

    /**
     * 在所有测试方法前执行，例如：初始化参数
     */
    @Before
    public void testInitVar() {
        System.out.println("initVar...");

//        sql = "select id, name, money from account";
//        sql = "insert into account (id, name, money) values (null, '宋江', '2300'),(null, '孔明', '4500')";
//        sql = "update account set money = 9999 where name = '郑超'";
        sql = "delete from account where money = 1000";
    }

    /**
     * 在所有测试方法后执行，例如：重置参数的值
     */
    @After
    public void testResetVar() {
        System.out.println("resetVar...");

        sql = "";
    }

    /**
     * 在所有测试方法之后执行一次，例如：关闭数据库连接
     */
    @AfterClass
    public static void testClose() throws SQLException {
        System.out.println("close...");
        if (resultSet != null)
            resultSet.close();
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
    }

    @Ignore
    @Test(timeout = 1500)
    public void testQuery() throws SQLException {
        System.out.println("query...");

        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.print(resultSet.getObject("id") + " ");
            System.out.print(resultSet.getObject("name") + " ");
            System.out.println(resultSet.getObject("money") + " ");
        }
    }

    @Test(timeout = 1000)
    public void testRemove() throws SQLException {
        System.out.println("remove...");

        int i = statement.executeUpdate(sql);
        System.out.println("影响行数：" + i);
    }

    @Ignore
    @Test(timeout = 1000)
    public void testModify() throws SQLException {
        System.out.println("modify...");

        int i = statement.executeUpdate(sql);
        System.out.println("影响行数：" + i);
    }

    @Ignore
    @Test(timeout = 1000)
    public void testAdd() throws SQLException {
        System.out.println("add...");

        int i = statement.executeUpdate(sql);
        System.out.println("影响行数：" + i);
    }

}
