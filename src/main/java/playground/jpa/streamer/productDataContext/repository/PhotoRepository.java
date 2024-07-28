package playground.jpa.streamer.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productDataContext.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {}
