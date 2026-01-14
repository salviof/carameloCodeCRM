package br.org.carameloCode.erp.modulo.crm.api.dominio;

import br.org.carameloCode.erp.modulo.crm.api.ErroAcessandoCRM;
import br.org.carameloCode.erp.modulo.crm.api.FabConfigErpCRM;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfServicoLinkDeEntidadesERP;
import br.org.carameloCode.erp.modulo.crm.api.dominio.CRMCarameloCodePadraoPadrao;
import br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.CPEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.ProcessadorEmailHtml;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsExtrator;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ItfTDadoDinamicoCRM;
import jakarta.json.JsonObject;
import java.util.Map;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ComoLead;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidorEmailAvancado;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.repositorioLinkEntidades.RepositorioLinkEntidadesGenerico;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@CRMCarameloCodePadrao
public class CRMCarameloCodePadraoimpl extends RepositorioLinkEntidadesGenerico
        implements
        br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm {

    private static final String CSS_REGULADOR = "p {white-space:pre-wrap; word-wrap:break-word;font-family:'Roboto',Verdana,sans-serif;font-size:14px;"
            + "margin-block-start: 5 px; margin-block-end: 0px; margin-inline-start: 0px;  margin-inline-end: 0px;";
    private static final String ASSINATURA_PADRAO = "";

    private static final Map<String, ItfServidorEmailAvancado> servidoresPadrao = new HashMap<>();
    private ItfServidorEmailAvancado servidorPadrao;

    private synchronized ItfServidorEmailAvancado buildEmailSErver(UsuarioCRM pUsuario) throws ErroEnvioEmail {
        CaixaPostal novoServidor = new CaixaPostal();

        novoServidor.setSenhaSMTP(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_SENHA));
        novoServidor.setUsuarioSMTP(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_USUARIO));
        novoServidor.setEnderecoServidor(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_HOSTNAME));
        novoServidor.setPortaRecepcao(587);
        novoServidor.setUsarSSLRecepcao(true);

        if (pUsuario == null || UtilCRCStringValidador.isNuloOuEmbranco(pUsuario.getAssinaturaEmail())) {
            if (servidorPadrao != null) {
                return servidorPadrao;
            } else {
                novoServidor.setNomeRemetente(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_NOME_REMETENTE));
                novoServidor.setEmailRemetente(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_EMAIL_REMETENTE));
                novoServidor.setNome(SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_EMAIL_REMETENTE));
                servidorPadrao = novoServidor;
                return servidorPadrao;
            }
        } else {
            if (servidoresPadrao.containsKey(pUsuario.getEmail())) {
                return servidoresPadrao.get(pUsuario.getEmail());
            }
            novoServidor.setNome(pUsuario.getEmail());
            novoServidor.setNomeRemetente(pUsuario.getNome());
            novoServidor.setEmailRemetente(pUsuario.getEmail());
            servidoresPadrao.put(pUsuario.getEmail(), novoServidor);
        }
        ItfServidorEmailAvancado servidor = servidoresPadrao.get(pUsuario.getEmail());
        if (servidor == null) {
            throw new ErroEnvioEmail("Falha definindo dados de serviço de e-mail");
        }
        if (servidor.getEnderecoServidor() == null || servidor.getEnderecoServidor().isEmpty()) {
            throw new ErroEnvioEmail("Defina o endereço do seridor de envio");
        }
        if (servidor.getUsuarioSMTP() == null || servidor.getUsuarioSMTP().isEmpty()) {
            throw new ErroEnvioEmail("Defina o usuário SMTP");
        }
        return servidor;
    }

    public CRMCarameloCodePadraoimpl() {
        String hostname = SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_HOSTNAME);
        String usuario = SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_USUARIO);
        String senha = SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_SERVICE_SENHA);
        String nomeRemetente = SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_NOME_REMETENTE);
        String emailREmetente = SBCore.getConfigModulo(FabConfigErpCRM.class).getPropriedade(FabConfigErpCRM.EMAIL_SIMPLE_MAIL_EMAIL_REMETENTE);
        UtilCRCEmail.configurar(new ConfigEmailServersProjeto(hostname, nomeRemetente + " <" + emailREmetente + ">", usuario, senha));

    }

    @Override
    public ItfServidorEmailAvancado getEMailServer(ComoUsuario pUsuario) throws ErroEnvioEmail {
        if (pUsuario == null || UtilCRCStringValidador.isNuloOuEmbranco(pUsuario.getEmail())) {
            if (servidorPadrao == null) {
                return servidorPadrao;
            }
            return getEMailServer(null);
        }
        if (servidoresPadrao.containsKey(pUsuario.getEmail())) {
            return servidoresPadrao.get(pUsuario.getEmail());
        }
        return buildEmailSErver((UsuarioCRM) pUsuario);

    }

    @Override
    public ItfTDadoDinamicoCRM getDadoCRM(String pDocumento, String pnomeDado) throws ErroAcessandoCRM {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            String documento = UtilCRCStringsExtrator.filtrarApenasNumeros(pnomeDado);
            Pessoa pessoa = (Pessoa) UtilSBPersistencia.gerarConsultaDeEntidade(Pessoa.class, em)
                    .addcondicaoCampoIgualA(CPPessoa.documento, documento)
                    .getPrimeiroRegistro();
            Optional<DadoCRM> dado = pessoa.getDadosColetados().stream().filter(dt -> dt.getNome().equals(pnomeDado)).findFirst();
            if (dado.isPresent()) {
                return dado.get();
            }
            if (!pessoa.getCampoInstanciadoByNomeOuAnotacao(pnomeDado).isCampoNaoInstanciado()) {
                DadoCRM dadoNovo = new DadoCRM(pessoa);
                // dadoNovo.setTipoDadoCRM(tipoDadoCRM);
                // dadoNovo.getCampoInstanciado().setValor(dadoNovo);
                return dadoNovo;
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return null;
    }

    @Override
    public ItfTDadoDinamicoCRM atualizarDadoCRM(String pDocumento, String pnomeDado, Object pValor) throws ErroAcessandoCRM {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            String documento = UtilCRCStringsExtrator.filtrarApenasNumeros(pnomeDado);
            Pessoa pessoa = (Pessoa) UtilSBPersistencia.gerarConsultaDeEntidade(Pessoa.class, em)
                    .addcondicaoCampoIgualA(CPPessoa.documento, documento)
                    .getPrimeiroRegistro();
            Optional<DadoCRM> dado = pessoa.getDadosColetados().stream().filter(dt -> dt.getNome().equals(pnomeDado)).findFirst();
            if (dado.isPresent()) {
                DadoCRM dadoEncontrado = dado.get();
                try {
                    dadoEncontrado.getCampoInstanciado().setValorSeValido(pValor);
                    dadoEncontrado = UtilSBPersistencia.mergeRegistro(dadoEncontrado);
                    return dadoEncontrado;
                } catch (ErroValidacao ex) {
                    return null;
                }

            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return null;
    }

    @Override
    public String aplicarAssinaturaEMail(ComoUsuario pUsuario, String pConteudo) {
        String texto = pConteudo;

        String assinatura = getModeloEMail(pUsuario);
        if (assinatura == null || assinatura.isEmpty()) {
            assinatura = "[conteudo]";
        }
        if (!assinatura.contains("[conteudo]")) {
            throw new UnsupportedOperationException("Tag: [conteudo] não encontrada");
        }
        texto = assinatura.replace("[conteudo]", texto);

        texto = UtilCRCEmail.gerarConteudoEmailNormatizado(texto);
        texto = new ProcessadorEmailHtml(texto, CSS_REGULADOR).getHtmlProcessado();

        try {
            byte[] bytes = texto.getBytes("UTF-8");
            texto = new String(bytes, "UTF-8");
            return texto;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CRMCarameloCodePadraoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return texto;
    }

    @Override
    public String getModeloEmailPadrao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String iaCorrijaOTexto(String pTExto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String iaMelhoreEsteTexto(String pTexto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String iaMelhoreEsteTExtoFormal(String pTExto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String iaMelhoreEsteTExtoInformal(String pTExto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getModeloEMail(ComoUsuario pUsuario) {
        if (!(pUsuario instanceof UsuarioCRM)) {
            return "";
        }
        UsuarioCRM usuario = (UsuarioCRM) pUsuario;
        return ((UsuarioCRM) usuario).getAssinaturaEmail();
    }

    @Override
    public String gerarTagLink(String pTexto, String pLink) {
        return " <a href='" + pLink + "' >  " + pTexto + "  </a> ";
    }

    @Override
    public boolean enviarEMailAplicandoModeloAssinatura(ComoUsuario pRemetente, ComoEmailSimples pEmail, List<ComoArquivo> pArquivos) throws ErroEnvioEmail {
        JSONParser parser = new JSONParser();

        UsuarioCRM usuarioCRM = (UsuarioCRM) pRemetente;
        ItfServidorEmailAvancado servidorPadrao = null;
        servidorPadrao = getEMailServer(pRemetente);
        if (servidorPadrao != null) {
            try {
                JSONObject jsonCampoPara = (JSONObject) parser.parse(pEmail.getCampoInstanciadoByNomeOuAnotacao(CPEnvioEmail.htmlmailcampoparajson).getValor().toString());
                JSONObject jsonCampoEmCopia = (JSONObject) parser.parse(pEmail.getCampoInstanciadoByNomeOuAnotacao(CPEnvioEmail.htmlmailcampoemcopiajson).getValor().toString());

                String texto = pEmail.getTextoMensagem();

                texto = aplicarAssinaturaEMail(pRemetente, texto);

                String pMail = UtilCRCEmailAvancado.enviarEmailComAnexoV2(servidorPadrao, jsonCampoPara, jsonCampoEmCopia, pEmail.getAssunto(), texto, pArquivos);

                if (pMail != null) {
                    return true;
                }
            } catch (ParseException ex) {
                throw new ErroEnvioEmail("Erro criando Remententes e destinatários" + ex.getMessage());
            }
        } else {
            if (!UtilCRCListas.isNuloOuVazio(pArquivos)) {
                throw new UnsupportedOperationException("O serviço de envio com anxos não é suportado com o servidor padrão, configure uma caixa de entrada para o usuário");
            }
            return UtilCRCEmail.enviarPorServidorPadraoV2(pEmail.getCPinst("destinatarios").getValorTextoFormatado(), pEmail.getTextoMensagem(), pEmail.getAssunto()) != null;
        }
        return false;
    }

    @Override
    public boolean enviarEMailAplicandoModeloAssinatura(ComoUsuario pRemetente, ComoContatoSimples pDestinatario, String pAssunto, String pTexto) throws ErroEnvioEmail {
        JSONParser parser = new JSONParser();

        UsuarioCRM usuarioCRM = (UsuarioCRM) pRemetente;
        ItfServidorEmailAvancado servidorPadrao = null;
        servidorPadrao = getEMailServer(pRemetente);

        pTexto = aplicarAssinaturaEMail(pRemetente, pTexto);
        JSONObject campoPara = new JSONObject();
        JSONArray contatosJson = new JSONArray();
        campoPara.put("contatos", contatosJson);
        JSONObject novoColaborador = new JSONObject();
        novoColaborador.put("email", pDestinatario.getEmail());
        if (!UtilCRCStringValidador.isNuloOuEmbranco(pDestinatario.getNome())) {
            novoColaborador.put("nome", pDestinatario.getNome());
        } else {
            novoColaborador.put("nome", pDestinatario.getEmail());
        }
        contatosJson.add(novoColaborador);
        campoPara.put("contatos", contatosJson);

        if (servidorPadrao != null) {
            String pMail = UtilCRCEmailAvancado.enviarEmailComAnexoV2(servidorPadrao, campoPara, null, pAssunto, pTexto, null);
            if (pMail != null) {
                return true;
            }
        } else {

            return UtilCRCEmail.enviarPorServidorPadraoV2(pDestinatario.getEmail(), pTexto, pAssunto) != null;
        }
        return false;
    }

    @Override
    public JsonObject getMedataDadosLead() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, String> getMarcadoresDeSubstituicao(ComoLead pPessoa) {
        HashMap<String, String> marcadores = new HashMap<>();
        return marcadores;

    }
}
