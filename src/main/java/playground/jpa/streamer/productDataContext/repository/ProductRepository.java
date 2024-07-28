package playground.jpa.streamer.productDataContext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.jpa.streamer.productDataContext.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

