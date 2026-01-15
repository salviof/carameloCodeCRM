/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.transacionalMkt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Email Mkt Transacional", plural = "Emails de Marketing Transacionais")
public class EmailTransacionalMkt extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Pessoa pessoa;

    @InfoCampo()
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraDisparo;

    @ManyToOne
    @Temporal(TemporalType.TIMESTAMP)
    private UsuarioCRM usuarioDisparo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean foiEnviado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean foiLido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraLeitura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String iconeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDatahoraDisparo() {
        return datahoraDisparo;
    }

    public void setDatahoraDisparo(Date datahoraDisparo) {
        this.datahoraDisparo = datahoraDisparo;
    }

    public UsuarioCRM getUsuarioDisparo() {
        return usuarioDisparo;
    }

    public void setUsuarioDisparo(UsuarioCRM usuarioDisparo) {
        this.usuarioDisparo = usuarioDisparo;
    }

    public boolean isFoiEnviado() {
        return foiEnviado;
    }

    public void setFoiEnviado(boolean foiEnviado) {
        this.foiEnviado = foiEnviado;
    }

    public boolean isFoiLido() {
        return foiLido;
    }

    public void setFoiLido(boolean foiLido) {
        this.foiLido = foiLido;
    }

    public Date getDataHoraLeitura() {
        return dataHoraLeitura;
    }

    public void setDataHoraLeitura(Date dataHoraLeitura) {
        this.dataHoraLeitura = dataHoraLeitura;
    }

    public String getIconeStatus() {
        return iconeStatus;
    }

    public void setIconeStatus(String iconeStatus) {
        this.iconeStatus = iconeStatus;
    }

}
