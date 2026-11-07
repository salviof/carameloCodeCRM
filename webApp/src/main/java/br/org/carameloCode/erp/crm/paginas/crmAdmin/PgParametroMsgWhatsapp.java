/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import java.util.ArrayList;

/**
 *
 * @author salvio
 */
@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_MB_GESTAO)
@InfoPagina(nomeCurto = "TPTZP", tags = {"Tipo Parametro msg Whatsapp"})
@ViewScoped
public class PgParametroMsgWhatsapp extends MB_paginaCadastroEntidades<ParametroMensagemWtzap> {

    @InfoParametroURL(nome = "Pr tipo mensagem", tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = true, tipoEntidade = TipoMensagemMktWhatsApp.class)
    private ParametroURL prTipoMensagem;
    private TipoMensagemMktWhatsApp tipoMensagem;

    @PostConstruct
    public void inicio() {
        if (getParametroInstanciado(prTipoMensagem).isValorDoParametroFoiConfigurado()) {
            tipoMensagem = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prTipoMensagem).getValor(), getEMPagina());
        }

    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        if (getParametroInstanciado(prTipoMensagem).isValorDoParametroFoiConfigurado()) {
            renovarEMPagina();
            tipoMensagem = UtilSBPersistencia.loadEntidade(tipoMensagem, getEMPagina());
            setEntidadesListadas(tipoMensagem.getParametros());
        }
    }

    private List<ParametroMensagemWtzap> cabecalho = new ArrayList();

    public List<ParametroMensagemWtzap> getParametrosCabecalho() {
        if (getEntidadesListadas() != null) {
            cabecalho = new ArrayList<>();
            if (cabecalho == null || cabecalho.isEmpty() || !isListaAtualizada()) {
                getEntidadesListadas().stream().filter(pr -> pr.isCabecalho()).forEach(cabecalho::add);
            }
        } else {
            cabecalho = new ArrayList<>();
        }
        return cabecalho;
    }
    private List<ParametroMensagemWtzap> corpo = new ArrayList();

    public List<ParametroMensagemWtzap> getParametrosCorpo() {
        if (getEntidadesListadas() != null) {
            corpo = new ArrayList();
            if (corpo == null || corpo.isEmpty() || !isListaAtualizada()) {
                getEntidadesListadas().stream().filter(pr -> !pr.isCabecalho() && !pr.getTipoParametroWtzap().equals("button")).forEach(corpo::add);
            }
        } else {
            corpo = new ArrayList();
        }
        return corpo;
    }

    private List<ParametroMensagemWtzap> botao = new ArrayList();

    public List<ParametroMensagemWtzap> getParametrosBotoes() {
        if (getEntidadesListadas() != null || isListaAtualizada()) {
            botao = new ArrayList();
            if (botao == null || botao.isEmpty() || !isListaAtualizada()) {
                getEntidadesListadas().stream().filter(pr -> !pr.isCabecalho() && pr.getTipoParametroWtzap().equals("button")).forEach(botao::add);
            }
        } else {
            botao = new ArrayList();
        }
        return botao;
    }

    private boolean isListaAtualizada() {
        int total = botao.size() + corpo.size() + cabecalho.size();
        if (total != getEntidadesListadas().size()) {
            return false;
        }
        return true;
    }

}
