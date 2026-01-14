package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadorPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadoresPessoa;

@ValidadorPessoa(validador = ValidadoresPessoa.LOCALIZACAO)
public class ValidacaoPessoaLocalizacao extends ValidacaoGenerica<Pessoa> {

    public ValidacaoPessoaLocalizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getPessoa().getLocalizacao() == null) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getCEP())) {
                if (getPessoa().getLocalizacao() != null) {
                    if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getLocalizacao().getCep())) {
                        getCampoInstanciado().getComoCampoLocalizacao().aplicarCEP(getPessoa().getLocalizacao().getCep());
                    }
                }
            }
        }
        normalizaEndereco(getPessoa());

        return null;
    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }

    private void normalizaEndereco(Pessoa pEmp) {
        if (pEmp.getLocalizacao() != null) {
            if (pEmp.getLocalizacao().getBairro() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getNome())) {
                    pEmp.setLocalizacao(null);
                    return;
                }
            }
            if (pEmp.getLocalizacao().getBairro() != null && pEmp.getLocalizacao().getBairro().getCidade() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getCidade().getNome())) {
                    pEmp.setLocalizacao(null);
                    return;
                }
            }
            if (pEmp.getLocalizacao().getBairro() != null && pEmp.getLocalizacao().getBairro().getCidade().getUnidadeFederativa() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getCidade().getUnidadeFederativa().getNome())) {
                    pEmp.setLocalizacao(null);

                }
            }
        }
    }
}
