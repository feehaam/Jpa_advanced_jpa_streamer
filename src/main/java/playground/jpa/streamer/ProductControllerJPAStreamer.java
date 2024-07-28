package playground.jpa.streamer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/jpa-streamer")
public class ProductControllerJPAStreamer extends ProductControllerBase{

    public ProductControllerJPAStreamer(ProductServiceJPAStreamerImpl productService) {
        super(productService);
    }
}
