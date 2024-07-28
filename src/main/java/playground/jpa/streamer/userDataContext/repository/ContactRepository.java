package playground.jpa.streamer.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userDataContext.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
