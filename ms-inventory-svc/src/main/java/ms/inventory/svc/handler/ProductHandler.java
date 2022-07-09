package ms.inventory.svc.handler;

import lombok.AllArgsConstructor;
import ms.inventory.core.service.ProductService;
import ms.inventory.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductHandler {
    @Autowired
    private ProductService service;

    public List<ProductDTO> getAll(){
        return service.getAll();
    }

    public ProductDTO getById(UUID id){
        return service.getById(id);
    }

    public ProductDTO insert(ProductDTO dto){
        return service.insert(dto);
    }

    public ProductDTO update(ProductDTO dto){
        return service.update(dto);
    }

    public ProductDTO upsert(ProductDTO dto){
        return service.upsert(dto);
    }

    public void deleteById(UUID id){
        service.deleteById(id);
    }
}
