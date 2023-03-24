package ro.jlg.academy.trainapp.domain;

import java.time.LocalDateTime;

public class Person {
    private final String id;
    private String name;
    private LocalDateTime dateOfBirth;

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Person(final String id, final String name, final LocalDateTime dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void update(String newName, LocalDateTime newDateOfBirth) {
        this.name = newName;
        this.dateOfBirth = newDateOfBirth;
    }

}
