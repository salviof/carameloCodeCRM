package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivoanexado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.ValorLogicoArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.ValoresLogicosArquivoAnexado;

@ValorLogicoArquivoAnexado(calculo = ValoresLogicosArquivoAnexado.ICONE)
public class ValorLogicoArquivoAnexadoIcone extends ValorLogicoCalculoGenerico {

    public ValorLogicoArquivoAnexadoIcone(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        String texto = getArquivo().getCampoInstanciadoByNomeOuAnotacao(CPArquivoAnexado.arquivo).getComoArquivoDeEntidade().getLinkAbrirArquivo();
        if (texto != null) {
            FabTipoArquivoConhecido tipo = FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(texto);
            switch (tipo) {
                case IMAGEM_WEB:

                case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:

                case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:

                case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                    setValorPorReflexao("fa fa-file-image-o");
                case VIDEO:
                    break;
                case DOCUMENTO_WORD_XDOC2007:
                    setValorPorReflexao("fa fa-file-word-o");
                    break;
                case FOLHA_DE_ESTILO_CSS:
                    setValorPorReflexao("fa fa-paint-brush");
                    break;
                case JAVASCRIPT:
                    setValorPorReflexao("fa file-code-o");
                    break;
                case DOCUMENTO_PDF:
                    setValorPorReflexao("fa fa-file-pdf-o");
                    break;
                case ARQUIVO_TEXTO_SIMPLES:
                    setValorPorReflexao("fa fa-file-text-o");
                    break;
                default:
                    throw new AssertionError(tipo.name());

            }
        }
        return getArquivo().getIcone();
    }

    public ArquivoAnexado getArquivo() {
        return (ArquivoAnexado) getCampoInst().getObjetoDoAtributo();
    }

}
