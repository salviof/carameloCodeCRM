/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosCRM(modulo = FabModulosCRM.ATENDIMENTO_CRM)
public enum FabAcaoAcessoAnonimoIntranet implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Pagina Inicial", descricao = "Pagina gerenciadora de acessos ao sistema", entidade = UsuarioCRM.class,
            xhtmlDaAcao = "/site/home.xhtml")
    LOGIN_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Efetuar Login",
            icone = "fa fa-sign-in", entidade = UsuarioCRM.class,
            xhtmlDaAcao = "/site/login/homeLogin.xhtml")
    LOGIN_FRM_REALIZAR_LOGIN,
    @InfoTipoAcaoFormulario(nomeAcao = "Efetuar Login Cliente",
            icone = "fa fa-sign-in", entidade = UsuarioCrmCliente.class,
            xhtmlDaAcao = "/site/login/homeLoginCliente.xhtml")
    LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE,
    @InfoTipoAcaoFormulario(nomeAcao = "PRIMEIRO ACESSO",
            icone = "fa fa-flag-o", entidade = UsuarioCrmCliente.class,
            xhtmlDaAcao = "/site/login/homePrimeiroAcessoEmail.xhtml")
    LOGIN_FRM_REALIZAR_PRIMEIRO_ACESSO_PELO_EMAIL,
    @InfoTipoAcaoFormulario(nomeAcao = "Primeiro Acesso - Definição de senha",
            icone = "fa fa-sign-in", entidade = UsuarioCrmCliente.class,
            xhtmlDaAcao = "/site/login/homePrimeiroAcessoDefineSenha.xhtml")
    LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO,
    @InfoTipoAcaoController(icone = "fa fa-key")
    LOGIN_CTR_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Pagina Inicial", descricao = "Pagina gerenciadora de acessos ao sistema", entidade = ReservaHorario.class)
    RESERVA_PUBLICA_MB_RESERVAS,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ACESSO_NEGADO)
    RESERVA_PUBLICA_FRM_SEM_HORARIOS_DISPONIVEIS,
    @InfoTipoAcaoFormulario(nomeAcao = "Escolha um horário para reservar",
            icone = "fa fa-ticket")
    RESERVA_PUBLICA_FRM_LISTAR_HORARIOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Escolha um horário para reservar",
            icone = "fa fa-address-card-o")
    RESERVA_PUBLICA_FRM_MEUS_DADOS,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_OK, nomeAcao = "Detalhes da reserva")
    RESERVA_PUBLICA_FRM_RESERVA_CONCLUIDA,
    @InfoTipoAcaoController(icone = "fa fa-ban", nomeAcao = "CANCELAR")
    RESERVA_PUBLICA_CTR_CANCELAR,
    @InfoTipoAcaoController(icone = "fa fa-calendar-check-o", nomeAcao = "CONFIRMAR")
    RESERVA_PUBLICA_CTR_CONFIRMAR,
    @InfoTipoAcaoController(icone = "fa fa-calendar-check-o", nomeAcao = "Adquirir Reserva")
    RESERVA_PUBLICA_CTR_RESERVAR,
    @InfoTipoAcaoController(icone = "fa fa-calendar-check-o", nomeAcao = "Confirmar")
    RESERVA_PUBLICA_CTR_PUBLICAR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Arquivo compartilhado", entidade = ArquivoAnexado.class, icone = "fa fa-share")
    ARQUIVOS_COMPARTILHADOS_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Clientes compartilhados")
    ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_PESSOAS,
    @InfoTipoAcaoFormulario(nomeAcao = "Pastas compartilhadas")
    ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_PASTAS,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar pastas Compartilhadas")
    ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_ARQUIVOS_PASTA,
    @InfoTipoAcaoController(nomeAcao = "Atualizar arquivo")
    ARQUIVOS_COMPARTILHADOS_CTR_ATUALIZAR_ARQUIVO,
    @InfoTipoAcaoController(nomeAcao = "Remover Arquivo")
    ARQUIVOS_COMPARTILHADOS_CTR_REMOVER_ARQUIVO;

}
