package playground.jpa.streamer;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Service;
import playground.jpa.streamer.productData.entities.Product;
import playground.jpa.streamer.productData.entities.Tag;
import playground.jpa.streamer.productData.entities.Variant;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ProductServiceJPAStreamerImpl implements ProductService {
    private final JPAStreamer jpaStreamer;

    public ProductServiceJPAStreamerImpl(EntityManagerFactory emf) {
        jpaStreamer = JPAStreamer.of(emf);
    }

    private Stream<Product> _productContext(){
        return jpaStreamer.stream(Product.class);
    }

    // A scanner to read all sub models to overcome Lazy loading.
    private boolean subModelsLoaded(List<?> ... subModels) {
        for (List<?> subModel: subModels) {
            subModel.size();
        }
        return true;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return _productContext()
                .filter(product -> Objects.equals(product.getId(), id)
                        && subModelsLoaded(product.getVariants(), product.getPhotos(), product.getTags()))
                .findFirst();
    }

    @Override
    public List<Product> findByTitle(String title) {
        return _productContext()
                .filter(product -> product.getTitle().toLowerCase().contains(title.toLowerCase())
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findByDescriptionContaining(String keyword) {
        return _productContext()
                .filter(product -> product.getDescription().toLowerCase().contains(keyword.toLowerCase())
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findByTagsName(String tagName) {
        return _productContext()
                .filter(product -> product
                        .getTags()
                            .stream()
                            .map(Tag::getName)
                            .toList()
                        .contains(tagName)
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findByVariantsPriceLessThan(Double price) {
        return _productContext()
                .filter(product -> {
                    var min = product.getVariants().stream().mapToDouble(Variant::getPrice).min();
                    return min.isPresent()
                            && min.getAsDouble() < price
                            && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags());
                })
                .toList();
    }

    @Override
    public List<Product> findByVariantsAvailableQuantityGreaterThan(Integer quantity) {
        return _productContext()
                .filter(product -> {
                    var max = product.getVariants().stream().mapToInt(Variant::getAvailableQuantity).max();
                    return max.isPresent()
                            && max.getAsInt() > quantity
                            && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags());
                })
                .toList();
    }

    @Override
    public List<Product> findByCreatedAtAfter(LocalDateTime dateTime) {
        return _productContext()
                .filter(product -> product.getCreatedAt().isAfter(dateTime)
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findByVariantsName(String variantName) {
        return _productContext()
                .filter(product -> product.getVariants().stream().map(Variant::getName).toList().contains(variantName)
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findByTitleOrDescription(String title, String description) {
        return _productContext()
                .filter(product -> product.getTitle().toLowerCase().contains(title)
                        || product.getDescription().toLowerCase().contains(description)
                        && subModelsLoaded(product.getPhotos(), product.getVariants(), product.getTags()))
                .toList();
    }

    @Override
    public List<Product> findProductsWithVariantsPriceBetween(Double minPrice, Double maxPrice) {
        return _productContext()
                .filter(product -> {
                    for (Variant variant: product.getVariants()) {
                        if (variant.getPrice() >= minPrice && variant.getPrice() <= maxPrice) {
                            return subModelsLoaded(product.getPhotos(), product.getTags());
                        }
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public List<Product> findProductsWithTagAndVariantPriceBetween(String tagName, Double minPrice, Double maxPrice) {
        return _productContext()
                .filter(product -> {
                    for (Variant variant: product.getVariants()) {
                        if (variant.getPrice() >= minPrice
                                && variant.getPrice() <= maxPrice
                                && product.getTags().stream().map(Tag::getName).toList().contains(tagName)) {
                            return subModelsLoaded(product.getPhotos());
                        }
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public List<Product> findProductsWithTotalAvailabilityBelow(Integer minLimit) {
        return _productContext()
                .filter(product -> {
                    return product.getVariants()
                            .stream()
                            .mapToInt(Variant::getAvailableQuantity)
                            .sum() < minLimit
                        && subModelsLoaded(product.getPhotos(), product.getTags());
                })
                .toList();
    }

    @Override
    public List<Product> findProductsByMultipleTags(List<String> tagNames) {
        return _productContext()
                .filter(product -> {
                    List<String> productTags = product.getTags().stream().map(Tag::getName).toList();
                    for(String tag: productTags) {
                        if (tagNames.contains(tag)) {
                            return subModelsLoaded(product.getVariants(), product.getPhotos());
                        }
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public Object findProductBasicInfoList() {
       return _productContext().map(product -> Map.of(
                "productId", product.getId(),
                "productName", product.getTitle(),
                "thumbnail", product.getPhotos().getFirst().getUrl()
        )).toList();
    }

    @Override
    public Object findProductAndTotalQuantityList() {
        return _productContext()
                .map(product -> Map.of(
                "productId", product.getId(),
                "productName", product.getTitle(),
                "totalAvailableQuantity", product.getVariants()
                        .stream()
                        .mapToInt(Variant::getAvailableQuantity)
                        .sum()
        )).toList();
    }
}
