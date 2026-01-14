/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação confirnação cliente", plural = "Solicitações de confirmações de cliente")
public class SolicitacaoConfirmacaoCliente extends Solicitacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    @Column(length = 4000)
    private String descricaoConfirmacao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Contato")
    @ManyToOne(targetEntity = ContatoPessoal.class)
    private ContatoPessoal contatoPessoa;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class, UsuarioCRM.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());

    }

    public String getDescricaoConfirmacao() {
        return descricaoConfirmacao;
    }

    public void setDescricaoConfirmacao(String descricaoConfirmacao) {
        this.descricaoConfirmacao = descricaoConfirmacao;
    }

}
