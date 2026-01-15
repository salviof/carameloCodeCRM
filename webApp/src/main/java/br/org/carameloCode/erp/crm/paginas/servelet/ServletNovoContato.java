package br.org.carameloCode.erp.crm.paginas.servelet;

import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.CPTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJsonRest;
import jakarta.json.JsonObjectBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import jakarta.json.JsonValue;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class ServletNovoContato extends HttpServlet {

    private static ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = SBCore.getConfigModulo(FabConfigApiMatrixChat.class).getPropriedade(FabConfigApiMatrixChat.SEGREDO);
        if (codigo != null) {
            String codigoEnviado = req.getHeader("CODIGO_ACESSO");
            if (!codigo.equals(codigoEnviado)) {
                resp.setStatus(400);
                JsonObjectBuilder respJsonBuilder = UtilCRCJsonRest.getRespostaJsonBuilderBaseFalha("Acesso negado, verifique suas credenciais com o administrador");
                resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(respJsonBuilder.build()));
                return;
            } else {
                resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(UtilCRCJsonRest.getRespostaJsonBuilderBaseSucesso("OK", JsonValue.EMPTY_JSON_OBJECT).build()));
            }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SBCore.isEmModoProducao()) {

            String origin = req.getHeader("Origin");
            if (origin != null) {
                if (origin.equals("https://casanovadigital.com.br")
                        || origin.endsWith(".casanovadigital.com.br")) {

                    resp.setHeader("Access-Control-Allow-Origin", origin);
                    resp.setHeader("Vary", "Origin"); // importante para cache
                    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                    resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, codigo_acesso");
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);

        } else {
            resp.setHeader("Access-Control-Allow-Headers", "*");

            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, codigo_acesso");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String codigo = SBCore.getConfigModulo(FabConfigApiMatrixChat.class
        ).getPropriedade(FabConfigApiMatrixChat.SEGREDO);
        if (codigo != null) {
            String codigoEnviado = req.getHeader("CODIGO_ACESSO");
            if (SBCore.isEmModoProducao()) {
                if (!codigo.equals(codigoEnviado)) {
                    resp.setStatus(400);
                    JsonObjectBuilder respJsonBuilder = UtilCRCJsonRest.getRespostaJsonBuilderBaseFalha("Acesso negado, verifique suas credenciais com o administrador");
                    resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(respJsonBuilder.build()));
                    return;
                }
            } else {
                if (codigo.equals(codigoEnviado)) {
                    System.out.println("OK");
                } else {
                    System.out.println("NEGADO");
                }
            }
        }

        String codigoFormulario = null;
        try {

            if (req.getParameterMap().containsKey("formulario")) {
                codigoFormulario = req.getParameter("formulario");
            }

            if (codigoFormulario == null) {
                resp.setStatus(400);
                JsonObjectBuilder respJsonBuilder = UtilCRCJsonRest.getRespostaJsonBuilderBaseFalha("Parametro de requisição formulario, não foi encontrado, defina a solicitação com: "
                        + SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.UTRL_PAGINA) + "/leadAdd?formulario=cmhz5zxxxxxxxxxxxcma8l");
                resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(respJsonBuilder.build()));
                return;
            }

            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(TipoFormulario.class, em);
                consulta.addcondicaoCampoIgualA(CPTipoFormulario.codigotypebot, codigoFormulario);
                TipoFormulario formulario = consulta.getPrimeiroRegistro();
                if (formulario == null) {

                    resp.setStatus(400);
                    JsonObjectBuilder respJsonBuilder = UtilCRCJsonRest.getRespostaJsonBuilderBaseFalha("Formulario" + codigoFormulario + " não foi encontrado");

                    resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(respJsonBuilder.build()));
                    return;

                }
                ItfRespostaAcaoDoSistema resposta = ModuloCRMAplicacao.FormularioTypebotProcessar(formulario);
                JsonObjectBuilder jb = UtilCRCJsonRest.getRespostaJsonBuilder(resposta);
                String texto = UtilCRCJson.getTextoByJsonObjeect(jb.build());
                System.out.println(texto);
                resp.getWriter().append(texto);
                return;
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }

        } catch (Throwable erroValidacao) {

            JsonObjectBuilder respJsonBuilder = UtilCRCJsonRest.getRespostaJsonBuilderBaseFalha(erroValidacao.getMessage());
            resp.setStatus(406);
            resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(respJsonBuilder.build()));
        }

    }

}
