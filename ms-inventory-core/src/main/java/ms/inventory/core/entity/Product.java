package ms.inventory.core.entity;

import com.dev.core.lib.utility.core.model.entity.BaseEntity;
import lombok.*;
import ms.inventory.core.converter.UnitMeasurementConverter;
import ms.inventory.core.enums.UnitMeasurement;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

//    @Column(name = "code", unique = true)
//    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_measurement")
    @Convert(converter = UnitMeasurementConverter.class)
    private UnitMeasurement unitMeasurement;

    @Column(name = "price")
    private double price;
}
