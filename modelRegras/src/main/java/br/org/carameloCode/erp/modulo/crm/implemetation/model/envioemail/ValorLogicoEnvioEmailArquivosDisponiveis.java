package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.model.ArquivoDeEntidadeComHash;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.apresentacao.DocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.ARQUIVOSDISPONIVEIS)
public class ValorLogicoEnvioEmailArquivosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailArquivosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    List<DocumentoApresentacao> apresentacoesAtivas;

    public List<DocumentoApresentacao> getListaDeApresentacoes() {

        //       Query query = em.createNativeQuery("select ae.id,ae.nome,ae.arquivo, "
        //             + " he.hashCalculado as identificacaoHash"
        //            + " FROM ArquivoAnexado ae "
        //          + " left join  HashsDeArquivoDeEntidade as he "
        //         + "     on he.entidade='DocumentoApresentacao' "
        //       + "    where he.entidade ='DocumentoApresentacao' ",
        //     "QrArquivoDeEntidadeComHash");
        //List<ArquivoDeEntidadeComHash> arquivos = query.getResultList();
        if (apresentacoesAtivas == null) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            apresentacoesAtivas = new ArrayList<>();
            List<DocumentoApresentacao> todasApresentacoes = UtilSBPersistencia.getListaTodos(DocumentoApresentacao.class, em);
            todasApresentacoes.stream().filter(dc -> dc.isAtivo()).forEach(apresentacoesAtivas::add);
            UtilSBPersistencia.fecharEM(em);
        }
        return apresentacoesAtivas;
    }

    public List<ArquivoDeEntidadeComHash> getListaDeArquivosEnviados() {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        Query query = em.createNativeQuery("select ae.id,ae.nome,ae.arquivo, "
                + " he.hashCalculado as identificacaoHash"
                + " FROM ArquivoAnexado ae "
                + " inner join  HashsDeArquivoDeEntidade as he "
                + "     on he.entidade='DocumentoApresentacao' and idEntidade= ae.id "
                + "    where he.entidade = 'ArquivoDeUsuario' and ae.prospecto_id =" + getEnvioEmail().getProspecto().getId()
                + " order by he.id desc", "QrArquivoDeEntidadeComHash");
        List<ArquivoDeEntidadeComHash> arquivos = query.getResultList();
        UtilSBPersistencia.fecharEM(em);
        return arquivos;
    }

    public List<ArquivoDeEntidadeComHash> getArquivosDeUsuario() {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        Query query = em.createNativeQuery("select ae.id,ae.nome,ae.arquivo, "
                + " he.hashCalculado as identificacaoHash"
                + " FROM ArquivoAnexado ae "
                + " inner join  HashsDeArquivoDeEntidade as he "
                + "     on he.entidade='DocumentoApresentacao' and idEntidade=ae.id "
                + "    where he.entidade = 'ArquivoDeUsuario'  and ae.usuarioCriou_id = " + SBCore.getUsuarioLogado().getId() + " orde by ae.id desc",
                "QrArquivoDeEntidadeComHash");
        List<ArquivoDeEntidadeComHash> arquivos = query.getResultList();
        UtilSBPersistencia.fecharEM(em);
        return arquivos;
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!isCacheAtivado()) {
            try {

                if ((getEnvioEmail().getArquivosDisponiveis() == null || getEnvioEmail().getArquivosDisponiveis().isEmpty())) {
                    //Listando apresentações
                    List<ArquivoAnexado> arquivos = new ArrayList();
                    getListaDeApresentacoes().stream().forEach(arquivos::add);

                    //Listando Arquivos do prospecto caso seja um mail de prospecto
                    if (getEnvioEmail().getProspecto() != null) {
                        arquivos.addAll(getEnvioEmail().getProspecto().getArquivos().stream().filter(arq -> arq.getNome() != null).collect(Collectors.toList()));
                    }
                    arquivos.stream().forEach(arq -> {
                        System.out.println(arq.getNome());
                        System.out.println(arq.getTipoAnexo());
                        System.out.println(arq.getArquivo());

                    });

                    getEnvioEmail().setArquivosDisponiveis(arquivos);
                    ativarCache(5000);
                }
            } catch (Throwable t) {

            }
        }
        return getEnvioEmail().getArquivosDisponiveis();

    }

    public EnvioEmail getEnvioEmail() {
        EntityManager em = UtilSBPersistencia.getEMDoContexto();
        return UtilSBPersistencia.loadEntidade(getCampoInst().getObjetoDoAtributo(), em);
    }

}
