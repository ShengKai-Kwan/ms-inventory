package ms.inventory.svc.controller;

import ms.inventory.model.dto.ProductDTO;
import ms.inventory.svc.constant.ResourcePath;
import ms.inventory.svc.handler.ProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ResourcePath.APP_VERSION)
public class ProductController {

    @Autowired
    private ProductHandler handler;

    @GetMapping(value = ResourcePath.PRODUCT_GET_ALL)
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(handler.getAll());
    }

    @GetMapping(value = ResourcePath.PRODUCT_GET_BY_ID)
    public ResponseEntity<ProductDTO> getById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(handler.getById(id));
    }

    @PostMapping(value = ResourcePath.PRODUCT_UPSERT)
    public ResponseEntity<ProductDTO> upsert(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(handler.upsert(dto));
    }

    @PostMapping(value = ResourcePath.PRODUCT_DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") UUID id){
        handler.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
