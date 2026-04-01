package senac.tsi.minha_saude.exceptions;

public class MedicoNotFoundException extends RuntimeException{
    public MedicoNotFoundException(Long id){
        super("Não foi possivel encontrar o medico " + id);
    }
}
