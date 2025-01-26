package com.dev.SpringAPI.controller;

import com.dev.SpringAPI.controller.dto.CreatePedidoDto;
import com.dev.SpringAPI.controller.dto.MessageResponseDto;
import com.dev.SpringAPI.controller.dto.UpdatePedidoDto;
import com.dev.SpringAPI.models.Pedido;
import com.dev.SpringAPI.models.User;
import com.dev.SpringAPI.repository.PedidoRepository;
import com.dev.SpringAPI.repository.UserRepository;
import com.dev.SpringAPI.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@Validated
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> criarPedido(@RequestBody CreatePedidoDto dto, Authentication authentication) {
        String userIdStr = authentication.getName();

        UUID userId = UUID.fromString(userIdStr);
        System.out.println("Username: " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
                System.out.println("User: " + user);

        Pedido pedido = new Pedido();
                pedido.setNome(dto.getNome());
                pedido.setDescricao(dto.getDescricao());
                pedido.setValorTotal(dto.getValorTotal());
                pedido.setStatus(dto.getStatus());
                pedido.setDataCriacao(LocalDateTime.now());
                pedido.setUser(user);

        pedidoRepository.save(pedido);

        var emailUser = user.getEmail();

        emailService.enviarEmail(emailUser, "Pedido criado com sucesso!", "O pedido foi criado com sucesso!");

        var messageResponseDto = new MessageResponseDto("Pedido criado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidosDoUsuario(Authentication authentication) {
        String username = authentication.getName();
        UUID userId = UUID.fromString(username);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Pedido> pedidos = pedidoRepository.findByUser(user);

        return ResponseEntity.ok(pedidos);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarPedido(@RequestParam UUID pedidoId) {
        var pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido não encontrado para o ID fornecido.");
        }
        pedidoRepository.deleteById(pedidoId);
        return ResponseEntity.ok("Pedido deletado com sucesso.");
    }
    @PutMapping
    public ResponseEntity<?> atualizarPedido(@RequestParam UUID pedidoId, @RequestBody UpdatePedidoDto updatePedidoDto) {
        var pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido não encontrado para o ID fornecido.");
        }
        var pedido = pedidoOptional.get();
        pedido.setNome(updatePedidoDto.getNome());
        pedido.setDescricao(updatePedidoDto.getDescricao());
        pedido.setValorTotal(updatePedidoDto.getValorTotal());
        pedido.setStatus(updatePedidoDto.getStatus());
        pedido.setDataAtualizacao(LocalDateTime.now());
        pedidoRepository.save(pedido);
        return ResponseEntity.ok("Pedido atualizado com sucesso.");
    }
}
