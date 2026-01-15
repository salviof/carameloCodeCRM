/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;

/**
 *
 * @author salvio
 */
@InfoModulosCRM(modulo = FabModulosCRM.CONVIDADO)
public enum FabAcaoCRMConvidado implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-heart", nomeAcao = "Meus Clientes", precisaPermissao = true, entidade = Pessoa.class)
    MEUS_CLIENTES_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-heart", nomeAcao = "Clientes")
    MEUS_CLIENTES_FRM_LISTAR,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-folder-o", nomeAcao = "Pastas do Cliente", entidade = CategoriaArquivoEquipe.class, precisaPermissao = true)
    PASTAS_DO_CLIENTE_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Ver Pastas", icone = "fa fa-folder-o")
    PASTAS_DO_CLIENTE_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes pasta", icone = "fa fa-files-o")
    PASTAS_DO_CLIENTE_FRM_VER,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Arquivos", icone = "fa fa-files-o", entidade = ArquivoAnexado.class, precisaPermissao = true)
    ARQUIVO_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-file-pdf-o")
    ARQUIVO_FRM_DETALHES,

}
