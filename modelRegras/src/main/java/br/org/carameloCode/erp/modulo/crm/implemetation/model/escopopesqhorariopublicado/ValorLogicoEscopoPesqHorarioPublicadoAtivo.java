package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioAtivo;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.ATIVO)
public class ValorLogicoEscopoPesqHorarioPublicadoAtivo
        extends
        ValorLogicoEscopoPesquisaMelhorHorarioAtivo {

    public ValorLogicoEscopoPesqHorarioPublicadoAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getEscopo().setAtivo(getEscopo().isPublicado());
        if (getEscopo().getDataHoraTokenPublicoExpira().getTime() <= new Date().getTime()) {
            getEscopo().setAtivo(false);
        }
        return getEscopo().isAtivo();
    }

    public EscopoPesqHorarioPublicado getEscopo() {
        return (EscopoPesqHorarioPublicado) getCampoInst().getObjetoDoAtributo();
    }

}
