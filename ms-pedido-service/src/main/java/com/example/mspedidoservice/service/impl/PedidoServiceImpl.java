package com.example.mspedidoservice.service.impl;

import com.example.mspedidoservice.dto.ClienteDto;
import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feign.ProductoFeign;
import com.example.mspedidoservice.feign.ClienteFeign;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private ProductoFeign catalogoFeign;

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Integer id) {
        Optional<Pedido> pedido =pedidoRepository.findById(id);
        ClienteDto clienteDto = clienteFeign.buscarPorId(pedido.get().getClienteId()).getBody();
       /* for (PedidoDetalle pedidoDetalle : pedido.get().getDetalle()) {
            pedidoDetalle.setProductoDto(catalogoFeign.productoBuscarPorId(pedidoDetalle.getProductoId()).getBody());
        }*/

        List<PedidoDetalle> pedidoDetalles = pedido.get().getDetalle().stream().map(pedidoDetalle -> {
            pedidoDetalle.setProductoDto(catalogoFeign.productoBuscarPorId(pedidoDetalle.getProductoId()).getBody());
            return pedidoDetalle;
        }).toList();
        pedido.get().setClienteDto(clienteDto);
        pedido.get().setDetalle(pedidoDetalles);

        return pedido;
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
