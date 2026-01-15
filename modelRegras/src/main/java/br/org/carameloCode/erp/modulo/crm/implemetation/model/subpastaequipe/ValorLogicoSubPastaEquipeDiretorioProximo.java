package br.org.carameloCode.erp.modulo.crm.implemetation.model.subpastaequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValorLogicoSubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValoresLogicosSubPastaEquipe;

@ValorLogicoSubPastaEquipe(calculo = ValoresLogicosSubPastaEquipe.DIRETORIOPROXIMO)
public class ValorLogicoSubPastaEquipeDiretorioProximo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSubPastaEquipeDiretorioProximo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getSubPasta().getCategoriaEquipe() != null) {
            getSubPasta().setDiretorioProximo(getSubPasta().getCategoriaEquipe());
        } else {
            boolean fim = false;
            SubPastaEquipe subpastaAtual = (SubPastaEquipe) getSubPasta().getPastaPai();
            int i = 0;
            while (!fim) {
                if (subpastaAtual.getCategoriaEquipe() != null) {
                    getSubPasta().setDiretorioProximo(subpastaAtual.getCategoriaEquipe());
                    fim = true;
                } else {
                    subpastaAtual = (SubPastaEquipe) subpastaAtual.getPastaPai();
                }
                if (i > 10 || subpastaAtual == null) {
                    fim = true;
                }
                i++;
            }

        }
        return getSubPasta().getDiretorioProximo();
    }

    public SubPastaEquipe getSubPasta() {
        return (SubPastaEquipe) getCampoInst().getObjetoDoAtributo();
    }
}
