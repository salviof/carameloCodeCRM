package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.CPTipoServico;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(descricao = " Serviço Oferecido", icone = "", plural = "Serviços Oferecidos", tags = "Serviço Oferecido")
public class ServicoOferecido extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(descricao = "Identificador", label = "Id", tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(descricao = "Nome", label = "Observação", tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(descricao = "Descrição", label = "Descrição", tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;

    @ManyToOne(targetEntity = TipoServico.class, fetch = FetchType.LAZY, optional = false)
    @InfoCampo(descricao = "Serviço Disponivel", label = "Serviço Disponivel", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    @InfoGrupoCampo(camposDeclarados = {"id", "nome", CPTipoServico.descricao})
    private TipoServico tipoServico;

    @ManyToOne(targetEntity = Pessoa.class)
    private Pessoa prospecto;

    @InfoCampo(descricao = "Valor Setup", label = "Valor Setup", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorSetup;

    @InfoCampo(descricao = "Valor Mensal", label = "Valor Mensal", tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorMensal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = Orcamento.class)
    private Orcamento orcamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Serviço Ativo?")
    @InfoCampoValorLogico(nomeCalculo = "Servico Ativo")
    private boolean servicoAtivo;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = Orcamento.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {

        for (Object obj : parametros) {
            if (obj instanceof Orcamento) {
                setOrcamento(getParametroInicialEnviado(Orcamento.class, parametros));
            }
        }
        for (Object obj : parametros) {
            if (obj instanceof Pessoa) {
                Pessoa pPessoa = (getParametroInicialEnviado(Pessoa.class, parametros));
                setOrcamento(pPessoa.getUltimoOrcamento());
            }
        }

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public double getValorSetup() {
        return valorSetup;
    }

    public void setValorSetup(double valorSetup) {
        this.valorSetup = valorSetup;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    public boolean isServicoAtivo() {
        return servicoAtivo;
    }

    public void setServicoAtivo(boolean servicoAtivo) {
        this.servicoAtivo = servicoAtivo;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

}
