package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.LINKDEACESSO)
public class ValorLogicoEscopoPesqHorarioPublicadoLinkDeAcesso
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesqHorarioPublicadoLinkDeAcesso(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEscopo) {
        if (!getEscopoPesqHorarioPublicado().isPublicado()) {
            getEscopoPesqHorarioPublicado().setLinkDeAcesso("Sem link, o escopo não foi publicado");
        } else {

        }
        return getEscopoPesqHorarioPublicado().getLinkDeAcesso();
    }

    public EscopoPesqHorarioPublicado getEscopoPesqHorarioPublicado() {
        return (EscopoPesqHorarioPublicado) getCampoInst().getObjetoDoAtributo();
    }

}
