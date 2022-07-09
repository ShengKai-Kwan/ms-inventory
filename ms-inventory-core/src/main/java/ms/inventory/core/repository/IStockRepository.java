package ms.inventory.core.repository;

import com.dev.core.lib.utility.core.model.enums.Status;
import ms.inventory.core.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStockRepository extends JpaRepository<Stock, UUID> {
    List<Stock> findAllByStatus(Status status);
    Optional<Stock> findByIdAndStatus(UUID id, Status status);
}
