package br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValorLogicoAreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValoresLogicosAreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.CPEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.CPEmailRecebido;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@ValorLogicoAreaTrabalho(calculo = ValoresLogicosAreaTrabalho.NAOLIDOEMAILPESSOASQTD)
public class ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtd
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtd(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private boolean valorFoiAtualizado = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valorFoiAtualizado) {
            if (getAreaTrabalho().getUsuario().getCaixaPostalPrincipal() == null) {
                getAreaTrabalho().setNaoLidoEmailPessoasQtd(0);
                valorFoiAtualizado = true;

            } else {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                try {

                    ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(EmailRecebido.class, em);

                    novaConsulta.addCondicaoNegativo(CPEmailRecebido.foilidoporusuariodestinatario);
                    novaConsulta.addCondicaoNegativo(CPEmailRecebido.foiignorado);
                    novaConsulta.addCondicaoManyToOneIgualA(getAreaTrabalho().getUsuario().getCaixaPostalPrincipal());
                    novaConsulta.addCondicaoManyToOneDiferenteDeNulo(CPEmailCrm.prospecto);
                    Long valor = novaConsulta.resultadoSomarQuantidade();
                    getAreaTrabalho().setNaoLidoEmailPessoasQtd(valor.intValue());
                    valorFoiAtualizado = true;
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha obtendo valor de " + this.getClass().getSimpleName(), t);
                }
                UtilSBPersistencia.fecharEM(em);
            }

        }
        return getAreaTrabalho().getNaoLidoEmailPessoasQtd();

    }

    public AreaTrabalho getAreaTrabalho() {
        return (AreaTrabalho) getCampoInst().getObjetoDoAtributo();
    }
}
