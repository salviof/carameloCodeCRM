package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.DATAHORAMAXIMOPESQUISA)
public class ValorLogicoEscopoPesquisaMelhorHorarioDatahoraMaximoPesquisa
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioDatahoraMaximoPesquisa(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!isCacheAtivado()) {
            Date dataInicial = getEscopoPesquisaMelhorHorario().getDataInicial();
            if (dataInicial == null) {
                dataInicial = new Date();
                getEscopoPesquisaMelhorHorario().setDataInicial(dataInicial);
                getEscopoPesquisaMelhorHorario().setDatahoraMaximoPesquisa(UtilCRCDataHora.incrementaDias(dataInicial, 30));
            }
            if (getEscopoPesquisaMelhorHorario().getDatahoraMaximoPesquisa() == null) {
                getEscopoPesquisaMelhorHorario().setDatahoraMaximoPesquisa(UtilCRCDataHora.incrementaDias(new Date(), 30));
            }

            ativarCache(5);
        }
        return getEscopoPesquisaMelhorHorario().getDatahoraMaximoPesquisa();
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
