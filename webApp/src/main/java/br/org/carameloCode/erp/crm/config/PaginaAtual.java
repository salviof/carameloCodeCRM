package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.crm.paginas.ComoPaginaAtualCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MB_SiteMapa;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComGestaoChamado;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalEmail;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.crm.paginas.crmAgenda.ItfPaginaListaDeHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;

@Named
@ViewScoped
public class PaginaAtual extends MB_PaginaAtual implements ItfPaginaAtual, ComoPaginaAtualCRM {

    @Inject
    private SiteMap siteMapa;

    @Override
    public MB_SiteMapa getSiteMap() {
        return siteMapa;
    }

    @Override
    public ItfPaginaComModalProspecto getComoPaginaComModal() {
        return (ItfPaginaComModalProspecto) getInfoPagina();
    }

    @Override
    public ItfPaginaComModalEmail getComoPaginaComModalEmail() {
        return (ItfPaginaComModalEmail) getInfoPagina();
    }

    @Override
    public ItfPaginaListaDeHorariosDisponiveis getComoPaginaComHorarioDisponivel() {
        return (ItfPaginaListaDeHorariosDisponiveis) getInfoPagina();
    }

    @Override
    public UsuarioCRM getUsuarioCrmLogado() {
        try {
            return UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getInfoPagina().getComoFormularioWeb().getEMPagina());
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public ItfPaginaComGestaoChamado getComoPaginaGestaoChamado() {
        return (ItfPaginaComGestaoChamado) getInfoPagina();
    }

}
