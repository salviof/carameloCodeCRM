/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servelet;

import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.CPEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.erpColetivoJava.api.model.usuariosb.CPUsuarioSB;

/**
 *
 * @author salvio
 */
public class ServletCanaisPublicos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailAtendimento = req.getHeader("atendimendo");
        String contatoNome = req.getHeader("nome");
        String contatoTelefone = req.getHeader("telefone");
        if (!SBCore.isAmbienteCoreConfigurado() || !SBWebPaginas.isAmbienteConfigurado()) {
            resp.setStatus(401);
            resp.getWriter().append("{}");
        } else {
            JsonObject resposta = getLinksPublicos(emailAtendimento);
            // Define tipo de conteúdo e charset
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().append(UtilCRCJson.getTextoByJsonObjeect(resposta));
        }
    }

    public JsonObject getLinksPublicos(String pEmailAtendimento) {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            UsuarioCRM usuario = null;
            if (pEmailAtendimento == null) {
                usuario = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(pEmailAtendimento);
            }
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(EscopoPesqHorarioPublicado.class, em);
            if (usuario != null) {
                consulta.addCondicaoManyToManyContendoObjeto(CPEscopoPesquisaMelhorHorario.atendentes, usuario);
            }
            consulta.addCondicaoPositivo(CPEscopoPesqHorarioPublicado.publicado);

            List<EscopoPesqHorarioPublicado> escopos = consulta.gerarResultados();
            JsonObjectBuilder canais = Json.createObjectBuilder();

            if (!escopos.isEmpty()) {
                JsonArrayBuilder agendasPublicas = Json.createArrayBuilder();
                for (EscopoPesqHorarioPublicado escopo : escopos) {
                    if (escopo.getAtendentes().stream().filter(at -> at.getEmail() != null && at.getEmail().equals(pEmailAtendimento)).findFirst().isPresent()) {
                        if (escopo.getCPinst("ativo").getValorComoBoolean()) {
                            JsonObjectBuilder jsonAgenda = Json.createObjectBuilder();
                            jsonAgenda.add("linkDeAcesso", escopo.getLinkDeAcesso());
                            jsonAgenda.add("tipo", escopo.getTipoAgendamento().getNome());
                            JsonArrayBuilder atendentesAgenda = Json.createArrayBuilder();
                            for (UsuarioSB atendente : escopo.getAtendentes()) {
                                JsonObjectBuilder atententeDaAgenda = Json.createObjectBuilder();
                                atententeDaAgenda.add(CPUsuarioSB.email, atendente.getEmail());
                                atententeDaAgenda.add(CPUsuarioSB.nome, atendente.getNome());
                                atententeDaAgenda.add(CPUsuarioSB.id, atendente.getId());
                                atendentesAgenda.add(atententeDaAgenda.build());
                            }
                            jsonAgenda.add("atendentes", atendentesAgenda.build());
                            agendasPublicas.add(jsonAgenda);
                        }
                    }
                }
                canais.add("agendasPublicas", agendasPublicas.build());
            }

            List<UsuarioCRM> atendentesDisponiveis = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, em)
                    .addCondicaoPositivo(CPUsuarioSB.ativo).gerarResultados();

            List<UsuarioCrmCliente> usuariosClientes = UtilSBPersistencia.getListaTodos(UsuarioCrmCliente.class, em);
            atendentesDisponiveis.removeAll(usuariosClientes);
            if (!atendentesDisponiveis.isEmpty()) {
                JsonArrayBuilder atendentes = Json.createArrayBuilder();
                for (UsuarioCRM usuarioatendente : atendentesDisponiveis) {
                    JsonObjectBuilder atententeJson = Json.createObjectBuilder();
                    atententeJson.add(CPUsuarioSB.email, usuarioatendente.getEmail());
                    atententeJson.add(CPUsuarioSB.nome, usuarioatendente.getNome());
                    atententeJson.add(CPUsuarioSB.id, usuarioatendente.getId());
                    atendentes.add(atententeJson.build());
                }
                canais.add("atendentes", atendentes);
            }

            canais.add("atendentePreferencial", "salvio@casanovadigital.com.br");

            return canais.build();
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }
}
