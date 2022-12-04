import de.re.easymodbus.modbusclient.ModbusClient;

import java.util.Scanner;


public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ScannerWriter scannerWriter = new ScannerWriter();
    ConsoleOut consoleOut = new ConsoleOut();
    ModbusClient modbusClient = new ModbusClient(scannerWriter.writeIp(), scannerWriter.writePort());
    InputRegisterSave inputRegisterSave = new InputRegisterSave();
    RegisterSelect registerSelect = new RegisterSelect();
    HoldingRegisterSave holdingRegisterSave = new HoldingRegisterSave();

    boolean shouldContinue = true;
    int pressedKey;
    while (shouldContinue) {
      consoleOut.showMenu();
      pressedKey = scanner.nextInt();
      scanner.nextLine();
      switch (pressedKey) {
        case 0 -> shouldContinue = false;
        case 1 -> modbusClient.setipAddress(scannerWriter.writeIp());
        case 2 -> modbusClient.setPort(scannerWriter.writePort());
        case 3 -> inputRegisterSave.saveRegister(modbusClient);
        case 4 -> holdingRegisterSave.saveRegister(modbusClient);
        case 5 -> registerSelect.selectData();
        default -> System.out.println("Brak numeru");
        }
      }
    }
  }

