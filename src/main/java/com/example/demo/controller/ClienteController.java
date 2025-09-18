package com.example.demo.controller;



import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionClienteDTO;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Buscar clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarClientes(@RequestParam("busqueda") String busqueda) {
        List<ClienteDTO> clientes = clienteService.buscarClientes(busqueda);
        return ResponseEntity.ok(clientes);
    }

    // Crear cliente con dirección matriz
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCreado = clienteService.crearClienteConDireccionMatriz(clienteDTO);
        return ResponseEntity.ok(clienteCreado);
    }

    // Editar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> editarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.editarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }

    // Registrar nueva dirección para cliente
    @PostMapping("/{id}/direcciones")
    public ResponseEntity<String> registrarDireccion(@PathVariable Long id, @RequestBody DireccionClienteDTO direccionDTO) {
        clienteService.registrarDireccion(id, direccionDTO);
        return ResponseEntity.ok("Dirección registrada exitosamente");
    }

    // Listar direcciones de un cliente
    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<DireccionClienteDTO>> listarDirecciones(@PathVariable Long id) {
        List<DireccionClienteDTO> direcciones = clienteService.listarDirecciones(id);
        return ResponseEntity.ok(direcciones);
    }
}
