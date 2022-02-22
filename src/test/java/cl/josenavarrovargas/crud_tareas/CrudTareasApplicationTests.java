package cl.josenavarrovargas.crud_tareas;

import static org.mockito.Answers.RETURNS_DEFAULTS;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import cl.josenavarrovargas.crud_tareas.models.Tarea;
import cl.josenavarrovargas.crud_tareas.repositories.TareaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CrudTareasApplicationTests {
	private static Logger logger = LogManager.getLogger(CrudTareasApplication.class);
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TareaRepository tareaRepo;

	@Test
	public void testCrearTarea() throws JsonProcessingException, Exception {
		logger.info("\n");
		logger.info("testCrearTarea()");

		Tarea tareaNueva = new Tarea(1L, "Tarea metodo testCrearTarea", LocalDateTime.of(LocalDate.of(2022, 02, 9), LocalTime.of(12, 12)), true);

		Mockito.when(tareaRepo.save(any(Tarea.class))).thenReturn(tareaNueva);

		String url = "/tarea/guardar-tarea";

		mockMvc.perform(
			MockMvcRequestBuilders.post(url)
			.contentType(MediaType.APPLICATION_JSON.toString())
			.content(objectMapper.writeValueAsString(tareaNueva))
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tareaNueva)));

		logger.info("\n\n");
	}

	@Test
	public void testActualizarTarea() throws JsonProcessingException, Exception {
		logger.info("\n");
		logger.info("testActualizarTarea()");

		Tarea tarea = new Tarea(1L, "Creación de tarea metodo testActualizarTarea", LocalDateTime.of(LocalDate.of(2022, 02, 9), LocalTime.of(12, 12)), true);
		Mockito.when(tareaRepo.save(any(Tarea.class))).thenReturn(tarea);

		mockMvc.perform(
			MockMvcRequestBuilders.post("/tarea/guardar-tarea")
			.contentType(MediaType.APPLICATION_JSON.toString())
			.content(objectMapper.writeValueAsString(tarea))
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tarea)));

		Tarea tareaActualizada = new Tarea(1L, "Actualización de tarea metodo testActualizarTarea", LocalDateTime.of(LocalDate.of(2022, 02, 9), LocalTime.of(12, 12)), true);

		String urlActualizarTarea = "/tarea/actualizar-tarea";
		
		Mockito.when(tareaRepo.findById(1L)).thenReturn(Optional.of(tarea));
		Mockito.when(tareaRepo.save(any(Tarea.class))).thenReturn(tareaActualizada);

		mockMvc.perform(
			MockMvcRequestBuilders.put(urlActualizarTarea)
			.contentType(MediaType.APPLICATION_JSON.toString())
			.content(objectMapper.writeValueAsString(tareaActualizada))
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(tareaActualizada)));

		logger.info("\n\n");
	}

	@Test
	public void testObtenerTareaPorId() throws Exception {
		logger.info("\n");
		logger.info("testObtenerTareaPorId()");

		long identificadorTarea = 1L;

		Tarea tarea = new Tarea(1L, "Tarea metodo testObtenerTareaPorId", LocalDateTime.of(LocalDate.of(2022, 02, 9), LocalTime.of(12, 12)), true);
		Optional<Tarea> optionalTarea = Optional.of(tarea);
		
		Mockito.when(tareaRepo.findById(identificadorTarea)).thenReturn(optionalTarea);

		String url = "/tarea/identificador/".concat(String.valueOf(identificadorTarea));

		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
 		).andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		
		String respuestaObtencionTarea = mvcResult.getResponse().getContentAsString();
		logger.info("respuestaObtencionTarea: {}", respuestaObtencionTarea);

		logger.info("\n\n");
	}

	@Test
	public void testObtenerTodasLasTareas() throws Exception {
		logger.info("\n");
		logger.info("testObtenerTodasLasTareas()");

		List<Tarea> lstTareas = new ArrayList<>();

		Tarea tarea1 = new Tarea(1L, "Tarea metodo testObtenerTodasLasTareas 1", LocalDateTime.of(LocalDate.of(2022, 02, 7), LocalTime.of(7, 7)), true);
		Tarea tarea2 = new Tarea(2L, "Tarea metodo testObtenerTodasLasTareas 2", LocalDateTime.of(LocalDate.of(2022, 02, 8), LocalTime.of(8, 8)), false);
		Tarea tarea3 = new Tarea(3L, "Tarea metodo testObtenerTodasLasTareas 3", LocalDateTime.of(LocalDate.of(2022, 02, 9), LocalTime.of(9, 9)), true);
		Tarea tarea4 = new Tarea(4L, "Tarea metodo testObtenerTodasLasTareas 4", LocalDateTime.of(LocalDate.of(2022, 02, 10), LocalTime.of(10, 10)), false);
		Tarea tarea5 = new Tarea(5L, "Tarea metodo testObtenerTodasLasTareas 5", LocalDateTime.of(LocalDate.of(2022, 02, 11), LocalTime.of(11, 11)), true);
		
		lstTareas.add(tarea1);
		lstTareas.add(tarea2);
		lstTareas.add(tarea3);
		lstTareas.add(tarea4);
		lstTareas.add(tarea5);

		Mockito.when(tareaRepo.findAll()).thenReturn(lstTareas);

		String url = "/tarea/tareas";

		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
 		).andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		
		String respuestaObtencionTodasLasTareas = mvcResult.getResponse().getContentAsString();
		logger.info("\nrespuestaObtencionTodasLasTareas: {}", respuestaObtencionTodasLasTareas);

		logger.info("\n\n");
	}

	@Test
	public void testEliminarTarea() throws JsonProcessingException, Exception {
		logger.info("\n");
		logger.info("testEliminarTarea()");

		int identificadorTarea = 1;

		String url = "/tarea/eliminar-tarea/".concat(String.valueOf(identificadorTarea));

		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.delete(url)
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Tarea con identificador " + identificadorTarea + " eliminada correctamente. "))
		.andReturn();
		
		String respuestaEliminacionTarea = mvcResult.getResponse().getContentAsString();
		logger.info("respuestaEliminacionTarea: {}", respuestaEliminacionTarea);

		logger.info("\n\n");
	}

}
