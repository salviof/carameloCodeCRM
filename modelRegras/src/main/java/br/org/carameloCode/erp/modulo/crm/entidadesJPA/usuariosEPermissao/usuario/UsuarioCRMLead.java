package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNome;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalPostagem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Usuário Convidado", plural = "Usuários Convidados")
public class UsuarioCRMLead extends ContatoAnonimoDadoTansitorio implements ComoUsuario {

    public UsuarioCRMLead(String pNome, String pTelefone) {
        setNomeUsuario(pNome);
        setCelular(pTelefone);
    }

    @Override
    public String getSenha() {
        return getNomeUsuario();
    }

    @Override
    public ComoGrupoUsuario getGrupo() {
        return FabGruposCRMCaramelo.CRM_LEAD.getRegistro();
    }

    @Override
    public void setGrupo(ComoGrupoUsuario grupo) {

    }

    @Override
    public List<ComoGrupoUsuario> getGruposAdicionais() {
        return new ArrayList<>();
    }

    @Override
    public Date getDataCadastro() {
        return new Date();
    }

    @Override
    public String getApelido() {
        return UtilCRCStringNome.gerarNomeAbreviado(getNomeUsuario());
    }

    @Override
    public boolean isAtivo() {
        return true;
    }

    @Override
    public String getTipoUsuario() {
        return UsuarioCRMLead.class.getSimpleName();
    }

    @Override
    public ComoLocalPostagem getLocalizacao() {
        return null;
    }

    @Override
    public void instanciarNovoEndereco() {

    }

    @Override
    public String getTelefone() {
        return getCelular();
    }

    @Override
    public void setLocalizacao(ComoLocal pLocal) {

    }

    @Override
    public String getNomeLongo() {
        return getNomeUsuario() + " em " + getNomeEmpresa();
    }

    @Override
    public void setNomeLongo(String pnomeLongo) {

    }

    @Override
    public String getDescritivo() {
        return "Lead " + getNomeLongo();
    }

    @Override
    public void setDescritivo(String pDescritivo) {

    }

    @Override
    public List<String> getGaleria() {
        return new ArrayList<>();
    }

    @Override
    public void setAtivo(boolean pAtivo) {

    }

    @Override
    public String getImgMedia() {
        return null;
    }

    @Override
    public boolean isTemImagemMedioAdicionada() {
        return false;
    }

    @Override
    public String getImgGrande() {
        return null;
    }

    @Override
    public boolean isTemImagemGrandeAdicionada() {
        return false;
    }

}
