package playground.jpa.streamer.productData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productData.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

