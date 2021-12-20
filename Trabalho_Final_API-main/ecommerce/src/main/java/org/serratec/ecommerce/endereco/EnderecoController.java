package org.serratec.ecommerce.endereco;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/{cep}")
    @ApiOperation(value = "Pesquisar Endereço por CEP", notes = "Pesquisar Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um Endereço"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Endereco> pesquisar(@PathVariable String cep){
        return this.enderecoService.pesquisar(cep);
    }

    /*--------------------*/

    @PostMapping
    @ApiOperation(value = "Inserir um Endereço", notes = "Inserir Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco inserir(@Valid @RequestBody Endereco endereco) {
        return this.enderecoService.inserir(endereco);
    }

    /*--------------------*/


    @PutMapping("/{id}")
    @ApiOperation(value = "Editar um Endereço por ID", notes = "Editar Endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um Endereço editado"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Endereco> editar(@PathVariable Long id, @RequestBody Endereco endereco) {
        return enderecoService.editar(id, endereco);
    }

    /*--------------------*/

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um endereço por ID", notes = "Deletar endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta um endereço"),
            @ApiResponse(code = 401, message = ERROR_401),
            @ApiResponse(code = 403, message = ERROR_403),
            @ApiResponse(code = 404, message = ERROR_404),
            @ApiResponse(code = 505, message = ERROR_405)
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return enderecoService.deletar(id);
    }
}
