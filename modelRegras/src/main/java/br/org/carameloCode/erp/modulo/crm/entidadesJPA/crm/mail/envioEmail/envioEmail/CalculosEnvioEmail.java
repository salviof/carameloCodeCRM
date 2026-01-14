/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum CalculosEnvioEmail implements ItfCalculos {
    FOI_ENVIADO,
    ULTIMO_RASCUNHO,;

    @Override
    public Object getValor(Object... pEntidade) {

        EnvioEmail envioEmail;
        try {
            envioEmail = (EnvioEmail) pEntidade[0];
            if (envioEmail == null) {
                throw new UnsupportedOperationException("Erro obtendo calculos de envio de email");
            }

            switch (this) {
                case FOI_ENVIADO:
                    if (FabStatusEnvioEmail.ENVIADO.getRegistro().getId() == envioEmail.getStatusEnvio().getId()) {
                        return true;
                    }
                    if (FabStatusEnvioEmail.CONFIRMADO.getRegistro().getId() == envioEmail.getStatusEnvio().getId()) {
                        return true;
                    }
                    return false;
                case ULTIMO_RASCUNHO:

                    EnvioEmailRascunhoAutoSave ultimoRascunho
                            = (EnvioEmailRascunhoAutoSave) UtilSBPersistencia.getRegistroByJPQL(
                                    "from " + EnvioEmailRascunhoAutoSave.class.getSimpleName()
                                    + " where emailVinculado_id=" + envioEmail.getId() + " order by id desc", UtilSBPersistencia.getEMDoContexto()
                            );
                    return ultimoRascunho;

                default:
                    throw new AssertionError(this.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando calculo" + this.toString(), t);
            return null;
        }

    }

}
