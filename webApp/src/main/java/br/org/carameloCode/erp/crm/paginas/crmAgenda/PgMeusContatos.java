/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.sms.MensagemSMS;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.InfoAcaoCRMAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import java.util.HashMap;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
@InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Meus contatos", tags = {"cts"})
public class PgMeusContatos extends MB_paginaCadastroEntidades<ContatoProspecto> {

    @InfoParametroURL(nome = "Contato selecionado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ContatoProspecto.class, representaEntidadePrincipalMB = true)
    private ParametroURL contatoSelecionado;

    private MensagemSMS mensagemSMS;
    private MensagemMktWhatsapp mensagemWhatsapp;

    @Override
    public ContatoProspecto getEntidadeSelecionada() {
        return super.getEntidadeSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcao(ContatoProspecto pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
        if (pResposta.isSucesso()) {
            mensagemSMS = null;
        }

    }

    public MensagemMktWhatsapp getMensagemWhatsapp() {
        if (mensagemWhatsapp == null) {
            mensagemWhatsapp = new MensagemMktWhatsapp();
            try {
                mensagemWhatsapp.prepararNovoObjeto(getEntidadeSelecionada());
            } catch (ErroPreparandoObjeto ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando nova mensagem de Whatsapp", ex);
                mensagemWhatsapp = null;
            }
        }
        return mensagemWhatsapp;
    }

    public MensagemSMS getMensagemSMS() {
        if (mensagemSMS == null) {
            mensagemSMS = new MensagemSMS();
            mensagemSMS.setContato(getEntidadeSelecionada());
        }
        return mensagemSMS;
    }

    private ItfTokenAcessoDinamico token;

    public synchronized ItfTokenAcessoDinamico getTokenAcesso() {
        if (token != null) {
            return token;
        }
        if (getEntidadeSelecionada() == null) {
            return null;
        }
        UsuarioCrmCliente usuario = (UsuarioCrmCliente) getEntidadeSelecionada().getCPinst("usuarioVinculado").getValor();
        if (usuario == null) {
            return null;
        }
        token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO, getEntidadeSelecionada(), usuario.getEmail());
        return token;
    }

    public String getUrlAcessoDireto() {
        ItfTokenAcessoDinamico token = getTokenAcesso();
        if (token == null) {
            return null;
        }
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
        url = url.replace("crm.", "atendimento.");
        return url;
    }
    private Map<TipoDadoCRM, DadoCRM> dados = new HashMap<>();

    public DadoCRM getDado(TipoDadoCRM pTipoDado) {
        if (!dados.containsKey(pTipoDado)) {
            DadoCRM dado = FabDadoCRM.getDadoNovoSeNaoExistir(getEntidadeSelecionada().getProspecto(), pTipoDado);
            dados.put(pTipoDado, dado);
        }
        return dados.get(pTipoDado);

    }
}
