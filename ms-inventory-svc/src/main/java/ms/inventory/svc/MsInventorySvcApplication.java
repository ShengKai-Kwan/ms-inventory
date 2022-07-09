package ms.inventory.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
		"ms.inventory",
		"com.dev.core.lib.utility"
})
@EnableJpaRepositories({"ms.inventory.core.repository"})
@EntityScan({"ms.inventory.core.entity"})
public class MsInventorySvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInventorySvcApplication.class, args);
	}

}
