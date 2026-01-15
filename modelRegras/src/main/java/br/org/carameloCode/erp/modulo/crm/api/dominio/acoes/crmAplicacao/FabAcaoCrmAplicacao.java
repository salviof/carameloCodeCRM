/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.acoesAutomatizadas.FabTipoAutoExecucaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosCRM(
        modulo = FabModulosCRM.APLICACAO_CRM)
public enum FabAcaoCrmAplicacao implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = UsuarioCRM.class)
    ACOES_PROGRAMADAS_MB,
    @InfoTipoAcaoController(entidade = EnvioEmail.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_ENVIAR_EMAIL_PROGRAMADO,
    @InfoTipoAcaoController(entidade = UsuarioSistemaRoot.class, iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    ACOES_PROGRAMADAS_CTR_INICIALIZAR_TAREFAS_AGENDADAS,
    @InfoTipoAcaoController(entidade = UsuarioCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_RECEBER_EMAIL,
    @InfoTipoAcaoController(nomeAcao = "Testar caixa de e-mail", entidade = UsuarioCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_TESTAR_CAIXA_DE_ENTRADA,
    @InfoTipoAcaoController(nomeAcao = "Receber emails", entidade = UsuarioCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR, autoExecucao = FabTipoAutoExecucaoAcao.MINUTOS_A_CADA_5)
    ACOES_PROGRAMADAS_CTR_RECEBER_EMAILS_AUTO_EXEC,
    @InfoTipoAcaoController(entidade = UsuarioCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_MARCAR_EMAILS_LIDOS,
    @InfoTipoAcaoController(entidade = EmailRecebido.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_SALVAR_EMAIL_RECEBIDO,
    @InfoTipoAcaoController(entidade = HistoricoRelacionamento.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ACOES_PROGRAMADAS_CTR_ALTERAR_RELACIONAMENTO_POR_INERCIA,
    ACOES_PROGRAMADAS_CTR_ALTERAR_TODOS_RELACIONAMENTOS_POR_INERCIA,
    @InfoTipoAcaoController(entidade = Solicitacao.class, autoExecucao = FabTipoAutoExecucaoAcao.SISTEMA_BOOT)
    ACOES_PROGRAMADAS_CTR_CARREGAR_SOLICITACOES_AUTO_EXEC,
    @InfoTipoAcaoController(entidade = TipoFormulario.class, autoExecucao = FabTipoAutoExecucaoAcao.DIARIO_MEIA_NOITE)
    ACOES_PROGRAMADAS_CTR_TYPEBOT_FORM_SINCRONIZAR_AUTO_EXEC,
    @InfoTipoAcaoController(entidade = RespostaFormulario.class, autoExecucao = FabTipoAutoExecucaoAcao.MINUTOS_A_CADA_30)
    ACOES_PROGRAMADAS_CTR_TYPEBOT_RESPOSTA_SINCRONIZAR_AUTO_EXEC,
    @InfoTipoAcaoGestaoEntidade(entidade = AtividadeCRM.class)
    ATIVIDADE_AUTONOMA_CRM_MB_CATALOGO,
    ATIVIDADE_AUTONOMA_CRM_CTR_ALTERAR_RELACIONAMENTO,
    //todo imoplementar
    ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ACOES_AUTOMATICAS,
    //todo imoplementar
    ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ALTERAR_RESPONSAVEL,
    //todo imoplementar
    ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ALTERAR_TAGS,
    ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ACOES_AUTOMATICAS,
    @InfoTipoAcaoController(entidade = AtividadeCRM.class, icone = "fa fa-calendar", nomeAcao = "Agenda de nova atividade")
    ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_AGENDAR_NOVA_ATIVIDADE,
    ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ALTERAR_TAGS,
    ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ACAO_DE_MUDANCA_RELACIONAMENTO,
    ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_DISPARAR_TRANSACIONAL,

}
