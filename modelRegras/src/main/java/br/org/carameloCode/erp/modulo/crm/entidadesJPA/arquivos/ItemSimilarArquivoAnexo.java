/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilarGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author sfurbino
 */
public class ItemSimilarArquivoAnexo extends ItemSimilarGenerico {

    public ItemSimilarArquivoAnexo(ComoEntidadeSimples pObjetoAnalizado, String parametro) {
        super(pObjetoAnalizado, parametro, FabTipoPesquisaGenerica.NOME);
    }

    @Override
    public String getTextoReferencia() {
        try {
            return objetoAnalizado.getNome();

        } catch (Throwable t) {
            return null;
        }

    }

}
