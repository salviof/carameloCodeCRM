package br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FabStatusDisparo implements ComoFabricaStatusComPersistencia {
    @InfoObjetoDaFabrica(id = 1, classeObjeto = StatusDisparo.class, nomeObjeto = "Rascunho")
    RASCUNHO,
    @InfoObjetoDaFabrica(id = 2, classeObjeto = StatusDisparo.class, nomeObjeto = "Enviado")
    ENVIADO;

    @Override
    public StatusDisparo getRegistro() {
        StatusDisparo disparo = (StatusDisparo) ComoFabricaStatusComPersistencia.super.getRegistro();

        switch (this) {

            case RASCUNHO:
                disparo.setIconeStatus("fa fa fa-pencil");
                break;
            case ENVIADO:
                disparo.setIconeStatus("fa fa-paper-plane");
                break;
            default:
                throw new AssertionError();
        }
        return disparo;
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        List<ComoAcaoDoSistema> acoes = new ArrayList();

        switch (this) {

            case RASCUNHO:
                acoes.add(FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_EDITAR.getRegistro());
                acoes.add(FabAcaoCrmAdmin.DISPARO_EM_MASSA_CTR_REMOVER.getRegistro());
                acoes.add(FabAcaoCrmAdmin.DISPARO_EM_MASSA_CTR_DISPARAR.getRegistro());
                break;
            case ENVIADO:
                acoes.add(FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_VISUALIZAR.getRegistro());
                break;
            default:
                throw new AssertionError();
        }
        return Collections.emptyList();
    }

}
