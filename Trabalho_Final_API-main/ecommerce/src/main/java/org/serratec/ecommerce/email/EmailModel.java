package org.serratec.ecommerce.email;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_EMAIL")
public class EmailModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;

    private String proprietario;
    private String emailDe;
    private String emailPara;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String corpoMensagem;

    private LocalDateTime dataDeEnvio;
    private StatusDeEnvio statusDoEmail;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getEmailDe() {
        return emailDe;
    }

    public void setEmailDe(String emailDe) {
        this.emailDe = emailDe;
    }

    public String getEmailPara() {
        return emailPara;
    }

    public void setEmailPara(String emailPara) {
        this.emailPara = emailPara;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(String corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public LocalDateTime getDataDeEnvio() {
        return dataDeEnvio;
    }

    public void setDataDeEnvio(LocalDateTime dataDeEnvio) {
        this.dataDeEnvio = dataDeEnvio;
    }

    public StatusDeEnvio getStatusDoEmail() {
        return statusDoEmail;
    }

    public void setStatusDoEmail(StatusDeEnvio statusDoEmail) {
        this.statusDoEmail = statusDoEmail;
    }

}
