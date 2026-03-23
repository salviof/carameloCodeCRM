package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
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
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;

@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_MB_GESTAO)
@InfoPagina(nomeCurto = "", tags = {"chamada recebida"})
@ViewScoped
public class PgTipoChamadaRecebida extends MB_paginaCadastroEntidades<TipoAtvChamadaRecebida> {
    @InfoParametroURL(nome = "Chamada Recebida", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = TipoAtvChamadaRecebida.class, obrigatorio = false)
    private ParametroURL prTipoAtvChamadaRecebida;
    private TipoAtvChamadaRecebida tipoAtvChamadaRecebida;
    @PostConstruct
    private void inicio() {
        if (getParametroInstanciado(prTipoAtvChamadaRecebida).isValorDoParametroFoiConfigurado()) {
            tipoAtvChamadaRecebida = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prTipoAtvChamadaRecebida).getValor(), getEMPagina());
        }
    }
    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {
        return super.getAcoesRegistros(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    @Override
    protected void listarDados(boolean mostrarInativos) {
        List<TipoAtvChamadaRecebida> atividades = UtilSBPersistencia.getListaRegistrosByHQL("from " + TipoAtvChamadaRecebida.class.getSimpleName() + " order by id DESC", 200, getEMPagina());
        setEntidadesListadas(atividades);
    }
}