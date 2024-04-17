package com.example.mspedidoservice.feign;

import com.example.mspedidoservice.dto.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(name = "ms-cliente-service",path = "/cliente")
public interface ClienteFeign {
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable(required = true) Integer id);
}