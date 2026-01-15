package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.ComparadorPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.LEADSENCONTRADOS)
public class ValorLogicoPesquisaLeadLeadsEncontrados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadLeadsEncontrados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private String ultimoHashCalculado;

    @Override
    public synchronized Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            String novoHash = gerarHash();
            if (ultimoHashCalculado == null || !novoHash.equals(ultimoHashCalculado)) {

                boolean gerente = SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro());
                if (getPesquisa().getTipoPesquisa() == null) {
                    if (gerente) {
                        getPesquisa().setTipoPesquisa(FabTipoPesquisaLeads.LEADS_URGENTES.getRegistro());
                    } else {
                        getPesquisa().setTipoPesquisa(FabTipoPesquisaLeads.MEUS_LEADS.getRegistro());
                    }
                }

                FabTipoPesquisaLeads tipoPesquisa = getPesquisa().getTipoPesquisa().getTipoPesquisa();
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

                ConsultaDinamicaDeEntidade consultaPesquisa = new ConsultaDinamicaDeEntidade(Pessoa.class, em);
                if (getPesquisa().getOrigem() != null) {
                    if (getPesquisa().getOrigem() instanceof OrigemProspectoPrivado) {
                        getPesquisa().setTipoPesquisa(FabTipoPesquisaLeads.ORIGEM_PRIVADA.getRegistro());

                    } else {
                        getPesquisa().setTipoPesquisa(FabTipoPesquisaLeads.ORIGEM_PUBLICAS.getRegistro());

                    }
                }
                if (getPesquisa().getTagAtendimento() != null) {
                    consultaPesquisa.addCondicaoManyToManyContendoObjeto(CPPessoa.tagsatendimento, getPesquisa().getTagAtendimento());
                }

                switch (tipoPesquisa) {
                    case MEUS_LEADS:
                        break;
                    default:
                        if (getPesquisa().getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.datainicial).getValor() != null && getPesquisa().getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.datafinal).getValor() != null) {
                            consultaPesquisa.addCondicaoDataHoraMaiorOuIgualA(CPPessoa.datahoraultimainteracao, getPesquisa().getDatainicial());
                            if (!getPesquisa().getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.momentoatual).getValorComoBoolean()) {
                                consultaPesquisa.addCondicaoDataHoraMenorOuIgualA(CPPessoa.datahoraultimainteracao, UtilCRCDataHora.incrementaHoras(new Date(), 10));
                            }
                        }
                }

                if (getPesquisa().getTipoRelacionamento() != null) {
                    consultaPesquisa.addCondicaoManyToOneIgualA(getPesquisa().getTipoRelacionamento());
                } else {
                    if (getPesquisa().getMetaRelacionamento() != null) {
                        MetaRelacionamento meta = UtilSBPersistencia.loadEntidade(getPesquisa().getMetaRelacionamento(), em);
                        consultaPesquisa.addCondicaoManyToOneContemNoIntervalo(CPPessoa.relacionamento, meta.getTiposRelacionamento());
                    }
                }

                switch (tipoPesquisa) {
                    case MEUS_LEADS:
                        if (getPesquisa().getUsuario() == null) {
                            getPesquisa().setUsuario((UsuarioCRM) SBCore.getUsuarioLogado());
                        }
                        consultaPesquisa.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", getPesquisa().getUsuario());
                        setValorPorReflexao(consultaPesquisa.gerarResultados());
                        break;
                    case ORIGEM_PUBLICAS:
                    case ORIGEM_PRIVADA:
                        consultaPesquisa.addCondicaoManyToOneIgualA(getPesquisa().getOrigem());
                        setValorPorReflexao(consultaPesquisa.gerarResultados());
                        break;
                    case LEADS_URGENTES:
                        if (getPesquisa().getUsuario() == null) {
                            getPesquisa().setUsuario((UsuarioCRM) SBCore.getUsuarioLogado());
                        }
                        consultaPesquisa.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", getPesquisa().getUsuario());

                        List<Pessoa> listaCompleta = consultaPesquisa.resultadoRegistros();
                        List<Pessoa> listaComFiltro = new ArrayList<>();
                        listaCompleta.stream().filter(cp -> cp.getCampoInstanciadoByNomeOuAnotacao(CPPessoa.possuidemandaurgencia).getValorComoBoolean())
                                .forEach(listaComFiltro::add);
                        getPesquisa().setLeadsEncontrados(listaComFiltro);
                        break;
                    case PESQUISA_LIVRE:
                        if (UtilCRCStringValidador.isNuloOuEmbranco(getPesquisa().getTermoPesquisa())) {
                            SBCore.enviarAvisoAoUsuario("Defina o termo da pesquisa");
                            setValorPorReflexao(new ArrayList<>());
                        } else {
                            setValorPorReflexao(consultaPesquisa.resultadoRegistros());
                        }
                        break;
                    default:
                        throw new AssertionError(tipoPesquisa.name());

                }
                ultimoHashCalculado = novoHash;
            }
            int limite = 10000;

            String termo;
            if (!UtilCRCStringValidador.isNuloOuEmbranco(getPesquisa().getTermoPesquisa())) {
                limite = 100;
                termo = getPesquisa().getTermoPesquisa().toLowerCase();
            } else {
                termo = null;
            }
            List<Pessoa> prospectosListados = new ArrayList<>();
            List<ComparadorPessoa> comparadores = new ArrayList<>();
            if (termo != null && !termo.isEmpty()) {
                FabTipoPesquisaGenerica tipo = FabTipoPesquisaGenerica.getTipoPesquisaByTermo(termo);
                getPesquisa().getLeadsEncontrados().forEach(prospecto -> comparadores.add(new ComparadorPessoa(prospecto, termo, tipo)));
            } else {
                getPesquisa().getLeadsEncontrados().forEach(prospecto -> comparadores.add(new ComparadorPessoa(prospecto, null, FabTipoPesquisaGenerica.PERSONALIZADA)));
            }

            Collections.sort(comparadores);

            comparadores.stream().limit(limite).forEachOrdered(item -> prospectosListados.add(item.getObjetoAnalizado()));

            getPesquisa().setLeadsEncontrados(prospectosListados);
            setCacheSegundosPadrao(3);
        }
        return getPesquisa().getLeadsEncontrados();
    }

    public PesquisaLead getPesquisa() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }

    public String gerarHash() {
        String nomeHash = getPesquisa().getDatainicial() + "-" + getPesquisa().getDatafinal()
                + getPesquisa().getRelacionamento() + getPesquisa().getMetaRelacionamento() + getPesquisa().getUsuario() + getPesquisa().getTermoPesquisa();
        String hash = String.valueOf(nomeHash.hashCode());
        return hash;
    }

}
