package pc_shop.application.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "monitor")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "product_id")
public class Monitor extends Product {

    @Column(nullable = false, precision = 5, scale = 1)
    private BigDecimal diagonal;
}
