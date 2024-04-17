package com.example.mspedidoservice.service.impl;

import com.example.mspedidoservice.dto.ClienteDto;
import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feign.CatalogoFeign;
import com.example.mspedidoservice.feign.ClienteFeign;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private CatalogoFeign catalogoFeign;

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            ClienteDto clienteDto = clienteFeign.buscarPorId(pedido.getClienteId()).getBody();
            List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
                pedidoDetalle.setProductoDto(catalogoFeign.productoBuscarPorId(pedidoDetalle.getProductoId()).getBody());
                return pedidoDetalle;
            }).collect(Collectors.toList());
            pedido.setClienteDto(clienteDto);
            pedido.setDetalle(pedidoDetalles);
            return pedido;
        } else {
            // Manejar el caso en que no se encuentra el pedido
            return null; // O lanzar una excepción, dependiendo de tus requisitos
        }
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
