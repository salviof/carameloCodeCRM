package br.org.carameloCode.erp.modulo.crm.api.dominio.json_bind_caramelo_code_remoto.ServidorEmailAvancado;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.erp.dto.ItfDTOSBJSON;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidorEmailAvancado;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import br.org.carameloCode.erp.modulo.crm.api.dominio.json_bind_caramelo_code_remoto.ServidorEmailAvancado.JsonBindDTOServidorEmailAvancado;
import java.util.Date;
import java.lang.String;
import java.lang.Long;

/**
 *
 * @author salvio
 */
@JsonDeserialize(using = JsonBindDTOServidorEmailAvancado.class)
public interface ItfDTOServidorEmailAvancado
        extends
        ItfDTOSBJSON,
        ItfServidorEmailAvancado {

    @Override
    public default Date getDataHoraUltimoEmailRecebido() {
        return (Date) getValorPorReflexao();
    }

    @Override
    public default String getEnderecoServidor() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getEmail() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getSenha() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getNomeUnicoSlug() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default boolean isTemImagemPequenaAdicionada() {
        return (boolean) getValorPorReflexao();
    }

    @Override
    public default String getSlugIdentificador() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default Long getId() {
        return (Long) getValorPorReflexao();
    }

    @Override
    public default String getNomeDoObjeto() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getNomeDoObjetoPlural() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getRemetenteNome() {
        return (String) getValorPorReflexao();
    }

    @Override
    public default String getFromNome() {
        return (String) getValorPorReflexao();
    }
}
