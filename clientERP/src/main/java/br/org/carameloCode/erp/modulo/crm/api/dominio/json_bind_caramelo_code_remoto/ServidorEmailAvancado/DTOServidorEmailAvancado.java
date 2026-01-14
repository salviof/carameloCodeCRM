package br.org.carameloCode.erp.modulo.crm.api.dominio.json_bind_caramelo_code_remoto.ServidorEmailAvancado;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SBGENERICO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;

public class DTOServidorEmailAvancado
        extends
        DTO_SBGENERICO<ItfDTOServidorEmailAvancado>
        implements
        ItfDTOServidorEmailAvancado {

    public DTOServidorEmailAvancado(String pJson) {
        super(JsonBindDTOServidorEmailAvancado.class, pJson);
    }

    public DTOServidorEmailAvancado() {
        super(null, null);
    }

    @Override
    public void setDataHoraUltimoEmailRecebido(Date pDataHora) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.DATAHORA, pDataHora);
    }
}
