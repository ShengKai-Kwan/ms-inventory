package ms.inventory.core.entity;

import com.dev.core.lib.utility.core.model.entity.BaseEntity;
import lombok.*;
import ms.inventory.core.converter.UnitMeasurementConverter;
import ms.inventory.core.enums.UnitMeasurement;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_stock")
@EqualsAndHashCode(callSuper = true)
public class Stock extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_measurement")
    @Convert(converter = UnitMeasurementConverter.class)
    private UnitMeasurement unitMeasurement;

    @Column(name = "cost")
    private double cost;

    @Column(name = "selling_price")
    private double sellingPrice;
}
