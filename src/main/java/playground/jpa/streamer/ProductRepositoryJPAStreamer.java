package playground.jpa.streamer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.jpa.streamer.productData.entities.Product;

import java.util.List;

@Repository
public interface ProductRepositoryJPAStreamer extends JpaRepository<Product, Long> {
}
