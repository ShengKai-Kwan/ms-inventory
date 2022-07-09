package ms.inventory.core.service;

import com.dev.core.lib.utility.core.exception.GenericErrorException;
import com.dev.core.lib.utility.core.model.enums.Status;
import lombok.AllArgsConstructor;
import ms.inventory.core.entity.Stock;
import ms.inventory.core.exception.InventoryError;
import ms.inventory.core.repository.IStockRepository;
import ms.inventory.model.dto.StockDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private IStockRepository repo;

    @Autowired
    @Qualifier("baseModelMapper")
    private ModelMapper mapper;

    public List<StockDTO> getAll(){
        return repo.findAll().stream()
                .map(record -> mapper.map(record, StockDTO.class))
                .collect(Collectors.toList());
    }

    public StockDTO getById(UUID id){
        Stock stock = repo.findById(id)
                .orElseThrow(() -> new GenericErrorException(InventoryError.STOCK_RECORD_NOT_FOUND));
        return mapper.map(stock, StockDTO.class);
    }

    public StockDTO insert(StockDTO dto){
        if(null != dto.getId() && repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.STOCK_RECORD_ALREADY_EXIST);

        Stock stock = mapper.map(dto, Stock.class);
        return mapper.map(
                repo.saveAndFlush(stock),
                StockDTO.class);
    }

    public StockDTO update(StockDTO dto){
        if(null == dto.getId() || !repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.STOCK_RECORD_NOT_FOUND);

        Stock stock = mapper.map(dto, Stock.class);
        return mapper.map(
                repo.saveAndFlush(stock),
                StockDTO.class);
    }

    public StockDTO upsert(StockDTO dto){
        if(null == dto.getId() || !repo.existsById(dto.getId()))
            return this.insert(dto);
        else {
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
