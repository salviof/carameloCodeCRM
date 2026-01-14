/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;

/**
 *
 * @author desenvolvedor
 */
public enum FabGruposIntranetCasaNova implements ComoFabricaComPersistencia {

    CRM_ATENDIMENTO,
    CRM_ADMIN,
    CRM_CLIENTE,
    CRM_CONVIDADO;

    @Override
    public GrupoUsuarioCRM getRegistro() {
        GrupoUsuarioCRM novoGrupo = new GrupoUsuarioCRM();
        novoGrupo.setTipoGrupoNativo(true);
        novoGrupo.setId((long) this.ordinal() + 1);

        switch (this) {
            case CRM_ATENDIMENTO:
                novoGrupo.setNome("Gestão de Relacionamento com o Cliente");
                novoGrupo.setPaginaInicial(FabAcaoCRMAtendimento.MEU_DASHBOARD_MB_GESTAO);
                novoGrupo.adicionarModulo(FabModulosCRM.ATENDIMENTO_CRM.getRegistro());
                novoGrupo.setModuloPrincipal(FabModulosCRM.ATENDIMENTO_CRM.getRegistro());
                break;
            case CRM_ADMIN:
                novoGrupo.setNome("Administração de CRM");
                novoGrupo.setPaginaInicial(FabAcaoCrmAdmin.ADMINISTRATIVO_PAGINA_PRINCIPAL_MB);
                novoGrupo.adicionarModulo(FabModulosCRM.ADMIN_CRM.getRegistro());
                novoGrupo.setModuloPrincipal(FabModulosCRM.ADMIN_CRM.getRegistro());
                break;
            case CRM_CLIENTE:
                novoGrupo.setNome("Cliente");
                novoGrupo.setPaginaInicial(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO);
                novoGrupo.adicionarModulo(FabModulosCRM.CLIENTE_CONTATO.getRegistro());
                novoGrupo.setModuloPrincipal(FabModulosCRM.CLIENTE_CONTATO.getRegistro());
                break;
            case CRM_CONVIDADO:
                novoGrupo.setNome("Convidado");
                novoGrupo.setPaginaInicial(FabAcaoCRMConvidado.MEUS_CLIENTES_MB_GESTAO);
                novoGrupo.adicionarModulo(FabModulosCRM.CONVIDADO.getRegistro());

                break;

            default:
                throw new AssertionError(this.name());

        }

        return novoGrupo;
    }

    public final static GrupoUsuarioCRM GRUPOADMIN = CRM_ADMIN.getRegistro();

}
