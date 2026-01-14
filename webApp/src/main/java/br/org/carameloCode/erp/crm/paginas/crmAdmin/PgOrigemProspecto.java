
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GOP", tags = {"Origens de prospecto "})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_MB_GERENCIAR)
public class PgOrigemProspecto extends MB_paginaCadastroEntidades<OrigemProspecto> {

    private final ComoAcaoDoSistema acaoSalvar = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_SALVAR_MERGE.getRegistro();
    private final ComoAcaoDoSistema acaoListar = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_LISTAR.getRegistro();
    private final ComoAcaoDoSistema acaoNovo = FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_NOVO.getRegistro();

    private OrigemProspecto origemDestino;
    private UsuarioCRM usuarioDestino;

    private List<UsuarioCRM> usuariosDisponiveis;

    public PgOrigemProspecto() {

        super(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_MB_GERENCIAR.getRegistro().getAcaoDeGestaoEntidade());

    }

    public List<UsuarioCRM> getUsuariosDisponivesi() {
        if (usuariosDisponiveis == null || usuariosDisponiveis.isEmpty()) {
            usuariosDisponiveis = UtilSBPersistencia.getListaRegistrosByHQL(" from " + UsuarioSB.class.getSimpleName() + " where tipoUsuario = '" + UsuarioCRM.class.getSimpleName() + "' ", -1, getEMPagina());
        }
        return usuariosDisponiveis;
    }

    @PostConstruct
    public void init() {

        listarDados();
        setAcaoSelecionada(acaoListar);
        removerAcaoDeEntidade(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_REMOVER.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_CONVERTER.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_CONVERTER);

    }

    public ComoAcaoDoSistema getAcaoSalvar() {
        return acaoSalvar;
    }

    public ComoAcaoDoSistema getAcaoListar() {
        return acaoListar;
    }

    @Override
    public void executarAcao(OrigemProspecto pEntidadeSelecionada) {

        super.executarAcao(pEntidadeSelecionada);

        if (getAcaoSelecionada().equals(acaoSalvar)) {

            if (ModuloCRMAdmin.origemProspectoSalvar(pEntidadeSelecionada).isSucesso()) {

                setAcaoSelecionada(acaoListar);
                xhtmlAcaoAtual = getAcaoSelecionada().getComoFormularioEntidade().getXhtml();
                getPaginaUtil().atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
                listarDados();

            } else {

                setAcaoSelecionada(acaoNovo);
                xhtmlAcaoAtual = getAcaoSelecionada().getComoFormularioEntidade().getXhtml();

            }

        }

    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        setEntidadesListadas(UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina()));
    }

    public OrigemProspecto getOrigemDestino() {
        return origemDestino;
    }

    public void setOrigemDestino(OrigemProspecto origemDestino) {
        this.origemDestino = origemDestino;
    }

    public void moverOrigens() {
        if (origemDestino == null) {
            SBCore.enviarAvisoAoUsuario("Selecione a origem de destino");
            executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_REMOVER);
            return;
        }
        if (!isRespostaComunicacaoTransientPreAcaoEnviada()) {
            exibeModalComunicacaoTransientPreAcaoAtual();

        } else {
            FabTipoRespostaComunicacao respComunicacao = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(getAcaoSelecionada().getNomeUnico());
            if (respComunicacao != null) {
                if (respComunicacao.isRespostaPositiva()) {
                    ItfResposta resposta = ModuloCRMAdmin.origemProspectoMover(getEntidadeSelecionada(), origemDestino);
                    if (resposta.isSucesso()) {
                        UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_LISTAR.getRegistro()));
                    } else {
                        executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_REMOVER);
                        resposta.dispararMensagens();
                    }
                }

            }

        }

    }

    public void converderOrigem() {
        if (getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao("umaOrigempublica").getValorComoBoolean()) {
            if (usuarioDestino == null) {
                SBCore.enviarAvisoAoUsuario("Selecione o novo dono da origem privada");
                executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_CONVERTER);
                return;
            }
        }
        if (!isRespostaComunicacaoTransientPreAcaoEnviada()) {
            exibeModalComunicacaoTransientPreAcaoAtual();

        } else {
            FabTipoRespostaComunicacao respComunicacao = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(getAcaoSelecionada().getNomeUnico());
            if (respComunicacao != null) {
                if (respComunicacao.isRespostaPositiva()) {
                    ItfResposta resposta = ModuloCRMAdmin.origemProspectConverter(getEntidadeSelecionada(), usuarioDestino);
                    if (resposta.isSucesso()) {
                        UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_LISTAR.getRegistro()));
                    } else {
                        executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_CONVERTER);
                        resposta.dispararMensagens();
                    }
                }

            }

        }

    }

    public UsuarioCRM getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(UsuarioCRM usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

}
