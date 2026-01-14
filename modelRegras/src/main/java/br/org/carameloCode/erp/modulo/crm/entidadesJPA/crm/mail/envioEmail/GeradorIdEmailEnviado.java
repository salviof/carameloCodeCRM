/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.Serializable;
import java.util.Date;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author SalvioF
 */
public class GeradorIdEmailEnviado implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object obj) throws HibernateException {
        try {
            EnvioEmail email = ((EnvioEmail) obj);
            String valorCodigo = email.getAssunto() + new Date().toString();

            return Math.abs(valorCodigo.hashCode());

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando id para Ação", t);
        }

        return 0;
    }

}
