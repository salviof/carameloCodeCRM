package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.ATENDENTESPOSSIVEIS)
public class ValorLogicoEscopoPesqHorarioPublicadoAtendentesPossiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesqHorarioPublicadoAtendentesPossiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEscopo().getAtendentesPossiveis() == null || getEscopo().getAtendentesPossiveis().isEmpty()) {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();
            List<UsuarioCRM> atendentesDisponiveis = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, em)
                    .addCondicaoPositivo(CPUsuarioSB.ativo).gerarResultados();

            List<UsuarioCrmCliente> usuariosClientes = UtilSBPersistencia.getListaTodos(UsuarioCrmCliente.class, em);
            atendentesDisponiveis.removeAll(usuariosClientes);
            getEscopo().setAtendentesPossiveis((List) atendentesDisponiveis);
        }
        return getEscopo().getAtendentesPossiveis();
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
