/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "RESP_TYPEBOT", tags = {"formulario typebot"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_MB_GESTAO)
public class PgRespostaFormTypebot extends MB_paginaCadastroEntidades<RespostaFormulario> {

    @InfoParametroURL(nome = "Formulário", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = TipoFormulario.class, obrigatorio = true)
    private ParametroURL prFormulario;
    private TipoFormulario tipoFormulario;

    @PostConstruct
    private void inicio() {
        if (getParametroInstanciado(prFormulario).isValorDoParametroFoiConfigurado()) {
            tipoFormulario = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prFormulario).getValor(), getEMPagina());
        }
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {
        return super.getAcoesRegistros(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoVisualizar() {
        return FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_FRM_VISUALIZAR.getRegistro().getComoFormularioEntidade();
    }

//    @Override
//    protected void listarDados(boolean mostrarInativos) {
//        if (getParametroInstanciado(prFormulario).isValorDoParametroFoiConfigurado()) {

////            renovarEMPagina();
//            tipoFormulario = UtilSBPersistencia.loadEntidade(tipoFormulario, getEMPagina());
//            setEntidadesListadas(tipoFormulario.getRespostas());
//        }
//    }
}
