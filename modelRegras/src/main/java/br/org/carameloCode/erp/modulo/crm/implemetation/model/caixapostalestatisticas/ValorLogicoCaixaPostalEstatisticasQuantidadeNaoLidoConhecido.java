package br.org.carameloCode.erp.modulo.crm.implemetation.model.caixapostalestatisticas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas.ValorLogicoCaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas.ValoresLogicosCaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.CPEmailRecebido;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@ValorLogicoCaixaPostalEstatisticas(calculo = ValoresLogicosCaixaPostalEstatisticas.QUANTIDADENAOLIDOCONHECIDO)
public class ValorLogicoCaixaPostalEstatisticasQuantidadeNaoLidoConhecido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoCaixaPostalEstatisticasQuantidadeNaoLidoConhecido(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valorDefinido) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            //UsuarioCRM usuario = (UsuarioCRM) getControle().getCPinst(CPLogEmailRecebidoLido.usuario).getValor();
            // ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(LogEmailRecebidoLido.class,
            //       UtilSBPersistencia.getEMDoContexto())
            //      .addCondicaoManyToManyContendoObjeto(CPLogEmailRecebidoLido.usuario, usuario);
            try {
                Long quantidadeConhecido = new ConsultaDinamicaDeEntidade(EmailRecebido.class, em)
                        .addCondicaoNegativo(CPEmailRecebido.foilidoporusuariodestinatario)
                        .addCondicaoNegativo(CPEmailRecebido.foiignorado)
                        .addCondicaoManyToOneIgualA(getControle().getCaixaPostal())
                        .addCondicaoManyToOneIgualA(FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro())
                        .resultadoSomarQuantidade();
                getControle().setQuantidadeNaoLidoConhecido(quantidadeConhecido.intValue());
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro calculando" + getCampoInst().getClassePontoIdentificador(), t);
            }
            UtilSBPersistencia.fecharEM(em);

        }

        return getControle().getQuantidadeNaoLidoConhecido();
    }

    public CaixaPostalEstatisticas getControle() {
        return (CaixaPostalEstatisticas) getCampoInst().getObjetoDoAtributo();
    }
}
