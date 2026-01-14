/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.ItfCalculosJPA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum CalculosAtividadeCRM implements ItfCalculosJPA {

    //DOCUMENTO_FOI_ENVIADO,
    ///DOCUMENTO_FOI_ENTEGUE,
    //   DOCUMENTO_GERADO,
    //FORMULARIO_EXECUCAO,
    //ATIVIDADE_MESMO_TIPO_USUAIRO_LOGADO,
    //PERMITIDO_CONCLUIR,
    //  TIPO_BLOQUEIO_CONCLUSAO,
    FRM_ETAPA_DISPARO_EMAIL,
    FRM_ETAPA_GERAR_DOCUMENTO,
    //ETAPAS_RESTANTES,
    // BLOQUEIOS_RESTANTES,
    POSSUI_ETAPAS_PENDENTES;

    @Override
    public Object getValor(Object... pParamentros) {
        AtividadeCRM atividade = null;
        try {
            atividade = (AtividadeCRM) pParamentros[0];
            switch (this) {

                //  case DOCUMENTO_FOI_ENTEGUE:
                //      break;
                //case DOCUMENTO_GERADO:
                //          case FORMULARIO_EXECUCAO:
                //            return atividade.getAcaoFormularioExecucao().getComoFormulario().getXhtml();
                //if (atividade.getTipoAtividade().isTemFormularioAlternativo()) {
                //  return atividade.getTipoAtividade().getXhtmlAlternativo();
                //} else {
                //  return atividade.getAcaoFormularioExecucao().getComoFormulario().getXhtml();
                //}
                // case ACAO_FORMULARIO_EXECUCAO:
                //        case ATIVIDADE_MESMO_TIPO_USUAIRO_LOGADO:
                //      case PERMITIDO_CONCLUIR:
                //   case TIPO_BLOQUEIO_CONCLUSAO:
                case FRM_ETAPA_DISPARO_EMAIL:

                    if (atividade.getTipoAtividade().isTemDisparoDeEmail()) {

                        return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL.getRegistro();
                    }

                    return atividade.getTipoAtividade().getAcaoFormularioExecucao();

                case FRM_ETAPA_GERAR_DOCUMENTO:
                    if (atividade.getTipoAtividade().isTemModeloDocumentoVinculado()) {

                        if (!atividade.isDocumentoGerado()) {
                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_GERAR.getRegistro();
                        } else {

                            if (!atividade.isDocumentoEnviado() && atividade.getTipoAtividade().isPrecisaEnviarDocumento()) {

                                if (atividade.getEmailVinculado() != null) {
                                    FabStatusEnvioEmail statusEnvioMailAtividade = FabStatusEnvioEmail.getStatusByRegistro(atividade.getEmailComoEnvio().getStatusEnvio());
                                    if (statusEnvioMailAtividade == null) {
                                        return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_FORMATAR.getRegistro();
                                    }
                                    switch (statusEnvioMailAtividade) {
                                        case RASCUNHO:
                                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_FORMATAR.getRegistro();

                                        case FORMATADO:
                                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_ENVIAR.getRegistro();

                                        case ENVIADO:
                                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_RELATORIO.getRegistro();

                                        case CONFIRMADO:
                                            return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_RELATORIO.getRegistro();

                                        default:
                                            throw new AssertionError(statusEnvioMailAtividade.name());

                                    }
                                }

                            }
                        }
                    }
                    return atividade.getTipoAtividade().getAcaoFormularioExecucao();
                //   case ETAPAS_RESTANTES:

                case POSSUI_ETAPAS_PENDENTES:

                //  case BLOQUEIOS_RESTANTES:
                default:
                    throw new AssertionError(this.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Calculo de valor em " + this.toString(), t);
            if (atividade != null) {
                return atividade.getTipoAtividade().getAcaoFormularioExecucao();
            }
            return null;
        }

    }

}
