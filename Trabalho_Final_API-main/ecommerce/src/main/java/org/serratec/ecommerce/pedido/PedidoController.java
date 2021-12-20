package org.serratec.ecommerce.pedido;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.serratec.ecommerce.pedido.dto.PedidoInserirDTO;
import org.serratec.ecommerce.pedido.dto.PedidoRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ApiOperation(value = "Pesquisar todos os pedidos", notes = "Listar pedidos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Set<PedidoRespDTO>> pesquisar(){
        return this.pedidoService.pesquisar();
    }

    /*--------------------*/

    @GetMapping("/pesquisar")
    @ApiOperation(value = "Pesquisar pedido por ID", notes = "Pesquisar pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o pedido por ID"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<PedidoRespDTO> pesquisar(@RequestParam(name = "PedidoID") Long idPedido){
        return this.pedidoService.pesquisar(idPedido);
    }


    @PostMapping(path = "/inserir")
    @ApiOperation(value = "Inserir um Pedido", notes = "Inserir pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inserir um pedido"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoRespDTO> inserirPedido(
            @RequestParam(name = "ClienteID") Long idCliente,
            @Valid @RequestBody PedidoInserirDTO pedidosDTO) {
        return this.pedidoService.adicionarPedido(idCliente, pedidosDTO);
    }

    /*--------------------*/

    @PostMapping(path = "/editar")
    @ApiOperation(value = "Editar um Pedido", notes = "Editar pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Editar um pedido"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoRespDTO> editarPedido(
            @RequestParam(name = "PedidoID") Long idPedido,
            @Valid @RequestBody PedidoInserirDTO pedidosDTO){
        return this.pedidoService.editarPedido(idPedido, pedidosDTO);
    }

    /*--------------------*/

    @DeleteMapping("/remover")
    @ApiOperation(value = "Remove um pedido por ID", notes = "Remover pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um pedido"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Pedido> remover(@RequestParam(name = "PedidoID") Long idPedido) {
        return this.pedidoService.remover(idPedido);
    }
}
