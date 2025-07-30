package utilities;

import entities.Airplane;
import entities.Airport;
import interfaces.interfacesUI.ManageAirport;
import interfaces.interfacesUI.ShowMenu;
import java.util.Scanner;

public class UI implements ShowMenu, ManageAirport {
  private static final Scanner SC = new Scanner(System.in);

  @Override
  public String showMenu() {
    return """
				\n***** CONTROL TOWER *****
				1- Create Airplane
				2- Edit Airplane
				3- Delete Airplane
				4- Assign Boarding Gate
				5- Release Boarding Gate
				6- Show Airplanes
				7- Show Boarding Gates
				8- Exit
				Option:""";
  }

  @Override
  public void manageAirport() {
    Airport airport = new Airport(5);

    Airplane airplane = new Airplane();

	String op;
    boolean flag = true;
    do {
      System.out.print(showMenu());
      op = SC.next();
      System.out.println();
      switch (op) {
        case "1" -> airport.addAircraft((Airplane) airplane.createAircraft());
        case "2" -> airport.editAircraft();
        case "3" -> airport.deleteAircraft();
        case "4" -> airport.assignGate();
        case "5" -> airport.releaseBoardingGate();
        case "6" -> airport.showAircraft();
        case "7" -> airport.showBoardingGates();
        case "8" -> {
          System.out.println("Are you sure you want to exit the system? Y/N");
          String opExit = SC.next().toUpperCase();
          flag = !opExit.equals("Y");
        }
        default -> System.out.println("The option is not correct.");
      }
    } while (flag);
    System.out.println("Thank you for using our system.");
  }
}
