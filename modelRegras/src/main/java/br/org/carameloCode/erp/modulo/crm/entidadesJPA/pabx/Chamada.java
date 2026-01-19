package br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

import javax.persistence.*;
import java.util.Date;

@InfoObjetoSB(tags = "Chamada", plural = "Chamadas")
@Entity
public class Chamada extends EntidadeSimples {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @Column(unique = true)
    private Long codigoChamada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataChamada;
//    @ManyToOne(targetEntity = TipoChamadaRealizada.class, optional = false)
//    private TipoChamadaRealizada tipoChamadaLigar;
//
//    @ManyToOne(targetEntity = TipoChamadaRecebida.class, optional = false)
//    private TipoChamadaRecebida tipoChamadaRecebida;

    public Long getId() {
        return id;
    }

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

    public Long getCodigoChamada() {
        return codigoChamada;
    }

    public void setCodigoChamada(Long codigoChamada) {
        this.codigoChamada = codigoChamada;
    }

    public Date getDataChamada() {
        return dataChamada;
    }

    public void setDataChamada(Date dataChamada) {
        this.dataChamada = dataChamada;
    }

//    public TipoChamadaRealizada getTipoChamadaLigar() {
//        return tipoChamadaLigar;
//    }
//
//    public void setTipoChamadaLigar(TipoChamadaRealizada tipoChamadaLigar) {
//        this.tipoChamadaLigar = tipoChamadaLigar;
//    }
//
//    public TipoChamadaRecebida getTipoChamadaRecebida() {
//        return tipoChamadaRecebida;
//    }
//
//    public void setTipoChamadaRecebida(TipoChamadaRecebida tipoChamadaRecebida) {
//        this.tipoChamadaRecebida = tipoChamadaRecebida;
//    }
}
