import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MySqlDao {

    private Connection connection;


    public MySqlDao() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("MySqlClient.properties"));
            String url = properties.getProperty("database.url");
            String user = properties.getProperty("database.user");
            String password = properties.getProperty("database.password");
            this.connection = DriverManager.getConnection(url, user, password);
            this.connection.setAutoCommit(false);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    private List<String> showDatabaseOrTable(String query) {
        List<String> stringList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        try (ResultSet resultSet = this.connection.createStatement().executeQuery(query)) {
            ResultSetMetaData rsm = resultSet.getMetaData();
            while (resultSet.next()) {
                int i = 1;
                stringList.add(resultSet.getString(i++));
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return stringList;
    }


    private List<String> getTable(ResultSet resultSet, ResultSetMetaData rsm) {
        List<String> stringList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                sb = sb.append(rsm.getColumnName(i)).append(" |  ");
            }
            stringList.add(sb.toString());
            sb.delete(0, sb.length() - 1);
            while (resultSet.next()) {
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    sb = sb.append(resultSet.getString(rsm.getColumnName(i))).append("  |  ");
                }
                stringList.add(sb.toString());
                sb.delete(0, sb.length() - 1);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return stringList;
    }


    public List<String> getQuery(String query) {
        List<String> list = new LinkedList<>();
        if (query.toLowerCase().contains("insert") || query.toLowerCase().contains("update") || query.toLowerCase().contains("delete") ||
                query.toLowerCase().contains("create") || query.toLowerCase().contains("alter") || query.toLowerCase().contains("drop") ||
                query.toLowerCase().contains("use")) {
            getQueryWithUpdate(query);
        } else if (query.toLowerCase().contains("show")) {
            list = showDatabaseOrTable(query);
        } else {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ResultSetMetaData rsm = resultSet.getMetaData();
                    list = getTable(resultSet, rsm);
                }
            } catch (SQLException throwable) {
                System.out.println(throwable.getMessage());
            }
        }
        return list;
    }


    private void getQueryWithUpdate(String query) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
    }
}