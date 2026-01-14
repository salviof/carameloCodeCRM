package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ATENDENTELOGADOTEMACESSO)
public class ValorLogicoPessoaAtendenteLogadoTemAcesso
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaAtendenteLogadoTemAcesso(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valorDefinido) {
            try {
                UsuarioCRM usuarioLogado = (UsuarioCRM) SBCore.getUsuarioLogado();
                getPessoa().setAtendenteLogadoTemAcesso(getPessoa().getUsuariosResponsaveis().contains(usuarioLogado));
            } catch (Throwable t) {
                getPessoa().setAtendenteLogadoTemAcesso(false);
                valorDefinido = true;
            }
        }

        return getPessoa().isAtendenteLogadoTemAcesso();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
