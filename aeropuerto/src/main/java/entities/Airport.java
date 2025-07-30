package entities;

import exception.GatesException;
import interfaces.interfacesairport.*;
import interfaces.validationinterfaces.ValidationGate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Airport
    implements ShowAircraft,
        AddAircraft,
        SearchAircraft,
        DeleteAircraft,
        AssignGate,
        ReleaseBoardingGate,
        ValidationGate {
  private List<BoardingGate> boardingGates = new ArrayList<>();
  private List<Aircraft> aircraftList = new ArrayList<>();
  private final Scanner SC = new Scanner(System.in);

  // Instancio un Airplane para poder usar los metodos de validación de la clase
  private Aircraft plane = new Airplane();

  public Airport(int numberGates) {
    if (numberGates > 0) {
      for (int i = 1; i <= numberGates; i++) {
        BoardingGate boardingGate = new BoardingGate(i);
        this.boardingGates.add(boardingGate);
      }
    } else {
      BoardingGate boardingGate = new BoardingGate(1);
      this.boardingGates.add(boardingGate);
    }
  }

  @Override
  public void showAircraft() {
    if (this.aircraftList.size() > 0) {
      for (Aircraft aircraft : this.aircraftList) {
        System.out.println(aircraft);
      }
    } else {
      System.out.println("There are no airplanes.");
    }
  }

  public void showBoardingGates() {
    for (BoardingGate gate : this.boardingGates) {
      System.out.println(gate);
    }
  }

  @Override
  public void addAircraft(Aircraft aircraft) {
    for (Aircraft air : this.aircraftList) {
      if (air.getNumberRegistration().equals(aircraft.getNumberRegistration())) {
        System.out.println("There is an airplane with that registration number.");
        return;
      }
    }
    System.out.println("The airplane was successfully created.");
    this.aircraftList.add(aircraft);
  }

  @Override
  public Aircraft searchAircraft(String searchCriteria) {
    for (Aircraft aircraft : this.aircraftList) {
      if (aircraft.getNumberRegistration().equals(searchCriteria)) {
        return aircraft;
      }
    }
    return null;
  }

  public void editAircraft() {
    String op;
    if (this.aircraftList.size() == 0) {
      System.out.println("There are no airplanes.");
      return;
    }
    System.out.print("Enter the registration number of the airplane to be edit (4 digits): ");
    String numberRegistration = ((Airplane) this.plane).validateNumberRegistration();
    Aircraft aircraft = searchAircraft(numberRegistration);
    if (aircraft == null) {
      System.out.println("The airplane is not on the list.");
      return;
    }
    while (true) {
      System.out.println("\n" + aircraft);
      System.out.print(
          """
				1- Change Model
				2- Change Airline
				3- Change Capacity
				4- Change State
				5- Back to previous menu
				: """);
      op = this.SC.nextLine();
      switch (op) {
        case "1" -> {
          System.out.print("Enter the model of airplane: ");
          String model = this.SC.nextLine();
          aircraft.setModel(model);
          System.out.println("Model successfully updated.");
        }
        case "2" -> {
          System.out.print("Enter the name of the company: ");
          String airline = this.SC.nextLine();
          aircraft.setAirline(airline);
          System.out.println("Company successfully updated.");
        }
        case "3" -> {
          int capacity = ((Airplane) aircraft).validateCapacity();
          aircraft.setCapacity(capacity);
          System.out.println("Capacity successfully updated.");
        }
        case "4" -> {
          StatesAircraft state = ((Airplane) aircraft).validateState();
          aircraft.setState(state);
          System.out.println("State successfully updated.");
        }
        case "5" -> {
          return;
        }
        default -> System.out.println("Invalid option.");
      }
    }
  }

  @Override
  public void deleteAircraft() {
    if (this.aircraftList.size() == 0) {
      System.out.println("There are no airplanes.");
      return;
    }
    System.out.print("Enter the registration number of the airplane to be deleted (4 digits): ");
    String numberRegistration = ((Airplane) this.plane).validateNumberRegistration();
    Airplane airPlane = (Airplane) searchAircraft(numberRegistration);
    if (airPlane == null) {
      System.out.println("The airplane is not on the list.");
      return;
    }
    for (BoardingGate gate : this.boardingGates) {
      Aircraft aircraft = gate.getAircraft();
      if (aircraft != null && aircraft.getNumberRegistration().equals(numberRegistration)) {
        gate.setStatus(false);
      }
    }
    this.aircraftList.remove(airPlane);
    System.out.println("The airplane has been successfully deleted.");
  }

  @Override
  public void assignGate() {

    // Verifico si la lista de aviones no esta vazia
    if (this.aircraftList.size() == 0) {
      System.out.println("There are no airplanes.");
      return;
    }

    // Verifica si todas las puertas estan en uso
    int i = 0;
    for (; i < this.boardingGates.size(); i++) {
      if (!this.boardingGates.get(i).isStatus()) {
        break;
      }
    }
    if (i == this.boardingGates.size()) {
      System.out.println("All boarding gates are in use.");
      return;
    }

    System.out.print(
        "Enter the registration number of the airplane to assign to a boarding gate (4 digits): ");
    String numberRegistration = ((Airplane) this.plane).validateNumberRegistration();
    // Verifico si el avión ya existe
    Aircraft aircraft = searchAircraft(numberRegistration);
    if (aircraft == null) {
      System.out.println("The airplane does not exist.");
      return;
    }
    // Verifico si el número de puerta es válido y si ya no está siendo usado por otro avión
    // devuelve -1 si no pasa la validación
    int numberGate = validateGate(aircraft);
    if (numberGate > -1) {
      BoardingGate boardingGate = this.boardingGates.get(numberGate);
      try {
        if (!boardingGate.isStatus()) {
          boardingGate.setStatus(true);
          boardingGate.setAircraft(aircraft);
          this.boardingGates.set(numberGate, boardingGate);
          System.out.println(
              "Update of Boarding Gate number "
                  + boardingGate.getGateNumber()
                  + " for Status In use.\n");
        } else {
          throw new GatesException("The boarding gate " + (numberGate + 1) + " is in use.");
        }
      } catch (GatesException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @Override
  public void releaseBoardingGate() {
    int numberGate = 0;

    // Verifica si todas las puertas estan liberadas
    int i = 0;
    for (; i < this.boardingGates.size(); i++) {
      if (this.boardingGates.get(i).isStatus()) {
        break;
      }
    }
    if (i == this.boardingGates.size()) {
      System.out.println("All boarding gates are released.");
      return;
    }

    try {
      System.out.print("Enter the boarding gate number to be released ");
      numberGate = this.SC.nextInt();
      SC.nextLine();
      if ((numberGate > this.boardingGates.size()) || (numberGate < 1)) {
        throw new GatesException("That boarding gate number " + numberGate + " does not exist.");
      }
    } catch (InputMismatchException e) {
      System.out.println("It needs to be a number.");
      return;
    } catch (GatesException e) {
      System.out.println(e.getMessage());
      return;
    }
    numberGate--;
    BoardingGate boardingGate = this.boardingGates.get(numberGate);
    if (boardingGate.isStatus()) {
      boardingGate.setStatus(false);
      boardingGate.setAircraft(null);
      System.out.println("The boarding gate number " + (numberGate + 1) + " has been released.");
    } else {
      System.out.println(
          "That boarding gate number " + (numberGate + 1) + " was already released.");
    }
  }

  @Override
  public int validateGate(Aircraft aircraft) {
    int numberGate = 0;
    try {
      System.out.print("Enter the number boarding gate: ");
      numberGate = this.SC.nextInt();
      // para liberar el buffer
      this.SC.nextLine();
      if ((numberGate > this.boardingGates.size()) || (numberGate < 1)) {
        throw new GatesException("That boarding gate number " + numberGate + " does not exist.");
      }
      numberGate--;
      // Recorro la lista de puertas para saber si el avión ya está en otra puerta
      for (BoardingGate gate : this.boardingGates) {
        if (gate.getAircraft() != null && gate.getAircraft().equals(aircraft)) {
          throw new GatesException(
              "This airplane is already at the boarding gate " + gate.getGateNumber());
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("It needs to be a number.");
      return -1;
    } catch (GatesException e) {
      System.out.println(e.getMessage());
      return -1;
    }
    return numberGate;
  }
}
