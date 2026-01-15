package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.TipoPesquisaLead;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadorPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadoresPesquisaLead;

@ValidadorPesquisaLead(validador = ValidadoresPesquisaLead.TIPOPESQUISA)
public class ValidacaoPesquisaLeadTipoPesquisa extends ValidacaoGenerica<PesquisaLead> {

    public ValidacaoPesquisaLeadTipoPesquisa(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        if (pValor != null) {
            TipoPesquisaLead tipoPesquisa = (TipoPesquisaLead) pValor;
            switch (tipoPesquisa.getTipoPesquisa()) {
                case MEUS_LEADS:
                    break;
                case ORIGEM_PUBLICAS:
                    break;
                case ORIGEM_PRIVADA:
                    break;
                case LEADS_URGENTES:
                    break;
                case PESQUISA_LIVRE:
                    getPesquisaLead().setMomentoAtual(true);
                    getPesquisaLead().setDatafinal(new Date());
                    getPesquisaLead().setDatainicial(UtilCRCDataHora.decrementarDias(new Date(), 360));
                    getPesquisaLead().setOrigem(null);
                    getPesquisaLead().setUsuario(null);

                    break;
                default:
                    throw new AssertionError(tipoPesquisa.getTipoPesquisa().name());

            }

            getPesquisaLead().setTipoPesquisa(tipoPesquisa);
        }

        return null;
    }

    public PesquisaLead getPesquisaLead() {
        return getObjetoDoAtributo();
    }
}
