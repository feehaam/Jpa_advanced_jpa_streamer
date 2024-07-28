package playground.jpa.streamer.productData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productData.entities.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {}
