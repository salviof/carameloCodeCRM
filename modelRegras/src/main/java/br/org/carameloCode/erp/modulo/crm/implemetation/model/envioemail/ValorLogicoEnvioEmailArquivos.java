package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.ARQUIVOS)
public class ValorLogicoEnvioEmailArquivos extends ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private List<ArquivoAnexado> arquivosAnexados;

    @Override
    public Object getValor(Object... pEntidade) {
        if (arquivosAnexados == null) {
            arquivosAnexados = new ArrayList<>();
            getEmail().getArquivos().forEach(arquivosAnexados::add);
            getEmail().setArquivos(arquivosAnexados);
        }
        return getEmail().getArquivos();
    }

    public EnvioEmail getEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
