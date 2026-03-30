package senac.tsi.minha_saude.entities.Usuarios;

public enum Genero {
    MASCULINO(1),FEMININO(2),OUTRO(3);

    private final int generoNum;

    Genero(int generoNum){
        this.generoNum = generoNum;
    }

    public int getGeneroNum() {
        return this.generoNum;
    }
}
