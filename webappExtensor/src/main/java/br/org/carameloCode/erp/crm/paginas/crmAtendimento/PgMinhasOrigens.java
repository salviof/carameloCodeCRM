/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
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
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "MINHAS_ORIGNES", tags = {"Minhas Origens"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MINHAS_ORIGENS_MB_GESTAO)
public class PgMinhasOrigens extends MB_paginaCadastroEntidades<OrigemProspectoPrivado> {

    @InfoParametroURL(nome = "Origem", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            obrigatorio = false,
            representaEntidadePrincipalMB = true,
            tipoEntidade = OrigemProspectoPrivado.class)
    private ParametroURL parametroOrigemSelecionada;

    private UsuarioCRM usuario;

    @Override
    protected void listarDados(boolean mostrarInativos) {
        List<OrigemProspectoPrivado> origens = UtilSBPersistencia
                .gerarConsultaDeEntidade(OrigemProspectoPrivado.class, getEMPagina())
                .addCondicaoManyToOneIgualA("usuarioDono", usuario)
                .resultadoRegistros();
        setEntidadesListadas(origens);
    }

    public UsuarioCRM getUsuario() {
        usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        return usuario;
    }

    @Override
    protected void autoexecEntidadeNova() {

        super.autoexecEntidadeNova();
        getEntidadeSelecionada().setUsuarioDono(getUsuario());
    }

}
