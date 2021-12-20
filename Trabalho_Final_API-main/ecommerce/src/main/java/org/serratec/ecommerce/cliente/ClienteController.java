package org.serratec.ecommerce.cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.serratec.ecommerce.cliente.dto.ClienteRequestDTO;
import org.serratec.ecommerce.cliente.dto.ClienteRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    @ApiOperation(value = CLIENTE_PESQUISAR_TODOS)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RESPONSE_200),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<List<ClienteRespDTO>> pesquisar(){
        return this.clienteService.pesquisar();
    }

    /*--------------------*/

    @GetMapping("/pesquisar")
    @ApiOperation(value = "Pesquisar cliente por ID", notes = "Pesquisar cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um cliente"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<ClienteRespDTO> pesquisar(@RequestParam(name = "ClienteID") Long id){
        return this.clienteService.pesquisar(id);
    }

    /*Método de inserção de Cliente*/

    @PostMapping
    @ApiOperation(value = "Inserir um Cliente", notes = "Inserir cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um cliente"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteRespDTO> inserir(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return this.clienteService.inserir(clienteRequestDTO);
    }

    /*------------Método de edição de Cliente--------*/


    @PutMapping("/editar")
    @ApiOperation(value = "Editar um cliente por ID", notes = "Editar cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um cliente editado"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<ClienteRespDTO> editar(@RequestParam(name = "ClienteID") Long id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.editar(id, clienteRequestDTO);
    }

    /*--------------------*/

    @DeleteMapping("/deletar")
    @ApiOperation(value = "Deletar um cliente por ID", notes = "Deletar cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta um clientes"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Void> deletar(@RequestParam(name = "ClienteID") Long id) {
        return clienteService.deletar(id);
    }
}
