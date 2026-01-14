/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabDadosIniciais;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.ListasProspectos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.util.UtilGeradorDocumentoCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfGrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.superBits.utilitario.editorArquivos.office.MapaSubstituicaoWord;

/**
 *
 * @author desenvolvedor
 */
public class TesteFluxo extends TesteCRMCarameloCode {

    @Test
    public void testefluxo() {
        try {
            OrigemProspecto novaOrigem = new OrigemProspecto();
            ListasProspectos.PROSPECTOS_DESTA_PROGIEM.getLista(getEMTeste(), novaOrigem);
            //ModuloCRMAdmin.atualizarDadosProspectoTeste(pModelo)
            renovarConexao();
            PessoaJuridica prosp = UtilSBPersistencia.loadEntidade((PessoaJuridica) FabDadosIniciais.PROSPECTO1.getRegistro(), getEMTeste());
            ItfCampoInstanciado campo = prosp.getCampoInstanciadoByNomeOuAnotacao("preAnalise.fotoMobile");

            System.out.println(campo.getTipoCampoSTR());

            MapaSubstituicaoWord mapa = UtilGeradorDocumentoCRM.getMapaSubstituicao(prosp, "/home/SalvioF/testeGerador.docx");
            for (String pl : mapa.getpalavrasChave()) {
                System.out.println(pl);
            }
            mapa.substituirEmArquivo();
            SBCore.getControleDeSessao().logarEmailESenha("salviof@gmail.com", "123");
            ItfAcaoFormulario acao = (ItfAcaoFormulario) FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_NOVO.getRegistro();

            for (ItfGrupoCampos gpr : acao.getGruposDeCampos()) {
                for (ItfCampoExibicaoFormulario caminhoCampo : gpr.getCampos()) {
                    System.out.println(caminhoCampo.getLabel());
                    System.out.println("Alt:" + caminhoCampo.getLabel());
                }
            }
            PessoaJuridica prospTEste = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, 1l, getEMTeste());

            EnvioEmailAtividade testeEnvio = new EnvioEmailAtividade();
            testeEnvio.setProspecto(prospTEste);
            ItfCampoInstanciado cp = testeEnvio.getCampoInstanciadoByNomeOuAnotacao("contatos");
            ItfCampoInstanciado campoListagem = testeEnvio.getCampoInstanciadoByNomeOuAnotacao("prospecto.contatosProspecto");

            ItfCampoInstanciado cpInst = prospTEste.getCampoInstanciadoByNomeOuAnotacao("contatosProspecto[]");
            cpInst.getFabricaTipoAtributo();
            List teste = cp.getComoSeltorItens().getSeletor().getOrigem();

            System.out.println(cpInst.getLabel());
            for (ContatoProspecto contato : prospTEste.getContatosProspecto()) {
                System.out.println(contato);
            }

            ItfCampoInstanciado campoInst = prospTEste.getCampoInstanciadoByNomeOuAnotacao("usuariosResponsaveis");
            campoInst.getComoSeltorItens().getCampoSeletorItens().getOrigem();
            System.out.println(campoInst.getValorMaximo());
            TipoAtividadeCRM tipoAtividade = FabTipoAtividadeCRM.CRIAR_PROSPECTO.getRegistro();
            ItfCampoInstanciado campotst = tipoAtividade.getCampoInstanciadoByNomeOuAnotacao("nomeInicioAtivida");
            System.out.println(campotst.getTipoCampoSTR());
            PessoaJuridica prospecto = (PessoaJuridica) FabDadosIniciais.PROSPECTO1.getRegistro();
            prospecto = (PessoaJuridica) ModuloCRMAtendimento.prospectoSalvar(prospecto).getRetorno();
            AtividadeCRM novaAtividade
                    = (AtividadeCRM) ModuloCRMEmail.iniciarAtendimento(FabTipoAtividadeCRM.CRIAR_PROSPECTO.getRegistro(), prospecto, false).getRetorno();
            renovarConexao();
            novaAtividade = UtilSBPersistencia.loadEntidade(novaAtividade, getEMTeste());
            AtividadeCRM atividadeIncompletaAnterior = novaAtividade.getAtividadeMesmoTipoAbertaUsuarioLogado();

            ItfAcaoFormulario acaoFormularioAtividade = novaAtividade.getAcaoFormularioExecucao();

            if (acaoFormularioAtividade.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SOBRESCREVER_ATIVIDADE.getRegistro())) {
                ModuloCRMEmail.atividadecancelaAtividadeAnteriorEmAberto(novaAtividade);
            }

            // CRIANDO NOVO PROSPECTO
            assertNotSame("A atividade continua com id 0", novaAtividade.getId(), 0);
            renovarConexao();
            novaAtividade = UtilSBPersistencia.loadEntidade(novaAtividade, getEMTeste());
            List<DadoCRM> dadosNaoColetados = novaAtividade.getDadosNaoColetados();
            SBCore.getControleDeSessao().logarEmailESenha("atendimento@casanovadigital.com.br", "123");
            for (DadoCRM dado : dadosNaoColetados) {

                String label = dado.getCampoInstanciado().getLabel();

                System.out.println(dado.getTipoDadoCRM().getLabel());
                System.out.println(dado.getTipoDadoCRM().getNomeClasseAtributoDeclarado());

                FabTipoAtributoObjeto tipoCampo = dado.getCampoInstanciado().getFabricaTipoAtributo();
                System.out.println("LabeL:" + label);
                System.out.println("TipoCampo" + dado.getCampoInstanciado().getFabricaTipoAtributo());

                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.SITE.getRegistro())) {
                    dado.getCampoInstanciado().setValor("teste site");
                }

                if (dado.getTipoDadoCRM().getLabel().equals(FabTipoDadoCRM.PORTE.getRegistro().getLabelPadrao())) {
                    List<ComoEntidadeSimples> lista = dado.getCampoInstanciado().getListaDeOpcoes();
                    System.out.println("Label: - " + label + "TIPOCAMPO:" + tipoCampo + "Lista:" + lista);
                    dado.getCampoInstanciado().setValor(FabPorteProspectoEmpresa.GRANDE.getRegistro());
                    dado.getCampoInstanciado().getValor();
                }
                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.INDICACAO.getRegistro())) {
                    dado.getCampoInstanciado().setValor("teste indicação");
                }
                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.BR_E_MOBILE.getRegistro())) {
                    dado.getCampoInstanciado().setValor(true);
                }
                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.BR_E_OTIMIZADO.getRegistro())) {
                    //                dado.getCampoInstanciado().setValor(false);
                }

                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.BR_SITE_CONCORRENTE1.getRegistro())) {
                    dado.getCampoInstanciado().setValor("www.google.com.br");
                }
                if (dado.getTipoDadoCRM().equals(FabTipoDadoCRM.BR_SITE_CONCORRENTE2.getRegistro())) {
                    dado.getCampoInstanciado().setValor("www.facebook.com.br");
                }

            }

            novaAtividade.ajustarColeta();

            if (novaAtividade.isPermitidoConcluir()) {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Permitido");
            } else {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Não Permitido");
            }
            novaAtividade = (AtividadeCRM) ModuloCRMEmail.atividadeCRMComplementarSalvarDadosDinamicos(novaAtividade).getRetorno();
            renovarConexao();
            novaAtividade = UtilSBPersistencia.loadEntidade(novaAtividade, getEMTeste());
            if (novaAtividade.isPermitidoConcluir()) {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Permitido");
            } else {
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Não Permitido");
            }
            ItfResposta resp = ModuloCRMAtendimento.atividadeConcluir(novaAtividade);
            renovarConexao();
            List<TipoAtividadeCRM> tiposAtividade = UtilSBPersistencia.getListaTodos(TipoAtividadeCRM.class, getEMTeste());
            List<TipoRelacionamento> tiposRelacionameto = UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMTeste());
            List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, getEMTeste());

            for (TipoRelacionamento tipo : tiposRelacionameto) {
                for (UsuarioCRM usuario : usuarios) {
                    if (tipo.getTotalAtividadesPorUsuario(usuario) > 0) {
                        System.out.println(usuario.getNome() + tipo.getTotalAtividadesPorUsuario(usuario));
                    }
                }
                System.out.println("");
            }

            // prospecto criado
            AtividadeCRM atividadeGerada = (AtividadeCRM) resp.getRetorno();

            atividadeGerada = UtilSBPersistencia.loadEntidade(atividadeGerada, getEMTeste());
            Pessoa prospAtividade = atividadeGerada.getProspectoEmpresa();
            // lista de relacionamentos possíveis
            List<TipoRelacionamento> relacionamentosPossiveis = prospAtividade.getRelacionamentosPossiveisEvolucao();
            for (TipoRelacionamento tipoRel : relacionamentosPossiveis) {
                System.out.println(tipoRel.getNome());
            }
            // Criando modelo de Docomento
            ModeloDocumentoTipoAtividade modeloDocumento = new ModeloDocumentoTipoAtividade();

            ItfCampoInstanciado campoTipoAtividade = modeloDocumento.getCampoInstanciadoByNomeOuAnotacao("tipoAtividadeVinculada");

            System.out.println(campoTipoAtividade.getFabricaTipoAtributo());

            modeloDocumento.setNome("teste");
            //   ItfCampoInstanciado campo = modeloDocumento.getCampoInstanciadoByNomeOuAnotacao("documento");
            campo.getComoArquivoDeEntidade().uploadArquivo("preanalise.docx",
                    FileUtils.readFileToByteArray(new File("/home/superBits/projetos/Super_Bits/source/SuperBits_FrameWork/utilitarios/EditorDeArquivos/exemplo.docx")));
            modeloDocumento.setTipoAtividadeVinculada(FabTipoAtividadeCRM.ENVIAR_BOLETO.getRegistro());
            if (!ModuloCRMAdmin.salvarModeloDocumentoTipoAtiviadade(modeloDocumento).isSucesso()) {
                throw new UnsupportedOperationException("Erro salvando novo documento");
            }

            List<ModeloDocumentoCRM> modelosDeDocumento = UtilSBPersistencia.getListaTodos(ModeloDocumentoCRM.class);
            // Gerando docomento teste
            ModuloCRMAdmin.gerarDocumentoProspectoTeste(modelosDeDocumento.get(0));

            AtividadeCRM atividadeGeradoraDocumento
                    = (AtividadeCRM) ModuloCRMEmail.iniciarAtendimento(FabTipoAtividadeCRM.ENVIAR_BOLETO.getRegistro(),
                            prospecto, false).getRetorno();

            ModuloCRMEmail.gerarDocumentos(atividadeGeradoraDocumento, (UsuarioCRM) SBCore.getUsuarioLogado());

            renovarConexao();
            atividadeGeradoraDocumento = UtilSBPersistencia.loadEntidade(atividadeGeradoraDocumento, getEMTeste());
            System.out.println(atividadeGeradoraDocumento.getFormularioExecucao());

            System.out.println("Documentos da Atividade=" + atividadeGeradoraDocumento.getDocumentosGerados());
            EnvioEmailAtividade novoEnvio = new EnvioEmailAtividade();

            novoEnvio.prepararNovoObjeto(atividadeGeradoraDocumento);
            novoEnvio.adicionarContato(atividadeGeradoraDocumento.getProspectoEmpresa().getContatosProspecto().get(0));
            novoEnvio.atualizarDocumentosDaAtividade();
            novoEnvio.setNome("Colé, teste");
            ModuloCRMAtendimento.envioDocumentoEnviar(novoEnvio);

            //ModuloCRMAtendimento.concluirAtividade(atividadeEnvioPreAnalise);
            List<DocumentoAtividadeCRM> documentosGerados = UtilSBPersistencia.getListaTodos(DocumentoAtividadeCRM.class, getEMTeste());
            for (DocumentoAtividadeCRM doc : documentosGerados) {
                System.out.println("Gerado documento" + doc.getNome());
                System.out.println(doc.getArquivo());
                System.out.println(doc.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().getCaminhoArquivoLocal());
            }
            UsuarioCRM atendimento = UtilSBPersistencia.loadEntidade(FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro(), getEMTeste());
            System.out.println("O usuário " + atendimento + " possui " + atendimento.getProspectos().size() + "prospecto");

            atendimento.getMeusProspectosComAtivadadePendentes();

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

}
