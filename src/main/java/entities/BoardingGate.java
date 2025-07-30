package entities;

import lombok.Getter;
import lombok.Setter;

public class BoardingGate {

  @Getter @Setter private int gateNumber;

  @Getter @Setter private boolean status;

  @Getter @Setter private Aircraft aircraft;

  public BoardingGate(int gateNumber) {
    this.gateNumber = gateNumber;
  }

  @Override
  public String toString() {
    String status = this.status ? "In use" : "Enabled";
    if (this.status) {
      return "Boarding Gate Number "
          + this.gateNumber
          + " ==> Status: "
          + status
          + " by aircraft number registration "
          + this.aircraft.getNumberRegistration();
    } else {
      return "Boarding Gate Number " + this.gateNumber + " ==> Status: " + status;
    }
  }
}
