package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.ModuloDemandasSincronizacao;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.UtilCRCDemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;

/**
 *
 * @author salvio
 */
public class ModuloCRMMauticSync extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_CTR_ATUALIZAR_MAUTIC)
    public static ItfRespostaAcaoDoSistema prospectoAtualizarMautic(final Pessoa pPessoaJuridica
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoaJuridica), pPessoaJuridica) {
            DemandaSinc demanda;

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    ModuloDemandasSincronizacao.processarDemanda(demanda);
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                demanda = UtilCRCDemandaSinc.gerarDemanda(pPessoaJuridica, MauticSincPessoa.class);
                demanda = atualizarEntidade(demanda);
            }

        }.getResposta();
    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema salvarModeloEmailMArketing(final ModeloEmailMarketing pModloMkt) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pModloMkt), pModloMkt) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    ItfRespostaAcaoDoSistema resp = enviarModeloEmailMktParaMautic((ModeloEmailMarketing) getRetorno());
                    if (!resp.isSucesso()) {
                        addAviso("Modelo Foi salvo mas não foi enviado para o Mautic");
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pModloMkt));
            }
        }.dispararMensagens();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_ATUALIZAR_COM_IA)
    public static ItfRespostaAcaoDoSistema enviarModeloEmailMktParaMautic(final ModeloEmailMarketing pModloMkt) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pModloMkt), pModloMkt) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ModeloEmailMarketing emailMkt = loadEntidade(pModloMkt);
                DemandaSinc demanda = UtilCRCDemandaSinc.gerarDemanda(emailMkt, MauticSincEmailMarketing.class);
                MauticSincEmailMarketing processador = (MauticSincEmailMarketing) UtilCRCDemandaSinc.gerarProcessador(demanda);
                UtilCRCDemandaSinc.iniciarEregistrarProcessamento(processador);
                processador.aguardarProcessamento();
                if (!processador.isProcessouComSucesso()) {
                    throw new ErroRegraDeNegocio("Falha sincronizando marketing");
                }
            }
        }.dispararMensagens();

    }

}
