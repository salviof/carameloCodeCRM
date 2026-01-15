/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public enum FabStatusEnvioEmail implements ComoFabricaStatusComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = StatusEnvioEmail.class, id = 1, nomeObjeto = "Rascunho")
    RASCUNHO,
    @InfoObjetoDaFabrica(classeObjeto = StatusEnvioEmail.class, id = 2, nomeObjeto = "Envio programado")
    FORMATADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusEnvioEmail.class, id = 3, nomeObjeto = "Enviado")
    ENVIADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusEnvioEmail.class, id = 4, nomeObjeto = "Entrega Confirmada")
    CONFIRMADO;

    @Override
    public StatusEnvioEmail getRegistro() {
        return (StatusEnvioEmail) ComoFabricaStatusComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

    public static FabStatusEnvioEmail getStatusByRegistro(StatusEnvioEmail pStatus) {
        if (pStatus == null) {
            return null;
        }
        for (FabStatusEnvioEmail status : FabStatusEnvioEmail.class.getEnumConstants()) {
            if (status.getRegistro().getId() == pStatus.getId()) {
                return status;
            }
        }
        return null;
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        return new ArrayList<>();
    }

}
