package br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValorLogicoAreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValoresLogicosAreaTrabalho;

@ValorLogicoAreaTrabalho(calculo = ValoresLogicosAreaTrabalho.NAOEXECUTADOAGENDAQTD)
public class ValorLogicoAreaTrabalhoNaoExecutadoAgendaQtd
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAreaTrabalhoNaoExecutadoAgendaQtd(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return 0;
    }

    public AreaTrabalho getAreaTrabalho() {
        return (AreaTrabalho) getCampoInst().getObjetoDoAtributo();
    }
}
