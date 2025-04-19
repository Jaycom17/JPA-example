package edu.co.unicauca.tallerJPA;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Docente;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Estado;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Evaluacion;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.FormatoA;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.FormatoppA;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.FormatotiA;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Historico;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Observacion;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.entities.Rol;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.DocenteRepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.EvaluacionRepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.FormatoARepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.FormatoPPARepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.FormatoTIARepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.HistoricoRepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.ObservacionRepository;
import edu.co.unicauca.tallerJPA.capaAccesoADatos.repositories.RolReposotory;

@SpringBootApplication
@Transactional
public class TallerJpaApplication implements CommandLineRunner {

	@Autowired
	private DocenteRepository docenteRepository;

	@Autowired
	private FormatoPPARepository formatoPPARepository;

	@Autowired
	private FormatoTIARepository formatoTIARepository;

	@Autowired
	private EvaluacionRepository evaluacionRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

	@Autowired
	private ObservacionRepository observacionRepository;

	@Autowired
	private RolReposotory rolReposotory;

	@Autowired
	private FormatoARepository formatoARepository;

	public static void main(String[] args) {
		SpringApplication.run(TallerJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Taller JPA Application started successfully!");

		crearFormatoA();

		crearDocentes();

		crearObservacion();

		listarObservaciones();

		crearRol();

		crearHistoricos();

		listarMiembrosComite();

		listarFormatosPorDocente();
	}

	@Transactional
	public void crearFormatoA() {
		
		// Crear un nevo docente

		Docente docente;

		if (!docenteRepository.existsById(1)) {
			docente = new Docente();
			docente.setNombresDocente("Armando");
			docente.setApellidosDocente("Armas");
			docente.setNombreGrupo("Grupo 1");
			docente.setCorreo("armando@gmail.com");
		} else {
			docente = docenteRepository.findById(1).orElseThrow();
		}
		

		//crear un nuevo estado
		Estado estado = new Estado();
		estado.setEstadoActual("EN_FORMULACION");
		estado.setFechaRegistroEstado(new Date());

		FormatoA formato = new FormatoppA("La cura del cancer", "Curar el cancer", "Curarlo bien", "pedro pica piedra", "Rolando Casas", "ruta/carta");

		//FormatoA formato = new FormatotiA("La cura del cancer", "Curar el cancer", "Curarlo bien", "pedro", "paco");

		formato.setObjDocente(docente);
		formato.setObjEstado(estado);

		estado.setObjFormatoA(formato);

		docente.getFormatos().add(formato);

		if (formato instanceof FormatoppA) {
			FormatoppA formatoPPA = (FormatoppA) formato;

			formatoPPARepository.save(formatoPPA);
		} else if (formato instanceof FormatotiA) {
			FormatotiA formatoTIA = (FormatotiA) formato;

			formatoTIARepository.save(formatoTIA);
		}
	}

	@Transactional
	public void crearDocentes(){
		Docente docente1 = new Docente();
		docente1.setNombresDocente("Pedro");
		docente1.setApellidosDocente("Pica Piedra");
		docente1.setNombreGrupo("Grupo 1");
		docente1.setCorreo("pedro@gmail.com");

		Docente docente2 = new Docente();
		docente2.setNombresDocente("Paco");
		docente2.setApellidosDocente("Pica Piedra");
		docente2.setNombreGrupo("Grupo 1");
		docente2.setCorreo("paco@gmail.com");

		List<Docente> listaDocentes = List.of(docente1, docente2);

		this.docenteRepository.saveAll(listaDocentes);
	}

	@Transactional
	public void crearObservacion() {
		List<Integer> listaIdDocentes = List.of(2, 3);

		List<Docente> listaDocentes = new ArrayList<Docente>();

		listaIdDocentes.forEach(id -> {
			if(!docenteRepository.existsById(id)) {
				System.out.println("No existe el docente con id: " + id);
				return;
			}
			Docente docente = docenteRepository.getReferenceById(id);
			listaDocentes.add(docente);
		});

		// Verificar formato a

		int idFormatoA = 1;

		List<Evaluacion> listaEvaluaciones = this.evaluacionRepository.findAll();
		Evaluacion evaluacion = null;

		if (listaEvaluaciones.isEmpty()) {
			evaluacion = new Evaluacion();
			evaluacion.setConcepto("concepto aun por establecer");
			evaluacion.setFechaRegistroConcepto(new Date());
		}else{

			//ordernar la lista por fecha de registro
			listaEvaluaciones.sort((e1, e2) -> e1.getFechaRegistroConcepto().compareTo(e2.getFechaRegistroConcepto()));

			// Verificar si existe una evaluacion para el formato A en la lista
			for (Evaluacion eval : listaEvaluaciones) {
				if (eval.getObjFormatoA().getIdFormatoA() == idFormatoA) {
					evaluacion = eval;
					break;
				}
			}

			if (evaluacion == null) {
				evaluacion = new Evaluacion();
				evaluacion.setConcepto("concepto aun por establecer");
				evaluacion.setFechaRegistroConcepto(new Date());
			}
		}

		if (evaluacion.getIdEvaluacion() == null) {
			FormatoA formatoA = formatoARepository.findById(idFormatoA).orElseThrow();
			evaluacion.setObjFormatoA(formatoA);
			formatoA.getEvaluaciones().add(evaluacion);
			this.evaluacionRepository.save(evaluacion);
		}

		Observacion observacion = new Observacion();
		observacion.setObservacion("Observacion mela");
		observacion.setFechaRegistroObservacion(new Date());
		observacion.setObjEvaluacion(evaluacion);
		observacion.setDocentes(listaDocentes);

		evaluacion.getObservaciones().add(observacion);

		this.observacionRepository.save(observacion);
		
	}

	@Transactional(readOnly = true)
	public void listarObservaciones(){
		List<Observacion> listaObservaciones = this.observacionRepository.findAll();

		if (listaObservaciones.isEmpty()) {
			System.out.println("No hay observaciones");
			return;
		}

		listaObservaciones.forEach(observacion -> {
			System.out.println("ID: " + observacion.getIdObservacion());
			System.out.println("Observacion: " + observacion.getObservacion());
			System.out.println("Fecha de registro: " + observacion.getFechaRegistroObservacion());
			System.out.println("Docentes: ");
			observacion.getDocentes().forEach(docente -> {
				System.out.println("\tID: " + docente.getIdDocente());
				System.out.println("\tNombre: " + docente.getNombresDocente() + " " + docente.getApellidosDocente());
			});
			System.out.println("Evaluacion: ");
			System.out.println("\tID: " + observacion.getObjEvaluacion().getIdEvaluacion());
			System.out.println("\tConcepto: " + observacion.getObjEvaluacion().getConcepto());
			System.out.println("\tFecha de registro: " + observacion.getObjEvaluacion().getFechaRegistroConcepto());
			System.out.println("Formato A: ");
			System.out.println("\tID: " + observacion.getObjEvaluacion().getObjFormatoA().getIdFormatoA());
			System.out.println("\tTitulo: " + observacion.getObjEvaluacion().getObjFormatoA().getTitulo());
			System.out.println("\tObjetivo General: " + observacion.getObjEvaluacion().getObjFormatoA().getObjDocente().getNombresDocente() + " " + observacion.getObjEvaluacion().getObjFormatoA().getObjetivoGeneral());
			System.out.println("\tEstado: " + observacion.getObjEvaluacion().getObjFormatoA().getObjEstado().getEstadoActual());
			System.out.println("\tFecha de registro: " + observacion.getObjEvaluacion().getObjFormatoA().getObjEstado().getFechaRegistroEstado());
			System.out.println("\t\tDocente que la planteó: " + observacion.getObjEvaluacion().getObjFormatoA().getObjDocente().getNombresDocente() + " " + observacion.getObjEvaluacion().getObjFormatoA().getObjDocente().getApellidosDocente());
			System.out.println("--------------------------------------------------");
			System.out.println("--------------------------------------------------");
		});
	}

	@Transactional
	public void crearRol() {
		Rol rolDocente = new Rol();
		rolDocente.setRolAsignado("Docente");

		Rol rolComite = new Rol();
		rolComite.setRolAsignado("Miembro Comite");

		this.rolReposotory.save(rolDocente);
		this.rolReposotory.save(rolComite);
	}

	private Date fechaMesDespues(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Fecha actual
		calendar.add(Calendar.MONTH, 1); // Sumamos 1 mes
		Date fechaFin = calendar.getTime();

		return fechaFin;
	}

	@Transactional
	public void crearHistoricos() {

		//traer los roles
		Rol rolDocente = rolReposotory.getReferenceById(1);
		Rol rolComite = rolReposotory.getReferenceById(2);

		//crear los historicos

		//Docente
		Historico historicoDocente = new Historico();
		historicoDocente.setActivo(1);
		historicoDocente.setFechaInicio(new Date());
		historicoDocente.setFechaFin(fechaMesDespues());
		historicoDocente.setObjDocente(this.docenteRepository.getReferenceById(1));//traer el docente

		historicoDocente.setObjRol(rolDocente);
		this.historicoRepository.save(historicoDocente);

		//Comite 1
		Historico historicoComite = new Historico();
		historicoComite.setActivo(1);
		historicoComite.setFechaInicio(new Date());
		historicoComite.setFechaFin(fechaMesDespues());
		historicoComite.setObjDocente(this.docenteRepository.getReferenceById(2));//traer el docente miembro del comite

		historicoComite.setObjRol(rolComite);
		this.historicoRepository.save(historicoComite);

		//Comite 2
		Historico historicoComite2 = new Historico();
		historicoComite2.setActivo(1);
		historicoComite2.setFechaInicio(new Date());
		historicoComite2.setFechaFin(fechaMesDespues());
		historicoComite2.setObjDocente(this.docenteRepository.getReferenceById(3));//traer el docente miembro del comite

		historicoComite2.setObjRol(rolComite);
		this.historicoRepository.save(historicoComite2);

	}

	@Transactional(readOnly = true)
	public void listarMiembrosComite(){
		List<Historico> listaHistoricos = this.historicoRepository.findAll();

		if (listaHistoricos.isEmpty()) {
			System.out.println("No hay historicos");
			return;
		}

		listaHistoricos.forEach(historico -> {
			if (historico.getObjRol().getRolAsignado().equals("Miembro Comite")) {
				System.out.println("ID: " + historico.getIdHistorico());
				System.out.println("Docente: " + historico.getObjDocente().getNombresDocente() + " " + historico.getObjDocente().getApellidosDocente());
				System.out.println("Rol: " + historico.getObjRol().getRolAsignado());
				System.out.println("Fecha inicio: " + historico.getFechaInicio());
				System.out.println("Fecha fin: " + historico.getFechaFin());
				System.out.println("--------------------------------------------------");
			}
		});
	}

	@Transactional(readOnly = true)
	public void listarFormatosPorDocente(){
		int idDocente = 1;

		Optional<Docente> docenteOp = this.docenteRepository.findById(idDocente);

		if(docenteOp.isPresent()){
			Docente docente = docenteOp.get();

			System.out.println("ID docente: " + docente.getIdDocente());
			System.out.println("Nombre docente: " + docente.getNombresDocente() + " " + docente.getApellidosDocente());
			System.out.println("Formatos:");
			docente.getFormatos().forEach(formato -> {
				System.out.println("\tID formato: " + formato.getIdFormatoA());
				System.out.println("\tTitulo formato: " + formato.getTitulo());
				System.out.println("\tObjetivo general formato: " + formato.getObjetivoGeneral());
				System.out.println("\tEstado formato: " + formato.getObjEstado().getEstadoActual());
				System.out.println("\tFecha registro formato: " + formato.getObjEstado().getFechaRegistroEstado());
				System.out.println("\tEstado formato: " + formato.getObjEstado().getEstadoActual());
				System.out.println("\tEvaluacion:");
				formato.getEvaluaciones().forEach(evaluacion -> {
					System.out.println("\t\tID evaluacion: " + evaluacion.getIdEvaluacion());
					System.out.println("\t\tConcepto evaluacion evaluacion: " + evaluacion.getConcepto());
					evaluacion.getObservaciones().forEach(observacion -> {
						System.out.println("\t\t\tID observacion: " + observacion.getIdObservacion());
						System.out.println("\t\t\tObservacion: " + observacion.getObservacion());
						System.out.println("\t\t\tFecha registro observacion: " + observacion.getFechaRegistroObservacion());
					});
				});
				System.out.println("\t--------------------------------------------------");
			});
		}
	}
}
