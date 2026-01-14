/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Solicitação atualização de arquivo", plural = "SOlicitações de atualização de arquivos")
@Entity
public class SolicitacaoAtualizacaoArquivoEquipe extends Solicitacao {

    public SolicitacaoAtualizacaoArquivoEquipe() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ATUALIZAR_DOCUMENTO_EQUIPE.getRegistro());
    }

    @ManyToOne(targetEntity = ArquivoAnexado.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE)
    private ArquivoAnexado arquivo;

    public ArquivoAnexado getArquivo() {
        return arquivo;
    }

    public void setArquivo(ArquivoAnexado arquivo) {
        this.arquivo = arquivo;
    }

}
