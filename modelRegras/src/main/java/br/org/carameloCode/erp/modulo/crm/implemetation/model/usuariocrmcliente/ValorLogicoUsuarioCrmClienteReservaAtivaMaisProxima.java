package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.RESERVAATIVAMAISPROXIMA)
public class ValorLogicoUsuarioCrmClienteReservaAtivaMaisProxima
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteReservaAtivaMaisProxima(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, em);
            consulta.addCondicaoManyToOneIgualA("atendidoResponsavel", getUsuarioCliente());
            consulta.addCondicaoManyToOneContemNoIntervalo("status", Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro()));
            consulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, UtilCRCDataHora.decrementaMinutos(new Date(), 600));
            List<ReservaHorario> reservas = consulta.resultadoRegistros();
            if (!reservas.isEmpty()) {
                UtilCRCListasObjeto.ordernarPorCampoReverso(reservas, CPReservaHorario.inicioreservaatendente);
                getUsuarioCliente().setReservaAtivaMaisProxima(reservas.get(0));
            } else {
                getUsuarioCliente().setReservaAtivaMaisProxima(null);
            }

        }

        return getUsuarioCliente().getReservaAtivaMaisProxima();
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }
}
