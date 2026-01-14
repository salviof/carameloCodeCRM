package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ATENDENTELOGADORESPONSAVELPRINCIPAL)
public class ValorLogicoPessoaAtendenteLogadoResponsavelPrincipal
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaAtendenteLogadoResponsavelPrincipal(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {
        boolean responsavelVIP = false;
        if (!valorDefinido) {
            if (SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.GRUPOADMIN)) {
                responsavelVIP = true;
            } else {
                UsuarioCRM userVenda = (UsuarioCRM) getPessoa().getCPinst("usuarioResponsavel").getValor();
                if (userVenda == null && !getPessoa().getUsuariosRespDisponiveis().isEmpty()) {
                    userVenda = (UsuarioCRM) getPessoa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
                }
                if (userVenda != null) {
                    if (userVenda.equals(SBCore.getUsuarioLogado())) {
                        responsavelVIP = true;
                    }
                }
                if (!responsavelVIP) {
                    UsuarioCRM userAtendimento = getPessoa().getUsuarioAtendimento();
                    if (getPessoa().getUsuarioAtendimento() != null) {

                        if (userAtendimento.equals(SBCore.getUsuarioLogado())) {
                            responsavelVIP = true;
                        }
                    }
                }
            }
            getPessoa().setAtendenteLogadoResponsavelPrincipal(responsavelVIP);
        }
        return getPessoa().isAtendenteLogadoResponsavelPrincipal();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
