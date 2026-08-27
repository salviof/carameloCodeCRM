package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico.ValidacaoDisponibilidadeAtdmtPublicoDiaSemanaQuarta;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValidadorDisponibilidadeAtdmtPublico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValidadoresDisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorDisponibilidadeAtdmtPublico(validador = ValidadoresDisponibilidadeAtdmtPublico.DIASEMANAQUARTA)
public class ValidacaoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaQuarta
        extends
        ValidacaoDisponibilidadeAtdmtPublicoDiaSemanaQuarta {

    public ValidacaoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaQuarta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {
        return super.validar(o);
    }

    public DisponibilidadeAtdmtPublico getDisponibilidadeAtdmtPublico() {
        return getObjetoDoAtributo();
    }
}
