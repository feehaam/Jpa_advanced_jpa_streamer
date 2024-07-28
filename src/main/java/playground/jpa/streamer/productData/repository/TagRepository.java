package playground.jpa.streamer.productData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productData.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
