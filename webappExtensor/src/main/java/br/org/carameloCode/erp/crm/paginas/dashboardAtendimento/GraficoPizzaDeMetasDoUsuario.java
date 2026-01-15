/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.dashboardAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItemGraficoTotalPorTipo;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import java.util.List;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.GraficoPizza;
import java.util.ArrayList;

/**
 *
 * @author sfurbino
 */
public class GraficoPizzaDeMetasDoUsuario extends GraficoPizza {

    private List<MetaRelacionamento> metas;
    private UsuarioCRM usuario;

    private GraficoPizza graficoPizza;

    public GraficoPizzaDeMetasDoUsuario(List<MetaRelacionamento> pMetas, UsuarioCRM pUsuario) {
        super(buildDados(pMetas, pUsuario), "Meus Leads por Meta ", FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR);
        metas = pMetas;
        usuario = pUsuario;
    }

    private static List<ItfDadoGraficoTotal> buildDados(List<MetaRelacionamento> pMetas, UsuarioCRM pUsuario) {
        final List<ItfDadoGraficoTotal> itens = new ArrayList<>();
        pMetas.
                forEach(mt -> {
                    int qtd = 0;
                    try {
                        qtd = pUsuario.getEmpresasNestaMeta(mt);
                    } catch (Throwable t) {

                    }
                    if (qtd > 0) {
                        ItemGraficoTotalPorTipo item = new ItemGraficoTotalPorTipo(mt, qtd, CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR, mt, pUsuario));
                        itens.add(item);
                    }

                });

        return itens;

    }

    public List<MetaRelacionamento> getMetas() {
        return metas;
    }

}
