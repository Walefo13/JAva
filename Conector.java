	package co.edu.unbosque.programacion.retos.reto2.Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
//	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:sqlite:C:/sqlite/bd_estudiantes.db";
//	private static final String USUARIO = "root";
//	private static final String CONTRASEÑA = "alejandro";
//	
	
	public Connection conectar () {
		Connection conexion =null;
		
		try {
//			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL);
		System.out.println("conexion establecida");
		}catch (SQLException e) {
			System.out.println("error en la conexion");
		}
	
		return conexion;
	}
		
}
