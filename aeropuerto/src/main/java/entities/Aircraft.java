package entities;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Aircraft {

  @Getter @Setter protected String model;

  @Getter @Setter protected String numberRegistration;

  @Getter @Setter protected String airline;

  @Getter @Setter protected int capacity;

  @Getter @Setter protected StatesAircraft state;

  public abstract Aircraft createAircraft();
}
