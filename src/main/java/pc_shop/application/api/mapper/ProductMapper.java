package pc_shop.application.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pc_shop.application.api.dto.CreateProductRequest;
import pc_shop.application.api.dto.ProductResponse;
import pc_shop.application.api.dto.UpdateProductRequest;
import pc_shop.application.service.model.ProductModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductModel createRequestToModel(CreateProductRequest request);

    ProductResponse modelToResponse(ProductModel model);

    ProductModel updateRequestToModel(UpdateProductRequest request);

}
