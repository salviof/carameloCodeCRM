/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Documento Enviado"}, plural = "Documentos enviados")
public class EnvioEmailAtividade extends EnvioEmail {

    @ManyToOne(targetEntity = AtividadeCRM.class, cascade = CascadeType.ALL)
    private AtividadeCRM atividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean subAtividadeAgendadaAposLeituraEmail;

    @Deprecated
    public EnvioEmailAtividade() {

    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {AtividadeCRM.class})
    public void prepararNovoObjeto(Object... parametros) {
        try {
            atividade = getParametroInicialEnviado(AtividadeCRM.class, parametros);
            setProspecto(atividade.getProspectoEmpresa());
            if (atividade.getTipoAtividade().getModeloEmail() != null) {
                setModeloEmail(atividade.getTipoAtividade().getModeloEmail());
            }
            setStatusEnvio(FabStatusEnvioEmail.RASCUNHO.getRegistro());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando objeto: " + EnvioEmailAtividade.class.getSimpleName(), t);
        }
        setUmEmailDeAtividade(true);
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
    }

    public void atualizarDocumentosDaAtividade() {
        getArquivosAnexados().clear();
        for (DocumentoAtividadeCRM doc : getAtividade().getDocumentosGerados()) {
            getArquivosAnexados().add(doc);
        }
    }

    public boolean isSubAtividadeAgendadaAposLeituraEmail() {
        return subAtividadeAgendadaAposLeituraEmail;
    }

    public void setSubAtividadeAgendadaAposLeituraEmail(boolean subAtividadeAgendadaAposLeituraEmail) {
        this.subAtividadeAgendadaAposLeituraEmail = subAtividadeAgendadaAposLeituraEmail;
    }

}
