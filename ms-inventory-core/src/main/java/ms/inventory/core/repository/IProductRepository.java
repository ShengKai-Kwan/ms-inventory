package ms.inventory.core.repository;

import com.dev.core.lib.utility.core.model.enums.Status;
import ms.inventory.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByStatus(Status status);
    Optional<Product> findByIdAndStatus(UUID id, Status status);
}
