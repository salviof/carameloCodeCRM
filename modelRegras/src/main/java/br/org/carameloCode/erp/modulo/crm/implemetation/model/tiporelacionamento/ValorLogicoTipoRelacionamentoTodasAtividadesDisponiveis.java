package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValorLogicoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValoresLogicosTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoTipoRelacionamento(calculo = ValoresLogicosTipoRelacionamento.TODASATIVIDADESDISPONIVEIS)
public class ValorLogicoTipoRelacionamentoTodasAtividadesDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoRelacionamentoTodasAtividadesDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        try {
            if (!valorDefinido) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                try {
                    List<TipoAtividadeCRM> tiposAtividades = new ArrayList<>();
                    if (getTipoRelacionamento().getMetaRelacionamento() != null) {
                        if (!getTipoRelacionamento().getMetaRelacionamento().getTiposAtividadeDisponiveis().isEmpty()) {
                            getTipoRelacionamento().getMetaRelacionamento().getTiposAtividadeDisponiveis().forEach(tiposAtividades::add);
                        }
                    }
                    getTipoRelacionamento().getTiposAtividadeDisponiveis().stream().filter(tp
                            -> !tiposAtividades.contains(tp)).forEach(tiposAtividades::add);
                    getTipoRelacionamento().setTodasAtividadesDisponiveis(tiposAtividades);
                } finally {
                    UtilSBPersistencia.fecharEM(em);
                }
            }

        } finally {
            valorDefinido = true;
        }

        return getTipoRelacionamento().getTodasAtividadesDisponiveis();
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoRaizDoAtributo();
    }
}
