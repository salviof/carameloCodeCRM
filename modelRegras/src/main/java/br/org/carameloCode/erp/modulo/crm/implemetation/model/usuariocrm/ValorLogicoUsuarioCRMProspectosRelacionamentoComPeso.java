package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.calculosListagens.FabTipoFiltroCalculo;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.PROSPECTOSRELACIONAMENTOCOMPESO)
public class ValorLogicoUsuarioCRMProspectosRelacionamentoComPeso
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMProspectosRelacionamentoComPeso(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private final ComparadorOrdanacaoMeusProspectos comparador = new ComparadorOrdanacaoMeusProspectos();
    private List<TipoRelacionamento> relacionamentos = null;

    @Override
    public Object getValor(Object... pEntidade) {

        UsuarioCRM usuario = getUsuario();

        if (isCacheAtivado()) {
            return usuario.getProspectosRelacionamentoComPeso();
        }
        if (relacionamentos == null) {
            relacionamentos = UtilSBPersistencia.getListaRegistrosByHQL("from TipoRelacionamento where peso >= 0", -1, UtilSBPersistencia.getEMDoContexto());
            relacionamentos.forEach(rl -> {
                rl.getId();
                rl.getPeso();
            });
        }

        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, UtilSBPersistencia.getEMDoContexto());
        novaConsulta.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", usuario);
        novaConsulta.addCondicaoManyToOneContemNoIntervalo("relacionamento", relacionamentos);

        setValorSeCacheExipirado(5000, novaConsulta, FabTipoFiltroCalculo.LISTAGENS);
        getUsuario().getProspectosRelacionamentoComPeso().sort(comparador);
        return getUsuario().getProspectosRelacionamentoComPeso();

    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoDoAtributo();
    }

    public void setValorSeCacheExipirado(int segundosCache, ConsultaDinamicaDeEntidade pConsulta, FabTipoFiltroCalculo pTipoCalculo) {
        if (isCacheAtivado()) {
            return;
        } else {
            switch (pTipoCalculo) {
                case SOMA_QTD:
                    break;
                case REGISTRO_UNICO:
                    break;
                case MAIOR:
                    break;
                case MENOR:
                    break;
                case LISTAGENS:
                    setValorPorReflexao(pConsulta.resultadoRegistros());
                    break;
                default:
                    throw new AssertionError(pTipoCalculo.name());

            }

        }
        ativarCache(segundosCache);

    }

}
