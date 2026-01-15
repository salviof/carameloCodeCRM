/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.dashboardAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItemGraficoTotalPorTipo;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.GraficoPizza;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.UtilGraficoPrimefaces;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.pie.PieChartModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public class GraficoPizzaOrigem {

    private OrigemProspecto origem;

    private Map<Integer, MetaRelacionamento> metasDaOrigem = new HashMap<>();

    private List<MetaRelacionamento> metas;
    private GraficoPizza grafico;

    public GraficoPizzaOrigem(List<MetaRelacionamento> pMetas, OrigemProspecto pOrigem) {
        origem = pOrigem;
        metas = pMetas;

        final List<ItfDadoGraficoTotal> itens = new ArrayList<>();

        getMetas().forEach(mt -> {
            int qtd = 0;
            try {
                Long quantidade = new ConsultaDinamicaDeEntidade(Pessoa.class, SBCore.getCentralDados().getAcessoDadosDoContexto().getEntitiManager())
                        .addCondicaoManyToOneIgualA(origem)
                        //     .addCondicaoManyToOneIgualA(mt)
                        .addCondicaoManyToOneContemNoIntervalo(CPPessoa.relacionamento, mt.getTiposRelacionamento())
                        //.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", SBCore.getUsuarioLogado())
                        .resultadoSomarQuantidade();

                qtd = quantidade.intValue();
            } catch (Throwable t) {

            }
            if (qtd > 0) {
                ItemGraficoTotalPorTipo item = new ItemGraficoTotalPorTipo(mt, qtd);
                itens.add(item);

                metasDaOrigem.put(metasDaOrigem.size(), mt);

            }
        });
        grafico = UtilGraficoPrimefaces.gerarGraficoPizza(itens, "Cartões por estágio em:" + pOrigem.getNome());

    }

    public Map<Integer, MetaRelacionamento> getMetasDaOrigem() {
        return metasDaOrigem;
    }

    public List<MetaRelacionamento> getMetas() {
        return metas;
    }

    public void itemGraficoMeusAtendentesOrigem(ItemSelectEvent event) {
        int codigoMetaGraficoSelecionada = event.getItemIndex();
        MetaRelacionamento meta = metasDaOrigem.get(codigoMetaGraficoSelecionada);
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR.getRegistro(), meta, origem);
        UtilSBWP_JSFTools.vaParaPagina(url);

    }

    public PieChartModel getGrafico() {
        return grafico.getGrafico();
    }

}
