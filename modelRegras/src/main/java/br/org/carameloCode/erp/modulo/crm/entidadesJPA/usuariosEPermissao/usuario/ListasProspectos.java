/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.ItfListasJPA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ParametroLista;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.listas.InfoLista;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.LazyInitializationException;

/**
 *
 * @author desenvolvedor
 */
public enum ListasProspectos implements ItfListasJPA {

    MEUS_PROSPECTOS,
    MEUS_PROSPECTOS_COM_ATIVIDADES_PENDENTES,
    MEUS_PROSPECTOS_EM_NEGOCIACAO,
    MEUS_PROSPECTOS_ATIVOS,
    @InfoLista(parametros = {
        @ParametroLista(classe = OrigemProspecto.class, nomeParametro = "Origem prospecto", valorPadrao = "")
    }, descricao = "", nomeParametro = "Tipo relacionamento", tipoObjetoListado = Pessoa.class)
    PROSPECTOS_DESTA_PROGIEM,
    @InfoLista(parametros = {
        @ParametroLista(classe = TipoRelacionamento.class, nomeParametro = "Tipo Relacionamento", valorPadrao = ""),
        @ParametroLista(classe = OrigemProspecto.class, nomeParametro = "Origem prospecto", valorPadrao = "")
    }, descricao = "", nomeParametro = "Tipo relacionamento", tipoObjetoListado = Pessoa.class)
    PROSPECTOS_DESTE_RELACIONAMENTO_NESTA_ORIGEM,
    @InfoLista(parametros = {
        @ParametroLista(classe = TipoRelacionamento.class, nomeParametro = "Tipo Relacionamento", valorPadrao = "")},
            descricao = "", nomeParametro = "Tipo relacionamento", tipoObjetoListado = Pessoa.class)
    PROSPECTOS_DESTE_RELACIONAMENTO,
    ATIVIDADES_PENDENTES,
    @InfoLista(parametros = {
        @ParametroLista(classe = AtividadeCRM.class, nomeParametro = "Atividade", valorPadrao = "")}, descricao = "", nomeParametro = "Atividade")
    DADOS_NAO_COLETADOS,
    RELACIONAMENTOS_COM_DADOS_COLETADOS,
    DADOS_COLETADOS_POR_TIPO_RELACIONAMENTO,
    DADOS_COLETADOS_POR_TIPO_ATIVIDADE,
    DADOS_COLETADOS_NAO_VINCULADOS;

    public List getLista(EntityManager pEm, Object... pParametros) {
        return getListagemPorCriteriaAPI(pEm, pParametros);
    }

    @Override
    public List getLista(Object... pParametros) {
        Pessoa prosp = (Pessoa) pParametros[0];
        switch (this) {

            case MEUS_PROSPECTOS:
                break;

            case MEUS_PROSPECTOS_COM_ATIVIDADES_PENDENTES:

                break;

            case MEUS_PROSPECTOS_EM_NEGOCIACAO:
                break;

            case MEUS_PROSPECTOS_ATIVOS:
                break;
            case ATIVIDADES_PENDENTES:
                break;
            case DADOS_NAO_COLETADOS:

                List<DadoCRM> dadosNaoColetados = new ArrayList<>();
                try {
                    if (pParametros.length > 1) {
                        if (pParametros[1] instanceof AtividadeCRM) {

                        }

                        if (pParametros[1] instanceof TipoRelacionamento) {

                        }
                        AtividadeCRM atividadeCRM = (AtividadeCRM) pParametros[1];

                        List<TipoDadoCRM> dadosnescessarios = new ArrayList<>();
                        if (atividadeCRM.getTipoAtividade().getRelacionamentoGerado() != null) {
                            atividadeCRM.getTipoAtividade().getRelacionamentoGerado().getDadosNescessarios().forEach(dadosnescessarios::add);
                        }
                        atividadeCRM.getTipoAtividade().getTiposDadosColetarNaAtividade().stream().filter(ta -> !dadosnescessarios.contains(ta)).forEach(dadosnescessarios::add);

                        for (TipoDadoCRM tipoDado : dadosnescessarios) {

                            DadoCRM dadoCRM = FabDadoCRM.getNovoDadoDeAtividade(tipoDado.getValorPadrao(), atividadeCRM, tipoDado);

                            dadoCRM.setTipoRelacionamentoVinculado(atividadeCRM.getTipoAtividade().getRelacionamentoGerado());
                            dadoCRM.setProspectoEmpresa(prosp);
                            if (!prosp.getDadosColetados().contains(dadoCRM)) {
                                try {
                                    if (!UtilCRCStringValidador.isNuloOuEmbranco(dadoCRM.getTipoDadoCRM().getCampoProspectoCorrespondente())) {
                                        if (prosp.getCampoInstanciadoByNomeOuAnotacao(dadoCRM.getTipoDadoCRM().getCampoProspectoCorrespondente()).getFabricaTipoAtributo().equals(dadoCRM.getCampoInstanciado().getFabricaTipoAtributo())) {
                                            Object valor = prosp.getCampoInstanciadoByNomeOuAnotacao(tipoDado.getCampoProspectoCorrespondente()).getValor();
                                            dadoCRM.getCampoInstanciado().setValor(valor);
                                        }
                                    }
                                } catch (Throwable t) {
                                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro vinculando campo " + tipoDado.getCampoProspectoCorrespondente(), t);
                                }

                                dadosNaoColetados.add(dadoCRM);
                            }
                        }

                    }

                } catch (LazyInitializationException t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O entitymanager do prospecto ou atividade foi finalizado, impossível obter os dados", t);
                } catch (Throwable t) {

                }

                return dadosNaoColetados;
            case RELACIONAMENTOS_COM_DADOS_COLETADOS:

                List<TipoRelacionamento> listaRelacionamentosComDados = new ArrayList();
                for (DadoCRM dado : prosp.getDadosColetados()) {
                    if (dado.getAtividadeCRM() != null) {
                        if (dado.getAtividadeCRM().getTipoAtividade().getRelacionamentoGerado() != null) {
                            if (!listaRelacionamentosComDados.contains(dado.getAtividadeCRM().getTipoAtividade().getRelacionamentoGerado())) {
                                listaRelacionamentosComDados.add(dado.getAtividadeCRM().getTipoAtividade().getRelacionamentoGerado());
                            }
                        }
                    } else {
                        if (dado.getTipoRelacionamentoVinculado() != null) {
                            if (!listaRelacionamentosComDados.contains(dado.getTipoRelacionamentoVinculado())) {
                                listaRelacionamentosComDados.add(dado.getTipoRelacionamentoVinculado());
                            }
                        }
                    }
                }
                return listaRelacionamentosComDados;
            case DADOS_COLETADOS_NAO_VINCULADOS:
                List<DadoCRM> dadosDesvinculados = new ArrayList();
                for (DadoCRM dado : prosp.getDadosColetados()) {
                    if (dado.getAtividadeCRM() != null) {
                        if (dado.getAtividadeCRM().getRelacionamentoGerado() != null) {
                            dadosDesvinculados.add(dado);
                        }
                    } else {
                        dadosDesvinculados.add(dado);
                    }
                }

                return dadosDesvinculados;
            case DADOS_COLETADOS_POR_TIPO_RELACIONAMENTO:

                if (pParametros.length < 2 || pParametros[1] == null) {
                    return ListasProspectos.DADOS_COLETADOS_NAO_VINCULADOS.getLista(pParametros);
                }
                TipoRelacionamento prRelacionamento = (TipoRelacionamento) pParametros[1];

                List<DadoCRM> dadosPorRelacionamento = new ArrayList();
                for (DadoCRM dado : prosp.getDadosColetados()) {

                    if (dado.getAtividadeCRM() != null) {
                        if (dado.getTipoDadoCRM() != null) {
                            if (dado.getAtividadeCRM().getTipoAtividade().getRelacionamentoGerado().equals(prRelacionamento)) {
                                dadosPorRelacionamento.add(dado);
                            }
                        }
                    } else {
                        if (dado.getTipoRelacionamentoVinculado() != null) {
                            if (dado.getTipoRelacionamentoVinculado().equals(prRelacionamento)) {
                                dadosPorRelacionamento.add(dado);
                            }
                        }

                    }

                }
                return dadosPorRelacionamento;
            case PROSPECTOS_DESTA_PROGIEM:

                break;
            case PROSPECTOS_DESTE_RELACIONAMENTO_NESTA_ORIGEM:
                break;
            case PROSPECTOS_DESTE_RELACIONAMENTO:
                break;
            case DADOS_COLETADOS_POR_TIPO_ATIVIDADE:
                if (pParametros.length < 2 || pParametros[1] == null) {
                    return ListasProspectos.DADOS_COLETADOS_NAO_VINCULADOS.getLista(pParametros);
                }
                List<DadoCRM> dadosColetadosPorTipoAcao = new ArrayList();
                TipoAtividadeCRM tipoAtividade = (TipoAtividadeCRM) pParametros[1];

                prosp.getDadosColetados().stream().filter(dado
                        -> tipoAtividade.
                                getTiposDadosColetarNaAtividade()
                                .stream()
                                .filter(td -> td.getId().equals(dado.getTipoDadoCRM().getId()))
                                .findFirst().isPresent())
                        .forEach(dadosColetadosPorTipoAcao::add);

                if (tipoAtividade.getRelacionamentoGerado() != null) {
                    prosp.getDadosColetados().stream().filter(dado
                            -> tipoAtividade.getRelacionamentoGerado().getDadosNescessarios().stream().filter(dn -> dn.getId() == dado.getTipoDadoCRM().getId()).findFirst().isPresent())
                            .forEach(dadosColetadosPorTipoAcao::add);
                }
                return dadosColetadosPorTipoAcao;

            default:

                throw new AssertionError(this.name());

        }

        return null;

    }

    @Override
    public Class getClasse() {
        return Pessoa.class;
    }

}
