import java.util.Scanner;

public class ScannerWriter {
  Scanner scanner = new Scanner(System.in);

  public String writeIp(){
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
    return ipModbus;
  }

  public int writePort(){
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
    return portNumber;
  }
}
