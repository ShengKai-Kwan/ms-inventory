package ms.inventory.svc.handler;

import lombok.AllArgsConstructor;
import ms.inventory.core.service.StockService;
import ms.inventory.model.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockHandler {
    @Autowired
    private StockService service;

    public List<StockDTO> getAll(){
        return service.getAll();
    }

    public StockDTO getById(UUID id){
        return service.getById(id);
    }

    public StockDTO insert(StockDTO dto){
        return service.insert(dto);
    }

    public StockDTO update(StockDTO dto){
        return service.update(dto);
    }

    public StockDTO upsert(StockDTO dto){
        return service.upsert(dto);
    }

    public void deleteById(UUID id){
        service.deleteById(id);
    }
}
