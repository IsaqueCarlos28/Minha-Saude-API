package senac.tsi.minha_saude.dominios.medicos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import senac.tsi.minha_saude.dominios.compartilhado.UnidadeFederativa;

@Embeddable
public class CRM {

    @NotBlank(message = "Os digitos do Crm não podem estar em branco")
    @Column(name = "crm_digits", nullable = false ,length = 6)
    private String digits;

    @NotNull(message = "UF é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "crm_uf",nullable = false)
    private UnidadeFederativa uf;

    public CRM(){}

    public CRM(String num, UnidadeFederativa uf){
        setDigits(num);
        setUf(uf);
    }

    public String getDigits() {
        return digits;
    }
    public void setDigits(String digits) {
        digits =  (digits != null) ? digits.replaceAll("\\D", "") : null;

        if (digits == null || digits.length() != 6) {
            throw new IllegalArgumentException("CRM inválido");
        }

        this.digits = digits;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }
    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    @Override
    public String toString(){
        return digits + " / " + uf;
    }
}
