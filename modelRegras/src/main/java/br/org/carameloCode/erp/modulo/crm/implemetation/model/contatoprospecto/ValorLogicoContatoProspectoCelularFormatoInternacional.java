package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValorLogicoContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValoresLogicosContatoProspecto;

@ValorLogicoContatoProspecto(calculo = ValoresLogicosContatoProspecto.CELULARFORMATOINTERNACIONAL)
public class ValorLogicoContatoProspectoCelularFormatoInternacional
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoProspectoCelularFormatoInternacional(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        String telefoneFormatoInternacional = getContato().getCelular();

        if (telefoneFormatoInternacional == null || telefoneFormatoInternacional.isEmpty()) {
            getContato().setCelularFormatoInternacional(null);
        } else {
            telefoneFormatoInternacional = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefoneFormatoInternacional);
            getContato().setCelularFormatoInternacional(telefoneFormatoInternacional);

        }
        return getContato().getCelularFormatoInternacional();
    }

    public ContatoProspecto getContato() {
        return (ContatoProspecto) getCampoInst().getObjetoDoAtributo();
    }

}
