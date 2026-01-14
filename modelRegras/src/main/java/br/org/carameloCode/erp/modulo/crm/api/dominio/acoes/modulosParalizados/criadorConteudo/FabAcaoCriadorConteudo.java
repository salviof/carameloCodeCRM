/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.modulosParalizados.criadorConteudo;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public enum FabAcaoCriadorConteudo implements ComoFabricaAcoes {
    @InfoTipoAcaoGestaoEntidade(
            nomeAcao = "Gestão de Conteúdos",
            icone = "fa fa-newspaper-o",
            xhtmlDaAcao = "/site/producao/conteudo/gerenciarConteudo.xhtml")
    CONTEUDO_MB_GESTAO,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Criar Rascunho",
            icone = "fa fa-pencil",
            xhtmlDaAcao = "/site/producao/conteudo/gerenciarConteudo.xhtml"
    )
    CONTEUDO_FRM_RASCUNHO,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Gerar Conteúdo",
            icone = "fa fa-cog",
            xhtmlDaAcao = "/site/producao/conteudo/criarRascunho.xhtml"
    )
    CONTEUDO_FRM_GERAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Aprovar Conteúdo",
            icone = "fa fa-gavel",
            xhtmlDaAcao = "/site/producao/conteudo/gerarConteudo.xhtml"
    )
    CONTEUDO_FRM_APROVAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Aprovar Conteúdo",
            icone = "fa fa-rocket",
            xhtmlDaAcao = "/site/producao/conteudo/publicarConteudo.xhtml"
    )
    CONTEUDO_FRM_PUBLICAR;

    @Override
    public Class getEntidadeDominio() {
        return UsuarioSB.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

    @Override
    public AcaoDoSistema getRegistro() {
        try {
            AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
            return acao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando ação " + this, t);
            return null;
        }
    }

}
