package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Optional;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.ACAOFORMULARIOEXECUCAO)
public class ValorLogicoAtividadeCRMAcaoFormularioExecucao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMAcaoFormularioExecucao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        AtividadeCRM atividade = getAtividade();
        ItfAcaoFormulario acaopadrao = atividade.getTipoAtividade().getAcaoFormularioExecucao();
        atividade.setAcaoFormularioExecucao((ItfAcaoFormulario) acaopadrao);
        try {
            if (acaopadrao == null) {
                throw new UnsupportedOperationException("Impossível determinar a ação padrão para" + atividade.getTipoAtividade());
            }
            if (getAtividade().isTemCodigoRemoto()) {
                getAtividade().setAcaoFormularioExecucao((ItfAcaoFormulario) FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE.getRegistro());
                return getAtividade().getAcaoFormularioExecucao();
            }
            if (!atividade.getTipoAtividade().getUsuariosAutorizados().isEmpty()) {
                Optional<UsuarioCRM> usuarioLocadoEncontrado = atividade.getTipoAtividade().getUsuariosAutorizados().stream().filter(usuario -> usuario.equals(SBCore.getUsuarioLogado())).findFirst();
                if (!usuarioLocadoEncontrado.isPresent()) {
                    getAtividade().setAcaoFormularioExecucao((ItfAcaoFormulario) FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_GERADORA.getRegistro());
                    return getAtividade().getAcaoFormularioExecucao();
                }
            }

            if (atividade.isFoiAtividadeFinalizada() || atividade.getStatusAtividade().equals(FabStatusAtividade.CANCELADA.getRegistro())) {
                getAtividade().setAcaoFormularioExecucao((ItfAcaoFormulario) FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro());
                return getAtividade().getAcaoFormularioExecucao();
            }

            if (atividade.getAtividadeMesmoTipoAbertaUsuarioLogado() != null) {
                if (!atividade.isSobrescreverAcaoAtual()) {
                    getAtividade().setAcaoFormularioExecucao((ItfAcaoFormulario) FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SOBRESCREVER_ATIVIDADE.getRegistro());
                    return getAtividade().getAcaoFormularioExecucao();
                }
            }
            FabTipoBloqueio tipoBloqueioAtual = (FabTipoBloqueio) getAtividade().getCPinst(CPAtividadeCRM.tipobloqueio).getValor();
            if (tipoBloqueioAtual != null) {
                ItfAcaoFormulario acaoFormulario = (ItfAcaoFormulario) tipoBloqueioAtual.formularioPorBloqueio(atividade);
                getAtividade().setAcaoFormularioExecucao(acaoFormulario);
                return getAtividade().getAcaoFormularioExecucao();
            }
            getAtividade().setAcaoFormularioExecucao((ItfAcaoFormulario) acaopadrao);
            return getAtividade().getAcaoFormularioExecucao();
        } catch (Throwable t) {
            if (acaopadrao == null) {
                return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SIMPLES.getRegistro();
            }
            return getAtividade().getAcaoFormularioExecucao();
        }

    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
