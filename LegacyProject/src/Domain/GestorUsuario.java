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

public class GestorUsuario {
	
	public boolean autenticar(String login, String password) throws Exception{
		boolean autenticado = false;
		
		if(read(login, password) != null)
			autenticado = true;
		return autenticado;
	}
	
	public static boolean nuevoUsuario(String login, String password) throws Exception{
		boolean insertado = false;
		
		Usuario u = new Usuario(login, password);
		if(u.insert(login,password) ==1)
			insertado = true;
		return insertado;		
	}
	public Usuario read(String login, String password) throws Exception{
		Usuario u = null;
		Vector<Object> aux = null;
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
		String SQL_Consulta = "SELECT login, pass FROM Usuario WHERE login = '"+login+"' AND pass = '"+password+"'";
		Vector<Object> vectoradevolver=new Vector<Object>();
		Statement stmt = mBD.createStatement();
		ResultSet res=stmt.executeQuery(SQL_Consulta);
		while (res.next()) {
			aux=new Vector<Object>();
			aux.add(res.getObject(1));
			aux.add(res.getObject(2));
			vectoradevolver.add(aux);
		}
    	stmt.close();
    	mBD.close();
		aux = new Vector<Object>();
		if (vectoradevolver.size() == 1){
			aux = (Vector<Object>) vectoradevolver.elementAt(0);
			u = new Usuario((String) aux.elementAt(0), (String) aux.elementAt(1));
		}
		
		return u;
	}
	public int insert(String login, String password) throws Exception{
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
		PreparedStatement stmt = mBD.prepareStatement("INSERT INTO Usuario VALUES('"+login+"','"+password+"')");
    	int res=stmt.executeUpdate();
    	stmt.close();
    	mBD.close();
		return res;
	}
	
	public int update(String login, String password) throws Exception{
	Driver derbyEmbeddedDriver = new EmbeddedDriver();
	DriverManager.registerDriver(derbyEmbeddedDriver);
	Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+
			";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
	PreparedStatement stmt = mBD.prepareStatement("DELETE FROM Usuario WHERE"
			+ " LOGIN='"+login+"' AND PASS='"+password+"'");
	int res=stmt.executeUpdate();
	stmt.close();
	mBD.close();
	return res;
	}
}
