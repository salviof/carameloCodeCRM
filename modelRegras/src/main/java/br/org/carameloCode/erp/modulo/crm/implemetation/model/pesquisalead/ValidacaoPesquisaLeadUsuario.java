package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadorPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadoresPesquisaLead;

@ValidadorPesquisaLead(validador = ValidadoresPesquisaLead.USUARIO)
public class ValidacaoPesquisaLeadUsuario extends ValidacaoGenerica<PesquisaLead> {

    public ValidacaoPesquisaLeadUsuario(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (pValor != null) {
            if (getPesquisaLead().getTipoPesquisa() != null) {
                switch (getPesquisaLead().getTipoPesquisa().getEnumVinculado()) {
                    case MEUS_LEADS:
                        break;
                    case ORIGEM_PUBLICAS:
                        break;
                    case ORIGEM_PRIVADA:
                        break;
                    case LEADS_URGENTES:
                        break;
                    case PESQUISA_LIVRE:
                        getPesquisaLead().getCPinst(CPPesquisaLead.tipopesquisa).setValorValidando(FabTipoPesquisaLeads.MEUS_LEADS.getRegistro());
                        break;
                    default:
                        throw new AssertionError(getPesquisaLead().getTipoPesquisa().getEnumVinculado().name());

                }
            }
        }
        getPesquisaLead().setUsuario((UsuarioCRM) pValor);
        return null;
    }

    public PesquisaLead getPesquisaLead() {
        return getObjetoDoAtributo();
    }
}
