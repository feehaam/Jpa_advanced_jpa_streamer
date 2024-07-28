package playground.jpa.streamer.productData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productData.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {}
