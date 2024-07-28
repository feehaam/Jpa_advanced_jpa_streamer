package playground.jpa.streamer.userData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userData.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
