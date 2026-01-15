/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class UtilPermissoesObjetos {

    public static boolean isPermitidoArquivoCliente(ComoUsuario pUsuario, ArquivoCliente pArquivo) {

        ArquivoCliente arquivo = pArquivo;
        if (!(pUsuario instanceof UsuarioCRM)) {
            return false;
        }
        if (pUsuario instanceof UsuarioCrmCliente) {
            if (pUsuario.getEmail() == null) {
                return false;
            }
            return arquivo.getProspecto().getContatosProspecto().stream().filter(ct -> ct.isPossuiEmail() && ct.getEmail().equals(pUsuario.getEmail())).findFirst().isPresent();
        }
        if (pUsuario.getEmail() == null) {
            return false;
        }
        return arquivo.getProspecto().getUsuariosResponsaveis().stream().filter(ct -> ct.getEmail() != null && ct.getEmail().equals(pUsuario.getEmail())).findFirst().isPresent();

    }

    public static boolean isPermitidoArquivoAnexo(ComoUsuario pUsuario, ArquivoAnexado pArquivo) {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

        try {
            Pessoa pessoa = UtilSBPersistencia.loadEntidade(pArquivo.getProspecto(), em);
            if (pUsuario.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                return true;
            }
            if (pArquivo instanceof ArquivoCliente) {
                return isPermitidoArquivoCliente(pUsuario, (ArquivoCliente) pArquivo);
            }

            ArquivoAnexado arquivo = pArquivo;
            if (!(pUsuario instanceof UsuarioCRM)) {
                return false;
            }
            if (pUsuario instanceof UsuarioCrmCliente) {
                return false;
            }
            if (pUsuario.getEmail() == null) {
                return false;
            }
            boolean umUsuarioConvidado = pUsuario instanceof UsuarioConvidado;

            if (umUsuarioConvidado) {
                if (!pessoa.getUsuariosConvidados().stream().filter(usr -> usr.equals(pUsuario)).findFirst().isPresent()) {
                    return false;
                }

                if (arquivo.getId() == null) {
                    return true;
                }
                //TODO se atualizando, só permitir se foi ele que criou, se ele criou só pode atulizar em  até 90 dias
                return true;

            }

            return pessoa.getUsuariosResponsaveis().stream().filter(ct -> ct.getEmail() != null && ct.getEmail().equals(pUsuario.getEmail())).findFirst().isPresent();
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
    }

    public static boolean isPermitidoPessoa(ComoUsuario pUsuario, Pessoa pPessoa) {

        Pessoa pessoa = pPessoa;
        if (!(pUsuario instanceof UsuarioCRM)) {
            return false;
        }
        if (pUsuario instanceof UsuarioCrmCliente) {
            if (pUsuario.getEmail() == null) {
                return false;
            }
            return pessoa.getContatosProspecto().stream().filter(ct -> ct.isPossuiEmail() && ct.getEmail().equals(pUsuario.getEmail())).findFirst().isPresent();
        }
        if (pUsuario.getEmail() == null) {
            return false;
        }
        return pessoa.getUsuariosResponsaveis().stream().filter(ct -> ct.getEmail() != null && ct.getEmail().equals(pUsuario.getEmail())).findFirst().isPresent();

    }

}
