package pc_shop.application.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "product_id")
public class Laptop extends Product {

    @Column(nullable = false)
    private Integer screenSize;
}
