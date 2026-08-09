package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublicoAtivo;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.ATIVO)
public class ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoAtivo
        extends
        ValorLogicoDisponibilidadeAtdmtPublicoAtivo {

    public ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoAtivo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
