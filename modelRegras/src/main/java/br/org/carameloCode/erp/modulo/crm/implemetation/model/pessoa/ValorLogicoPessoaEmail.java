package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.Optional;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.EMAIL)
public class ValorLogicoPessoaEmail extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        final String valorAtual = getPessoa().getEmail();
        if (getPessoa().getId() != null && getPessoa().getId() > 0) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(valorAtual)) {
                if (valorAtual.contains("mailto:")) {
                    getPessoa().setEmail(valorAtual.replace("mailto:", ""));
                }

                if (getPessoa().getContatoPrincipal() != null && !UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getContatoPrincipal().getEmail())) {

                    try {
                        getPessoa().getCPinst("email").setValorSeValido(getPessoa().getContatoPrincipal().getEmail());
                    } catch (ErroValidacao t) {

                    }

                } else {
                    Optional<ContatoProspecto> opcoes = getPessoa().getContatosComEmail().stream().filter(ct -> !UtilCRCStringValidador.isNuloOuEmbranco(ct.getEmail())).
                            findFirst();
                    if (opcoes.isPresent()) {
                        getPessoa().setEmail(opcoes.get().getEmail());
                    }
                }
            } else {

                if (getPessoa().getContatoPrincipal() != null && !UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getContatoPrincipal().getEmail())) {
                    getPessoa().setEmail(getPessoa().getContatoPrincipal().getEmail());
                } else {
                    if (!getPessoa().getContatosProspecto().isEmpty()) {
                        Optional<ContatoProspecto> opcoes = getPessoa().getContatosComEmail().stream().filter(ct -> !UtilCRCStringValidador.isNuloOuEmbranco(ct.getEmail())).
                                findFirst();
                        if (opcoes.isPresent()) {
                            getPessoa().setEmail(opcoes.get().getEmail());
                        }
                    }
                }

            }
        }

        return getPessoa().getEmail();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
