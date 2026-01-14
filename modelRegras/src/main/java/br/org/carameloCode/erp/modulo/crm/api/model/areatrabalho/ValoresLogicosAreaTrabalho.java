package br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AreaTrabalho.class)
public enum ValoresLogicosAreaTrabalho {
	NOME, NAOLIDOEMAILQTD, NAOLIDOEMAIDESCONHECIDOLQTD, NAOLIDOEMAILASSINATURASQTD, NAOLIDOEMAILPESSOASQTD, NAOLIDAMENSAGEMQTD, NAOEXECUTADOAGENDAQTD
}