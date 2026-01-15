/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.codigoAcesso;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salviofurbino
 * @since 21/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = {"Convite agendamento"}, plural = "Convites de Agendamentos")
public class CodigoConviteAgendamento extends CodigoConvite {

    private boolean foiagendado;
    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    private TipoAtividadeCRM tipoAtividade;
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioCRM;

    public boolean isFoiagendado() {
        return foiagendado;
    }

    public TipoAtividadeCRM getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividadeCRM tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public UsuarioCRM getUsuarioCRM() {
        return usuarioCRM;
    }

    public void setUsuarioCRM(UsuarioCRM usuarioCRM) {
        this.usuarioCRM = usuarioCRM;
    }

}
