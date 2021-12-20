package org.serratec.ecommerce.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.ecommerce.endereco.Endereco;
import org.serratec.ecommerce.pedido.Pedido;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;
import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.*;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    @ApiModelProperty(hidden = true)
    @JsonProperty("Cliente ID")
    private Long id;

    @Email
    @Column(name = "email", nullable = false)
    @NotBlank(message = EMAIL_NOTNULL)
    @JsonProperty("E-mail")
    private String email;

    @NotBlank(message = NOME_NOTNULL)
    @Column(name = "nome", nullable = false)
    @Size(max = 50, message = "Nome não pode ultrapassar {max} Letras")
    @JsonProperty("Nome")
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    @NotBlank(message = SOBRENOME_NOTNULL)
    @Size(max = 60, message = "Sobrenome não pode ultrapassar {max} Letras")
    @JsonProperty("Sobrenome")
    private String sobrenome;

    //@Future - Para datas no futuro
    @Past(message = DATA_ANTERIOR)
    @NotNull(message = DATA_NOTNULL)
    @Column(name = "data_nascimento", nullable = false)
    @JsonProperty("Data de Nascimento")
    private LocalDate dataNascimento;

    @CPF
    @Column(name = "cpf", nullable = false)
    @ApiModelProperty(value = "CPF do cliente")
    @JsonProperty("CPF")
    private String cpf;

    @NotNull(message = ENDERECO_NOTNULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", nullable = false)
    @ApiModelProperty(value = "Endereços do cliente")
    @JsonProperty("Endereço")
    @Valid
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(hidden = true)
    @JsonProperty("Pedidos")
    private Set<Pedido> pedidos = new HashSet<>();


    /*-----------------*/

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Pedido> getPedidos() { return pedidos; }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
