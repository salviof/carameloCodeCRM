package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_MB_GESTAO)
@InfoPagina(nomeCurto = "", tags = {"chamada recebida"})
@ViewScoped
public class PgTipoChamadaRealizada extends MB_paginaCadastroEntidades<TipoAtvChamadaRealizada> {
    @InfoParametroURL(nome = "Chamada Realizada", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = TipoAtvChamadaRealizada.class, obrigatorio = false)
    private ParametroURL prTipoAtvChamadaRealizada;
    private TipoAtvChamadaRealizada tipoAtvChamadaRealizada;

    @PostConstruct
    private void inicio() {
        if (getParametroInstanciado(prTipoAtvChamadaRealizada).isValorDoParametroFoiConfigurado()) {
            tipoAtvChamadaRealizada = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prTipoAtvChamadaRealizada).getValor(), getEMPagina());
        }
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {
        return super.getAcoesRegistros(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        List<TipoAtvChamadaRealizada> atividades = UtilSBPersistencia.getListaRegistrosByHQL("from " + TipoAtvChamadaRealizada.class.getSimpleName() + " order by id DESC", 200, getEMPagina());
        setEntidadesListadas(atividades);
    }
}