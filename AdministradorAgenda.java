/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.programacion.retos.reto2.Negocio;

import co.edu.unbosque.programacion.retos.reto2.dto.Estudiante;
import java.util.*;

/**
 *
 * @author User
 */
public class AdministradorAgenda {
    
    List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

    /**
    *
    * Este metodo crea un nuevo estudiante
    */
    public void crearEstudiante (Estudiante miEstudiante){
	    this.listaEstudiantes.add(miEstudiante);
	     
    }
    
    /**
    *
    * Este metodo lee en pantalla todos los estudiantes
    */
    public List <Estudiante> mostrarEstudiantes (){
	    return this.listaEstudiantes;
	    
    }
    /**
    *
    * Este metodo eliminar los estudiantes
    */
    
    public void eliminarEstudiante (String correoi){
    	Estudiante e = buscarEstudiante(correoi);
        listaEstudiantes.remove(e);
        
    }
    
    public Estudiante buscarEstudiante (String correoInstitucional){
        Estudiante encontrado = null;
	     for (Estudiante e: listaEstudiantes){
		     if (e.getCorreoInstitucional().equals(correoInstitucional)){
		    	 encontrado=e;
		    	 break;
		     }
	     }
	     return encontrado;
    }

    /**
    *
    * Este metodo modifica en pantalla todos los estudiantes
    */
    
    public Estudiante modificarEstudiante (String correoi, String correop, Long celnum, Long celf, String programa){
	    Estudiante est = buscarEstudiante(correoi);	
	    est.setCorreoPersonal(correop);
	    est.setNumeroCelular(celnum);
	    est.setNumeroFijo(celf);
	    est.setProgramaAcademico(programa);
	    return est;
    }
    

    
  
    
    
    
}
