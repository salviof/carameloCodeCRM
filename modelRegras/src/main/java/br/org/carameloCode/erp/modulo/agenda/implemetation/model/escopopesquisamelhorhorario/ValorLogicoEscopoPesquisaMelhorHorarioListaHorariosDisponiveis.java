package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.LISTAHORARIOSDISPONIVEIS)
public class ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private String hashUltimaPesquisa;

    @Override
    public Object getValor(Object... pEntidade) {

        boolean hashultimaPesquisaAlterado = true;
        if (!isCacheAtivado()) {
            if (hashultimaPesquisaAlterado) {
                EscopoPesquisaMelhorHorario escopo = getEscopoPesquisaMelhorHorario();
                escopo.setListaHorariosDisponiveis(new ArrayList<>());

                List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = MapaHorariosDisponiveis.getHorarioDisponivelPrimeiras9pcoes(getEscopoPesquisaMelhorHorario());
                escopo.setListaHorariosDisponiveis(horariosDisponiveis);
                ativarCache(3);
            }
        }

        return getEscopoPesquisaMelhorHorario().getListaHorariosDisponiveis();
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
