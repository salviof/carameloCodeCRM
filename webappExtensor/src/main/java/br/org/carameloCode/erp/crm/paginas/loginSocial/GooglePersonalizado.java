package br.org.carameloCode.erp.crm.paginas.loginSocial;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCClienteRest;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringUrl;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientCabecalho;
import jakarta.json.JsonObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brickred.socialauth.AbstractProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Permission;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.exception.AccessTokenExpireException;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.oauthstrategy.OAuthStrategyBase;
import org.brickred.socialauth.provider.GoogleImpl;
import org.brickred.socialauth.util.AccessGrant;
import org.brickred.socialauth.util.Constants;
import org.brickred.socialauth.util.OAuthConfig;
import org.brickred.socialauth.util.OpenIdConsumer;
import org.brickred.socialauth.util.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author salvio
 */
public class GooglePersonalizado extends AbstractProvider implements AuthProvider, ItfTokenGestaoOauth {

    private static final String OAUTH_SCOPE = "https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile+openid+openid+openid+https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile";
//redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+openid+openid+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&access_type=offline

    private static final Map<String, String> ENDPOINTS;
    private final Log LOG = LogFactory.getLog(GoogleImpl.class);

    private Permission scope;
    private AccessGrant accessToken;
    private OAuthConfig config;
    private Profile userProfile;
    private Oauth2Google authenticationStrategy;

    static {
        ENDPOINTS = new HashMap<String, String>();
        ENDPOINTS.put(Constants.OAUTH_REQUEST_TOKEN_URL,
                "https://oauth2.googleapis.com/token");
        ENDPOINTS.put(Constants.OAUTH_ACCESS_TOKEN_URL,
                "https://oauth2.googleapis.com/token");
        ENDPOINTS.put(Constants.OAUTH_AUTHORIZATION_URL, "https://accounts.google.com/o/oauth2/auth");

    }

    public GooglePersonalizado() throws Exception {

    }

    public GooglePersonalizado(OAuthConfig pConfig) throws Exception {

        config = pConfig;
        accessToken = null;
        if (config.getCustomPermissions() != null) {
            scope = Permission.CUSTOM;
        }
        if (config.getRequestTokenUrl() != null) {
            ENDPOINTS.put(Constants.OAUTH_REQUEST_TOKEN_URL,
                    config.getRequestTokenUrl());
        } else {
            config.setRequestTokenUrl(ENDPOINTS
                    .get(Constants.OAUTH_AUTHORIZATION_URL));
        }

        if (config.getAccessTokenUrl() != null) {
            ENDPOINTS.put(Constants.OAUTH_ACCESS_TOKEN_URL,
                    config.getAccessTokenUrl());
        } else {
            config.setAccessTokenUrl(ENDPOINTS
                    .get(Constants.OAUTH_ACCESS_TOKEN_URL));
        }

        //authenticationStrategy = new Hybrid(config, ENDPOINTS);
        authenticationStrategy = new Oauth2Google(config, ENDPOINTS);
        authenticationStrategy.setPermission(scope);
        authenticationStrategy.setScope(getScope());

    }

    private Profile getProfile(final Map<String, String> requestParams) {
        userProfile = OpenIdConsumer.getUserInfo(requestParams);
        userProfile.setProviderId(getProviderId());
        LOG.debug("User Info : " + userProfile.toString());
        return userProfile;
    }

    @Override
    protected List<String> getPluginsList() {
        return new ArrayList<>();
    }

    @Override
    protected OAuthStrategyBase getOauthStrategy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getLoginRedirectURL(String pUrlSucesso) throws Exception {
        String url = authenticationStrategy.getLoginRedirectURL(pUrlSucesso);
        LOG.info("Redirection to following URL should happen : " + url);
        return url;
    }

    @Override
    public Profile verifyResponse(Map<String, String> map) throws Exception {
        System.out.println("Verificando resposta");

        for (String chave : map.keySet()) {
            System.out.println(chave);
            System.out.println(map.get(chave));
        }
        if (map.keySet().stream().filter(chave -> chave.equals("code")).findFirst().isPresent()) {
            accessToken = authenticationStrategy.verifyResponse(map, "POST");

            for (Entry<String, Object> chavaValor : accessToken.getAttributes().entrySet()) {
                System.out.println(chavaValor.getKey());
                System.out.println(chavaValor.getValue());
            }
            return getUserProfile();
        } else {

            Profile perfil = getProfile(map);
            return perfil;
        }
    }

    @Override
    public Response updateStatus(String string) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Contact> getContactList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Profile getUserProfile() throws Exception {
        //
        if (userProfile != null) {
            return userProfile;
        }
        try {
            String chave = accessToken.getKey();
            Map<String, String> cabecalho = UtilSBApiRestClientCabecalho.criarCabeCalhoPadraoBearer(chave);
            RespostaWebServiceSimples resp = UtilSBApiRestClient
                    .getRespostaRest("https://www.googleapis.com/userinfo/v2/me", FabTipoConexaoRest.GET, false,
                            cabecalho, null);
            System.out.println(resp.getRespostaTexto());
            if (resp.isSucesso()) {
                JsonObject jsonDadosUsuario = UtilCRCJson.getJsonObjectByTexto(resp.getRespostaTexto());
                userProfile = new Profile();
                userProfile.setFullName(jsonDadosUsuario.getString("name"));
                userProfile.setEmail(jsonDadosUsuario.getString("email"));
                userProfile.setFirstName(jsonDadosUsuario.getString("given_name"));
                userProfile.setProfileImageURL(jsonDadosUsuario.getString("picture"));

            }
        } catch (Throwable t) {

        }
        return userProfile;
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setPermission(Permission prmsn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response api(String string, String string1, Map<String, String> map, Map<String, String> map1, String string2) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AccessGrant getAccessGrant() {
        return accessToken;
    }

    @Override
    public String getProviderId() {
        return "googleColetivoJava";
    }

    @Override
    public void setAccessGrant(AccessGrant ag) throws AccessTokenExpireException, SocialAuthException {
        accessToken = ag;
    }

    @Override
    public Response uploadImage(String string, String string1, InputStream in) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getScope() {
        String scopeStr;
        if (Permission.AUTHENTICATE_ONLY.equals(scope)) {
            scopeStr = null;
        } else if (Permission.CUSTOM.equals(scope)) {
            StringBuffer sb = new StringBuffer();
            String arr[] = config.getCustomPermissions().split(",");
            sb.append(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                sb.append(" ").append(arr[i]);
            }
            scopeStr = sb.toString();
        } else {
            scopeStr = OAUTH_SCOPE;
        }
        String pluginScopes = getPluginsScope(config);
        if (pluginScopes != null) {
            if (scopeStr != null) {
                scopeStr += "," + pluginScopes;
            } else {
                scopeStr = pluginScopes;
            }
        }
        return scopeStr;
    }

    @Override
    public boolean isCodigoSolicitacaoRegistrado() {
        if (accessToken == null) {
            return false;
        }
        return true;
    }

    @Override
    public String getUrlServidorApiRest() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUrlObterCodigoSolicitacao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUrlRetornoReceberCodigoSolicitacao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String extrairNovoCodigoSolicitacao(HttpServletRequest pRespostaServidorAutenticador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCodigoSolicitacao(String pCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JsonObject pJson) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean armazenarRespostaToken(String pJson) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JsonObject loadTokenArmazenadoComoJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getToken() {
        if (accessToken == null) {
            return null;
        }
        return accessToken.getKey();
    }

    @Override
    public ItfTokenDeAcessoExterno getTokenCompleto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ConfigModulo getConfig() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isTemTokemAtivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItfTokenDeAcessoExterno gerarNovoToken() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean excluirToken() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FabStatusToken getStatusToken() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FabTipoAgenteClienteApi getTipoAgente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarToken() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItfTokenDeAcessoExterno loadTokenArmazenado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getIdentificacaoToken() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
