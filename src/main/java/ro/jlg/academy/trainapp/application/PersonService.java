package ro.jlg.academy.trainapp.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.jlg.academy.trainapp.domain.Person;
import ro.jlg.academy.trainapp.domain.Train;
import ro.jlg.academy.trainapp.infrastructure.PersonRepositoryImpl;
import ro.jlg.academy.trainapp.infrastructure.TrainRepositoryImpl;
import ro.jlg.academy.trainapp.interfaces.dto.CreateUpdatePersonDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    //dependency injection
    private TrainRepositoryImpl trainRepository;
    private PersonRepositoryImpl personRepository;
    //dependency injection
    public PersonService(final PersonRepositoryImpl personRepository, final TrainRepositoryImpl trainRepository) {
        this.personRepository = personRepository;
        this.trainRepository = trainRepository;
    }

    public void create(final CreateUpdatePersonDTO dto) {
        final Person person = new Person(
                UUID.randomUUID().toString(),
                dto.getName(),
                LocalDateTime.parse(dto.getDateOfBirth())
        );
        this.personRepository.save(person);
    }

    public void update(final String id, final CreateUpdatePersonDTO dto) {
        // 1. Find person you want to update (by ID)
        final Person person = this.personRepository.findById(id);
        // 2. Update information
        person.update(dto.getName(), LocalDateTime.parse(dto.getDateOfBirth()));
        // 3. Save the modified person in the database.
        this.personRepository.save(person);
    }

    public void delete(final String personId) {
        final List<Train> passengerTrains = this.trainRepository.findByPassengerId(personId);
        passengerTrains.forEach( train -> {
            train.cancelReservation(personId);
            this.trainRepository.save(train);
        });

        this.personRepository.delete(personId);
    }

    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

}
