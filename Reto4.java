/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.programacion.retos.reto2.vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//import com.mysql.cj.xdevapi.PreparableStatement;

import co.edu.unbosque.programacion.retos.reto2.Negocio.AdministradorAgendaDAO;
import co.edu.unbosque.programacion.retos.reto2.dto.Estudiante;

/**
 *
 * @author User
 */
public class Reto4 {

    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws SQLException 
     */
	
    public static void main(String[] args) throws IOException, SQLException {
    	
    	// listado de variables
        int opcion = 0;
        AdministradorAgendaDAO admin = new AdministradorAgendaDAO();
        Scanner sc = new Scanner(System.in);
//        AdministradorAgendaDAO adminDAO = new AdministradorAgendaDAO();
//        FileOutputStream fos = new FileOutputStream("datos_estudiantes.dat");
//        DataOutputStream salida = new DataOutputStream(fos);
//        adminDAO.setFos(fos);
//        adminDAO.setSalida(salida);
//        importarCSV(admin);
       
    	while (opcion != 6) {
    		
    		
    		System.out.println("INSTITUTO LA FLORESTA");
            System.out.println("Seleccione tarea a realizar:");
            System.out.println("1. Ingresar estudiante");
            System.out.println("2. Consultas");
            System.out.println("3. Modificar estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Ver directorio de estudiantes");
            System.out.println("6. Salir");
            System.out.println("Opción:");
            try {
				opcion = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("El dato ingresado no es un número");
				System.out.println();
				System.out.println("Error: Opción no válida");
				opcion = 0;
			}
            switch (opcion)
            {
				case 1:
					ingresarEstudiante(admin, sc);
					break;
				case 2:
					consultas(admin, sc);
					break;
				case 3:
					modificarEstudiante(admin, sc);
					break;
				case 4:
					eliminarEstudiante(admin, sc);
					break;
				case 5:
					directorioEstudiantes(admin);
					break;
				case 6:
//					fos.close();
					System.out.println("Hasta pronto");
					break;
					
            }
    	}
    }
    
    public static void ingresarEstudiante(AdministradorAgendaDAO admin, Scanner sc) throws IOException {
    	System.out.println("Ingresar estudiante");
		System.out.println("Ingresar nombres:");
		String nombre = sc.nextLine();
		System.out.println("Ingresar apellidos:");
		String apellido = sc.nextLine();
		System.out.println("Ingresar fecha de nacimiento (YYYY-MM-DD):");
		String fechaDeNacimiento = sc.nextLine();
		System.out.println("Ingresar correo institucional:");
		String correoInstitucional = sc.nextLine();
		System.out.println("Ingresar correo personal:");
		String correoPersonal = sc.nextLine();
		System.out.println("Ingresar número de celular:");
		Long numeroDeCelular = 0L;
		try {
			numeroDeCelular = sc.nextLong();
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("El dato ingresado no es un número");
		}
		System.out.println("Ingresar número fijo:");
		Long numeroFijo = 0L;
		try {
			numeroFijo = sc.nextLong();
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("El dato ingresado no es un número");
		}
		System.out.println("Ingresar programa:");
		String programa = sc.nextLine();
		Estudiante nuevoEstudiante = new Estudiante ();
		nuevoEstudiante.setNombres(nombre);
		nuevoEstudiante.setApellidos(apellido);
		nuevoEstudiante.setFechaDeNacimiento(fechaDeNacimiento);
		nuevoEstudiante.setCorreoInstitucional(correoInstitucional);
		nuevoEstudiante.setCorreoPersonal(correoPersonal);
		nuevoEstudiante.setNumeroCelular(numeroDeCelular);
		nuevoEstudiante.setNumeroFijo(numeroFijo);
		nuevoEstudiante.setProgramaAcademico(programa);
//		admin.crearEstudiante(nuevoEstudiante);
		admin.crearEstudianteDB(nuevoEstudiante);
		System.out.println("Se agregó el estudiante");
		System.out.println();
    }
    
    public static void consultas(AdministradorAgendaDAO admin, Scanner sc) throws IOException {
    	int opcion = 0;
 
    	System.out.println("Consultas");
    	System.out.println("Seleccione consulta a realizar:");
    	System.out.println("1. Buscar estudiante por correo electrónico");
        System.out.println("2. Buscar estudiante por apellidos");
        System.out.println("3. Bucar por programa");
        System.out.println("4. Buscar cantidad de estudiantes por programa");
        System.out.println("5. Buscar por fecha de nacimiento");
        System.out.println("6. Buscar por número de celular");
        System.out.println("Opción:");
        try {
			opcion = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("El dato ingresado no es un número");
			System.out.println();
			System.out.println("Error: Opción no válida");
			opcion = 0;
		}
        switch (opcion)
        {
			case 1:
				consultaCorreoIn(admin, sc);
				break;
			case 2:
				consultaApellido(admin, sc);
				break;
			case 3:
				consultaPrograma(admin, sc);
				break;
			case 4:
				cantidadEstPrograma(admin, sc);
				break;
			case 5:
				consultaFechaNacimiento(admin, sc);
				break;
			case 6:
//				fos.close();
				consultaCell (admin, sc);
				break;
				
        }
    	
    	
    	System.out.println("Buscar estudiante");
		System.out.println("Ingresar correo institucional:");
		String correoInstitucional1 = sc.nextLine();
		Estudiante estudianteBuscado = admin.buscarEstudianteDB(correoInstitucional1);
		if(Objects.isNull(estudianteBuscado)) {
			System.out.println("El estudiante no se encuentra registrado en el instituto");
		} else  {
			System.out.println("Información del estudiante");
			System.out.println(estudianteBuscado.toString());
		}
		System.out.println();
		
    }
    
    public static void modificarEstudiante(AdministradorAgendaDAO admin, Scanner sc) throws IOException {
    	System.out.println("Modificar estudiante");
		System.out.println("Ingresar correo institucional:");
		String correoInstitucional2 = sc.nextLine();
		Estudiante estudianteBuscado1 = admin.buscarEstudianteDB(correoInstitucional2);
		if(Objects.isNull(estudianteBuscado1)) {
			System.out.println("El estudiante no se encuentra registrado en el instituto");
		} else  {
			System.out.println("Ingresar correo personal:");
			String correoPersonal1 = sc.nextLine();
			System.out.println("Ingresar número de celular:");
			Long numeroDeCelular1 = 0L;
			try {
				numeroDeCelular1 = sc.nextLong();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("El dato ingresado no es un número");
			}
			System.out.println("Ingresar número fijo:");
			Long numeroFijo1 = 0L;
			try {
				numeroFijo1 = sc.nextLong();
				sc.nextLine();
			} catch(Exception e) {
				System.out.println("El dato ingresado no es un número");
			}
			System.out.println("Ingresar programa:");
			String programa1 = sc.nextLine();
			admin.modificarEstudianteDB (correoInstitucional2, correoPersonal1, numeroDeCelular1, numeroFijo1, programa1);
			System.out.println("Se modificó el estudiante");
		}
		System.out.println();
		
    }
    
    public static void eliminarEstudiante(AdministradorAgendaDAO admin, Scanner sc) throws IOException {
    	System.out.println("Eliminar estudiante");
		System.out.println("Ingresar correo institucional:");
		String correoInstitucional3 = sc.nextLine();
		Estudiante esdudianteBuscado3 = admin.buscarEstudianteDB(correoInstitucional3);
		if(Objects.isNull(esdudianteBuscado3)) {
			System.out.println("El estudiante no se encuentra registrado en el instituto");
		} else  {
			admin.eliminarEstudianteDB (correoInstitucional3);
			System.out.println("Se eliminó el estudiante");
		}
		
		System.out.println();
		
    }
   
    
   public static void directorioEstudiantes(AdministradorAgendaDAO admin ) {
	    System.out.println("El directorio de los estudiantes");
		List <Estudiante> directorio = admin.mostrarEstudiantesDB();
		for (Estudiante x: directorio) {
			System.out.println();
			System.out.println(x.toString());
		}
   }
   public static void consultaCorreoIn (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("Ingresar correo institucional:");
   		String correoIn = sc.nextLine();
   		Estudiante buscarEst = admin.buscarEstudianteDB(correoIn);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("El estudiante no se encuentra registrado en el instituto");
   		} else {
   			System.out.println("Información del estudiante");
			System.out.println(buscarEst.toString());
   		
   		}	
   	}
   public static void consultaApellido (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("Ingresar apellidos:");
   		String consultaApellido = sc.nextLine();
   		List <Estudiante> buscarEst = admin.buscarEstudiantePorApellidoDB(consultaApellido);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("No hay resultados para esa consulta");
   		} else {
   			for (Estudiante estudianteNuevo: buscarEst ) {
   			System.out.println(estudianteNuevo.toString());
   			}
   		}
   }
   public static void consultaPrograma (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("3. Bucar por programa");
	   System.out.println("Ingresar programa:");
   		String consultaPrograma = sc.nextLine();
   		List <Estudiante> buscarEst = admin.buscarEstudiantePorProgramaDB(consultaPrograma);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("No hay resultados para esa consulta");
   		} else {
   			for (Estudiante estudianteNuevo: buscarEst ) {
   	   			System.out.println(estudianteNuevo.toStringNombreYApellido());
   	   			}
   		}
   }
   public static void cantidadEstPrograma (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("4. Buscar cantidad de estudiantes por programa");
	   System.out.println("Ingresar programa:");
   		String consultaCantEstPrograma = sc.nextLine();
   		List <Estudiante> buscarEst = admin.buscarEstudiantePorProgramaDB(consultaCantEstPrograma);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("No hay resultados para esa consulta");
   		} else {
			System.out.println("Cantidad estudiantes" + consultaCantEstPrograma + ": "+ buscarEst.size());
   		}
   } 
   
   public static void consultaFechaNacimiento (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("5. Buscar por fecha de nacimiento");
	   System.out.println("Ingresar fecha de nacimiento:");
   		String consultaFechaNacimiento = sc.nextLine();
   		List <Estudiante> buscarEst = admin.buscarEstudiantePorNaciDB(consultaFechaNacimiento);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("No hay resultados para esa consulta");
   		} else {
   			for (Estudiante estudianteNuevo: buscarEst ) {
   			System.out.println(estudianteNuevo.toString());
   			}
   		}
   
   
}
   public static void consultaCell (AdministradorAgendaDAO admin, Scanner sc) {
	   System.out.println("6. Buscar por número de celular");
	   System.out.println("Ingresar número de celular:");
   		Long consultaCell = sc.nextLong();
   		sc.nextLine();
   		Estudiante buscarEst = admin.buscarEstudiantePorCellDB(consultaCell);
   		if (Objects.isNull(buscarEst)) {
   			System.out.println("No hay resultados para esa consulta");
   		} else {
   			System.out.println (buscarEst.toStringCell());
   		}
}
   public static void importarCSV(AdministradorAgendaDAO admin) throws IOException, SQLException{
	   

	        String fileToParse = "C:/Users/Paula/Documents/MINTIC/CICLO 2/PROGRAMACION QUICES/RETO 4/RETO/Reto2/infoestudiantes.csv";
//	   		String fileToParse = "infoestudiantes.csv";
	   		BufferedReader fileReader = null;

	        try{
	            String line = "";
	            fileReader = new BufferedReader(new FileReader(fileToParse));
	            while ((line = fileReader.readLine()) != null)
	            {
	                String[] token= line.split(",");
					Estudiante est =new Estudiante ();
					est.setNombres(token[0].trim().replace("\"", ""));
					est.setApellidos(token[1].trim().replace("\"", ""));
					est.setFechaDeNacimiento(token[2].trim().replace("\"", ""));
					est.setCorreoInstitucional(token[3].trim().replace("\"", ""));
					est.setNumeroCelular(Long.parseLong(token[4].trim().replace("\"", "")));
					est.setNumeroFijo(Long.parseLong(token[5].trim().replace("\"", "")));
					est.setCorreoPersonal(token[6].trim().replace("\"", ""));
					est.setProgramaAcademico(token[7].trim().replace("\"", ""));
					admin.crearEstudianteDB(est);
					
	            }
	        }
	        finally
	        {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
   	}
	 
}


   
   
   
   
   

