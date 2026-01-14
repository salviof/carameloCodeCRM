/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ComoFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Modulo CRM Caramelo's Code"}, fabricaVinculada = FabModulosCRM.class, plural = "Módulos CRM",
        modulo = ERPCrm.NOME_MODULO_ERP
)
public class ModuloIntranetCasanova extends ModuloAcaoSistema {

    @Enumerated(EnumType.ORDINAL)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabModulosCRM moduloFab;

    @Override
    public FabModulosCRM getFabricaObjeto() {
        return moduloFab;
    }

    @Override
    public ComoFabricaModulo getEnumVinculado() {
        return moduloFab;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        super.setEnumVinculado(pFabrica); //To change body of generated methods, choose Tools | Templates.
    }

}
