package com.example.mspedidoservice.repository;

import com.example.mspedidoservice.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
