package ro.jlg.academy.trainapp.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.jlg.academy.trainapp.application.PersonService;
import ro.jlg.academy.trainapp.domain.Person;
import ro.jlg.academy.trainapp.interfaces.dto.CreateUpdatePersonDTO;

import java.util.List;

@RestController
public class PersonRestController {

    private PersonService personService;

    public PersonRestController(final PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> create(final @RequestBody CreateUpdatePersonDTO dto) {
        this.personService.create(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(this.personService.getAll());
    }

    @PatchMapping("/persons/{personID}")
    public ResponseEntity<Void> update(final @PathVariable String personID, final @RequestBody CreateUpdatePersonDTO dto) {
        this.personService.update(personID, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Void> delete(final @PathVariable String id) {
        this.personService.delete(id);

        return ResponseEntity.ok().build();
    }
}
