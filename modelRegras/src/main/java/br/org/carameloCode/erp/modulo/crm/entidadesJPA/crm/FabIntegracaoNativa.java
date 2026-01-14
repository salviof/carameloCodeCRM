/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.integracoes.restInterprestfull.implementacao.GestaoTokenRestInterprestfull;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabIntegracaoNativa implements ComoFabricaComPersistencia {
    @InfoObjetoDaFabrica(id = 1, classeObjeto = SistemaERPConfiavel.class, nomeObjeto = "Sistema fatura")
    FATURA;

    @Override
    public SistemaERPConfiavel getRegistro() {
        switch (this) {
            case FATURA:
                SistemaERPConfiavel fatura = new SistemaERPConfiavel();
                fatura.setNome("Sistema fatura");
                fatura.setUrlPublicaEndPoint("http://localhost:8080/acoesRestful");
                fatura.setDominio("localhost");

                fatura.setUrlRecepcaoCodigo("http://localhost:8086/solicitacaoAuth2Recept/code/Usuario/" + GestaoTokenRestInterprestfull.class.getSimpleName() + "/" + "UTF8");
                fatura.setChavePublica("MIICHzANBgkqhkiG9w0BAQEFAAOCAgwAMIICBwKCAf4AjY0+i1BaDFKrlqJ5zb9/oWfPbatBr66wplv+vHJIz+7CETu8OdI+kOFCkwRXOrkUFQ5yXePfKOg83YVDUxjF0Ur0Hvxe0kIsQnwzrSwco1Bl4/PzzQkxZvEB0kF944qHS/XISU4/7a7UO5pg3UoAO9uBuAEjB8FY9V6sk8xFbmjvkdP8b3DNbCn2QhreLcCiWc8Z3MnNKwgzqUymMKFCPk3HzWs21IuEEYz796BSRcXMGrFKlq48ckmOcbHAhXP99twanLG3FDe4e8/rtmde2W/zZTfPO1VPjJ6Tbt6oon+neOn4kNcdmQLQwwkcl9TMqFGJv5rbfL+C05snAqOLaGFMvgw/62KAYk5gX4I2s6SnCO9wI/nl3qp19bJgTWSrpW6IyzfulccnUgRXErj/mgezMGMaBgs9Be/LmUWfOlMQansC39i5ZtkXOVxL1pFjthArYIRdRqU9Hkq3bbpqPXpR6qzL3/wZdkMDPMfixGz4ngoOOaHa9XhFwVvhlaiqZofi3orE8veSmEE1lyveJscqbr+wmWJ4Eu/X/Hvz9dhLD7hMprq7SZHZJ8aqezbQU9PmQYP0rbF9AJwdNPbrKI1ehj2jp3D0CiVrlINxXqELKww4LRN/u5d5Hr4zTzl4YGimZIkgC4+FcFKHnrcbEtfI8VZvGMiXT61cQokCAwEAAQ==");

                return fatura;

            default:
                return (SistemaERPConfiavel) ComoFabricaComPersistencia.super.getRegistro();

        }

    }

}
