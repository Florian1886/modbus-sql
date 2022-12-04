import de.re.easymodbus.modbusclient.ModbusClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class InputRegisterSave {
  String url = "jdbc:mysql://localhost:3306/modbusdb";
  String username = "root";
  String password = "Florek99";
  Scanner scanner = new Scanner(System.in);
  public void saveRegister(ModbusClient modbusClient) {
    System.out.println("Podaj pierwszy adres");
    int startingAddress = scanner.nextInt();
    System.out.println("Podaj ilość adresów");
    int countAddress = scanner.nextInt();
    int[] registers = new int[countAddress];
    try {
      modbusClient.Connect();
      registers = modbusClient.ReadInputRegisters(startingAddress - 1, countAddress);
    } catch (Exception e) {
      System.out.println(e);
    }

    try {
      Connection connection = DriverManager.getConnection(url, username, password);

      String sql = "INSERT INTO data(date, time, address, value, type) VALUES(?, ? ,?, ?, ?)";

      PreparedStatement statement = connection.prepareStatement(sql);
      for(int i = startingAddress; i < startingAddress + countAddress; i++) {
        statement.setString(1, LocalDate.now().toString());
        statement.setString(2, LocalTime.now().toString().substring(0, 8));
        statement.setInt(3, i);
        statement.setInt(4, registers[i - startingAddress]);
        statement.setString(5, "Input Register");
        int rows = statement.executeUpdate();
        if (rows > 0) {
          System.out.println("Poprawne dodanie");
        }
      }
    } catch (SQLException e) {
      System.out.println("Niepodłączona baza danych");
      e.printStackTrace();
      }
    }
  }

