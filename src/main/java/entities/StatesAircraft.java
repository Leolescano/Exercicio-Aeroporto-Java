package entities;

import lombok.Getter;

public enum StatesAircraft {
  AVAILABLE(1),
  MAINTENANCE(2),
  FLYING(3);


  @Getter private int number;

  private StatesAircraft(int number) {
    this.number = number;
  }

  public static StatesAircraft getStateByNumber(int number) {
    for (StatesAircraft state : StatesAircraft.values()) {
      if (state.getNumber() == number) {
        return state;
      }
    }
    return null; // Si no se encuentra un estado con el número especificado
  }
}
