package br.org.carameloCode.erp.modulo.crm.api.dominio;

import br.org.carameloCode.erp.modulo.crm.api.ErroAcessandoCRM;
import br.org.carameloCode.erp.modulo.crm.api.ErroObtendoDadosIntegracaoCRM;
import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.ApiIntegracaoRestfulimpl;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.MapaSistemasConfiaveis;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.parametros.ParametroListaRestful;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.erp.client.FabConfigModuloERPCRMClient;
import com.super_bits.modulos.SBAcessosModel.fabricas.FabSegurancaGruposPadrao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import jakarta.json.JsonObject;
import java.util.Map;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ComoLead;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidorEmailAvancado;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ItfTDadoDinamicoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.List;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.repositorioLinkEntidades.RepositorioLinkEntidadesGenerico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.chavesPublicasePrivadas.RepositorioChavePublicaPrivada;
import static com.super_bits.modulosSB.SBCore.modulos.chavesPublicasePrivadas.RepositorioChavePublicaPrivada.MARCADOR_INICIO_CHAVE_PUBLIC;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@CRMCarameloCodeRemoto
public class CRMCarameloCodeRemotoimpl extends RepositorioLinkEntidadesGenerico
        implements
        br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm {

    private static SistemaERPConfiavel sistemaCRM;
    private static ApiIntegracaoRestfulimpl interacaoERP = (ApiIntegracaoRestfulimpl) ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto();

    private SistemaERPConfiavel getSistemaCRM() throws ErroObtendoDadosIntegracaoCRM {
        if (sistemaCRM != null) {
            return sistemaCRM;
        }
        String hashchavePublica = SBCore.getConfigModulo(FabConfigModuloERPCRMClient.class).getPropriedade(FabConfigModuloERPCRMClient.HASH_CHAVE_PUBLICA_CRM);

        if (hashchavePublica == null || hashchavePublica.isEmpty()) {
            throw new ErroObtendoDadosIntegracaoCRM("O código hash do crm não foi configurado via config "
                    + "modulo" + FabConfigModuloERPCRMClient.class.getSimpleName() + "." + FabConfigModuloERPCRMClient.HASH_CHAVE_PUBLICA_CRM);
        }
        if (MapaSistemasConfiaveis.getSistemaByHashChavePublica(hashchavePublica) != null) {
            return MapaSistemasConfiaveis.getSistemaByHashChavePublica(hashchavePublica);
        }
        String caminhoChave = RepositorioChavePublicaPrivada.gerarPathPersistenciaIdentificadorChave(hashchavePublica);
        if (!new File(caminhoChave).isFile()) {
            try {
                new File(caminhoChave).createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CRMCarameloCodeRemotoimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw new ErroObtendoDadosIntegracaoCRM("Configure o hash em " + caminhoChave + "\n conteudo exemplo:" + MARCADOR_INICIO_CHAVE_PUBLIC + "[chave]" + RepositorioChavePublicaPrivada.MARCADOR_FINAL_CHAVE_PUBLIC);
        }
        String chavePublica = RepositorioChavePublicaPrivada.getChavePublicaByHash(hashchavePublica);

        String nomeSistema = "Api padrão CRM Casanova";
        String dominioCRM = SBCore.getConfigModulo(FabConfigModuloERPCRMClient.class).getPropriedade(FabConfigModuloERPCRMClient.DOMINIO_CRM);
        String usuarioCRM = SBCore.getConfigModulo(FabConfigModuloERPCRMClient.class).getPropriedade(FabConfigModuloERPCRMClient.USUARIO_ADMIN_CRM);
        boolean acessoLocal = dominioCRM.equals("localhost");
        String protocolo = "https://";
        String porta = "";
        if (acessoLocal) {
            protocolo = "http://";
            porta = "8080";
        }

        SistemaERPConfiavel sistema = new SistemaERPConfiavel();
        sistema.setDominio(dominioCRM);
        sistema.setNome(nomeSistema);
        sistema.setChavePublica(chavePublica);
        sistema.setUrlRecepcaoCodigo(protocolo + dominioCRM + porta + "/solicitacaoAuth2Recept/");
        sistema.setUrlPublicaEndPoint(protocolo + dominioCRM + porta + "/acoesRestful");
        sistema.setEmailusuarioAdmin(usuarioCRM);
        MapaSistemasConfiaveis.persistirNovoSistema(sistema);
        sistemaCRM = sistema;
        return sistema;
    }

    @Override
    public ItfTDadoDinamicoCRM getDadoCRM(String pDocumento, String pCodigoDAdo) throws ErroAcessandoCRM {

        ParametroListaRestful parametroPesquisaDAdoDOcumento = new ParametroListaRestful();
        parametroPesquisaDAdoDOcumento.setId(0l);
        parametroPesquisaDAdoDOcumento.setPagina(0);
        parametroPesquisaDAdoDOcumento.setFiltros(new HashMap<>());
        //parametroEquipe.getParametros().put("id", 1);
        parametroPesquisaDAdoDOcumento.getFiltros().put("documentoPessoa", pDocumento);
        parametroPesquisaDAdoDOcumento.getFiltros().put("tipoDadoCRM", pCodigoDAdo);

        if (!SBCore.getUsuarioLogado().getEmail().equals(FabConfigModuloERPCRMClient.USUARIO_ADMIN_CRM.getValorParametroSistema())) {
            UsuarioSB usuario = new UsuarioSB();
            usuario.setNome("Usuário Remoto crm CRM");
            usuario.setEmail(FabConfigModuloERPCRMClient.USUARIO_ADMIN_CRM.getValorParametroSistema());
            usuario.setGrupo(FabSegurancaGruposPadrao.GRUPO_ADMINISTRADOR.getRegistro());
            SBCore.getServicoSessao().getSessaoAtual().setUsuario(usuario);
        }

        try {
            ItfResposta respostaLista = interacaoERP
                    .getResposta(getSistema(), "FabAcaoCRMAtendimento.DADO_CRM_FRM_LISTAR", parametroPesquisaDAdoDOcumento);
            System.out.println(respostaLista.getRetorno());
        } catch (ErroObtendoDadosIntegracaoCRM ex) {
            throw new ErroAcessandoCRM(ex.getMessage());
        }

        return null;
    }

    public ItfSistemaERP getSistema() throws ErroObtendoDadosIntegracaoCRM {
        return getSistemaCRM();
    }

    @Override
    public String aplicarAssinaturaEMail(ComoUsuario comoUsuario,
            java.lang.String s) {
        return null;
    }

    @Override
    public String getModeloEmailPadrao() {
        return null;
    }

    @Override
    public String iaMelhoreEsteTexto(java.lang.String s) {
        return null;
    }

    @Override
    public String iaMelhoreEsteTExtoFormal(java.lang.String s) {
        return null;
    }

    @Override
    public String iaMelhoreEsteTExtoInformal(java.lang.String s) {
        return null;
    }

    @Override
    public JsonObject getMedataDadosLead() {
        return null;
    }

    @Override
    public Map getMarcadoresDeSubstituicao(ComoLead comoLead) {
        return null;
    }

    @Override
    public ItfServidorEmailAvancado getEMailServer(
            com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario comoUsuario) {
        return null;
    }

    @Override
    public String getModeloEMail(
            com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario comoUsuario) {
        return null;
    }

    @Override
    public String iaCorrijaOTexto(java.lang.String s) {
        return null;
    }

    @Override
    public String gerarTagLink(java.lang.String s, java.lang.String s1) {
        return null;
    }

    @Override
    public boolean enviarEMailAplicandoModeloAssinatura(
            com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario comoUsuario,
            ComoContatoSimples comoContatoSimples, java.lang.String s,
            java.lang.String s1) {
        return false;
    }

    @Override
    public boolean enviarEMailAplicandoModeloAssinatura(
            com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario comoUsuario,
            ComoEmailSimples comoEmailSimples, List list) {
        return false;
    }

    @Override
    public ItfTDadoDinamicoCRM atualizarDadoCRM(java.lang.String s,
            java.lang.String s1, java.lang.Object o) {
        return null;
    }
}
