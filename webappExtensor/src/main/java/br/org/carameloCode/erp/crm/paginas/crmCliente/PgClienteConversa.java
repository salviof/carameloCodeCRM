/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.crm.paginas.servico.ServicoChatView;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author salvio
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CONVERSA_MB)
@InfoPagina(nomeCurto = "CONVCLI", tags = {"meu time"})
public class PgClienteConversa extends MB_paginaCadastroEntidades<UsuarioCrmCliente> {

    @InfoParametroURL(tipoEntidade = UsuarioCrmCliente.class, obrigatorio = false, tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "prUsuario")
    private ParametroURL prUsuario;

    @Inject
    private ServicoChatView servicoMatrix;

    @PostConstruct
    public void inicio() {
        setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina()));
    }

    public ComoChatSalaBean getCanalRocketChat() {

        return servicoMatrix.getSalaAtendimento(getEntidadeSelecionada().getRepresentanteLegal());

    }

}
