/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDReuniaoAtivaHorario extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDReuniaoAtivaHorario(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getDadoDinamico().setValorEmpacotado(null);
        List<ReservaHorario> reservasAtivas = new ArrayList<>();
        if (getDadoDinamico().getProspectoEmpresa() == null || getDadoDinamico().getProspectoEmpresa().getContatoPrincipal() == null) {
            return null;
        }
        getDadoDinamico().getProspectoEmpresa().getContatoPrincipal().getReservas()
                .stream().filter(rs -> !rs.getStatus().equals(FabStatusReservaHorario.REALIZADO.getRegistro()))
                .filter(rs -> UtilCRCDataHora.isDiaIgual(rs.getInicioReservaAtendente(), new Date()))
                .forEach(reservasAtivas::add);
        UsuarioCrmCliente usrCliente = getDadoDinamico().getProspectoEmpresa().getContatoPrincipal().getUsuarioVinculado();
        if (usrCliente != null) {
            String texto = usrCliente.getCPinst("metadados").getValorComoEntidadeSimples().getCPinst("textoProximaReserva").getValor().toString();
            getDadoDinamico().setValorEmpacotado(texto);
        }

        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
