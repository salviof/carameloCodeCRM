/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

/**
 *
 * @author salvio
 */
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoal.ContatoPessoal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@InfoObjetoSB(tags = "Solicitações de cliente", plural = "Solicitações de atividade")
public class SolicitacaoAtividadeCliente extends Solicitacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Contato")
    @ManyToOne(targetEntity = ContatoPessoal.class)
    private ContatoPessoal contatoPessoa;

    public SolicitacaoAtividadeCliente() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ATIVIDADE_CLIENTE.getRegistro());
    }

}
