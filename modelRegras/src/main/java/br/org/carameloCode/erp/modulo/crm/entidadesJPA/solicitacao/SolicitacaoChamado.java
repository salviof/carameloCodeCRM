/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação de chamado", plural = "Solicitações de chamados")
public class SolicitacaoChamado extends Solicitacao {

    @ManyToOne(targetEntity = ChamadoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ChamadoCliente chamado;

    public SolicitacaoChamado() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_CHAMADO.getRegistro());
    }

    public ChamadoCliente getChamado() {
        return chamado;
    }

    public void setChamado(ChamadoCliente chamado) {
        this.chamado = chamado;
    }

}
