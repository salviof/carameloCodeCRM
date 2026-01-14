package br.org.carameloCode.erp.modulo.crm.api.dominio.json_bind_caramelo_code_remoto.ServidorEmailAvancado;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.DTO_SB_JSON_PROCESSADOR_GENERICO;
import br.org.carameloCode.erp.modulo.crm.api.dominio.json_bind_caramelo_code_remoto.ServidorEmailAvancado.DTOServidorEmailAvancado;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public class JsonBindDTOServidorEmailAvancado
        extends
        DTO_SB_JSON_PROCESSADOR_GENERICO<DTOServidorEmailAvancado> {

    public JsonBindDTOServidorEmailAvancado() {
        super(DTOServidorEmailAvancado.class);
    }

    @Override
    public DTOServidorEmailAvancado deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return new DTOServidorEmailAvancado();
    }
}
