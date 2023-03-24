package ro.jlg.academy.trainapp.application;

import org.springframework.stereotype.Service;
import ro.jlg.academy.trainapp.domain.Person;
import ro.jlg.academy.trainapp.domain.Train;
import ro.jlg.academy.trainapp.infrastructure.PersonRepositoryImpl;
import ro.jlg.academy.trainapp.infrastructure.TrainRepositoryImpl;
import ro.jlg.academy.trainapp.interfaces.dto.CreateUpdateTrainDTO;
import ro.jlg.academy.trainapp.interfaces.dto.ReserveTicketDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TrainService {
    //dependency injection
    private PersonRepositoryImpl personRepository;
    private TrainRepositoryImpl trainRepository;

    public TrainService(final TrainRepositoryImpl trainRepository, final PersonRepositoryImpl personRepository) {
        this.trainRepository = trainRepository;
        this.personRepository = personRepository;
    }

    public void create(final CreateUpdateTrainDTO dto) {
        final Train train = new Train(
                UUID.randomUUID().toString(),
                dto.getDeparture(),
                dto.getDestination(),
                LocalDateTime.parse(dto.getDepartureTime()),
                dto.getNrOfSeats()
        );
        this.trainRepository.save(train);
    }

    public void update(final String id, final CreateUpdateTrainDTO dto) {
        final Train train = this.trainRepository.findById(id);
        train.update(dto.getDeparture(), dto.getDestination(), LocalDateTime.parse(dto.getDepartureTime()), dto.getNrOfSeats());
        this.trainRepository.save(train);
    }

    public void delete(final String id) {
        this.trainRepository.delete(id);
    }

    public boolean reserveTicket(final ReserveTicketDTO dto) {
        // 1. Find the train
        final Train train = this.trainRepository.findById(dto.getTrainId());
        // 2. Reserve = modify train nr of seats
        final boolean isSuccess = train.reserveTicket(dto.getPersonId());
        // 3. Save in database
        this.trainRepository.save(train);

        return isSuccess;
    }

    public List<Train> getAll() {
        return this.trainRepository.findAll();
    }

    public List<Train> getAllByDeparture(final String departure) {
        return this.trainRepository.findByDeparturePlace(departure);
    }

    public List<Train> getAllByDestination(final String destination) {
        return this.trainRepository.findByDestinationPlace(destination);
    }

    public List<Person> getAllPassengers() {
        final List<Train> allTrains = this.getAll();
        final Set<String> allPassengerIds = new HashSet<>();

        allTrains.forEach( train -> {
            allPassengerIds.addAll(train.getPassengerIds());
        });

        return this.personRepository.findAllByIds(allPassengerIds);
    }

}
