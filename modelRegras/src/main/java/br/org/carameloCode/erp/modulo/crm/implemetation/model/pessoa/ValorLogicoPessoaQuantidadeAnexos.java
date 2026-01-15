package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.QUANTIDADEANEXOS)
public class ValorLogicoPessoaQuantidadeAnexos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaQuantidadeAnexos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ArquivoAnexado.class, em);
                consulta.addCondicaoManyToOneDiferenteDeNulo(CPArquivoAnexado.categoriaarqequipe);
                Pessoa pessoa = UtilSBPersistencia.loadEntidade(getPessoa(), em);
                consulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.prospecto, pessoa);
                long quantidadeEquipe = consulta.resultadoSomarQuantidade();

                consulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class, em);
                consulta.addCondicaoManyToOneDiferenteDeNulo(CPArquivoAnexado.categoriaarqequipe);
                consulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.prospecto, pessoa);

                long quantidadeCliente = consulta.resultadoSomarQuantidade();
                int total = new Long(quantidadeEquipe + quantidadeCliente).intValue();
                getPessoa().setQuantidadeAnexos(total);
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
            ativarCache(5000);
        }

        return getPessoa().getQuantidadeAnexos();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
