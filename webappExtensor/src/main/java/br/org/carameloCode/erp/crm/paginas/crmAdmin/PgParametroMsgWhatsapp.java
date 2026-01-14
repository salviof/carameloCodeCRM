/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
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
    private void inicio() {
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

    private List<ParametroMensagemWtzap> cabecalho;

    public List<ParametroMensagemWtzap> getParametrosCabecalho() {
        if (cabecalho == null || cabecalho.isEmpty()) {
            getEntidadesListadas().stream().filter(pr -> pr.isCabecalho()).forEach(cabecalho::add);
        }
        return cabecalho;
    }
    private List<ParametroMensagemWtzap> corpo;

    public List<ParametroMensagemWtzap> getParametrosCorpo() {
        if (corpo == null || corpo.isEmpty()) {
            getEntidadesListadas().stream().filter(pr -> !pr.isCabecalho() && !pr.getTipoParametroWtzap().equals("button")).forEach(corpo::add);
        }
        return corpo;
    }

    private List<ParametroMensagemWtzap> botao;

    public List<ParametroMensagemWtzap> getParametrosBotoes() {
        if (botao == null || botao.isEmpty()) {
            getEntidadesListadas().stream().filter(pr -> !pr.isCabecalho() && pr.getTipoParametroWtzap().equals("button")).forEach(botao::add);
        }
        return botao;
    }

}
