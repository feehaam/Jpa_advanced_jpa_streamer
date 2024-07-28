package playground.jpa.streamer.userData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userData.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
