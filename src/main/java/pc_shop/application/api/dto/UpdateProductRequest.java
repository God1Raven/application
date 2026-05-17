package pc_shop.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pc_shop.application.shared.ProductType;

import java.math.BigDecimal;

public record UpdateProductRequest(@JsonProperty("serial_number") String serialNumber,
                                   @JsonProperty("manufacturer_name") String manufacturerName,
                                   @JsonProperty("product_type")ProductType productType,
                                   BigDecimal price,
                                   @JsonProperty("stock_quantity")Integer stockQuantity,
                                   @JsonProperty("form_factor")String formFactor,
                                   @JsonProperty("screen_size")Integer screenSize,
                                   BigDecimal diagonal,
                                   @JsonProperty("capacity_gb") Integer capacityGb) {
}
