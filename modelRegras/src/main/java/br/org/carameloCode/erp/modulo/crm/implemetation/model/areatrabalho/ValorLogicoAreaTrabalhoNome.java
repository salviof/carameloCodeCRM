package br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValorLogicoAreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.ValoresLogicosAreaTrabalho;

@ValorLogicoAreaTrabalho(calculo = ValoresLogicosAreaTrabalho.NOME)
public class ValorLogicoAreaTrabalhoNome extends ValorLogicoCalculoGenerico {

    public ValorLogicoAreaTrabalhoNome(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(getAreaTrabalho().getNome())) {
            if (getAreaTrabalho().getId() == null) {
                return "Area não definida";
            } else {
                int areas_de_trabalho = getAreaTrabalho().getUsuario().getAreasDisponiveis().size();
                return "Area personalizada" + areas_de_trabalho;
            }
        }
        return getAreaTrabalho().getNome();
    }

    public AreaTrabalho getAreaTrabalho() {
        return (AreaTrabalho) getCampoInst().getObjetoDoAtributo();
    }

}
