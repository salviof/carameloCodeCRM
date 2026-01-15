/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servelet;

import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCHtmlFormat;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salvio
 */
public class ServletCNPJPorCelular extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String telefone = req.getHeader("telefone");

        if (!SBCore.isAmbienteCoreConfigurado() || !SBWebPaginas.isAmbienteConfigurado() || telefone == null) {
            resp.setStatus(401);
            resp.getWriter().append("{}");
        } else {
            JsonObject resposta = getCPJ(telefone);
            // Define tipo de conteúdo e charset
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(resposta));
        }
    }

    public ContatoProspecto validarUsuarioContato(ContatoProspecto pContato, EntityManager em) {
        UsuarioCrmCliente usuario = (UsuarioCrmCliente) pContato.getCPinst("usuarioVinculado").getValor();
        if (usuario.getId() == null) {

            pContato = UtilSBPersistencia.mergeRegistro(pContato, em);
            usuario = pContato.getUsuarioVinculado();

        }
        return pContato;
    }

    public synchronized String getUrlAcessoCliente(ContatoProspecto pContato, FabAcaoCRMCliente pAcao) {

        UsuarioCrmCliente usuario = (UsuarioCrmCliente) pContato.getCPinst("usuarioVinculado").getValor();

        if (usuario == null) {
            return null;
        }
        ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(pAcao, pContato, usuario.getEmail());
        if (token == null) {
            return null;
        }
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
        url = url.replace("crm.", "atendimento.");
        return url;
    }

    public JsonObject getCPJ(String pCelular) {

        if (pCelular == null) {
            return null;
        }
        String telefoneFormato = pCelular.replace("-", "");
        if (telefoneFormato.length() < 8) {
            return null;
        }

        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            String finalCelular = telefoneFormato.substring(telefoneFormato.length() - 8, telefoneFormato.length());
            String finalCelularComTraco = finalCelular.substring(0, 4) + "-" + finalCelular.substring(finalCelular.length() - 4, finalCelular.length());
            List<ContatoProspecto> contatos = UtilSBPersistencia
                    .getListaRegistrosByHQL("select  c FROM " + ContatoProspecto.class.getSimpleName()
                            + " c where "
                            + "celular LIKE ?0 "
                            + " or telefoneAlternativo LIKE ?0 "
                            + " or celularFormatoInternacional LIKE ?0 "
                            + " or celular LIKE ?1 "
                            + " or telefoneAlternativo LIKE ?1 "
                            + " or celularFormatoInternacional LIKE ?1 ",
                            10, em, "%" + finalCelularComTraco, "%" + finalCelular);

            if (!contatos.isEmpty()) {
                try {

                    ContatoProspecto ctProspecto;
                    Optional<ContatoProspecto> ctcomCNPJ = contatos.stream()
                            .filter(ct -> !UtilCRCStringValidador.isNuloOuEmbranco(ct.getProspecto().getDocumento())).findFirst();

                    if (ctcomCNPJ.isPresent()) {
                        ctProspecto = ctcomCNPJ.get();
                    } else {
                        ctProspecto = contatos.get(0);
                    }

                    ctProspecto = UtilSBPersistencia.loadEntidade(validarUsuarioContato(ctProspecto, em), em);
                    JsonObjectBuilder json = Json.createObjectBuilder();
                    if (ctProspecto.getEmail() != null) {

                        String linkAreaCliente = getUrlAcessoCliente(ctProspecto, FabAcaoCRMCliente.DASHBOARD_MB_GESTAO);
                        json.add("linkAreaCliente", linkAreaCliente);
                        String linkAgendaConsultor = getUrlAcessoCliente(ctProspecto, FabAcaoCRMCliente.RESERVAS_MB_GESTAO);
                        json.add("linkAgendaConsultor", linkAgendaConsultor);
                        String linkAgendaAtendimento = getUrlAcessoCliente(ctProspecto, FabAcaoCRMCliente.RESERVAS_MB_GESTAO);
                        json.add("linkAgendaAtendimento", linkAgendaAtendimento);
                        String linkChamado = getUrlAcessoCliente(ctProspecto, FabAcaoCRMCliente.CHAMADO_MB_GESTAO);
                        json.add("linkChamado", linkChamado);
                    }
                    if (ctProspecto.getProspecto().getUsuarioAtendimento() != null) {
                        json.add("representanteAtendimento", ctProspecto.getProspecto().getUsuarioAtendimento().getEmail());
                    }
                    ;
                    if (ctProspecto.getProspecto().getUsuarioResponsavel() != null) {
                        json.add("representanteVendas", ctProspecto.getProspecto().getUsuarioResponsavel().getEmail());
                        //json.add(pCelular, BigDecimal.ONE);
                    }

                    if (ctProspecto.getProspecto().getDocumento() != null) {
                        json.add("documento", ctProspecto.getProspecto().getDocumento());

                    }
                    if (ctProspecto.getProspecto().getUsuarioResponsavel() != null) {

                        String emailPrincipal = ctProspecto.getProspecto().getUsuarioResponsavel().getEmail();
                        json.add("usuarioResponsavel", emailPrincipal);
                        JsonArrayBuilder usuariosREsponsaveis = Json.createArrayBuilder();
                        for (UsuarioCRM usr : ctProspecto.getProspecto().getUsuariosResponsaveis()) {
                            usuariosREsponsaveis.add(usr.getEmail());
                        }
                        json.add("usuariosResponsaveis", usuariosREsponsaveis.build());

                    }

                    List<ChamadoCliente> chamados = ctProspecto.getProspecto().getChamadosAbertos();
                    JsonArrayBuilder chamadosJson = Json.createArrayBuilder();
                    for (ChamadoCliente chamado : chamados) {
                        JsonObjectBuilder chamadoJson = Json.createObjectBuilder();
                        if (!chamado.getCPinst(CPChamadoCliente.salamatrix).isVazio()) {
                            chamadoJson.add("codigoSalaAtendimento", (String) chamado.getCPinst(CPChamadoCliente.salamatrix).getValor());
                        }
                        chamadoJson.add("descricao", UtilCRCHtmlFormat.gerarMarkdownDeWhatsapp(chamado.getDescricao()));
                        chamadoJson.add("id", chamado.getId());
                        chamadoJson.add("status", chamado.getStatus().getStatusEnum().toString());
                        if (chamado.getAtendenteResponsavel() != null) {
                            chamadoJson.add("atendenteResponsavel", chamado.getAtendenteResponsavel().getEmail());
                        }
                        String tipoChamado = chamado.getTipoChamado().getNome();
                        if (tipoChamado.length() > 24) {
                            tipoChamado = UtilCRCStringFiltros.getNomeReduzido(tipoChamado);
                        }
                        if (tipoChamado.length() > 24) {
                            tipoChamado = UtilCRCStringFiltros.getPrimeirasXLetrasDaString(tipoChamado, 20);
                        }
                        chamadoJson.add("tipo", tipoChamado);
                        chamadosJson.add(chamadoJson.build());
                    }
                    json.add("chamados", chamadosJson);
                    return json.build();

                } catch (Throwable ex) {
                    return JsonObject.EMPTY_JSON_OBJECT;
                }

            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return JsonObject.EMPTY_JSON_OBJECT;
    }

}
