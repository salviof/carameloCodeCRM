package br.org.carameloCode.erp.crm.paginas.servico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;

import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoSessao;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
public class ServicoChatView implements Serializable {

    private ComoChatSalaBean sala;

    private String urlClientChat;

    @Inject
    @QlSessaoFacesContext
    private ComoSessao sessao;

    @PostConstruct
    public void inicio() {

        urlClientChat = "https://chat.casanovadigital.com.br";

    }

    public ComoChatSalaBean getSala() {
        return sala;
    }

    public String getCodigoSala() {
        if (sala == null) {
            return null;
        }
        return sala.getCodigoChat();
    }

    public synchronized ComoChatSalaBean getSalaDebate(Pessoa pPessoa) {

        if (sala == null) {
            try {
                sala = UtilCRMChat.gerarSalaDebate(pPessoa);
            } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando sala para debate" + ex.getMessage(), ex);
            }
        }

        return sala;

    }

    public ComoChatSalaBean getSalaAtendimento(Pessoa pPessoa) {
        try {
            if (sala == null) {
                sala = UtilCRMChat.gerarSalaContatoPrincipal(pPessoa);
            }
            return sala;
        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando sala para atendimento" + ex.getMessage(), ex);
        }
        return null;

    }

    public ComoChatSalaBean getSalaChamado(ChamadoCliente pChamado) {
        try {

            sala = UtilCRMChat.gerarSalaChamado(pChamado);
        } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando sala para chamado", ex);
        }
        return sala;
    }

    private Thread adduser;

    public ItfErpChatService getServicoChatERP() {
        return UtilCRMChat.chatService;
    }

    public String getUrlSala() {
        if (sala == null) {
            return null;
        }
        StringBuilder salaUrlPatchBuilder = new StringBuilder();
        salaUrlPatchBuilder.append("?salaConversa=");
        try {
            salaUrlPatchBuilder.append(URLEncoder.encode(sala.getApelido(), StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServicoChatView.class.getName()).log(Level.SEVERE, null, ex);
        }

        return urlClientChat + salaUrlPatchBuilder.toString();
    }

    public boolean isExisteSala() {
        return sala != null;
    }

}
