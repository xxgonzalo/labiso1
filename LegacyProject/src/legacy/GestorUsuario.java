package legacy;

public class GestorUsuario {
	
	public static boolean autenticar(String login, String password) throws Exception{
		boolean autenticado = false;
		
		if(Usuario.read(login, password) != null)
			autenticado = true;
		return autenticado;
	}
	
	public static boolean nuevoUsuario(String login, String password) throws Exception{
		boolean insertado = false;
		
		Usuario u = new Usuario(login, password);
		if(u.insert() ==1)
			insertado = true;
		return insertado;		
	}

}
