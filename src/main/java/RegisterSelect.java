import java.sql.*;

public class RegisterSelect {
  String url = "jdbc:mysql://localhost:3306/modbusdb";
  String username = "root";
  String password = "Florek99";
  public void selectData(){

    try {
      Connection connection = DriverManager.getConnection(url, username, password);

      String sql = "SELECT * FROM data";
      Statement statement1 = connection.createStatement();
      ResultSet result = statement1.executeQuery(sql);
      int count = 0;
      System.out.println("id     date        time      address    value    type");
      while (result.next()) {
        String date = result.getString(2);
        String time = result.getString(3);
        int address = result.getInt(4);
        int value = result.getInt(5);
        String type = result.getString(6);
        count++;
        System.out.format("%2d%12s%12s%6d%11d%17s", count, date, time, address, value, type);
        System.out.println();
      }
    }
    catch (SQLException e) {
      System.out.println("Sql jebnął");
      e.printStackTrace();
    }
  }
}
