package playground.jpa.streamer.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userDataContext.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
