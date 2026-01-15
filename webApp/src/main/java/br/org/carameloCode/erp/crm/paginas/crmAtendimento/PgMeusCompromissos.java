/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Meus Compromissos", tags = {"Meus Compromissos"})
public class PgMeusCompromissos extends MB_paginaCadastroEntidades<AtividadeCRM> {

    @InfoParametroURL(nome = "Usuarário da agenda", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prUsuario;

    private UsuarioCRM usuario;

    @PostConstruct
    public void inicio() {
        UsuarioCRM usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
            if (usuarioLogado.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                usuario = (UsuarioCRM) getParametroInstanciado(prUsuario).getValor();
            }
        }
        if (usuario == null) {
            usuario = usuarioLogado;
        }
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

}
