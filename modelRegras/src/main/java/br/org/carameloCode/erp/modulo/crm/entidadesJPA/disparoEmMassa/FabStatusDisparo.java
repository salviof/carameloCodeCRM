package br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.StatusReserva;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public enum FabStatusDisparo implements ComoFabricaStatusComPersistencia {
    @InfoObjetoDaFabrica(id = 1, classeObjeto = StatusDisparo.class, nomeObjeto = "Rascunho")
    RASCUNHO,
    @InfoObjetoDaFabrica(id = 2, classeObjeto = StatusDisparo.class, nomeObjeto = "Enviado")
    ENVIADO;

    @Override
    public StatusDisparo getRegistro() {
        return (StatusDisparo) ComoFabricaStatusComPersistencia.super.getRegistro();
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        return Collections.emptyList();
    }

}
