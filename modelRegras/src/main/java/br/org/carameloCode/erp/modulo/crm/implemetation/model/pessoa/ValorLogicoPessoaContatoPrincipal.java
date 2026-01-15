package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.CONTATOPRINCIPAL)
public class ValorLogicoPessoaContatoPrincipal
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaContatoPrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getContatoPrincipal() == null) {

            if (!getPessoa().getContatosProspecto().isEmpty()) {
                for (ContatoProspecto c : getPessoa().getContatosProspecto()) {
                    if (c.isUmContatoPrincipal()) {
                        getPessoa().setContatoPrincipal(c);
                        return getPessoa().getContatoPrincipal();
                    }
                }
                getPessoa().getContatosProspecto().get(0).setUmContatoPrincipal(true);
                getPessoa().setContatoPrincipal(getPessoa().getContatosProspecto().get(0));
                return getPessoa().getContatoPrincipal();
            } else {
                try {

                    if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getResponsavel()) || getPessoa().getId() == null) {
                        getPessoa().setContatoPrincipal(new ContatoProspecto());
                        getPessoa().getContatoPrincipal().prepararNovoObjeto(getPessoa());
                        getPessoa().getContatoPrincipal().setUmContatoPrincipal(true);
                        getPessoa().getContatoPrincipal().setNome(getPessoa().getResponsavel());
                        if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getEmail())) {
                            try {
                                getPessoa().getContatoPrincipal().getCPinst(CPContatoProspecto.email).setValorSeValido(getPessoa().getEmail());
                            } catch (ErroValidacao ex) {
                                Logger.getLogger(ValorLogicoPessoaContatoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getTelefonePrincipal())) {
                            if (UtilCRCStringTelefone.isUmCelular(getPessoa().getTelefonePrincipal())) {
                                getPessoa().getContatoPrincipal().setCelular(getPessoa().getTelefonePrincipal());
                            }
                        }

                    }

                } catch (ErroPreparandoObjeto ex) {
                    Logger.getLogger(ValorLogicoPessoaContatoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return getPessoa().getContatoPrincipal();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
