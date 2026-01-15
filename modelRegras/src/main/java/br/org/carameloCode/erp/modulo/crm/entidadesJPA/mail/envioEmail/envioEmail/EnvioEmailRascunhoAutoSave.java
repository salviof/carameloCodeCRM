/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(plural = "Rascunhos de Emails", tags = "Mail Rascunho", descricao = "Rascunhos de e-mail")
public class EnvioEmailRascunhoAutoSave extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @ManyToOne
    private Pessoa prospecto;
    @ManyToOne
    private UsuarioSB usuario;

    @ManyToOne(targetEntity = EnvioEmail.class, optional = false)
    private EnvioEmail emailVinculado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Texto do Email")
    @Column(length = 8000, columnDefinition = "Text")
    private String texto = "";
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Assunto do e-mail", valorMinimo = 10, obrigatorio = true)
    @Min(10)
    private String assunto;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = EnvioEmail.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros); //To change body of generated methods, choose Tools | Templates.
        EnvioEmail email = getParametroInicialEnviado(EnvioEmail.class, parametros);
        setEmailVinculado(email);
        setTexto(email.getTexto());
        setAssunto(email.getAssunto());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(PessoaJuridica prospecto) {
        this.prospecto = prospecto;
    }

    public UsuarioSB getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSB usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        if (texto == null) {
            return "";
        }
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public EnvioEmail getEmailVinculado() {
        return emailVinculado;
    }

    public void setEmailVinculado(EnvioEmail emailVinculado) {
        this.emailVinculado = emailVinculado;
    }

}
