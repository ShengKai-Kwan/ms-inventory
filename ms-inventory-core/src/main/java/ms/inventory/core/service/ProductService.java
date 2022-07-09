package ms.inventory.core.service;

import com.dev.core.lib.utility.core.exception.GenericErrorException;
import com.dev.core.lib.utility.core.model.enums.Status;
import lombok.AllArgsConstructor;
import ms.inventory.core.entity.Product;
import ms.inventory.core.exception.InventoryError;
import ms.inventory.core.repository.IProductRepository;
import ms.inventory.model.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private IProductRepository repo;

    @Autowired
    @Qualifier("baseModelMapper")
    private ModelMapper mapper;

    public List<ProductDTO> getAll(){
        return repo.findAll().stream()
                .map(record -> mapper.map(record, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getById(UUID id){
        Product product = repo.findById(id)
                .orElseThrow(() -> new GenericErrorException(InventoryError.PRODUCT_RECORD_NOT_FOUND));
        return mapper.map(product, ProductDTO.class);
    }

    public ProductDTO insert(ProductDTO dto){
        if(null != dto.getId() && repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.PRODUCT_RECORD_ALREADY_EXIST);

        Product product = mapper.map(dto, Product.class);
        return mapper.map(
                repo.saveAndFlush(product),
                ProductDTO.class);
    }

    public ProductDTO update(ProductDTO dto){
        if(null == dto.getId() || !repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.PRODUCT_RECORD_NOT_FOUND);

        Product product = mapper.map(dto, Product.class);
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
        repo.findById(id)
                .ifPresent(record -> {
                    record.setStatus(Status.DELETED);
                    repo.saveAndFlush(record);
                });
    }
}
