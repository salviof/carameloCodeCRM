package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.filtroTipoRelacionamento;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabTipoCondicaoFiltroRelacionamento implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 1, nomeObjeto = "Contém")
    CONTEMTRECHO,
    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 2, nomeObjeto = "Igual")
    IGUAL,
    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 3, nomeObjeto = "Diferente")
    DIFERENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 4, nomeObjeto = "Maior que")
    MAIOR_QUE,
    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 5, nomeObjeto = "Menor que")
    MENOR_QUE,
    @InfoObjetoDaFabrica(classeObjeto = TipoCondicaoFiltroRelacionamento.class, id = 6, nomeObjeto = "Parecido")
    PARECIDO;

    @Override
    public Object getRegistro() {
        TipoCondicaoFiltroRelacionamento tipo = (TipoCondicaoFiltroRelacionamento) ComoFabricaComPersistencia.super.getRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        switch (this) {
            case CONTEMTRECHO:
                tipo.setIcone("fa fa-check");
                break;
            case IGUAL:
                tipo.setIcone("fa fa-check-circle-o");
                break;
            case DIFERENTE:
                tipo.setIcone("fa fa-ban");
                break;
            case MAIOR_QUE:
                tipo.setIcone("fa fa-search-plus");
                break;
            case MENOR_QUE:
                tipo.setIcone("fa fa-search-minus");
                break;
            case PARECIDO:
                tipo.setIcone("fa fa-thumbs-o-up");
                break;
            default:
                throw new AssertionError();
        }
        return tipo;
    }

}
