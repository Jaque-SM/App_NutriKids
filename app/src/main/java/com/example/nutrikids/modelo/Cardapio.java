package com.example.nutrikids.modelo;

public class Cardapio {

    private String email;
    private String cafe_manha;
    private String lanche_manha;
    private String Almoco;
    private String lanche_tarde;
    private String jantar;


    public Cardapio(String email_paciente, String cafe_manha, String lanche_manha, String almoco,
                    String lanche_tarde, String jantar){

        this.email=email_paciente;
        this.cafe_manha=cafe_manha;
        this.lanche_manha=lanche_manha;
        this.Almoco=almoco;
        this.lanche_tarde=lanche_tarde;
        this.jantar=jantar;

    }

    public Cardapio(){}

    public String getEmail_paciente() {
        return email;
    }

    public void setEmail_paciente(String email_paciente) {
        this.email = email_paciente;
    }

    public String getCafe_manha() {
        return cafe_manha;
    }

    public void setCafe_manha(String cafe_manha) {
        this.cafe_manha = cafe_manha;
    }

    public void setLanche_manha(String lanche_manha) {
        this.lanche_manha = lanche_manha;
    }

    public String getLanche_manha() {
        return lanche_manha;
    }

    public void setAlmoco(String almoco) {
        this.Almoco = almoco;
    }

    public String getAlmoco() {
        return Almoco;
    }

    public void setLanche_tarde(String lanche_tarde) {
        this.lanche_tarde = lanche_tarde;
    }

    public String getLanche_tarde() {
        return lanche_tarde;
    }

    public String getJantar() {
        return jantar;
    }

    public void setJantar(String jantar) {
        this.jantar = jantar;
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "email='" + email + '\'' +
                ", cafe_manha='" + cafe_manha + '\'' +
                ", lanche_manha='" + lanche_manha + '\'' +
                ", almoco='" + Almoco + '\'' +
                ", lanche_tarde='" + lanche_tarde + '\'' +
                ", jantar='" + jantar + '\'' +
                '}';
    }
}
