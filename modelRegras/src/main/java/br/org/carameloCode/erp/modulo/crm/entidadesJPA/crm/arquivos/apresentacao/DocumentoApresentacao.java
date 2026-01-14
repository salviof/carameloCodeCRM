/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.apresentacao;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = {"Apresentação CRM"}, plural = "Apresentações CRM", icone = "fa fa-file-powerpoint-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class DocumentoApresentacao extends ArquivoAnexado {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tipoSevico_documentoApresentacao",
            joinColumns = @JoinColumn(name = "documentoApresentacao_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoServico_id"))
    @InfoCampo(label = "Tipo de Serviço", tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<TipoServico> tiposServicoVinculado;

    public List<TipoServico> getTipoServicoVinculado() {
        return tiposServicoVinculado;
    }

    public void setTipoServicoVinculado(List<TipoServico> tiposServicoVinculado) {
        this.tiposServicoVinculado = tiposServicoVinculado;
    }

}
