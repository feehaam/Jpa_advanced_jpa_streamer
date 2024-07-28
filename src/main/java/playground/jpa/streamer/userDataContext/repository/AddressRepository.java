package playground.jpa.streamer.userDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.userDataContext.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
