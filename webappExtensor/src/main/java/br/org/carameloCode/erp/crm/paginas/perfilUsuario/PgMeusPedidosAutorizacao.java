/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.perfilUsuario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcesso;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "MPA", tags = {"Meus pedidos de autorização"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_PEDIDOS_AUTORIZACAO_MB_GESTAO)
public class PgMeusPedidosAutorizacao extends MB_paginaCadastroEntidades<PedidoAcesso> {

    @Override
    protected void listarDados(boolean mostrarInativos) {
        super.listarDados(false);

        try {
            UsuarioCRM usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            List<PedidoAcesso> pedidos = new ConsultaDinamicaDeEntidade(PedidoAcesso.class)
                    .addCondicaoManyToOneIgualA("usuarioConcedente", usuarioLogado).resultadoRegistros();
            setEntidadesListadas(pedidos);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro listando dados", t);
        }
    }

}
