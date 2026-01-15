package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

/**
 *
 * {
 * "kind": "analytics#gaData", "id":
 * "https://www.googleapis.com/analytics/v3/data/ga?ids=ga:134801077&dimensions=ga:userType,ga:eventCategory,ga:eventLabel&metrics=ga:uniqueEvents&start-date=1daysAgo&end-date=2020-06-23",
 * "query": { "start-date": "1daysAgo", "end-date": "2020-06-23", "ids":
 * "ga:134801077", "dimensions": "ga:userType,ga:eventCategory,ga:eventLabel",
 * "metrics": [ "ga:uniqueEvents" ], "start-index": 1, "max-results": 1000 },
 * "itemsPerPage": 1000, "totalResults": 3, "selfLink":
 * "https://www.googleapis.com/analytics/v3/data/ga?ids=ga:134801077&dimensions=ga:userType,ga:eventCategory,ga:eventLabel&metrics=ga:uniqueEvents&start-date=1daysAgo&end-date=2020-06-23",
 * "profileInfo": { "profileId": "134801077", "accountId": "88127166",
 * "webPropertyId": "UA-88127166-1", "internalWebPropertyId": "130939805",
 * "profileName": "Todos os dados do website", "tableId": "ga:134801077" },
 * "containsSampledData": false, "columnHeaders": [ { "name": "ga:userType",
 * "columnType": "DIMENSION", "dataType": "STRING" }, { "name":
 * "ga:eventCategory", "columnType": "DIMENSION", "dataType": "STRING" }, {
 * "name": "ga:eventLabel", "columnType": "DIMENSION", "dataType": "STRING" }, {
 * "name": "ga:uniqueEvents", "columnType": "METRIC", "dataType": "INTEGER" } ],
 * "totalsForAllResults": { "ga:uniqueEvents": "6" }, "rows": [ [ "New Visitor",
 * "email", "(not set)", "3" ], [ "New Visitor", "email", "leituraEmailGmail",
 * "1" ], [ "New Visitor", "email", "leituraTesteDeEmail", "2" ] ] }
 *
 * @author sfurbino
 */
@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.FOILIDOPELODESTINATARIO)
public class ValorLogicoEnvioEmailFoiLidoPeloDestinatario
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailFoiLidoPeloDestinatario(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    /**
     *
     * https://ga-dev-tools.appspot.com/query-explorer/?ids=ga%3A134801077&start-date=1daysAgo&end-date=2020-06-23&metrics=ga%3AuniqueEvents&dimensions=ga%3AuserType%2Cga%3AeventCategory%2Cga%3AeventLabel&include-empty-rows=true
     * https://www.googleapis.com/analytics/v3/data/ga?ids=ga%3A134801077&start-date=1daysAgo&end-date=2020-06-23&metrics=ga%3AuniqueEvents&dimensions=ga%3AuserType%2Cga%3AeventCategory%2Cga%3AeventLabel&include-empty-rows=true&access_token=????????????????????????
     * https://www.google-analytics.com/collect?v=1&tid=UA-88127166-1&cid=667&t=event&ec=email&ea=open&el=leituraTesteDeEmail
     *
     * @param pEntidade
     * @return
     */
    @Override
    public Object getValor(Object... pEntidade) {

        return getEnvioEmail().isFoiLidoPeloDestinatario();

    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }

}
