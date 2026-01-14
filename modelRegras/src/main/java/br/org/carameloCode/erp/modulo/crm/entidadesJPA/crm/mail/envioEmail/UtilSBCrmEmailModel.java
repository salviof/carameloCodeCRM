/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.util.UtilGeradorDocumentoCRM;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;

/**
 *
 * @author SalvioF
 */
public abstract class UtilSBCrmEmailModel {

    public static void aplicarModelov2(final EnvioEmail pEMail, UsuarioCRM pUsuario) {

        new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {
                if (pEMail.getTexto() == null || pEMail.getTexto().length() < 5) {
                    if (pEMail.getModeloEmail() != null) {
                        if (pEMail.getModeloEmail().getTextoModelo() != null) {
                            pEMail.setTexto(pEMail.getModeloEmail().getTextoModelo());
                            if (pEMail.getModeloEmail().getAssunto() != null) {
                                pEMail.setAssunto(pEMail.getModeloEmail().getAssunto());
                            }
                        }
                    }

                    UsuarioCRM usuario = pUsuario;
                    if (pEMail.getTexto() == null) {
                        pEMail.setTexto("");
                    }
                    MapaSubstituicao mapa = UtilGeradorDocumentoCRM.getMapaSubstituicaoTextoEmail(pEMail);

                    pEMail.setTexto(mapa.substituirEmString(pEMail.getTexto()));
                    pEMail.setAssunto(mapa.substituirEmString(pEMail.getAssunto()));

                }
                return "ok";
            }
        };
    }

}
