package ms.inventory.core.service;

import com.dev.core.lib.utility.core.exception.GenericErrorException;
import com.dev.core.lib.utility.core.model.enums.Status;
import lombok.AllArgsConstructor;
import ms.inventory.core.entity.Product;
import ms.inventory.core.entity.Stock;
import ms.inventory.core.exception.InventoryError;
import ms.inventory.core.repository.IProductRepository;
import ms.inventory.model.dto.ProductDTO;
import ms.inventory.model.dto.StockDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.dev.core.lib.utility.core.utils.Guard.isNotNullAndEmpty;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private IProductRepository repo;

    @Autowired
    @Qualifier("baseModelMapper")
    private ModelMapper mapper;

    public List<ProductDTO> getAll(){
        return repo.findAllByStatus(Status.ACTIVE).stream()
                .map(record -> mapper.map(record, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getById(UUID id){
        Product product = repo.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new GenericErrorException(InventoryError.PRODUCT_RECORD_NOT_FOUND));
        return mapper.map(product, ProductDTO.class);
    }

    public ProductDTO insert(ProductDTO dto){
        isNotNullAndEmpty(dto.getCode(), "code");
        if(null != dto.getId() && repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.PRODUCT_RECORD_ALREADY_EXIST);

        Product product = mapper.map(dto, Product.class);
        return mapper.map(
                repo.saveAndFlush(product),
                ProductDTO.class);
    }

    public ProductDTO update(ProductDTO dto){
        isNotNullAndEmpty(dto.getCode(), "code");
        if(null == dto.getId())
            throw new GenericErrorException(InventoryError.PRODUCT_ID_NULL);
        Product product = repo.findByIdAndStatus(dto.getId(), Status.ACTIVE)
                .orElseThrow(() -> new GenericErrorException(InventoryError.PRODUCT_RECORD_NOT_FOUND));
        Product inputProduct = mapper.map(dto, Product.class);
        product = this.mapInputDataWithFoundRecord(inputProduct, product);
        return mapper.map(
                repo.saveAndFlush(product),
                ProductDTO.class);
    }
    public ProductDTO upsert(ProductDTO dto){
        if(null == dto.getId() || !repo.existsById(dto.getId()))
            return this.insert(dto);
        else{
            return this.update(dto);
        }
    }

    public void deleteById(UUID id){
        repo.findByIdAndStatus(id, Status.ACTIVE)
                .ifPresent(record -> {
                    record.setStatus(Status.DELETED);
                    repo.saveAndFlush(record);
                });
    }

    private Product mapInputDataWithFoundRecord(Product input, Product foundRecord){
        foundRecord.setCode(input.getCode());
        foundRecord.setName(input.getName());
        foundRecord.setQuantity(input.getQuantity());
        foundRecord.setUnitMeasurement(input.getUnitMeasurement());
        foundRecord.setPrice(input.getPrice());

        return foundRecord;
    }
}
