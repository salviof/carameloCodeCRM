package br.org.carameloCode.erp.modulo.crm.implemetation.model.caixapostalestatisticas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas.ValorLogicoCaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas.ValoresLogicosCaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.CPEmailRecebido;

@ValorLogicoCaixaPostalEstatisticas(calculo = ValoresLogicosCaixaPostalEstatisticas.QUANTIDADENAOLIDODESCONHECIDO)
public class ValorLogicoCaixaPostalEstatisticasQuantidadeNaoLidoDesconhecido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoCaixaPostalEstatisticasQuantidadeNaoLidoDesconhecido(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        Long idCaixaDeEntrada = getControle().getId();
        //UsuarioCRM usuario = (UsuarioCRM) getControle().getCPinst(CPLogEmailRecebidoLido.usuario).getValor();
        // ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(LogEmailRecebidoLido.class,
        //       UtilSBPersistencia.getEMDoContexto())
        //      .addCondicaoManyToManyContendoObjeto(CPLogEmailRecebidoLido.usuario, usuario);

        if (!valorDefinido) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {

                Long quantidadeConhecido = new ConsultaDinamicaDeEntidade(EmailRecebido.class, em)
                        .addCondicaoNegativo(CPEmailRecebido.foilidoporusuariodestinatario)
                        .addCondicaoManyToOneIgualA(getControle().getCaixaPostal())
                        .addCondicaoNegativo("foiIgnorado")
                        .addCondicaoManyToOneIgualA(FabCategoriaEmailRecebido.DESCLASSIFICADO.getRegistro())
                        .resultadoSomarQuantidade();
                getControle().setQuantidadeNaoLidoDesconhecido(quantidadeConhecido.intValue());
                valorDefinido = true;
            } catch (Throwable t) {

            }
            UtilSBPersistencia.fecharEM(em);
        }

        return getControle().getQuantidadeNaoLidoDesconhecido();
    }

    public CaixaPostalEstatisticas getControle() {
        return (CaixaPostalEstatisticas) getCampoInst().getObjetoDoAtributo();
    }
}
