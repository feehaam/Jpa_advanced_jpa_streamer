package playground.jpa.streamer.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productDataContext.entities.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {}
