package eu.asgardschmiede.todoliste.repository;

import eu.asgardschmiede.todoliste.model.Todoliste;
import eu.asgardschmiede.todoliste.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodolisteRepository extends JpaRepository<Todoliste, UUID> {

    Optional<Todoliste> findByTodoIgnoreCase(String todo);
    List<Todoliste> findAllByUser(User user);

}
