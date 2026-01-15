/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.geradorCodigo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.codigoAcesso.CodigoConvite;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCGeradorDeID;
import java.io.Serializable;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author desenvolvedor
 */
public class GeradorIdCodigoConvite implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object obj) throws HibernateException {
        try {
            CodigoConvite codigoAtual = ((CodigoConvite) obj);
            String codigo = UtilCRCGeradorDeID.getIdentificadorUnicoNumerosLetras();
            codigoAtual.setNome(codigo);
            return Math.abs(codigo.hashCode());

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando id para Ação", t);
        }

        return 0;
    }

}
