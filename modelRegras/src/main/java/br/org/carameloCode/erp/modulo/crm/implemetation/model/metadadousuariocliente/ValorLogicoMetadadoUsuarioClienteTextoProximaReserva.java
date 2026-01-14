package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadousuariocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValorLogicoMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValoresLogicosMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoMetadadoUsuarioCliente(calculo = ValoresLogicosMetadadoUsuarioCliente.TEXTOPROXIMARESERVA)
public class ValorLogicoMetadadoUsuarioClienteTextoProximaReserva
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoUsuarioClienteTextoProximaReserva(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String texto = "Sem reservas";
        ReservaHorario reserva = (ReservaHorario) getMetadadosClienteUsuario().getUsuario()
                .getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCrmCliente.reservaativamaisproxima).getValor();
        if (reserva == null) {
            getMetadadosClienteUsuario().setTextoProximaReserva(texto);
            return getMetadadosClienteUsuario().getTextoProximaReserva();
        } else {
            Date hoje = new Date();
            Date horarioAtividade = reserva.getInicioReservaAtendente();
            SimpleDateFormat formatacaoHorario = new SimpleDateFormat("HH:mm");
            String horario = formatacaoHorario.format(horarioAtividade);

            if (UtilCRCDataHora.isDiaIgual(hoje, horarioAtividade)) {
                texto = "Hoje às " + horario;
                getMetadadosClienteUsuario().setTextoProximaReserva(texto);
            } else if (UtilCRCDataHora.isDiaIgual(horarioAtividade, UtilCRCDataHora.incrementaDias(hoje, 1))) {
                texto = "Amanhã às " + horario;
                getMetadadosClienteUsuario().setTextoProximaReserva(texto);
            } else {
                long dias = UtilCRCDataHora.intervaloTempoDias(hoje, horarioAtividade);
                String diaDaSemana = UtilCRCDataHora.getDiaDaSemana(horarioAtividade);
                if (dias < 6) {
                    texto = diaDaSemana + " às " + horario;
                    getMetadadosClienteUsuario().setTextoProximaReserva(texto);
                } else {
                    Locale local = new Locale("pt", "BR");
                    SimpleDateFormat formatacaoFaraway = new SimpleDateFormat("dd'/'MM, EE", local);
                    texto = formatacaoFaraway.format(horarioAtividade) + ", às " + horario;
                    getMetadadosClienteUsuario().setTextoProximaReserva(texto);
                }
            }

        }
        return getMetadadosClienteUsuario().getTextoProximaReserva();
    }

    public MetadadoUsuarioCliente getMetadadosClienteUsuario() {
        return (MetadadoUsuarioCliente) getCampoInst().getObjetoDoAtributo();
    }
}
