package org.serratec.ecommerce.endereco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.CEP_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.COMPLEMENTO_NOTNULL;

public class EnderecoRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = CEP_NOTNULL)
    @JsonProperty("CEP")
    private String cep;

    @NotBlank(message = COMPLEMENTO_NOTNULL)
    @JsonProperty("Complemento")
    private String complemento;

    @NotBlank(message = "Número")
    @JsonProperty("Número")
    private String numero;

    @NotBlank(message = "Número")
    @JsonProperty("Bairro")
    private String bairro;

    @NotBlank(message = "Número")
    @JsonProperty("Logradouro")
    private String logradouro;

    @NotBlank(message = "Número")
    @JsonProperty("Localidade")
    private String localidade;

    @NotBlank(message = "Número")
    @JsonProperty("UF")
    private String uf;

    @Override
    public String toString() {
        return "\n\nEndereco: \nCEP: " + cep +
                "\nComplemento: " + complemento +
                "\nNumero: " + numero +
                "\nBairro: " + bairro +
                "\nLogradouro: " + logradouro +
                "\nLocalidade: " + localidade +
                "\nUF: " + uf + "\n";
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
