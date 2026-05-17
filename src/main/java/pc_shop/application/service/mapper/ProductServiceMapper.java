package pc_shop.application.service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pc_shop.application.repository.entity.HardDrive;
import pc_shop.application.repository.entity.Laptop;
import pc_shop.application.repository.entity.Monitor;
import pc_shop.application.repository.entity.PersonalComputer;
import pc_shop.application.repository.entity.Product;
import pc_shop.application.service.model.ProductModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductServiceMapper {

    ProductServiceMapper INSTANCE = Mappers.getMapper(ProductServiceMapper.class);

    @Mapping(target = "manufacturerName", source = "manufacturer.name")
    ProductModel toModel(Product product);

    @Mapping(target = "manufacturer.name", source = "manufacturerName")
    Product toEntity(ProductModel model);

    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "productType", ignore = true)
    PersonalComputer toPersonalComputer(ProductModel model);

    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "productType", ignore = true)
    Laptop toLaptop(ProductModel model);

    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "productType", ignore = true)
    Monitor toMonitor(ProductModel model);

    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "productType", ignore = true)
    HardDrive toHardDrive(ProductModel model);

    @AfterMapping
    default void afterMappingToModel(Product product, @MappingTarget ProductModel model) {
        switch (product) {
            case PersonalComputer dc -> model.setFormFactor(dc.getFormFactor());
            case Laptop laptop -> model.setScreenSize(laptop.getScreenSize());
            case Monitor monitor -> model.setDiagonal(monitor.getDiagonal());
            case HardDrive hd -> model.setCapacityGb(hd.getCapacityGb());
            default -> {}
        }
    }
}
