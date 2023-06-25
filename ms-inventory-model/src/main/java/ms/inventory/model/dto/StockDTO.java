package ms.inventory.model.dto;

import com.dev.core.lib.utility.core.model.dto.BaseEntityDTO;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO extends BaseEntityDTO {
    private String name;
    private int quantity;
    private String unitMeasurement;
    private double cost;
}
