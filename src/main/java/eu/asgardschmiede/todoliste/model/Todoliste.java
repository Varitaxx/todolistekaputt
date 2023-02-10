package eu.asgardschmiede.todoliste.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Todoliste implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2") // Generator wird ausgewählt
    @GeneratedValue(generator = "uuid2") // Gewählte Generator wird benutzt
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @NotEmpty
    @Size(min = 2, max = 50)
    private String todo;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String datum;
    @ManyToOne
    private User user;

    public Todoliste() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}

