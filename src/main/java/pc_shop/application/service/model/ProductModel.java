package pc_shop.application.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pc_shop.application.shared.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private Long id;

    private String serialNumber;

    private String manufacturerName;

    private ProductType productType;

    private BigDecimal price;

    private Integer stockQuantity;

    private String formFactor;

    private Integer screenSize;

    private BigDecimal diagonal;

    private BigDecimal capacityGb;
}
