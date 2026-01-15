package br.org.carameloCode.erp.modulo.crm.implemetation.model.subpastaequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValorLogicoSubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe.ValoresLogicosSubPastaEquipe;

@ValorLogicoSubPastaEquipe(calculo = ValoresLogicosSubPastaEquipe.MIGALHASDEPAO)
public class ValorLogicoSubPastaEquipeMigalhasDePao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSubPastaEquipeMigalhasDePao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCListas.isNuloOuVazio(getSubPasta().getMigalhasDePao())) {
            List<SubPastaEquipe> migalhasDePao = new ArrayList<>();
            boolean fim = false;
            SubPastaEquipe subpastaAtual = (SubPastaEquipe) getSubPasta().getPastaPai();
            int i = 0;
            if (subpastaAtual == null) {
                fim = true;
            }
            while (!fim) {
                migalhasDePao.add(subpastaAtual);
                if (subpastaAtual.getCategoriaEquipe() == null) {

                    subpastaAtual = (SubPastaEquipe) subpastaAtual.getPastaPai();
                } else {
                    fim = true;
                }
                if (subpastaAtual == null) {
                    fim = true;
                }
                if (i > 10 || subpastaAtual == null) {
                    fim = true;
                }
                i++;
            }
            getSubPasta().setMigalhasDePao((List) migalhasDePao);
        }

        return getSubPasta().getMigalhasDePao();
    }

    public SubPastaEquipe getSubPasta() {
        return (SubPastaEquipe) getCampoInst().getObjetoDoAtributo();
    }
}
