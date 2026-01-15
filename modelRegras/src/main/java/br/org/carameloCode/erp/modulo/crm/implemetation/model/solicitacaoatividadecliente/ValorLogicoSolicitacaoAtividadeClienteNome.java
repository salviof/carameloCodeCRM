package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadecliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValorLogicoSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValoresLogicosSolicitacaoAtividadeCliente;

@ValorLogicoSolicitacaoAtividadeCliente(calculo = ValoresLogicosSolicitacaoAtividadeCliente.NOME)
public class ValorLogicoSolicitacaoAtividadeClienteNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAtividadeClienteNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCStringValidador.isNuloOuEmbranco(getSolicitacao().getNome())) {
            String texto = getSolicitacao().getUsuarioSolicitante().getNome();
            //   texto = texto + " solicita que a atividade  " + getSolicitacao().getTipoAtividade().getNome() + " seja executada para " + getSolicitacao().getPessoa().getNome();
            getSolicitacao().setNome(texto);
        }

        return getSolicitacao().getNome();

    }

    public SolicitacaoAtividadeCliente getSolicitacao() {
        return (SolicitacaoAtividadeCliente) getCampoInst().getObjetoDoAtributo();
    }

}
