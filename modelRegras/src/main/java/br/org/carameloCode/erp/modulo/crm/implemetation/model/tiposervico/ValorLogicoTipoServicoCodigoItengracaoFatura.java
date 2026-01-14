package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico;

import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValorLogicoTipoServico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValoresLogicosTipoServico;

@ValorLogicoTipoServico(calculo = ValoresLogicosTipoServico.CODIGOITENGRACAOFATURA)
public class ValorLogicoTipoServicoCodigoItengracaoFatura
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoServicoCodigoItengracaoFatura(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            List<SistemaERPConfiavel> sistemasErp = UtilSBPersistencia.getListaTodos(SistemaERPConfiavel.class, em);
            Optional<SistemaERPConfiavel> pesquisaSistema = sistemasErp.stream().filter(st -> st.getDominio().contains("fatura")).findFirst();
            if (pesquisaSistema.isPresent()) {
                String codigo = ERPIntegracaoSistemasApi.RESTFUL.getRepositorioLinkEntidadesByID().getCodigoApiExterna(pesquisaSistema.get(), getTipoServico());
                if (codigo != null) {
                    getTipoServico().setCodigoItengracaoFatura(codigo);
                }
            }
            setCacheSegundosPadrao(30);
            UtilSBPersistencia.fecharEM(em);
        }

        return getTipoServico().getCodigoItengracaoFatura();
    }

    public TipoServico getTipoServico() {
        return (TipoServico) getCampoInst().getObjetoDoAtributo();
    }
}
