package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.pabx;

public class ResultadoLigacao {
    private final TipoChamada tipo;
    private final String numeroExterno;
    private final String ramal;

    public ResultadoLigacao(TipoChamada tipo, String numeroExterno, String ramal) {
        this.tipo = tipo;
        this.numeroExterno = numeroExterno;
        this.ramal = ramal;
    }

    public TipoChamada getTipo() {
        return tipo;
    }

    public String getNumeroExterno() {
        return numeroExterno;
    }

    public String getRamal() {
        return ramal;
    }
}
