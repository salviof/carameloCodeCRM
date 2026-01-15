package br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@Entity
@InfoObjetoSB(tags = "Serviçõ recorrente", plural = "Serviços recorrentes")
public class TipoServicoRecorrente extends TipoServico {

    @Override
    @InfoPreparacaoObjeto()
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);
        setGeraPgtoSazonal(false);
        setGeraPgtoRecorrente(true);
        setFoiDefinidoTipoPgto(true);
    }

    @InfoCampo(label = "Valor Mensal Minimo", descricao = "Valor minimo por mês do Serviço", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorMensalMin;

    @InfoCampo(label = "Valor Mensal Maximo", descricao = "Valor maximo por mês do Serviço", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorMensalMax;

    public double getValorMensalMin() {
        return valorMensalMin;
    }

    public void setValorMensalMin(double valorMensalMin) {
        this.valorMensalMin = valorMensalMin;
    }

    public double getValorMensalMax() {
        return valorMensalMax;
    }

    public void setValorMensalMax(double valorMensalMax) {
        this.valorMensalMax = valorMensalMax;
    }

}
