package senac.tsi.minha_saude.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
 public UsuarioNotFoundException(Long id){
     super("Não foi possivel encontrar usuario " + id);
 }
}