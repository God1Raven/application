package pc_shop.application.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pc_shop.application.repository.HardDriveRepository;
import pc_shop.application.repository.LaptopRepository;
import pc_shop.application.repository.ManufacturerRepository;
import pc_shop.application.repository.MonitorRepository;
import pc_shop.application.repository.PersonalComputerRepository;
import pc_shop.application.repository.ProductRepository;
import pc_shop.application.repository.entity.HardDrive;
import pc_shop.application.repository.entity.Laptop;
import pc_shop.application.repository.entity.Manufacturer;
import pc_shop.application.repository.entity.Monitor;
import pc_shop.application.repository.entity.PersonalComputer;
import pc_shop.application.repository.entity.Product;
import pc_shop.application.service.mapper.ProductServiceMapper;
import pc_shop.application.service.model.ProductModel;
import pc_shop.application.shared.ProductType;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PersonalComputerRepository pcRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final HardDriveRepository hardDriveRepository;

    private final ProductServiceMapper mapper;

    @Transactional
    public ProductModel createProduct(ProductModel model) {
        Manufacturer manufacturer = manufacturerRepository.findByName(model.getManufacturerName())
                .orElseThrow();

        Product product = switch (model.getProductType()) {
            case PERSONAL_COMPUTER -> createPersonalComputer(model, manufacturer);
            case LAPTOP -> createLaptop(model, manufacturer);
            case MONITOR -> createMonitor(model, manufacturer);
            case HARD_DRIVE -> createHardDrive(model, manufacturer);
        };

        return mapper.toModel(product);
    }

    @Transactional
    public ProductModel updateProduct(Long id,ProductModel model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Товар с id = " + id + " не найден"));

        if (model.getPrice() != null) {
            product.setPrice(model.getPrice());
        }
        if (model.getStockQuantity() != null) {
            product.setStockQuantity(model.getStockQuantity());
        }
        if (model.getManufacturerName() != null) {
            Manufacturer manufacturer = manufacturerRepository.findByName(model.getManufacturerName())
                    .orElseThrow(() -> new IllegalArgumentException("Производитель не найден"));
            product.setManufacturer(manufacturer);
        }

        updateSpecificFields(product, model);

        Product savedProduct = productRepository.save(product);
        return mapper.toModel(savedProduct);
    }

    private void updateSpecificFields(Product product, ProductModel request) {
        switch (product.getProductType()) {
            case PERSONAL_COMPUTER -> {
                PersonalComputer dc = (PersonalComputer) product;
                if (request.getFormFactor() != null) dc.setFormFactor(request.getFormFactor());
            }
            case LAPTOP -> {
                Laptop laptop = (Laptop) product;
                if (request.getScreenSize() != null) laptop.setScreenSize(request.getScreenSize());
            }
            case MONITOR -> {
                Monitor monitor = (Monitor) product;
                if (request.getDiagonal() != null) monitor.setDiagonal(request.getDiagonal());
            }
            case HARD_DRIVE -> {
                HardDrive hd = (HardDrive) product;
                if (request.getCapacityGb() != null) hd.setCapacityGb(request.getCapacityGb());
            }
        }
    }

    private Product createPersonalComputer(ProductModel req, Manufacturer manufacturer) {
        PersonalComputer computer = mapper.toPersonalComputer(req);
        computer.setManufacturer(manufacturer);
        computer.setProductType(ProductType.PERSONAL_COMPUTER);
        computer.setPrice(req.getPrice());
        computer.setStockQuantity(req.getStockQuantity());
        computer.setSerialNumber(req.getSerialNumber());

        computer = pcRepository.save(computer);
        return computer;
    }

    private Product createLaptop(ProductModel req, Manufacturer manufacturer) {
        Laptop laptop = mapper.toLaptop(req);
        laptop.setManufacturer(manufacturer);
        laptop.setProductType(ProductType.LAPTOP);
        laptop.setPrice(req.getPrice());
        laptop.setStockQuantity(req.getStockQuantity());
        laptop.setSerialNumber(req.getSerialNumber());

        laptop = laptopRepository.save(laptop);
        return laptop;
    }

    private Product createMonitor(ProductModel req, Manufacturer manufacturer) {
        Monitor monitor = mapper.toMonitor(req);
        monitor.setManufacturer(manufacturer);
        monitor.setProductType(ProductType.MONITOR);
        monitor.setPrice(req.getPrice());
        monitor.setStockQuantity(req.getStockQuantity());
        monitor.setSerialNumber(req.getSerialNumber());

        monitor = monitorRepository.save(monitor);
        return monitor;
    }

    private Product createHardDrive(ProductModel req, Manufacturer manufacturer) {
        HardDrive hd = mapper.toHardDrive(req);
        hd.setManufacturer(manufacturer);
        hd.setProductType(ProductType.HARD_DRIVE);
        hd.setPrice(req.getPrice());
        hd.setStockQuantity(req.getStockQuantity());
        hd.setSerialNumber(req.getSerialNumber());

        hd = hardDriveRepository.save(hd);
        return hd;
    }

    @Transactional()
    public List<ProductModel> getAllProducts() {
        return productRepository.findAllWithManufacturer().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Transactional()
    public ProductModel getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Товар не найден"));
        return mapper.toModel(product);
    }

    @Transactional()
    public List<ProductModel> getProductsByType(ProductType type) {
        return productRepository.findAllByProductType(type).stream()
                .map(mapper::toModel)
                .toList();
    }
}
