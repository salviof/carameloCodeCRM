package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.PERTENCEAOUSUARIOLOGADO)
public class ValorLogicoPessoaPertenceAoUsuarioLogado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaPertenceAoUsuarioLogado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        try {
            UsuarioCRM usuario = (UsuarioCRM) SBCore.getUsuarioLogado();

            return getProsp().getUsuariosResponsaveis().contains(usuario);
        } catch (Throwable t) {
            return false;
        }

    }

    public Pessoa getProsp() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
