package playground.jpa.streamer.userData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userData.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
