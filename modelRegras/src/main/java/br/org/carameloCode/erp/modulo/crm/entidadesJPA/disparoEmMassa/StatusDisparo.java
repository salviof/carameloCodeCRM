package br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@InfoObjetoSB(plural = "Status de Envio", tags = "Status Envio", fabricaVinculada = FabStatusDisparo.class)
public class StatusDisparo extends EntidadeORMStatus {
    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(nomeCalculo = "Icone Status")
    private String iconeStatus;

    @InfoCampo(tipo = FabTipoAtributoObjeto.STATUS_ENUM)
    private FabStatusDisparo statusFabrica;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getIconeStatus() {
        return iconeStatus;
    }

    public void setIconeStatus(String iconeStatus) {
        this.iconeStatus = iconeStatus;
    }

    public FabStatusDisparo getStatusFabrica() {
        return statusFabrica;
    }

    public void setStatusFabrica(FabStatusDisparo statusFabrica) {
        this.statusFabrica = statusFabrica;
    }
}
