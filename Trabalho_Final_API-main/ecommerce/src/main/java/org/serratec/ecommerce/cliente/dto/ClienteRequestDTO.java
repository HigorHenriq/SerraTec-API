package org.serratec.ecommerce.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.ecommerce.cliente.Cliente;
import org.serratec.ecommerce.endereco.Endereco;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.*;

public class ClienteRequestDTO {

    @Email
    @NotBlank(message = "O campo 'email' não pode ser vazio.")
    @JsonProperty("E-mail")
    private String email;

    @NotBlank(message = NOME_NOTNULL)
    @Size(max = 50, message = "Nome não pode ultrapassar {max} Letras")
    @JsonProperty("Nome")
    private String nome;

    @NotBlank(message = "Sobrenome não pode ser nulo")
    @Size(max = 60, message = "Sobrenome não pode ultrapassar {max} Letras")
    @JsonProperty("Sobrenome")
    private String sobrenome;

    @Past(message = "A data deve ser anterior à data atual.")
    @NotNull(message = DATA_NOTNULL)
    @JsonProperty("Data de Nascimento")
    private LocalDate dataNascimento;

    @CPF
    @ApiModelProperty(value = "CPF do cliente")
    @JsonProperty("CPF")
    private String cpf;

    @NotNull(message = ENDERECO_NOTNULL)
    @ApiModelProperty(value = "Endereços do cliente")
    @JsonProperty("Endereço")
    @Valid
    private Endereco endereco;

    //--------------------

    public ClienteRequestDTO() {

    }

    public ClienteRequestDTO(Cliente cliente) {
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.dataNascimento = cliente.getDataNascimento();
        this.cpf = cliente.getCpf();
        this.endereco = cliente.getEndereco();
    }

    //--------------------

//    public static Set<Cliente> toEntity(List<ClienteRespDTO> clientesDTO){
//        return clientesDTO.stream().map(Cliente::new).collect(Collectors.toSet());
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
