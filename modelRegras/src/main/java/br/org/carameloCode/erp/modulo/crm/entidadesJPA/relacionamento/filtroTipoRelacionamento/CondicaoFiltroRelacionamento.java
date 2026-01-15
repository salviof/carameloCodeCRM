package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.filtroTipoRelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Filtro de relacionamento", plural = "Filtros de relacionamentos")
public class CondicaoFiltroRelacionamento extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = FiltroRelacionamento.class)
    private FiltroRelacionamento filtro;

    @ManyToOne(targetEntity = TipoDadoCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoDadoCRM tipoDado;

    @ManyToOne()
    private TipoCondicaoFiltroRelacionamento tipoCondicao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String valorEmpacotado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int toleranciaParecido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDadoCRM getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDadoCRM tipoDado) {
        this.tipoDado = tipoDado;
    }

    public TipoCondicaoFiltroRelacionamento getTipoCondicao() {
        return tipoCondicao;
    }

    public void setTipoCondicao(TipoCondicaoFiltroRelacionamento tipoCondicao) {
        this.tipoCondicao = tipoCondicao;
    }

    public String getValorEmpacotado() {
        return valorEmpacotado;
    }

    public void setValorEmpacotado(String valorEmpacotado) {
        this.valorEmpacotado = valorEmpacotado;
    }

    public int getToleranciaParecido() {
        return toleranciaParecido;
    }

    public void setToleranciaParecido(int toleranciaParecido) {
        this.toleranciaParecido = toleranciaParecido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FiltroRelacionamento getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroRelacionamento filtro) {
        this.filtro = filtro;
    }

}
