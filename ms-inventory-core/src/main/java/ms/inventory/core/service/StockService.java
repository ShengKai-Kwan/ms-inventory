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

import static com.dev.core.lib.utility.core.utils.Guard.isNotNullAndEmpty;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private IStockRepository repo;

    @Autowired
    @Qualifier("baseModelMapper")
    private ModelMapper mapper;

    public List<StockDTO> getAll(){
        return repo.findAllByStatus(Status.ACTIVE).stream()
                .map(record -> mapper.map(record, StockDTO.class))
                .collect(Collectors.toList());
    }

    public StockDTO getById(UUID id){
        Stock stock = repo.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new GenericErrorException(InventoryError.STOCK_RECORD_NOT_FOUND));
        return mapper.map(stock, StockDTO.class);
    }

    public StockDTO insert(StockDTO dto){
        isNotNullAndEmpty(dto.getCode(), "code");
        if(null != dto.getId() && repo.existsById(dto.getId()))
            throw new GenericErrorException(InventoryError.STOCK_RECORD_ALREADY_EXIST);

        Stock stock = mapper.map(dto, Stock.class);
        return mapper.map(
                repo.saveAndFlush(stock),
                StockDTO.class);
    }

    public StockDTO update(StockDTO dto){
        isNotNullAndEmpty(dto.getCode(), "code");
        if(null == dto.getId())
            throw new GenericErrorException(InventoryError.STOCK_ID_NULL);
        Stock stock = repo.findByIdAndStatus(dto.getId(), Status.ACTIVE)
                .orElseThrow(() -> new GenericErrorException(InventoryError.STOCK_RECORD_NOT_FOUND));
        Stock inputStock = mapper.map(dto, Stock.class);
        stock = this.mapInputDataWithFoundRecord(inputStock, stock);
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
        repo.findByIdAndStatus(id, Status.ACTIVE)
                .ifPresent(record -> {
                    record.setStatus(Status.DELETED);
                    repo.saveAndFlush(record);
                });
    }

    private Stock mapInputDataWithFoundRecord(Stock input, Stock foundRecord){
        foundRecord.setCode(input.getCode());
        foundRecord.setName(input.getName());
        foundRecord.setBrand(input.getBrand());
        foundRecord.setQuantity(input.getQuantity());
        foundRecord.setUnitMeasurement(input.getUnitMeasurement());
        foundRecord.setCost(input.getCost());

        return foundRecord;
    }
}
