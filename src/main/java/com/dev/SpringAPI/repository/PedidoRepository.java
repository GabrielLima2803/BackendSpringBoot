package com.dev.SpringAPI.repository;

import com.dev.SpringAPI.models.Pedido;
import com.dev.SpringAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByUser(User user);
}
