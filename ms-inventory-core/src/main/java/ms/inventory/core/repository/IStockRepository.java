package ms.inventory.core.repository;

import ms.inventory.core.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStockRepository extends JpaRepository<Stock, UUID> {
}
