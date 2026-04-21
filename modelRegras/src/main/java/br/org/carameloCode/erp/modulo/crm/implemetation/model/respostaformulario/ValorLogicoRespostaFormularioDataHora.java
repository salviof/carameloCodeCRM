package br.org.carameloCode.erp.modulo.crm.implemetation.model.respostaformulario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario.ValorLogicoRespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario.ValoresLogicosRespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta.RespostaFormulario;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import jakarta.json.JsonObject;
import java.time.Instant;
import java.util.Date;

@ValorLogicoRespostaFormulario(calculo = ValoresLogicosRespostaFormulario.DATAHORA)
public class ValorLogicoRespostaFormularioDataHora
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoRespostaFormularioDataHora(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getRespostaFormulario().getDataHora() == null) {
            if (getRespostaFormulario().getJsonResposta() != null) {
                JsonObject json = UtilCRCJson.getJsonObjectByTexto(getRespostaFormulario().getJsonResposta());
                if (json == null) {
                    return getRespostaFormulario().getDataHora();
                }
                String dataJson = json.getString("createdAt");
                Instant instant = Instant.parse(dataJson);

                // 2. Converte Instant para java.util.Date
                Date date = Date.from(instant);
                getRespostaFormulario().setDataHora(date);

            }
        }
        return getRespostaFormulario().getDataHora();
    }

    public RespostaFormulario getRespostaFormulario() {
        return (RespostaFormulario) getCampoInst().getObjetoRaizDoAtributo();
    }
}
