package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.ARQUIVOSANEXADOS)
public class ValorLogicoEnvioEmailAtividadeArquivosAnexados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeArquivosAnexados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private List<ArquivoAnexado> arquivosAnexados;

    @Override
    public Object getValor(Object... pEntidade) {
        if (arquivosAnexados == null) {
            arquivosAnexados = new ArrayList<>();
            getEnvioEmail().getArquivos().forEach(arquivosAnexados::add);
            getEnvioEmail().setArquivosAnexados(arquivosAnexados);
        }
        return getEnvioEmail().getArquivosAnexados();
    }

    public EnvioEmailAtividade getEnvioEmail() {
        return (EnvioEmailAtividade) getCampoInst().getObjetoDoAtributo();
    }

}
