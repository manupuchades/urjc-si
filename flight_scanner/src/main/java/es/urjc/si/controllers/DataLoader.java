package es.urjc.si.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.urjc.si.dtos.FlightArrivalsDto;
import es.urjc.si.dtos.MemberDeparturesDto;
import es.urjc.si.dtos.MemberFlightsDto;
import es.urjc.si.dtos.PlaneMechanicsDto;
import es.urjc.si.models.Airport;
import es.urjc.si.models.CrewMember;
import es.urjc.si.models.Flight;
import es.urjc.si.models.FlightCrew;
import es.urjc.si.models.Mechanic;
import es.urjc.si.models.Plane;
import es.urjc.si.models.Provincia;
import es.urjc.si.models.Review;
import es.urjc.si.repositories.AirportRepository;
import es.urjc.si.repositories.CrewMemberRepository;
import es.urjc.si.repositories.FlightRepository;
import es.urjc.si.repositories.MechanicRepository;
import es.urjc.si.repositories.PlaneRepository;
import es.urjc.si.repositories.ProvinciaRepository;
import es.urjc.si.repositories.ReviewRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

	AirportRepository airports;

	PlaneRepository planes;

	MechanicRepository mechanics;

	CrewMemberRepository crew;

	FlightRepository flights;

	ReviewRepository reviews;
	
	ProvinciaRepository repository;

	@Override
	public void run(String... args) {

//		populate();

		firstRequest();

		secondRequest();

		thirdRequest();

		fourthRequest();
		
		
        System.out.println(">>>> PROVINCIAS :");

        for (Provincia p : repository.findAll()) {
            System.out.println(p);
        }

	}

	/**
	 * Para cada avión, mostrar el nombre y apellidos de los responsables de sus
	 * revisiones.
	 */
	private void firstRequest() {
		System.out
				.println(">>>> Para cada avión, mostrar el nombre y apellidos de los responsables de sus revisiones.");

		List<PlaneMechanicsDto> m = reviews.findAllMechanicsForEveryPlane();

		for (PlaneMechanicsDto dto : m) {
			System.out.println(dto);
		}
	}

	/**
	 * Dado el nombre de una ciudad y una fecha, listar los vuelos que han
	 * aterrizado (destino) en los aeropuertos de esa ciudaden esa fecha, ordenados
	 * por hora.
	 */
	private void secondRequest() {
		System.out.println(
				">>>> Dado el nombre de una ciudad y una fecha, listar los vuelos que han aterrizado (destino) en los aeropuertos de esa ciudaden esa fecha, ordenados por hora.");

		List<FlightArrivalsDto> m = flights.getDestinationArrivals("Barcelona", LocalDate.now());

		for (FlightArrivalsDto dto : m) {
			System.out.println(dto);
		}
	}

	/**
	 * Dado el código de empleado de un tripulante, mostrar su nombre y apellidos y
	 * las ciudades desde las que ha despegado junto con la fecha en que despegó.
	 */
	private void thirdRequest() {
		System.out.println(
				">>>> Dado el código de empleado de un tripulante, mostrar su nombre y apellidos y las ciudades desde las que ha despegado junto con la fecha en que despegó.");

		List<MemberDeparturesDto> m = crew.getMemberDepartures("003");

		for (MemberDeparturesDto dto : m) {
			System.out.println(dto);
		}
	}

	/**
	 * Para cada tripulante, mostrar su nombre y apellidos y junto con su número
	 * total de vuelos y la suma de horas de estos.
	 */
	private void fourthRequest() {
		System.out.println(
				">>>> Para cada tripulante, mostrar su nombre y apellidos y junto con su número total de vuelos y la suma de horas de estos.");

		List<MemberFlightsDto> m = crew.findMembersFlightExperience();

		for (MemberFlightsDto dto : m) {
			System.out.println(dto);
		}
	}

	private void populate() {
		Airport a1 = airports
				.save(Airport.builder().iata("MAD").name("Adolfo Suarez Madrid-Barajas").city("Madrid").country("España").build());
		Airport a2 = airports
				.save(Airport.builder().iata("BCN").name("Josep Tarradellas Barcelona-El Prat").city("Barcelona").country("España").build());
		Airport a3 = airports
				.save(Airport.builder().iata("PMI").name("Palma de Mallorca").city("Palma de Mallorca").country("España").build());
		Airport a4 = airports
				.save(Airport.builder().iata("AGP").name("Malaga-Costa del Sol").city("Malaga").country("España").build());
		Airport a5 = airports
				.save(Airport.builder().iata("ALC").name("Alicante-Elche").city("Alicante").country("España").build());
		Airport a6 = airports
				.save(Airport.builder().iata("ECV").name("Madrid-Cuatro Vientos").city("Madrid").country("España").build());

		System.out.println(">>>> Airports:");
		for (Airport a : airports.findAll()){
			System.out.println(a);
		}

		Plane p1 = planes.save(Plane.builder().reference("REF001").builder("Airbus").model("A320").flightHours(100000).build());
		Plane p2 = planes.save(Plane.builder().reference("REF002").builder("Boeing").model("737").flightHours(1000).build());
		Plane p3 = planes.save(Plane.builder().reference("REF003").builder("Airbus").model("A350").flightHours(40000).build());
		Plane p4 = planes.save(Plane.builder().reference("REF004").builder("Airbus").model("A321").flightHours(2000).build());

		System.out.println(">>>> Planes:");
		for (Plane p : planes.findAll()){
			System.out.println(p);
		}

		Mechanic m1 = mechanics.save(
				Mechanic.builder().employeeId("MEC001").firstName("MecName01").lastName("MecSurname01").company("Iberia").education("Master").seniority(2000).build());
		Mechanic m2 = mechanics.save(
				Mechanic.builder().employeeId("MEC002").firstName("MecName02").lastName("MecSurname02").company("Iberia").education("Master").seniority(2000).build());
		Mechanic m3 = mechanics.save(
				Mechanic.builder().employeeId("MEC003").firstName("MecName03").lastName("MecSurname03").company("Iberia").education("Master").seniority(2000).build());

		System.out.println(">>>> Planes:");
		for (Mechanic m : mechanics.findAll()){
			System.out.println(m);
		}

		CrewMember c1 = CrewMember.builder().employeeId("001").firstName("pilot01").lastName("lastName01").job("Pilot")
				.company("Company").build();
		CrewMember c2 = CrewMember.builder().employeeId("002").firstName("pilot02").lastName("lastName02").job("Pilot")
				.company("Company").build();
		CrewMember c3 = CrewMember.builder().employeeId("102").firstName("co-pilot02").lastName("co-lastName02")
				.job("Co-Pilot").company("Company").build();
		CrewMember c4 = CrewMember.builder().employeeId("003").firstName("pilot03").lastName("lastName03").job("Pilot")
				.company("Company").build();

		Flight f1 = Flight.builder().flightId("MON00").company("Company").plane(p1).departure(a1).arrival(a2)
				.departureDateTime(LocalDateTime.now()).flightDuration(2.5).build();
		Flight f2 = Flight.builder().flightId("MON03").company("Company").plane(p1).departure(a1).arrival(a2)
				.departureDateTime(LocalDateTime.now().plusHours(3)).flightDuration(2.5).build();
		Flight f3 = Flight.builder().flightId("MON00").company("Company").plane(p2).departure(a1).arrival(a3)
				.departureDateTime(LocalDateTime.now()).flightDuration(2.5).build();
		Flight f4 = Flight.builder().flightId("MON03").company("Company").plane(p2).departure(a1).arrival(a3)
				.departureDateTime(LocalDateTime.now().plusHours(1)).flightDuration(2.5).build();

		FlightCrew fc1 = new FlightCrew(f1, c1);
		FlightCrew fc2a = new FlightCrew(f2, c2);
		FlightCrew fc2b = new FlightCrew(f2, c3);
		FlightCrew fc3 = new FlightCrew(f3, c4);

		f1.setCrewMembers(Arrays.asList(fc1));
		f2.setCrewMembers(Arrays.asList(fc2a, fc2b));
		f3.setCrewMembers(Arrays.asList(fc3));

		
		flights.save(f1);
		flights.save(f2);
		flights.save(f3);
		flights.save(f4);


		Review r1 = reviews
				.save(Review.builder().plane(p1).startDate(LocalDate.now().minusWeeks(1)).endDate(LocalDate.now().minusDays(4))
						.reviewDuration(12.5).mechanic(m1).reviewType("MONTHLY").description("Monthly review").build());
		Review r2 = reviews.save(Review.builder().plane(p1).startDate(LocalDate.now().minusMonths(1).minusWeeks(1))
				.endDate(LocalDate.now().minusMonths(1).minusDays(4)).reviewDuration(12.5).mechanic(m2).reviewType("MONTHLY")
				.description("Monthly review").build());
		Review r3 = reviews.save(Review.builder().plane(p2).startDate(LocalDate.now().minusMonths(1).minusWeeks(1))
				.endDate(LocalDate.now().minusMonths(1).minusDays(4)).reviewDuration(12.5).mechanic(m1).reviewType("MONTHLY")
				.description("Monthly review").build());

		System.out.println(">>>> Reviews:");
		for (Review r : reviews.findAll()){
			System.out.println(r);
		}
	}

}
