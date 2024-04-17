package com.example.mspedidoservice.feign;

import com.example.mspedidoservice.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo-service", path = "/producto")
public interface CatalogoFeign {
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> productoBuscarPorId(@PathVariable(required = true) Integer id);
}
