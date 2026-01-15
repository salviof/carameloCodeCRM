package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadorPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadoresPessoa;

@ValidadorPessoa(validador = ValidadoresPessoa.RESPONSAVEL)
public class ValidacaoPessoaResponsavel extends ValidacaoGenerica<Pessoa> {

    public ValidacaoPessoaResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getPessoa().getId() != null && getPessoa().getId() > 0) {
            if (getPessoa().getContatoPrincipal() != null) {
                if (!getPessoa().getContatoPrincipal().getNome().equals(getPessoa().getResponsavel())) {
                    throw new ErroValidacao("Edite os contatos para alterar o nome do responsável");
                }
            }

        }
        String valor = (String) pValor;
        if (!UtilCRCStringValidador.isNuloOuEmbranco(valor)) {
            ContatoProspecto contato = (ContatoProspecto) getPessoa().getCPinst(CPPessoa.contatoprincipal).getValor();
            contato.setNome(valor);
            return FabTipoWidgetFormulario.getCampos(contato.getCPinst("nome"));
        }

        return null;
    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }
}
