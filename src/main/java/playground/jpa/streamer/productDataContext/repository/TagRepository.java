package playground.jpa.streamer.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productDataContext.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
