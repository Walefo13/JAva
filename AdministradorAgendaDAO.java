/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.programacion.retos.reto2.Negocio;

import co.edu.unbosque.programacion.retos.reto2.dto.Estudiante;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author User
 */
public class AdministradorAgendaDAO {
    
	FileOutputStream fos;
    DataOutputStream salida;
    
    
	Conector conector;
	Connection nuevaConexion;
	
	public AdministradorAgendaDAO() throws SQLException{
		this.conector = new Conector();
		this.nuevaConexion = conector.conectar();
		this.nuevaConexion.setAutoCommit(true);

	}	
	public void crearEstudianteDB (Estudiante miEstudiante){
		String sql = "INSERT INTO estudiantes ( nombres, apellidos, fechaDeNacimiento, correoPersonal, numeroCelular, numeroFijo,\r\n"
	  				+ "    correoInstitucional, programaAcademico)\r\n"
	  				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
	  	try {
			PreparedStatement ps = nuevaConexion.prepareStatement(sql);
			ps.setString(1, miEstudiante.getNombres());
			ps.setString(2, miEstudiante.getApellidos());
			ps.setString(3, miEstudiante.getFechaDeNacimiento());
			ps.setString(4, miEstudiante.getCorreoPersonal());
			ps.setLong(5, miEstudiante.getNumeroCelular());
			ps.setLong(6, miEstudiante.getNumeroFijo());
			ps.setString(7, miEstudiante.getCorreoInstitucional());
			ps.setString(8, miEstudiante.getProgramaAcademico());
			ps.executeUpdate();
			ps.close();
				
				
	  	} catch (SQLException e) {
			e.printStackTrace();
		}
		  
    }    
    public List <Estudiante> mostrarEstudiantesDB (){   
	    String sql = "SELECT * FROM estudiantes;";
			List<Estudiante> list = new ArrayList<>();
			ResultSet rs = null;
		try {
			PreparedStatement st = nuevaConexion.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Estudiante aUser =new Estudiante ();
				aUser.setNombres(rs.getString("nombres"));
				aUser.setApellidos(rs.getString("apellidos"));
				aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
				aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
				aUser.setNumeroCelular(rs.getLong("numeroCelular"));
				aUser.setNumeroFijo(rs.getLong("numeroFijo"));
				aUser.setCorreoPersonal(rs.getString("correoPersonal"));
				aUser.setProgramaAcademico(rs.getString("programaAcademico"));		
				list.add(aUser);
			}
			st.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
    }

    public void eliminarEstudianteDB (String correoi){
    	String sql = "DELETE from estudiantes where correoInstitucional=?";
    	try {
    		PreparedStatement ps = nuevaConexion.prepareStatement(sql);
    		ps.setString(1, correoi);
    		ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

    }

    public Estudiante buscarEstudianteDB (String correoi){
    	String sql = "SELECT * FROM estudiantes where correoInstitucional=?";
		ResultSet rs = null;
		Estudiante aUser = null;
		try {
			PreparedStatement st = nuevaConexion.prepareStatement(sql);
			st.setString(1, correoi);
			rs = st.executeQuery();
			while (rs.next()) {
				aUser = new Estudiante ();
				aUser.setNombres(rs.getString("nombres"));
				aUser.setApellidos(rs.getString("apellidos"));
				aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
				aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
				aUser.setNumeroCelular(rs.getLong("numeroCelular"));
				aUser.setNumeroFijo(rs.getLong("numeroFijo"));
			aUser.setCorreoPersonal(rs.getString("correoPersonal"));
				aUser.setProgramaAcademico(rs.getString("programaAcademico"));		
			}
			st.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return aUser;
    }
   
    public void modificarEstudianteDB (String correoi, String correop, Long celnum, Long celf, String programa){
    	String sql = "UPDATE estudiantes set correoPersonal = ?, numeroCelular =?, numeroFijo =?,programaAcademico = ? WHERE correoInstitucional = ? ";
    	try {
    		PreparedStatement ps = nuevaConexion.prepareStatement(sql);
    		ps.setString(1, correop);
    		ps.setLong(2, celnum);
    		ps.setLong(3, celf);
    		ps.setString(4, programa);
    		ps.setString(5, correoi);
    		ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

    }
    
    
    /**
    *
    * Este metodo crea un nuevo estudiante
     * @throws IOException 
    */
    public void crearEstudiante (Estudiante miEstudiante) throws IOException{
    	salida.writeBytes(miEstudiante.toString());
    }
    
    /**
    *
    * Este metodo lee en pantalla todos los estudiantes
     * @throws IOException 
    */
    
    public void eliminarEstudiante (AdministradorAgenda admin) throws IOException{
        salida.writeBytes("");
        for(Estudiante e : admin.mostrarEstudiantes()) {
        	salida.writeBytes(e.toString());
        }
    }
    
    /**
    *
    * Este metodo modifica en pantalla todos los estudiantes
     * @throws IOException 
    */
    
    public void modificarEstudiante (AdministradorAgenda admin) throws IOException{
    	salida.writeBytes("");
        for(Estudiante e : admin.mostrarEstudiantes()) {
        	salida.writeBytes(e.toString());
        }
    }

	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public DataOutputStream getSalida() {
		return salida;
	}

	public void setSalida(DataOutputStream salida) {
		this.salida = salida;
	}

	 public List <Estudiante> buscarEstudiantePorApellidoDB (String consultaApellido){
	    	String sql = "SELECT * FROM estudiantes where apellidos=?";
			ResultSet rs = null;
			List <Estudiante> listaEst = new ArrayList<>();
			try {
				PreparedStatement st = nuevaConexion.prepareStatement(sql);
				st.setString(1, consultaApellido);
				rs = st.executeQuery();
				while (rs.next()) {
					Estudiante aUser =new Estudiante ();
					aUser.setNombres(rs.getString("nombres"));
					aUser.setApellidos(rs.getString("apellidos"));
					aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
					aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
					aUser.setNumeroCelular(rs.getLong("numeroCelular"));
					aUser.setNumeroFijo(rs.getLong("numeroFijo"));
				aUser.setCorreoPersonal(rs.getString("correoPersonal"));
					aUser.setProgramaAcademico(rs.getString("programaAcademico"));		
					listaEst.add(aUser);
				}
				st.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return listaEst;
	    }
	 public List <Estudiante> buscarEstudiantePorProgramaDB (String consultaPrograma){
	    	String sql = "SELECT * FROM estudiantes where programaAcademico=?";
			ResultSet rs = null;
			List <Estudiante> listaEst = new ArrayList<>();
			try {
				PreparedStatement st = nuevaConexion.prepareStatement(sql);
				st.setString(1, consultaPrograma);
				rs = st.executeQuery();
				while (rs.next()) {
					Estudiante aUser =new Estudiante ();
					aUser.setNombres(rs.getString("nombres"));
					aUser.setApellidos(rs.getString("apellidos"));
					aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
					aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
					aUser.setNumeroCelular(rs.getLong("numeroCelular"));
					aUser.setNumeroFijo(rs.getLong("numeroFijo"));
				aUser.setCorreoPersonal(rs.getString("correoPersonal"));
					aUser.setProgramaAcademico(rs.getString("programaAcademico"));	
					listaEst.add(aUser);
				}
				st.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return listaEst;
	    }
	 public List <Estudiante> buscarEstudiantePorNaciDB (String consultaNacimiento){
	    	String sql = "SELECT * FROM estudiantes where fechaDeNacimiento=?";
			ResultSet rs = null;
			List <Estudiante> listaEst = new ArrayList<>();
			try {
				PreparedStatement st = nuevaConexion.prepareStatement(sql);
				st.setString(1, consultaNacimiento);
				rs = st.executeQuery();
				while (rs.next()) {
					Estudiante aUser =new Estudiante ();
					aUser.setNombres(rs.getString("nombres"));
					aUser.setApellidos(rs.getString("apellidos"));
					aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
					aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
					aUser.setNumeroCelular(rs.getLong("numeroCelular"));
					aUser.setNumeroFijo(rs.getLong("numeroFijo"));
				aUser.setCorreoPersonal(rs.getString("correoPersonal"));
					aUser.setProgramaAcademico(rs.getString("programaAcademico"));		
					listaEst.add(aUser);
				}
				st.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return listaEst;
	    }
	 public Estudiante buscarEstudiantePorCellDB (Long celular){
	    	String sql = "SELECT * FROM estudiantes where numeroCelular=?";
			ResultSet rs = null;
			Estudiante aUser = null;
			try {
				PreparedStatement st = nuevaConexion.prepareStatement(sql);
				st.setLong(1, celular);
				rs = st.executeQuery();
				while (rs.next()) {
					aUser = new Estudiante ();
					aUser.setNombres(rs.getString("nombres"));
					aUser.setApellidos(rs.getString("apellidos"));
					aUser.setFechaDeNacimiento(rs.getString("fechaDeNacimiento"));
					aUser.setCorreoInstitucional(rs.getString("correoInstitucional"));
					aUser.setNumeroCelular(rs.getLong("numeroCelular"));
					aUser.setNumeroFijo(rs.getLong("numeroFijo"));
				aUser.setCorreoPersonal(rs.getString("correoPersonal"));
					aUser.setProgramaAcademico(rs.getString("programaAcademico"));		
				}
				st.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return aUser;
	    }
}
