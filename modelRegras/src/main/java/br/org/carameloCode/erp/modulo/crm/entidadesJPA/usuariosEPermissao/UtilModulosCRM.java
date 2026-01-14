/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilModulosCRM {

    public static UsuarioCRM getUsuarioCRMLogado(EntityManager em) {
        try {
            UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, SBCore.getUsuarioLogado().getId(), em);
            if (usuario == null) {
                throw new UnsupportedOperationException("O usuário Logado não é um usuário CRM");
            }
            return usuario;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Impossível encontrar o usuário logado como CRM", t);
            return null;
        }

    }

}
