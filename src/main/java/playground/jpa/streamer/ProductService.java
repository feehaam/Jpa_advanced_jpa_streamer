package playground.jpa.streamer;

import playground.jpa.streamer.productData.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // Directly implemented by JPA Repository methods
    Optional<Product> findById(Long id);

    List<Product> findByTitle(String title);

    List<Product> findByDescriptionContaining(String keyword);

    List<Product> findByTagsName(String tagName);

    List<Product> findByVariantsPriceLessThan(Double price);

    List<Product> findByVariantsAvailableQuantityGreaterThan(Integer quantity);

    List<Product> findByCreatedAtAfter(java.time.LocalDateTime dateTime);

    List<Product> findByVariantsName(String variantName);

    List<Product> findByTitleOrDescription(String title, String description);

    // Methods requiring custom queries
    List<Product> findProductsWithVariantsPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findProductsWithTagAndVariantPriceBetween(String tagName, Double minPrice, Double maxPrice);

    List<Product> findProductsWithTotalAvailabilityBelow(Integer minLimit);

    List<Product> findProductsByMultipleTags(List<String> tagNames);

    Object findProductBasicInfoList(); // Only product id, name, price and thumbnail photo

    Object findProductAndTotalQuantityList();
}

