/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.util;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.ServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoConsultaComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCOutputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.superBits.utilitario.editorArquivos.office.MapaSubstituicaoWord;
import org.superBits.utilitario.editorArquivos.util.UtilSBEditorArquivosConversor;

/**
 *
 * @author desenvolvedor
 */
public class UtilGeradorDocumentoCRM {

    public static MapaSubstituicaoWord getMapaSubstituicao(Pessoa pPessoa, String pCaminhoArquivo) {
        MapaSubstituicaoWord novoMapa = new MapaSubstituicaoWord(new File(pCaminhoArquivo));
        try {
            novoMapa.adicionarPalavrasChaveDoObjeto(pPessoa);
            novoMapa.adicionarPalavrasChaveDoObjeto(CPPessoa.ultimoorcamento, pPessoa.getUltimoOrcamento());
            novoMapa.adicionarImagem("[logoProspecto]", CarameloCode.getServicoArquivosDeEntidade()
                    .getEndrLocalImagem(pPessoa, FabTipoAtributoObjeto.IMG_PEQUENA));
            System.out.println("Logo Prospecto=" + CarameloCode.getServicoArquivosDeEntidade().getEndrLocalImagem(pPessoa, FabTipoAtributoObjeto.IMG_PEQUENA));
            adicionarSolucoes(novoMapa, pPessoa);
            adicionarDadosDinamicos(novoMapa, pPessoa);
        } catch (Throwable t) {
            System.out.println("Erro temporario adicionar palavrras chave do o objeto ainda não está homologado");
        }
        ItfErpCrm implentacao = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto();
        Map<String, String> marcadores = implentacao.getMarcadoresDeSubstituicao(pPessoa);
        for (String chave : marcadores.keySet()) {
            novoMapa.adicionarPalavraChave(chave, marcadores.get(chave));
        }
        return novoMapa;

    }

    private static void adicionarSolucoes(MapaSubstituicao pMapa, Pessoa pProspecto) {
        int i = 0;
        List<ServicoOferecido> servicos = (List<ServicoOferecido>) pProspecto.getCPinst(CPPessoa.servicos).getValor();
        for (ServicoOferecido solucao : servicos) {
            i++;

            pMapa.adicionarPalavraChave("[solucao[" + i + "].descricao]", solucao.getDescricao());

            if (solucao.getValorSetup() == 0) {
                pMapa.adicionarPalavraChave("[solucao[" + i + "].valorSetup]", "-");
            } else {
                pMapa.adicionarPalavraChave("[solucao[" + i + "].valorSetup]", solucao.getCampoInstanciadoByNomeOuAnotacao("valorSetup").getValorTextoFormatado());
            }
            if (solucao.getValorMensal() == 0) {
                pMapa.adicionarPalavraChave("[solucao[" + i + "].valorMensal]", "-");
            } else {
                pMapa.adicionarPalavraChave("[solucao[" + i + "].valorMensal]", solucao.getCampoInstanciadoByNomeOuAnotacao("valorMensal").getValorTextoFormatado());
            }
            pMapa.adicionarPalavraChave("[solucao[" + i + "].nome]", String.valueOf(solucao.getNome()));
            pMapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.nome]", String.valueOf(solucao.getTipoServico().getNome()));
            pMapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.descricao]", String.valueOf(solucao.getTipoServico().getDescricao()));
            pMapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.url]", String.valueOf(solucao.getTipoServico().getUrlDetalhes()));
            pMapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.descricaoApresentacao]", String.valueOf(solucao.getTipoServico().getDescricaoApresentacao()));

        }
    }

    private static void adicionarDadosDinamicos(MapaSubstituicao pMapa, Pessoa pProspecto) {
        for (DadoCRM dado : pProspecto.getDadosColetados()) {
            switch (dado.getCampoInstanciado().getFabricaTipoAtributo()) {
                case ENUM_FABRICA:
                case OBJETO_DE_UMA_LISTA:
                    try {

                        pMapa.adicionarPalavrasChaveDoObjeto("dados." + UtilCRCStringSlugs.gerarSlugCaixaAlta(dado.getTipoDadoCRM().getNome()), (ComoEntidadeSimples) dado.getCampoInstanciado().getValor());
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando Propriedades de objeto selecionado", t);
                    }
                    break;
                default:
                    pMapa.adicionarPalavraChave("[dados." + UtilCRCStringSlugs.gerarSlugCaixaAlta(dado.getTipoDadoCRM().getNome()) + "]", dado.getCampoInstanciado().getValorTextoFormatado());

            }

        }
    }

    public static MapaSubstituicao getMapaSubstituicaoTextoEmail(EnvioEmail pEmail) {

        final MapaSubstituicao novoMapa = new MapaSubstituicao();
        new ExecucaoConsultaComGestaoEntityManager() {
            @Override
            public Object regraDeNegocioRetornandoResultado() {
                Pessoa pProspecto = UtilSBPersistencia.loadEntidade(pEmail.getProspecto(), getEm());

                novoMapa.adicionarPalavrasChaveDoObjeto(pProspecto);
                if (pProspecto.getContatoPrincipal() != null) {
                    novoMapa.adicionarPalavrasChaveDoObjeto("contatoPrincipal", pProspecto.getContatoPrincipal());
                }
                adicionarDadosDinamicos(novoMapa, pProspecto);
                novoMapa.adicionarPalavraChave("[logo]", "<img src='"
                        + SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.UTRL_PAGINA)
                        + "imagens/" + pEmail.getId() + "/.logomarca.png' />");

                return novoMapa;
            }

        };

        return novoMapa;
    }

    public static String gerarDocumentoPastaTemporaria(Pessoa pProspecto, ModeloDocumentoCRM pModelo, String pArquivoTemporario) {
        //Local do modelo
        String arqquivoModelo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(pModelo.getCampoInstanciadoByNomeOuAnotacao("documento"));
        //nome do Arquivo

        //Local Temporário arquivo para Edição
        String arqModificado = pArquivoTemporario;
        if (arqquivoModelo.startsWith("http")) {
            System.out.println(pModelo.getNome() + pModelo.getId());
            System.out.println("baixando " + arqquivoModelo + " para " + arqModificado);
            InputStream is = UTilSBCoreInputs.getStreamByURL(arqquivoModelo, 60000);
            UtilCRCOutputs.salvarArquivoBfInput(new BufferedInputStream(is), arqModificado);
        } else {
            System.out.println("Copiando " + arqquivoModelo + " para " + arqModificado);
            UtilCRCArquivos.copiarArquivos(arqquivoModelo, arqModificado);
        }

        MapaSubstituicaoWord novoMapa = getMapaSubstituicao(pProspecto, arqModificado);

        novoMapa.substituirEmArquivo();
        return arqModificado;

    }

    public static String gerarDocumentoPastaTemporariaConvertendoEmPDF(Pessoa pProspecto, ModeloDocumentoCRM pModelo, String pArquivoTemporario) {
        String caminhoArquivo = gerarDocumentoPastaTemporaria(pProspecto, pModelo, pArquivoTemporario);
        UtilSBEditorArquivosConversor.converterWordEmPDF(caminhoArquivo, caminhoArquivo + ".pdf");
        return caminhoArquivo + ".pdf";
    }

}
