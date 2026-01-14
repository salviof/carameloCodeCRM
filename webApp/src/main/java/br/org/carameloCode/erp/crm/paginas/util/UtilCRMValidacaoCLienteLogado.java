/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class UtilCRMValidacaoCLienteLogado {

    public static boolean validarClienteLogado() {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

        try {
            UsuarioCrmCliente usuarioCliente = UtilSBPersistencia.loadEntidade((UsuarioCrmCliente) SBCore.getUsuarioLogado(), em);

            if (UtilCRCStringValidador.isNuloOuEmbranco(usuarioCliente.getEmail())) {
                return false;
            }

            if (UtilCRCStringValidador.isNuloOuEmbranco(usuarioCliente.getTelefone())) {
                return false;
            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return true;

    }

}
