package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCRMEmailEnvio;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import org.junit.Test;
import testesFW.geradorDeCodigo.util.model.geradorCodigo.modelRef.GeradorReferenciaCampos;

/**
 *
 * @author desenvolvedor
 */
public class TesteConformidade extends TesteCRMCarameloCode {

    @Test
    public void testePesquisa() {

        try {
            MapaObjetosProjetoAtual.adcionarObjeto(ContatoAnonimoDadoTansitorio.class);
            MapaObjetosProjetoAtual.adcionarObjeto(MetadadoUsuarioCliente.class);
            MapaObjetosProjetoAtual.adcionarObjeto(PesquisaAtividade.class);
            MapaObjetosProjetoAtual.adcionarObjeto(MetadadoAtendente.class);
            MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoLink.class);
            //MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoLink.class);

            Class classe = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(AtividadeCRMEmailEnvio.class.getSimpleName());
            EstruturaDeEntidade estrutura = MapaObjetosProjetoAtual.getEstruturaObjeto(classe);

            if (!classe.isAssignableFrom(ComoAcaoDoSistema.class)) {
                if (!classe.getSimpleName().startsWith("Acao") && !classe.getSimpleName().startsWith("estrutura")) {

                    GeradorReferenciaCampos ref = new GeradorReferenciaCampos(estrutura, classe, true);
                    ref.salvarEmDiretorioPadraoSubstituindoAnterior();

                }

            }

            try {
                if (estrutura.isTemCampoValidadoresLogicos()) {
                    criarAnotacaoValidacao(estrutura);
                }
                if (estrutura.isTemCampoValorLogico()) {
                    criarAnotacaoValorLogico(estrutura);
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
            }

            gerarCodigoModelProjeto();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha executando teste do projeto ataul", t);
            throw t;
        }

        //  gerarCodigoConvercaoObjetosIntegracaoAplicacao();
    }

    public void teste() {
        try {

            //SBCore.getConfigModulo(FabConfigModuloJiraRequisitos.class);
            SBCore.getConfigModulo(FabConfigModuloIntranet.class);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
