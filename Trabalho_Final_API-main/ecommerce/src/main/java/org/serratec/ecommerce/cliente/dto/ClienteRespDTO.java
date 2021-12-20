package org.serratec.ecommerce.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.cliente.Cliente;
import org.serratec.ecommerce.endereco.dto.EnderecoRespDTO;
import org.serratec.ecommerce.pedido.dto.PedidoRespDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteRespDTO {

    @Autowired
    private static ModelMapper modelMapper;

    @JsonProperty("Cliente ID")
    private Long id;

    @JsonProperty("E-mail")
    private String email;

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Sobrenome")
    private String sobrenome;

    @JsonProperty("Data de Nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("CPF")
    private String cpf;

    @JsonProperty("Endereço")
    private EnderecoRespDTO endereco;

    @JsonProperty("Pedidos")
    private Set<PedidoRespDTO> pedidoRespDTOS = new HashSet<>();

    //--------------------

    public ClienteRespDTO() {

    }

    public ClienteRespDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.dataNascimento = cliente.getDataNascimento();
        this.cpf = cliente.getCpf();
        this.endereco = modelMapper.map(cliente.getEndereco(), EnderecoRespDTO.class);
        this.pedidoRespDTOS = cliente.getPedidos().stream().map(pedido -> modelMapper.map(pedido, PedidoRespDTO.class)).collect(Collectors.toSet());
    }

    //--------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public EnderecoRespDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRespDTO endereco) {
        this.endereco = endereco;
    }

    public Set<PedidoRespDTO> getPedidoRespDTOS() {
        return pedidoRespDTOS;
    }

    public void setPedidoRespDTOS(Set<PedidoRespDTO> pedidoRespDTOS) {
        this.pedidoRespDTOS = pedidoRespDTOS;
    }

    @Override
    public String toString() {
        return "\n\nDados Cadastrais" +
                "\nEmail: '" + email +
                "\nNome: '" + nome +
                "\nSobrenome: '" + sobrenome +
                "\nData de Nascimento: " + dataNascimento +
                "\nCPF: '" + cpf +
                endereco +
                pedidoRespDTOS;
    }
}
