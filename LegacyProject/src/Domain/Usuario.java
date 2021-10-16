package Domain;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.derby.jdbc.EmbeddedDriver;

import Persistencia.BDConstantes;

public class Usuario {
	
	public String mLogin;
	public String mPassword;
	public GestorUsuario gestorUsuario;
	
	
	//Constructor para la creaci—n de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
		this.gestorUsuario=new GestorUsuario();
	}
	
	//Constructor para la creaci—n de un Usuario
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
		this.gestorUsuario=new GestorUsuario();
	}
	
	//Selecci—n de un usuario de la base de datos a partir del login y el password
	public Usuario read(String login, String password) throws Exception{
		return gestorUsuario.read(login, password);
	}
	
	//Inserci—n de un nuevo usuario en la base de datos
	public int insert(String login, String password) throws Exception{
		return gestorUsuario.insert(login, password);
	}
	
	public int update(String login, String password) throws Exception{
		return gestorUsuario.update(login, password);
	}
	
}
