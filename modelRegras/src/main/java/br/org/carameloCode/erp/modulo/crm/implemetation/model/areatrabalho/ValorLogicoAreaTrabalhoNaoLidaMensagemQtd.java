package br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValorLogicoAreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValoresLogicosAreaTrabalho;

@ValorLogicoAreaTrabalho(calculo = ValoresLogicosAreaTrabalho.NAOLIDAMENSAGEMQTD)
public class ValorLogicoAreaTrabalhoNaoLidaMensagemQtd
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAreaTrabalhoNaoLidaMensagemQtd(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valorCalculado;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valorCalculado) {
            //TODO
            valorCalculado = true;
        }
        return getAreaTrabalho().getNaoLidaMensagemQtd();
    }

    public AreaTrabalho getAreaTrabalho() {
        return (AreaTrabalho) getCampoInst().getObjetoDoAtributo();
    }
}
