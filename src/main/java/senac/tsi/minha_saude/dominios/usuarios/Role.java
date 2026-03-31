package senac.tsi.minha_saude.dominios.usuarios;

public enum Role {
    PACIENTE(1),MEDICO(2);

    private int roleNum;

    Role(int roleNum){
        this.roleNum = roleNum;
    }

    public int getRoleNum() {
        return this.roleNum;
    }
}
