/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade.FluxoDeAtividades;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.FabTipoRelacionamentoMarketingDigital;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBWPComponentePrimeModel {

    private static final Map<TipoRelacionamento, Element> ELEMENTO_ORGANOGRAMA_POR_TIPO_RELACIONAMENTO = new HashMap();

    private static enum MODELO_POSICIONAMENTO {
        AO_REDOR, ABAIXO, AESQUERDA, ADIREITA, POSICAO_INICIAL_SUPERIOR;
    };

    public static String ESTILO_LINHA_PRINCIPAL = "{strokeStyle:'#C7B097',lineWidth:3}";
    public static String ESTILO_LINHA_SECUNDARIA = "{strokeStyle:'#C7B097',lineWidth:1}";

    public static String ESTILO_SUCESSO = "ui-diagram-success";
    public static String ESTILO_FALHA = "ui-diagram-fail";
    public static String ESTILO_NORMAL = "";
    public static String ESTILO_ACOES_POSSIVEIS = "ui-diagram-acoespossiveis";

    private static String getColunaByPesoRelacionamento(TipoRelacionamento ptipo) {
        if (ptipo.getPeso() < 0) {
            return "05em";
        }
        if (ptipo.getPeso() == 0) {
            return "20em";
        }
        if (ptipo.getPeso() > 0) {
            return "20em";
        }
        return "20em";
    }

    private static PosicaoDiagrama getPosicao(Element referencia, int indice, MODELO_POSICIONAMENTO pModelo) {
        PosicaoDiagrama posicao = new PosicaoDiagrama();
        if (pModelo != MODELO_POSICIONAMENTO.POSICAO_INICIAL_SUPERIOR) {
            indice = 1;
            String xRef = referencia.getX();
            String yRef = referencia.getY();
            xRef = UtilCRCStringFiltros.getNumericosDaString(xRef);
            yRef = UtilCRCStringFiltros.getNumericosDaString(yRef);
            Integer valorXref = Integer.parseInt(xRef);
            Integer valorYref = Integer.parseInt(yRef);
            valorXref = valorXref + 20;
            valorYref = valorYref + 10;
            switch (pModelo) {
                case AO_REDOR:

                    break;
                case ABAIXO:

                    posicao.setX(xRef);
                    posicao.setY(valorYref.toString() + "em");

                    break;
                case AESQUERDA:

                    posicao.setX(valorXref.toString() + "em");
                    posicao.setY(yRef);

                    break;
                case ADIREITA:
                    posicao.setX(valorXref.toString() + "em");
                    posicao.setY(yRef);
                    break;

                default:
                    throw new AssertionError(pModelo.name());

            }
        } else {
            posicao.setY("20em");
            posicao.setX("5em");

        }

        return posicao;
    }

    private static Element getElementoDiagramaPorRelacionamento(TipoRelacionamento pTipo, PosicaoDiagrama pPosicao) {
        Element atividadeInicial = new Element(pTipo.getNome(), pPosicao.getX(), pPosicao.getY());
        return atividadeInicial;
    }

    private static Element getElementoDiagramaOutrasAcoes(TipoRelacionamento pTipo, PosicaoDiagrama pPosicao) {
        String texto = null;
        for (TipoAtividadeCRM tipoAtividade : pTipo.getTiposAtividadeDisponiveis()) {
            if (tipoAtividade.getRelacionamentoGerado() == null) {
                texto += " " + tipoAtividade.getNomeAtividade() + " \n ";
            }
        }
        if (texto != null) {
            return new Element(texto, pPosicao.getX(), pPosicao.getY());
        } else {
            return null;
        }
    }

    private static Element getAtividadeAcaoPositiva(TipoRelacionamento pTipo, PosicaoDiagrama pPosicao) {
        Element atividadeInicial = new Element(pTipo.getNome(), pPosicao.getX(), pPosicao.getY());
        return atividadeInicial;
    }

    private static Element getAtividadeAcaoNegativa(TipoRelacionamento pTipo, PosicaoDiagrama pPosicao) {
        Element atividadeInicial = new Element(pTipo.getNome(), pPosicao.getX(), pPosicao.getY());
        return atividadeInicial;
    }

    private static EndPoint pontoDeConecao(Element pElemento, EndPointAnchor local) {

        for (EndPoint ancora : pElemento.getEndPoints()) {
            if (ancora.getAnchor() == local) {
                return ancora;
            }
        }
        throw new UnsupportedOperationException("O ponto de conexão [" + local + "] não foi encontrado" + pElemento.getData());

    }

    private static void adicionarItemDoDiagrama(
            DefaultDiagramModel organograma,
            Element pEventosComplementares,
            TipoRelacionamento pItemOriginario,
            TipoRelacionamento pTipoNovoItem,
            TipoAtividadeCRM pTipoAtividadeLigacao,
            int pIndice,
            MODELO_POSICIONAMENTO posicionamento
    ) {

        Element elementoDaACaoOrigem = ELEMENTO_ORGANOGRAMA_POR_TIPO_RELACIONAMENTO.get(pItemOriginario);

        if (elementoDaACaoOrigem == null) {
            throw new UnsupportedOperationException("Elemento Origem não foi determinado");
        }
        PosicaoDiagrama posicao = getPosicao(elementoDaACaoOrigem, pIndice, posicionamento);
        Element novoElemento = getElementoDiagramaPorRelacionamento(pTipoNovoItem, posicao);
        novoElemento.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));

        switch (pTipoNovoItem.getResultado().getFabricaResultado()) {
            case POSITIVO:
                novoElemento.setStyleClass(ESTILO_SUCESSO);
                break;
            case NEGATIVO:
                novoElemento.setStyleClass(ESTILO_FALHA);
                break;
            case NEUTRO:
                break;
            default:
                throw new AssertionError(pTipoNovoItem.getResultado().getFabricaResultado().name());

        }

        switch (posicionamento) {
            case AO_REDOR:
                break;
            case ABAIXO:
                novoElemento.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));

                organograma.addElement(novoElemento);
                organograma.connect(createConnection(
                        pontoDeConecao(elementoDaACaoOrigem, EndPointAnchor.BOTTOM),
                        pontoDeConecao(novoElemento, EndPointAnchor.TOP), pTipoAtividadeLigacao.getNomeAtividade()));

                break;
            case AESQUERDA:
                novoElemento.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

                organograma.addElement(novoElemento);

                organograma.connect(createConnection(
                        pontoDeConecao(elementoDaACaoOrigem, EndPointAnchor.BOTTOM),
                        pontoDeConecao(novoElemento, EndPointAnchor.LEFT), pTipoAtividadeLigacao.getNomeAtividade()));

                break;
            case ADIREITA:
                novoElemento.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                organograma.addElement(novoElemento);
                organograma.connect(createConnection(
                        pontoDeConecao(elementoDaACaoOrigem, EndPointAnchor.BOTTOM),
                        pontoDeConecao(novoElemento, EndPointAnchor.RIGHT), pTipoAtividadeLigacao.getNomeAtividade()));

                break;
            case POSICAO_INICIAL_SUPERIOR:
                break;
            default:
                throw new AssertionError(posicionamento.name());

        }
        novoElemento.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        if (pEventosComplementares != null) {
            pEventosComplementares.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
            pEventosComplementares.setStyleClass("ui-diagram-acoespossiveis");
            organograma.addElement(pEventosComplementares);
            organograma.connect(createConnection(
                    pontoDeConecao(novoElemento, EndPointAnchor.BOTTOM_RIGHT),
                    pontoDeConecao(pEventosComplementares, EndPointAnchor.LEFT),
                    pTipoAtividadeLigacao.getNomeAtividade()));

        }
        ELEMENTO_ORGANOGRAMA_POR_TIPO_RELACIONAMENTO.put(pTipoNovoItem, novoElemento);
    }

    private static DefaultDiagramModel gerarNovoDiagrama() {
        DefaultDiagramModel diagrama = new DefaultDiagramModel();
        diagrama.setMaxConnections(-1);
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle(ESTILO_LINHA_PRINCIPAL);
        diagrama.setDefaultConnector(connector);
        return diagrama;

    }

    private static Connection createConnection(EndPoint from, EndPoint to, String label) {
        if (from == null) {
            throw new UnsupportedOperationException("Impossível criar uma ligação sem especificar a origem");
        }
        if (to == null) {
            throw new UnsupportedOperationException("Impossível criar uma ligação sem especificar o destino");
        }

        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    private static Connection gerarConecao(EndPoint from, EndPoint to, String label) {

        if (from == null) {
            throw new UnsupportedOperationException("Impossível criar uma ligação sem especificar a origem");
        }
        if (to == null) {
            throw new UnsupportedOperationException("Impossível criar uma ligação sem especificar o destino");
        }
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public static DefaultDiagramModel getDiagramaProximasAcoesDoRelacionamento(TipoRelacionamento pRelacionamento) {

        DefaultDiagramModel novodiagrama = gerarNovoDiagrama();
        Element relacionamentoInicial = getElementoDiagramaPorRelacionamento(pRelacionamento, getPosicao(null, 0, MODELO_POSICIONAMENTO.POSICAO_INICIAL_SUPERIOR));
        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        relacionamentoInicial.setDraggable(true);
        novodiagrama.addElement(relacionamentoInicial);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle(ESTILO_LINHA_PRINCIPAL);

        for (TipoAtividadeCRM tipoAtividade : pRelacionamento.getTiposAtividadeDisponiveis()) {
            if (tipoAtividade.getRelacionamentoGerado() != null) {
                MODELO_POSICIONAMENTO posicionamento = null;
                TipoRelacionamento relacionamentogerado = tipoAtividade.getRelacionamentoGerado();
                if (relacionamentogerado.getPeso() > pRelacionamento.getPeso()) {
                    posicionamento = MODELO_POSICIONAMENTO.ADIREITA;
                }
                if (relacionamentogerado.getPeso() < pRelacionamento.getPeso()) {
                    posicionamento = MODELO_POSICIONAMENTO.AESQUERDA;
                }
                if (relacionamentogerado.getPeso() == pRelacionamento.getPeso()) {
                    posicionamento = MODELO_POSICIONAMENTO.ABAIXO;
                }

                PosicaoDiagrama posicao = getPosicao(relacionamentoInicial, 0, posicionamento);
                Element elementoAtividadeProgresso = new Element(relacionamentogerado.getNomeDoRelacionado(), posicao.getX(), posicao.getY());
                elementoAtividadeProgresso.setDraggable(true);

                switch (posicionamento) {

                    case ABAIXO:
                        elementoAtividadeProgresso.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                        novodiagrama.connect(createConnection(
                                pontoDeConecao(relacionamentoInicial, EndPointAnchor.BOTTOM),
                                pontoDeConecao(elementoAtividadeProgresso, EndPointAnchor.TOP), tipoAtividade.getNomeAtividade()));
                        break;
                    case AESQUERDA:
                        elementoAtividadeProgresso.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                        novodiagrama.connect(createConnection(
                                pontoDeConecao(relacionamentoInicial, EndPointAnchor.LEFT),
                                pontoDeConecao(elementoAtividadeProgresso, EndPointAnchor.RIGHT), tipoAtividade.getNomeAtividade()));
                        break;
                    case ADIREITA:
                        elementoAtividadeProgresso.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

                        novodiagrama.connect(createConnection(
                                pontoDeConecao(relacionamentoInicial, EndPointAnchor.RIGHT),
                                pontoDeConecao(elementoAtividadeProgresso, EndPointAnchor.LEFT), tipoAtividade.getNomeAtividade()));
                        break;

                }
                novodiagrama.addElement(elementoAtividadeProgresso);

            }
        }

        return novodiagrama;
    }

    public static DefaultDiagramModel getDiagramModel(FluxoDeAtividades fluxo) {

        DefaultDiagramModel diagrama = gerarNovoDiagrama();

        TipoRelacionamento tipoRelacionamentoInicial = fluxo.getRelacionamentoInicial();
        Element relacionamentoInicial = getElementoDiagramaPorRelacionamento(tipoRelacionamentoInicial, getPosicao(null, 0, MODELO_POSICIONAMENTO.POSICAO_INICIAL_SUPERIOR));

        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        relacionamentoInicial.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        ELEMENTO_ORGANOGRAMA_POR_TIPO_RELACIONAMENTO.put(tipoRelacionamentoInicial, relacionamentoInicial);
        boolean temAcaoGeradora = true;
        TipoRelacionamento tipoRelacionamentoDoMomento = tipoRelacionamentoInicial;
        int id = 0;

        int indiceNegativo = 0;
        int indiceNeutro = 0;
        int indicePositivo = 0;

        diagrama.addElement(relacionamentoInicial);

        while (temAcaoGeradora) {
            List<TipoAtividadeCRM> atividadesGeradoras = tipoRelacionamentoDoMomento.getAtividadesGeradorasNovoRelacionemtnos();

            int pesoRelacionamentoAnterior = tipoRelacionamentoDoMomento.getPeso();

            TipoRelacionamento novoTipoRelacionamento = null;
            for (TipoAtividadeCRM tipoAtividade : atividadesGeradoras) {

                TipoRelacionamento tipoRelacionamentoGerado = tipoAtividade.getRelacionamentoGerado();
                Element elementoAtividadesComplementares = getElementoDiagramaOutrasAcoes(tipoRelacionamentoDoMomento, getPosicao(relacionamentoInicial, 0, MODELO_POSICIONAMENTO.ADIREITA));
                if (tipoRelacionamentoGerado.getPeso() < 0) {
                    adicionarItemDoDiagrama(diagrama, elementoAtividadesComplementares, tipoRelacionamentoDoMomento, tipoRelacionamentoGerado, tipoAtividade, indiceNegativo, MODELO_POSICIONAMENTO.AESQUERDA);
                    indiceNegativo++;
                }
                if (tipoRelacionamentoGerado.getPeso() > 0 && tipoRelacionamentoGerado.getPeso() < 10) {
                    adicionarItemDoDiagrama(diagrama, elementoAtividadesComplementares, tipoRelacionamentoDoMomento, tipoRelacionamentoGerado, tipoAtividade, indiceNeutro, MODELO_POSICIONAMENTO.ABAIXO);
                    indiceNeutro++;
                }

                if (tipoRelacionamentoGerado.getPeso() >= 10) {
                    adicionarItemDoDiagrama(diagrama, elementoAtividadesComplementares, tipoRelacionamentoDoMomento, tipoRelacionamentoGerado, tipoAtividade, indicePositivo, MODELO_POSICIONAMENTO.ADIREITA);
                    indicePositivo++;
                }
                if (pesoRelacionamentoAnterior < tipoAtividade.getRelacionamentoGerado().getPeso()) {

                    novoTipoRelacionamento = tipoAtividade.getRelacionamentoGerado();

                }
            }

            if (novoTipoRelacionamento != null) {
                temAcaoGeradora = true;
                tipoRelacionamentoDoMomento = novoTipoRelacionamento;
            } else {
                temAcaoGeradora = false;
            }

        }
        for (FabTipoRelacionamentoMarketingDigital tipoRelacionamento : FabTipoRelacionamentoMarketingDigital.class.getEnumConstants()) {

        }

        return diagrama;
    }

}
