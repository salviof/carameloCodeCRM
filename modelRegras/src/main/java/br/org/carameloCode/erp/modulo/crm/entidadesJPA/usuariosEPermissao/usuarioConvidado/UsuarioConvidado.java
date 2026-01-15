/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Usuario cliente", plural = "Usuarios de clientes")
public class UsuarioConvidado extends UsuarioCRM {

    public UsuarioConvidado() {
        setGrupo(FabGruposIntranetCasaNova.CRM_CONVIDADO.getRegistro());
        setSenha(UtilCRCStringGerador.getStringRandomicaUUID());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prospeco_usuarioConvidado",
            joinColumns = @JoinColumn(name = "usuarioCRM_id"),
            inverseJoinColumns = @JoinColumn(name = "prospecto_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = UsuarioConvidado.class)
    private List<Pessoa> leadsCompartilhados;

    public List<Pessoa> getLeadsCompartilhados() {
        return leadsCompartilhados;
    }

    public void setLeadsCompartilhados(List<Pessoa> leadsCompartilhados) {
        this.leadsCompartilhados = leadsCompartilhados;
    }

}
