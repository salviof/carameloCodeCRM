package br.org.carameloCode.erp.modulo.crm.implemetation.model.categoriaarquivocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente.ValorLogicoCategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente.ValoresLogicosCategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.CPSolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoCategoriaArquivoCliente(calculo = ValoresLogicosCategoriaArquivoCliente.TEMSOLICITACAOPARAMIM)
public class ValorLogicoCategoriaArquivoClienteTemSolicitacaoParaMim
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoCategoriaArquivoClienteTemSolicitacaoParaMim(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido = false;

    @Override
    public synchronized Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

        if (!valordefinido) {
            try {
                if (!CarameloCode.getServicoSessao().getSessaoAtual().isIdentificado()) {
                    getCategoriaArquivoCliente().setTemSolicitacaoParaMim(false);

                } else {
                    ConsultaDinamicaDeEntidade pesquisaSolicitacao = new ConsultaDinamicaDeEntidade(SolicitacaoArquivoCliente.class, em);
                    if (CarameloCode.getUsuarioLogado() instanceof UsuarioCrmCliente) {
                        pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacao.status, FabStatusSolicitacao.CLIENTE_DEVENDO_ARQUIVO.getRegistro());
                    } else {
                        if (CarameloCode.getUsuarioLogado() instanceof UsuarioCRM) {
                            pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacao.status, FabStatusSolicitacao.EQUIPE_DEVENDO_ARQUIVO_A_CLIENTE.getRegistro());
                        }
                    }

                    if (getCategoriaArquivoCliente() != null) {

                        pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacaoArquivoCliente.categoriaarqcliente, getCategoriaArquivoCliente());
                    }

                    pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitado, CarameloCode.getUsuarioLogado());
                    List<SolicitacaoArquivoCliente> soicitacoes = pesquisaSolicitacao.gerarResultados();
                    getCategoriaArquivoCliente().setTemSolicitacaoParaMim(!soicitacoes.isEmpty());
                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
                valordefinido = true;
            }
        }
        return getCategoriaArquivoCliente().isTemSolicitacaoParaMim();
    }

    public CategoriaArquivoCliente getCategoriaArquivoCliente() {
        return (CategoriaArquivoCliente) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
