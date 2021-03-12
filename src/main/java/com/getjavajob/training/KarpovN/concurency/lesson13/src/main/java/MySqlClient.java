import java.util.List;
import java.util.Scanner;

public class MySqlClient {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MySqlDao mySqlDao = new MySqlDao();
        while (true) {
            System.out.println("enter query or print quit...");
            String query = scanner.nextLine();
            if (query.toLowerCase().equals("quit")) {
                break;
            }
            List<String> queryList = mySqlDao.getQuery(query);
            printList(queryList);
        }
    }


    private static void printList(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }
}
