package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadousuariocliente;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValorLogicoMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValoresLogicosMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoMetadadoUsuarioCliente(calculo = ValoresLogicosMetadadoUsuarioCliente.RESERVASATIVAS)
public class ValorLogicoMetadadoUsuarioClienteReservasAtivas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoUsuarioClienteReservasAtivas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadadosClienteUsuario().getUsuario() != null) {
            if (isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, em);
                consulta.addCondicaoManyToOneIgualA(CPReservaHorario.atendidoresponsavel, getMetadadosClienteUsuario().getUsuario());

                consulta.addCondicaoManyToOneContemNoIntervalo(CPReservaHorario.status,
                        Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro())
                );

                getMetadadosClienteUsuario().setReuniaoAgendada(0);
                UtilSBPersistencia.fecharEM(em);

                ativarCache(30);
            }

        }
        return getMetadadosClienteUsuario().getChamadosAbertos();
    }

    public MetadadoUsuarioCliente getMetadadosClienteUsuario() {

        return (MetadadoUsuarioCliente) getCampoInst().getObjetoDoAtributo();

    }
}
