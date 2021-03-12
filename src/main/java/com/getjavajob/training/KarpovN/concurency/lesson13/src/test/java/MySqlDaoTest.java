import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlDaoTest {

    private MySqlDao mySqlDao;

    @Before
    public void setUP() {
        mySqlDao = new MySqlDao();
    }

    @Test
    public void getQueryTest1() {
        String query1 = "show databases";
        List<String> list = new LinkedList<>();
        list.add("information_schema");
        list.add("mysql");
        list.add("test");
        List<String> expectedList = mySqlDao.getQuery(query1);
        assertEquals(list, expectedList);
    }

    @Test
    public void getQueryTest2() {
        String query1 = "use test";
        String query2 = "show tables";
        mySqlDao.getQuery(query1);
        List<String> expectedList = mySqlDao.getQuery(query2);
        assertEquals("peoples", expectedList.get(0));
    }

    @Test
    public void getQueryTest3() {
        String query1 = "use test";
        String query2 = "create table testUsers (`id` INT(11) NOT NULL)";
        String query3 = "show tables";
        mySqlDao.getQuery(query1);
        mySqlDao.getQuery(query2);
        List<String> list = new LinkedList<>();
        list.add("peoples");
        list.add("testusers");
        List<String> expectedList = mySqlDao.getQuery(query3);
        assertEquals(list, expectedList);
    }

    @Test
    public void getQueryTest4() {
        String query1 = "use test";
        String query2 = "drop table testUsers";
        String query3 = "show tables";
        mySqlDao.getQuery(query1);
        mySqlDao.getQuery(query2);
        List<String> expectedList = mySqlDao.getQuery(query3);
        assertEquals("peoples", expectedList.get(0));
    }

    @Test
    public void getQueryTest5() {
        String query1 = "use test";
        String query2 = "select * from peoples";
        mySqlDao.getQuery(query1);
        List<String> expectedList = mySqlDao.getQuery(query2);
        assertEquals("id |  name |  surname |  salary |  ", expectedList.get(0));
    }

    @Test
    public void getQueryTest6() {
        String query1 = "use test";
        String query2 = "insert into peoples values (1, 'ivan', 'ivanov', 100)";
        String query3 = "select * from peoples";
        mySqlDao.getQuery(query1);
        List<String> list = new LinkedList<>();
        list.add("id |  name |  surname |  salary |  ");
        list.add(" 1  |  ivan  |  ivanov  |  100  |  ");
        mySqlDao.getQuery(query2);
        List<String> expectedList = mySqlDao.getQuery(query3);
        assertEquals(list, expectedList);
    }
}