/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.programacion.retos.reto2.dto;


/**
 *
 * @author User
 */
public class Estudiante {
    
    private String nombres;
    private String apellidos;
    private String fechaDeNacimiento;
    private String correoPersonal;
    private Long numeroCelular;
    private Long numeroFijo;
    private String correoInstitucional;
    private String programaAcademico;
    
    public Estudiante () {
    }
    
    public Estudiante(String correoInstitucional, String programaAcademico) {
        this.correoInstitucional = correoInstitucional;
        this.programaAcademico = programaAcademico;
    }

    
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(String programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public Long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(Long numeroDeCelular) {
        this.numeroCelular = numeroDeCelular;
    }

    public Long getNumeroFijo() {
        return numeroFijo;
    }

    public void setNumeroFijo(Long numeroFijo) {
        this.numeroFijo = numeroFijo;
    }

	@Override
	public String toString() {
		return "Nombres: " + nombres + "\n" 
				+ "Apellidos: " + apellidos + "\n" 
				+"Fecha nacimiento: "+ fechaDeNacimiento +  "\n"
				+ "Correo institucional: " + correoInstitucional +  "\n"
				+"Correo personal: " + correoPersonal +  "\n"
				+ "Número de teléfono celular: " + numeroCelular + "\n"
				+ "Número de teléfono fijo: " + numeroFijo + "\n"
				+ "Programa académico: " + programaAcademico + "\n";
	}
	public String toStringNombreYApellido() {
		return   apellidos + nombres + "\n"; 
    
	}
	public String toStringCell() {
		return   apellidos + nombres + programaAcademico + "\n"; 
    
	}
}
