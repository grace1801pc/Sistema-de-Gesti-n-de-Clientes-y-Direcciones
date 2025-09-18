package com.example.demo.service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.DireccionCliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DireccionClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final DireccionClienteRepository direccionClienteRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          DireccionClienteRepository direccionClienteRepository) {
        this.clienteRepository = clienteRepository;
        this.direccionClienteRepository = direccionClienteRepository;
    }

    // Buscar clientes por número o nombre
    public List<ClienteDTO> buscarClientes(String busqueda) {
        List<Cliente> clientes = clienteRepository
                .findByNumeroIdentificacionContainingIgnoreCaseOrNombresContainingIgnoreCase(busqueda, busqueda);

        return clientes.stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    // Crear cliente con dirección matriz
    @Transactional
    public ClienteDTO crearClienteConDireccionMatriz(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese número de identificación");
        }

        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setCelular(clienteDTO.getCelular());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        DireccionClienteDTO dirDTO = clienteDTO.getDireccionMatriz();
        if (dirDTO == null) {
            throw new IllegalArgumentException("Debe incluir la dirección matriz");
        }

        DireccionCliente direccionMatriz = new DireccionCliente();
        direccionMatriz.setProvincia(dirDTO.getProvincia());
        direccionMatriz.setCiudad(dirDTO.getCiudad());
        direccionMatriz.setDireccion(dirDTO.getDireccion());
        direccionMatriz.setMatriz(true);
        direccionMatriz.setCliente(clienteGuardado);

        direccionClienteRepository.save(direccionMatriz);

        return convertirAClienteDTO(clienteGuardado);
    }

    // Editar cliente 
    @Transactional
    public ClienteDTO editarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (!cliente.getNumeroIdentificacion().equals(clienteDTO.getNumeroIdentificacion()) &&
                clienteRepository.existsByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese número de identificación");
        }

        cliente.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setCelular(clienteDTO.getCelular());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return convertirAClienteDTO(clienteActualizado);
    }

    // Eliminar cliente
    @Transactional
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    // Registrar nueva dirección para cliente
    @Transactional
    public void registrarDireccion(Long clienteId, DireccionClienteDTO direccionDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (direccionDTO.isMatriz()) {
            DireccionCliente matrizExistente = direccionClienteRepository.findByClienteIdAndMatrizTrue(clienteId);
            if (matrizExistente != null) {
                throw new IllegalArgumentException("Ya existe una dirección matriz para este cliente");
            }
        }

        DireccionCliente direccion = new DireccionCliente();
        direccion.setProvincia(direccionDTO.getProvincia());
        direccion.setCiudad(direccionDTO.getCiudad());
        direccion.setDireccion(direccionDTO.getDireccion());
        direccion.setMatriz(direccionDTO.isMatriz());
        direccion.setCliente(cliente);

        direccionClienteRepository.save(direccion);
    }

    // Listar direcciones de un cliente
    public List<DireccionClienteDTO> listarDirecciones(Long clienteId) {
        List<DireccionCliente> direcciones = direccionClienteRepository.findByClienteId(clienteId);

        return direcciones.stream()
                .map(this::convertirADireccionDTO)
                .collect(Collectors.toList());
    }

    
    

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setTipoIdentificacion(cliente.getTipoIdentificacion());
        dto.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        dto.setNombres(cliente.getNombres());
        dto.setCorreo(cliente.getCorreo());
        dto.setCelular(cliente.getCelular());

        DireccionCliente matriz = direccionClienteRepository.findByClienteIdAndMatrizTrue(cliente.getId());
        if (matriz != null) {
            dto.setDireccionMatriz(convertirADireccionDTO(matriz));
        }
        return dto;
    }

    private DireccionClienteDTO convertirADireccionDTO(DireccionCliente direccion) {
        DireccionClienteDTO dto = new DireccionClienteDTO();
        dto.setId(direccion.getId());
        dto.setProvincia(direccion.getProvincia());
        dto.setCiudad(direccion.getCiudad());
        dto.setDireccion(direccion.getDireccion());
        dto.setMatriz(direccion.isMatriz());
        return dto;
    }
}
