package org.serratec.ecommerce.endereco;

import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final String API_CEP = "https://viacep.com.br/ws/";

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public ResponseEntity<Endereco> pesquisar(String cep) {
        Optional<Endereco> endereco = this.enderecoRepository.findByCep(cep);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Endereco> editar(Long id, Endereco endereco) {
        if (!this.enderecoRepository.existsById(id)) {
            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
                    EDICAO_FALHOU,
                    Arrays.asList(EDICAO_ENDERECO_FALHOU + id)));
        }

        try {
            endereco.setId(id);
            endereco = this.enderecoRepository.save(endereco);
            return ResponseEntity.ok(endereco);
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EDICAO_FALHOU,
                    Arrays.asList(EDICAO_ENDERECO_FALHOU + endereco.getCep())));
        }
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        if (!this.enderecoRepository.existsById(id)) {
            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
                    EXCLUSAO_FALHOU,
                    Arrays.asList(PESQUISA_ENDERECO_FALHOU + id)));
        }

        try {
            this.enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EXCLUSAO_FALHOU,
                    Arrays.asList(PESQUISA_ENDERECO_FALHOU + id)));
        }
    }

    @Override
    public Endereco inserir(Endereco endereco) throws GeneralException{

        if (endereco == null){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_CLIENTE_FALHOU + endereco.getCep())));
        }

        Endereco enderecoValido = buscar(endereco.getCep()).getBody();
        if (enderecoValido != null){
            try {
                enderecoValido.setNumero(endereco.getNumero());
                enderecoValido.setComplemento(endereco.getComplemento());
                return enderecoValido;
            }
            catch (IllegalArgumentException ex){
                throw new GeneralException(new ErroResposta(
                        HttpStatus.BAD_REQUEST,
                        INSERCAO_FALHOU,
                        Arrays.asList(INSERCAO_CLIENTE_FALHOU + endereco.getCep())));
            }
        }
        else{
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_ENDERECO_FALHOU + endereco.getCep())));
        }
    }

    public ResponseEntity<Endereco> buscar(String cep) throws HttpClientErrorException{
        Optional<Endereco> endereco = enderecoRepository.findByCep(cep);

        if (endereco.isPresent()){
            return ResponseEntity.ok(endereco.get());
        }
        else{
            try {
                RestTemplate restTemplate = new RestTemplate();
                String uri = API_CEP + cep + "/json/";
                Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));

                if (enderecoViaCep.get().getCep() != null){
                    String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                    enderecoViaCep.get().setCep(cepSemTraco);
                    return ResponseEntity.ok(enderecoViaCep.get());
                }
                else{
                    return null;
                }
            }catch (HttpClientErrorException ex){
                throw new GeneralException(new ErroResposta(
                        HttpStatus.BAD_REQUEST,
                        INSERCAO_FALHOU,
                        Arrays.asList(PESQUISA_CEP_FALHOU + cep)));
            }
        }
    }
}
