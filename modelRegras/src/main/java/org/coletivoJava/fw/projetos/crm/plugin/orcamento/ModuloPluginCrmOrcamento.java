/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.crm.plugin.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.Optional;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
public class ModuloPluginCrmOrcamento extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMPluginOrcamento(acao = FabAcaoOrcamento.ORCAMENTO_ATV_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema atividadeOrcamentoSalvarMerge(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                AtividadeCRM atividadeAtualizada = loadEntidade(pAtividade);
                if (pAtividade.getOrcamento() == null) {
                    throw new ErroRegraDeNegocio("O orçamento não foi definido");
                }
                if (pAtividade.getOrcamento().getServicoOferecido().isEmpty()) {
                    throw new ErroRegraDeNegocio("Os itens do orçamento não foram definidos");
                }
                atividadeAtualizada.setDemandaPluginFinalizada(true);
                atividadeAtualizada.setOrcamento(atualizarEntidade(pAtividade.getOrcamento()));
                atividadeAtualizada = atualizarEntidade(atividadeAtualizada);

                Pessoa pessoa = atividadeAtualizada.getProspectoEmpresa();
                pessoa.setUltimoOrcamento(atividadeAtualizada.getOrcamento());
                atualizarEntidade(pessoa);
            }
        };
    }

    @InfoAcaoCRMPluginOrcamento(acao = FabAcaoOrcamento.ORCAMENTO_ATV_CTR_NOVO)
    public static ItfRespostaAcaoDoSistema atividadeOrcamentoCriarNovo(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividadeAtualizada = loadEntidade(pAtividade);
                //   if (atividadeAtualizada.getOrcamento() == null) {
                //      //  throw new ErroRegraDeNegocio("O orçamento já se encontra como um novo orçamento");
                //   }
                //  if (atividadeAtualizada.getOrcamento().getServicoOferecido().isEmpty()) {
                //        throw new ErroRegraDeNegocio("Os itens do orçamento não foram definidos");
                //    }
                Optional<Orcamento> localizaOrcamento = atividadeAtualizada.getProspectoEmpresa().getOrcamentos().stream().filter(orc -> orc.getServicoOferecido().isEmpty()).findFirst();
                Orcamento novoOrcamento = new Orcamento();
                if (localizaOrcamento.isPresent()) {
                    novoOrcamento = localizaOrcamento.get();
                    addAviso("Um novo orçamento já havia sido criado, utilize o orçamento No" + novoOrcamento.getId());
                } else {

                    try {
                        novoOrcamento.prepararNovoObjeto(atividadeAtualizada.getProspectoEmpresa());
                    } catch (ErroPreparandoObjeto ex) {
                        throw new ErroRegraDeNegocio("Erro iniciando novo orçamento" + ex.getMessage());
                    }
                    novoOrcamento = atualizarEntidade(novoOrcamento);
                }
                atividadeAtualizada.setDemandaPluginFinalizada(false);
                atividadeAtualizada.setOrcamento(novoOrcamento);
                atividadeAtualizada = atualizarEntidade(atividadeAtualizada);

            }
        };
    }

}
