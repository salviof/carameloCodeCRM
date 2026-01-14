/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salviofurbino
 * @since 21/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = "Código para convite", plural = "Codigos de convite para atividade")
public class CodigoConviteAtividade extends CodigoConvite {

    @ManyToOne(targetEntity = AtividadeCRM.class)
    private AtividadeCRM atividade;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = AtividadeCRM.class)
    public void prepararNovoObjeto(Object... parametros) {
        try {
            super.prepararNovoObjeto(parametros); //To change body of generated methods, choose Tools | Templates.
            AtividadeCRM pAtividade = getParametroInicialEnviado(AtividadeCRM.class, parametros);
            atividade = pAtividade;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando código de convite", t);
        }
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
    }

    @Override
    public boolean isConviteFoiEnviado() {
        return getAtividade().isDocumentoEnviado();

    }

}
