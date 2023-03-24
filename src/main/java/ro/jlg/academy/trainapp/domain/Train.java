package ro.jlg.academy.trainapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Train {
    private final String id;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private Integer nrOfSeats;
    private List<String> passengerIds = new ArrayList<>();

    public Train(final String id, final String departure, final String destination, final LocalDateTime departureTime, Integer nrOfSeats) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.nrOfSeats = nrOfSeats;

    }
    public String getId() {
        return this.id;
    }
    public String getDeparture() {
        return this.departure;
    }
    public String getDestination() {
        return this.destination;
    }
    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }
    public Integer getNrOfSeats() {
        return this.nrOfSeats;
    }
    public List<String> getPassengerIds() {
        return this.passengerIds;
    }

    public void update(String newDeparture, String newDestination, LocalDateTime newDepartureTime, Integer nrOfSeats) {
        this.departure = newDeparture;
        this.destination = newDestination;
        this.departureTime = newDepartureTime;
        this.nrOfSeats = nrOfSeats;
    }
    public boolean reserveTicket(final String passengerId) {
        // 1. Check if there are available seats.
        boolean isSuccess = false;

        final boolean seatsAvailable = this.nrOfSeats > 0;
        final boolean alreadyReserved = this.passengerIds.contains(passengerId);
        final boolean catchTrain = LocalDateTime.now().isBefore(this.departureTime);
        // 3. Check also if the train hasn't left yet.
        if(seatsAvailable && !alreadyReserved && catchTrain) {
            // 2. Check if passenger does not already have a ticket on the train.
                isSuccess = true;
                --this.nrOfSeats;
                this.passengerIds.add(passengerId);
        }
        return isSuccess;
    }

    public void cancelReservation(final String passengerId) {
        this.passengerIds.remove(passengerId);
        ++this.nrOfSeats;
    }
}
