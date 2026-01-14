package br.org.carameloCode.erp.crm.paginas.dashboardAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.google.api.client.util.Lists;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItemGraficoTotalPorTipo;
import com.super_bits.modulosSB.SBCore.modulos.grafico.ItfDadoGraficoTotal;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.grafico.GraficoPizza;
import java.util.ArrayList;

import java.util.List;
import javax.persistence.EntityManager;

public class GraficoPizzaTagsUsuarioAtend extends GraficoPizza {

    private UsuarioCRM atendente;

    public GraficoPizzaTagsUsuarioAtend(UsuarioCRM pUsuario) {
        super(buildDados(pUsuario), "Meus Leads por Meta ", FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR);
        atendente = pUsuario;
    }

    private static List<ItfDadoGraficoTotal> buildDados(UsuarioCRM pUsuario) {
        final List<ItfDadoGraficoTotal> itens = new ArrayList<>();
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

        try {
            UsuarioCRM atendenteAtualizado = UtilSBPersistencia.loadEntidade(pUsuario, em);
            if (pUsuario.getTagsMonitoradas().isEmpty()) {
                return itens;
            }

            final List<TagAtendimento> tagsMonitoras = Lists.newArrayList(pUsuario.getTagsMonitoradas());
            pUsuario.getTagsMonitoradas().stream().
                    forEach(tagNonitorada -> {
                        long qtd = 0;
                        try {
                            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Pessoa.class, em);
                            consulta.addCondicaoManyToManyContendoObjeto(CPPessoa.tagsatendimento, tagNonitorada);
                            consulta.addCondicaoManyToManyContendoObjeto(CPPessoa.usuariosresponsaveis, atendenteAtualizado);
                            qtd = consulta.gerarResultadoSomarQuantidade();
                        } catch (Throwable t) {

                        }
                        if (qtd > 0) {
                            ItemGraficoTotalPorTipo item = new ItemGraficoTotalPorTipo(tagNonitorada, qtd,
                                    CarameloCode.getServicoVisualizacao()
                                            .getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR, tagNonitorada, pUsuario));
                            itens.add(item);
                        }

                    });
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return itens;

    }

}
