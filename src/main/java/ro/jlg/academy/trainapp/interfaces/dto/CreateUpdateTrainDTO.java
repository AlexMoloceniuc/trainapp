package ro.jlg.academy.trainapp.interfaces.dto;

import java.time.LocalDateTime;


// Tip de request body
public class CreateUpdateTrainDTO {
    private String departure;
    private String destination;
    private String departureTime;
    private Integer nrOfSeats;

    public String getDeparture() {
        return this.departure;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public Integer getNrOfSeats() {
        return this.nrOfSeats;
    }

}
