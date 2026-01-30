package br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@InfoObjetoSB(plural = "Disparo de Mensagens WhatsApp", tags = "Disparo WhatsApp", icone = "fa fa-paper-plane")
@Entity
public class DisparoEmMassa extends EntidadeSimples {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataDisparo;

    @ManyToOne(targetEntity = StatusDisparo.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private FabStatusDisparo statusDisparoFabrica;

    @ManyToOne
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValidadorLogico()
    private MetaRelacionamento metaRelacionamento;

    @OneToMany(fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "metaRelacionamento.tiposRelacionamento")
    private List<TipoRelacionamento> relacionamentos;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDataDisparo() {
        return dataDisparo;
    }

    public void setDataDisparo(Date dataDisparo) {
        this.dataDisparo = dataDisparo;
    }

    public FabStatusDisparo getStatusDisparoFabrica() {
        return statusDisparoFabrica;
    }

    public void setStatusDisparoFabrica(FabStatusDisparo statusDisparoFabrica) {
        this.statusDisparoFabrica = statusDisparoFabrica;
    }

    public MetaRelacionamento getMetaRelacionamento() {
        return metaRelacionamento;
    }

    public void setMetaRelacionamento(MetaRelacionamento metaRelacionamento) {
        this.metaRelacionamento = metaRelacionamento;
    }

    public List<TipoRelacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(List<TipoRelacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }


}
