package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado.ValidacaoEscopoPesqHorarioPublicadoDiaSemanaSexta;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.DIASEMANASEXTA)
public class ValidacaoExtErpcaramEscopoPesqHorarioPublicadoDiaSemanaSexta
        extends
        ValidacaoEscopoPesqHorarioPublicadoDiaSemanaSexta {

    public ValidacaoExtErpcaramEscopoPesqHorarioPublicadoDiaSemanaSexta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {
        return super.validar(o);
    }

}
