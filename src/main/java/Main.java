import de.re.easymodbus.modbusclient.ModbusClient;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    boolean isIpGood = false;
    String ipModbus = null;
    while (!isIpGood) {
      System.out.println("Wprowadź ip");
      ipModbus = scanner.nextLine();
      String[] parts = ipModbus.split("[.]");
      if (parts.length != 4) {
        System.out.println("Błędne ip");
        continue;
      }
      int numberIp;
      boolean isNumberIpInnRange = true;
      for (String part : parts) {
        numberIp = Integer.parseInt(part);
        if (numberIp < 0 || numberIp > 255) {
          System.out.println("Błędne ip");
          isNumberIpInnRange = false;
        }
      }
      if (isNumberIpInnRange) {
        isIpGood = true;
      }
    }
    boolean isPortNumberValid = false;
    int portNumber = 0;
    while (!isPortNumberValid) {
      System.out.println("Wprowadź port");
      portNumber = scanner.nextInt();
      if (portNumber < 0) {
        System.out.println("Błędny port");
        continue;
      }
      isPortNumberValid = true;
    }

    ModbusClient modbusClient = new ModbusClient(ipModbus, portNumber);
    try {
      modbusClient.Connect();
      //Czyta 32 Bit wartość z rejestrów 10 and 11
      System.out.println(ModbusClient.ConvertRegistersToFloat(modbusClient.ReadHoldingRegisters(9, 2)));
      //Czyta 32 Bit wartość z rejestrów 12 and 13
      System.out.println(ModbusClient.ConvertRegistersToDouble(modbusClient.ReadHoldingRegisters(11, 2)));
    } catch (Exception e) {
      System.out.println(e.toString());
    }

  }

}
