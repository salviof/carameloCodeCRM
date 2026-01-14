package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValorLogicoEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValoresLogicosEmailCrm;

@ValorLogicoEmailCrm(calculo = ValoresLogicosEmailCrm.TEXTO)
public class ValorLogicoEmailCrmTexto extends ValorLogicoCalculoGenerico {

    public ValorLogicoEmailCrmTexto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getemail().getTexto() == null) {
            getemail().isUmEmailModoRascunho();

            if (getemail().getCampoInstanciadoByNomeOuAnotacao("umEmailModoRascunho").getValorComoBoolean()) {
                EntityManager em = UtilSBPersistencia.getEMDoContexto();
                UsuarioCRM usaurioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), em);

                // getemail().setTexto(usaurioLogado.getAssinaturaEmail());
            }
        }

        return getemail().getTexto();
    }

    public EmailCrm getemail() {
        return (EmailCrm) getCampoInst().getObjetoDoAtributo();
    }

}
