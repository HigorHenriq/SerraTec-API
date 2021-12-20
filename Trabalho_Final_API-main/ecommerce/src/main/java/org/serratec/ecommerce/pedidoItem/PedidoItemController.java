package org.serratec.ecommerce.pedidoItem;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.NOTE_PESQUISAR_TODOS_PEDIDOS_ITEM;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.PESQUISAR_TODOS_PEDIDOS_ITEM;

@RestController
//@RequestMapping(value = "/api/pedidoitem")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    /* Método para pesquisar todas os PedidoItens*/

//    @GetMapping("/pesquisar")
//    @ApiOperation(value = PESQUISAR_TODOS_PEDIDOS_ITEM, notes = NOTE_PESQUISAR_TODOS_PEDIDOS_ITEM)
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = RESPONSE_200),
//            @ApiResponse(code = 401, message = ERROR_401),
//			@ApiResponse(code = 403, message = ERROR_403),
//            @ApiResponse(code = 404, message = ERROR_404),
//			@ApiResponse(code = 505, message = ERROR_405)})
//
//    public ResponseEntity<List<PedidoItem>> pesquisar() {
//        return this.pedidoItemService.pesquisar();
//
//    }

    /* Método para pesquisar PedidoItem por ID */

//    @GetMapping("/{id}")
//    @ApiOperation(value = PESQUISAR_PEDIDO_ITEM_ID, notes = NOTE_PESQUISAR_PEDIDO_ITEM_ID)
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = RESPONSE_200),
//            @ApiResponse(code = 401, message = ERROR_401),
//			@ApiResponse(code = 403, message = ERROR_403),
//            @ApiResponse(code = 404, message = ERROR_404),
//			@ApiResponse(code = 505, message = ERROR_405)})
//
//    public ResponseEntity<PedidoItem> pesquisar(@PathVariable Long id) {
//        return this.pedidoItemService.pesquisar(id);
//
//    }

    /* Método para inserir um PedidoItem */

//    @PostMapping
//    @ApiOperation(value = INSERIR_PEDIDO_ITEM, notes = NOTE_INSERIR_PEDIDO_ITEM)
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = RESPONSE_200),
//            @ApiResponse(code = 401, message = ERROR_401),
//			@ApiResponse(code = 403, message = ERROR_403),
//            @ApiResponse(code = 404, message = ERROR_404),
//			@ApiResponse(code = 505, message = ERROR_405)})
//
//    @ResponseStatus(HttpStatus.CREATED)
//    public PedidoItem inserir(@Valid @RequestBody PedidoItem pedidoItem) {
//        return this.pedidoItemService.inserir(pedidoItem);
//    }

    /* Método para editar PedidoItem por ID */

//    @PutMapping("/{id}")
//    @ApiOperation(value = EDITAR_PEDIDO_ITEM, notes = NOTE_EDITAR_PEDIDO_ITEM)
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = RESPONSE_200),
//            @ApiResponse(code = 401, message = ERROR_401),
//			@ApiResponse(code = 403, message = ERROR_403),
//            @ApiResponse(code = 404, message = ERROR_404),
//			@ApiResponse(code = 505, message = ERROR_405)})
//
//    public ResponseEntity<PedidoItem> editar(@Valid @RequestBody PedidoItem pedidoItem, @PathVariable Long id) {
//        return this.pedidoItemService.editar(pedidoItem, id);
//    }

    /* Método para deletar PedidoItem por ID */

//    @DeleteMapping("/{id}")
//    @ApiOperation(value = REMOVER_PEDIDO_ITEM, notes = NOTE_REMOVER_PEDIDO_ITEM)
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = RESPONSE_200),
//            @ApiResponse(code = 401, message = ERROR_401),
//			@ApiResponse(code = 403, message = ERROR_403),
//            @ApiResponse(code = 404, message = ERROR_404),
//			@ApiResponse(code = 505, message = ERROR_405)})
//
//    public ResponseEntity<Void> remover(@Valid @RequestBody PedidoItemID id) {
//        return this.pedidoItemService.remover(id);
//    }
}