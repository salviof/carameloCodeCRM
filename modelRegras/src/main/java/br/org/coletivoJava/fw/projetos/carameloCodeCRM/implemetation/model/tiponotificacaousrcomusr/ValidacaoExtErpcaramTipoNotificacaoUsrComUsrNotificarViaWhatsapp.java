package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacaousrcomusr;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacaousrcomusr.ValidacaoTipoNotificacaoUsrComUsrNotificarViaWhatsapp;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValidadorTipoNotificacaoUsrComUsr;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacaousrcomusr.ValidadoresTipoNotificacaoUsrComUsr;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoNotificacaoUsrComUsr(validador = ValidadoresTipoNotificacaoUsrComUsr.NOTIFICARVIAWHATSAPP)
public class ValidacaoExtErpcaramTipoNotificacaoUsrComUsrNotificarViaWhatsapp
        extends
        ValidacaoTipoNotificacaoUsrComUsrNotificarViaWhatsapp {

    public ValidacaoExtErpcaramTipoNotificacaoUsrComUsrNotificarViaWhatsapp(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {
        return super.validar(o);
    }

}
