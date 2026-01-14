/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servelet;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.UtilSBRestful;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao.MetadadosTokenDinamico;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPJson;
import jakarta.json.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
public class ServletAutoLoginEntreSistemas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //return UtilSBRestful.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String corpo = UtilSBRestful.getCorpoRequisicao(req);

        MetadadosTokenDinamico metadados = new MetadadosTokenDinamico(corpo);
        if (metadados.getLeadEmail() == null || metadados.getLeadEmail().isEmpty()) {
            resp.sendError(400, UtilCRCJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("usuário não enviado")));
            return;
        }

        // Criar usuário
        ComoUsuario usuario = SBCore.getServicoPermissao().getUsuarioByEmail(metadados.getLeadEmail());
        if (usuario == null) {
            resp.sendError(400, UtilCRCJson.getTextoByJsonObjeect(UtilSBWPJson.JSON_FALHA_GERANDO_JSONVIEW("Usuário não encontrado")));
            return;
        }
        ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_FRM_REDIRECIONAMENTO,
                null, metadados.getLeadEmail());
        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormularioTokenAcesso(token);

    }

}
