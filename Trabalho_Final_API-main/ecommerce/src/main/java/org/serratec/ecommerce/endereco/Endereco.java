package org.serratec.ecommerce.endereco;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.CEP_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.COMPLEMENTO_NOTNULL;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank(message = CEP_NOTNULL)
    @Column(name = "cep", nullable = false)
    private String cep;

    @NotBlank(message = COMPLEMENTO_NOTNULL)
    @Column(name = "complemento", nullable = false)
    private String complemento;

    @NotBlank(message = "Número")
    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    @ApiModelProperty(hidden = true)
    private String bairro;

    @Column(name = "logradouro", nullable = false)
    @ApiModelProperty(hidden = true)
    private String logradouro;

    @Column(name = "localidade", nullable = false)
    @ApiModelProperty(hidden = true)
    private String localidade;

    @Column(name = "uf", nullable = false)
    @ApiModelProperty(hidden = true)
    private String uf;

    @Override
    public String toString() {
        return "\n\nEndereco: " +
                "\nCEP: " + cep +
                "\nComplemento: " + complemento +
                "\nNúmero: " + numero +
                "\nBairro: " + bairro +
                "\nLogradouro: " + logradouro +
                "\nLocalidade: " + localidade +
                "\nUF: " + uf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
