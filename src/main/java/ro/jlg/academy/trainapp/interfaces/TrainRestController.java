package ro.jlg.academy.trainapp.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.jlg.academy.trainapp.application.TrainService;
import ro.jlg.academy.trainapp.domain.Person;
import ro.jlg.academy.trainapp.domain.Train;
import ro.jlg.academy.trainapp.interfaces.dto.CreateUpdateTrainDTO;
import ro.jlg.academy.trainapp.interfaces.dto.ReserveTicketDTO;

import java.util.List;

@RestController
public class TrainRestController {

    private TrainService trainService;
    public TrainRestController(final TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/trains")
    public ResponseEntity<Void> create(final @RequestBody CreateUpdateTrainDTO dto) {
        this.trainService.create(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/trains")
    public ResponseEntity<List<Train>> getAll() {
        return ResponseEntity.ok(this.trainService.getAll());
    }

    @PatchMapping("/trains/{trainID}")
    public ResponseEntity<Void> update(final @PathVariable String trainID, final @RequestBody CreateUpdateTrainDTO dto) {
        this.trainService.update(trainID, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("trains/{id}")
    public ResponseEntity<Void> delete(final @PathVariable String id) {
        this.trainService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/trains/reserve")
    public ResponseEntity<Boolean> reserve(final @RequestBody ReserveTicketDTO dto) {
        return ResponseEntity.ok(this.trainService.reserveTicket(dto));
    }

    @GetMapping("/trains/departure/{departure}")
    public ResponseEntity<List<Train>> getAllByDeparture(final @PathVariable String departure) {
        return ResponseEntity.ok(this.trainService.getAllByDeparture(departure));
    }

    @GetMapping("/trains/destination/{destination}")
    public ResponseEntity<List<Train>> getAllByDestination(final @PathVariable String destination) {
        return ResponseEntity.ok(this.trainService.getAllByDestination(destination));
    }

    @GetMapping("/trains/passengers")
    public ResponseEntity<List<Person>> getPassengers() {
        return ResponseEntity.ok(this.trainService.getAllPassengers());
    }
}