package br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@Entity
@InfoObjetoSB(plural = "Tipos serviços sazonais", tags = "Tipos de serviço")
public class TipoServicoSazonal extends TipoServico {

    @Override
    @InfoPreparacaoObjeto()
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);
        setGeraPgtoSazonal(true);
        setGeraPgtoRecorrente(false);
        setFoiDefinidoTipoPgto(true);
    }

    @InfoCampo(label = "Valor Minimo Setup", descricao = "Valor minimo para Setup do Serviço", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorSetupMin;

    @InfoCampo(label = "Valor Maximo Setup", descricao = "Valor maximo para Setup do Serviço", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorSetupMax;

    public double getValorSetupMin() {
        return valorSetupMin;
    }

    public void setValorSetupMin(double valorSetupMin) {
        this.valorSetupMin = valorSetupMin;
    }

    public double getValorSetupMax() {
        return valorSetupMax;
    }

    public void setValorSetupMax(double valorSetupMax) {
        this.valorSetupMax = valorSetupMax;
    }

}
