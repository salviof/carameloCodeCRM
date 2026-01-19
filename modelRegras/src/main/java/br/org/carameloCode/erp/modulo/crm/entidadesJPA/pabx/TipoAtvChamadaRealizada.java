package br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;

import javax.persistence.Entity;

@InfoObjetoSB(tags = "Tipo Chamada Realizada", plural = "Tipo Chamadas Realizadas", icone = "fa fa-phone")
@Entity
public class TipoAtvChamadaRealizada extends TipoAtividadeCRM {
}
