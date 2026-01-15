/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoBloqueio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.CalculosAtividadeCRM.FRM_ETAPA_DISPARO_EMAIL;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.CalculosAtividadeCRM.FRM_ETAPA_GERAR_DOCUMENTO;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.CPPessoaJuridica;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public enum FabTipoBloqueio {

    DADOS_NAO_CADASTRADOS,
    NOME_NAO_CADASTRADO,
    SITE_NAO_CADASTRADO,
    SOLUCOES_NAO_CADASTRADAS,
    PRECISA_TER_IMAGENS,
    DADOS_NAO_REVISADOS,
    PLUGIN_DEMANDA,
    PRECISA_GERAR_DOCUMENTO,
    PRECISA_ENVIAR_EMAIL;

    public String gerarMensagemPorTipo(AtividadeCRM pAtividade) {

        switch (this) {
            case DADOS_NAO_CADASTRADOS:
                return "Atenção: Alguns dados precisam ser cadastrados para finalizar esta atividade!";

            case NOME_NAO_CADASTRADO:
                return "Você precisa cadastrar um nome para concluir";

            case SITE_NAO_CADASTRADO:
                return "Você precisa cadastrar um site para concluir";

            case PLUGIN_DEMANDA:
                return "Para concluir é nescessário" + pAtividade.getTipoAtividade().getAcaoDePLuginVunculado().getNome();

            case SOLUCOES_NAO_CADASTRADAS:
                return "Você precisa adicionar um serviço ao prospect.";

            case PRECISA_TER_IMAGENS:
                return "Uma imagem precisa ser cadastrado para este prospect.";
            case PRECISA_GERAR_DOCUMENTO:
                return "Um documento precisa ser gerado para finalizar a atividade";

            case PRECISA_ENVIAR_EMAIL:
                return "O e-mail precisa ser enviado para finalizar a atividade";
            case DADOS_NAO_REVISADOS:
                return "Revise os dados";

            default:
                throw new AssertionError(this.name());

        }

    }

    public ItfAcaoFormulario formularioPorBloqueio(AtividadeCRM pAtividade) {
        try {
            switch (this) {
                case DADOS_NAO_CADASTRADOS:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario();
                case NOME_NAO_CADASTRADO:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SIMPLES.getRegistro().getComoFormulario();

                case SITE_NAO_CADASTRADO:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario();

                case PLUGIN_DEMANDA:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_PLUGIN_DE_ATIVIDADE.getRegistro().getComoFormulario();
                case SOLUCOES_NAO_CADASTRADAS:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_SERVICOS.getRegistro().getComoFormulario();

                case PRECISA_TER_IMAGENS:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_LOGOMARCA.getRegistro().getComoFormulario();

                case PRECISA_GERAR_DOCUMENTO:
                    ItfAcaoFormulario acaoGerarDoc = (ItfAcaoFormulario) FRM_ETAPA_GERAR_DOCUMENTO.getValor(pAtividade);
                    if (acaoGerarDoc == null) {
                        throw new UnsupportedOperationException("Falha obtendo formulário para geração de documento");
                    }
                    return acaoGerarDoc;

                case PRECISA_ENVIAR_EMAIL:
                    ItfAcaoFormulario formDispararEmail = (ItfAcaoFormulario) FRM_ETAPA_DISPARO_EMAIL.getValor(pAtividade);
                    if (formDispararEmail == null) {
                        throw new UnsupportedOperationException("Erro obtendo formulario para disparo de email");
                    }
                    return formDispararEmail;
                case DADOS_NAO_REVISADOS:
                    return FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario();

                default:
                    throw new AssertionError(this.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo formulário de demanda de atividade do tipo" + this.toString(), t);
            return null;
        }

    }

    public boolean bloqueioLiberado(AtividadeCRM pAtividade) {
        try {

            switch (this) {
                case DADOS_NAO_CADASTRADOS:
                    if (pAtividade.getTipoAtividade().getRelacionamentoGerado() == null
                            && pAtividade.getTipoAtividade().getTiposDadosColetarNaAtividade().isEmpty()) {
                        return true;
                    }
                    if (pAtividade.getTipoAtividade().getRelacionamentoGerado() != null
                            && pAtividade.getTipoAtividade().getRelacionamentoGerado().getDadosNescessarios().isEmpty()) {
                        return true;
                    }
                    //bloqueia apenas se os dados não coletados forem obrigatórios
                    boolean possuiDadoNaoColetadoObrigatorio = pAtividade.getDadosNaoColetados().stream().filter(dado -> dado.getTipoDadoCRM().isObrigatorio()).findFirst().isPresent();
                    return !possuiDadoNaoColetadoObrigatorio;
                case NOME_NAO_CADASTRADO:

                    return true;

                case SITE_NAO_CADASTRADO:
                    if (!pAtividade.getTipoAtividade().isPrecisaTerSite()) {
                        return true;
                    }

                    return !UtilCRCStringValidador.isNuloOuEmbranco(pAtividade.getProspectoEmpresa().getComoPessoaJuridica().getCampoInstanciadoByNomeOuAnotacao(CPPessoaJuridica.site).getValor().toString());

                case PLUGIN_DEMANDA:

                    if (pAtividade.getTipoAtividade().getAcaoDePLuginVunculado() == null) {
                        return true;
                    }
                    return pAtividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.demandapluginfinalizada).getValorComoBoolean();

                case SOLUCOES_NAO_CADASTRADAS:

                    if (!pAtividade.getTipoAtividade().isPrecisaServicosDefinidos()) {
                        return true;
                    }
                    return !((pAtividade.getProspectoEmpresa().getCPinst(CPPessoa.servicos).getValor() == null) || (pAtividade.getProspectoEmpresa().getServicos().isEmpty()));

                case PRECISA_TER_IMAGENS:
                    if (!pAtividade.getTipoAtividade().isPrecisaTerImagem()) {
                        return true;
                    }
                    return pAtividade.getProspectoEmpresa().isTemImagemPequenaAdicionada();

                case PRECISA_GERAR_DOCUMENTO:

                    if (!pAtividade.getTipoAtividade().isTemModeloDocumentoVinculado()) {
                        return true;
                    }
                    if (pAtividade.getDocumentosGerados().isEmpty()) {
                        return false;
                    }
                    if (!pAtividade.getTipoAtividade().isPrecisaEnviarDocumento()) {
                        return true;
                    } else {
                        if (pAtividade.getTipoAtividade().isPrecisaEnviarDocumento()) {
                            if (pAtividade.getEmailVinculado() == null) {
                                return true;
                            }
                            FabStatusEnvioEmail statusMail = FabStatusEnvioEmail.getStatusByRegistro(pAtividade.getEmailComoEnvio().getStatusEnvio());
                            switch (statusMail) {
                                case RASCUNHO:
                                    return false;

                                case FORMATADO:
                                    return false;

                                case ENVIADO:
                                    return true;

                                case CONFIRMADO:
                                    return true;

                                default:
                                    throw new AssertionError(statusMail.name());

                            }
                        }
                    }

                    return (pAtividade.getDocumentosGerados().isEmpty());

                case PRECISA_ENVIAR_EMAIL:

                    if (!pAtividade.getTipoAtividade().isTemDisparoDeEmail()) {
                        return true;
                    }

                    if (pAtividade.getEmailVinculado() == null) {
                        return false;
                    }
                    return (pAtividade.getEmailComoEnvio().getStatusEnvio().equals(FabStatusEnvioEmail.ENVIADO.getRegistro()));
                case DADOS_NAO_REVISADOS:
                    if (pAtividade.isDadosRevisados()) {
                        return true;
                    } else if (!pAtividade.getTipoAtividade().getTiposDadosColetarNaAtividade().isEmpty()
                            || (pAtividade.getTipoAtividade().getRelacionamentoGerado() != null && !pAtividade.getTipoAtividade().getRelacionamentoGerado().getDadosNescessarios().isEmpty())) {
                        return false;
                    } else {
                        return true;
                    }

                default:
                    throw new AssertionError(this.name());

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando bloqueio", t);
            return false;
        }
    }

}
