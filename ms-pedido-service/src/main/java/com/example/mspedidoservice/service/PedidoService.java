package com.example.mspedidoservice.service;

import com.example.mspedidoservice.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    public List<Pedido> listar();
    public Pedido guardar(Pedido pedido);
    public Optional<Pedido> buscarPorId(Integer id);
    public Pedido editar(Pedido pedido);
    public void eliminar(Integer id);
}
