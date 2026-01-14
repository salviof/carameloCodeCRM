package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.POSSUIRESERVAATIVA)
public class ValorLogicoUsuarioCrmClientePossuiReservaAtiva
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClientePossuiReservaAtiva(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        ReservaHorario reserva = (ReservaHorario) getUsuarioCliente().getCPinst(CPUsuarioCrmCliente.reservaativamaisproxima).getValor();
        getUsuarioCliente().setPossuiReservaAtiva(reserva != null);
        return getUsuarioCliente().isPossuiReservaAtiva();
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }
}
