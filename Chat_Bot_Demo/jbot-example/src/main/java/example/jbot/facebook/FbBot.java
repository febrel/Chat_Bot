package example.jbot.facebook;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.validation.constraints.AssertFalse;
import javax.xml.ws.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.sockjs.transport.handler.EventSourceTransportHandler;

import com.bl.BLExamenChat;
import com.fecha_hora.FechaHora;
import com.preguntas.Evaluacion;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.facebook.Bot;
import me.ramswaroop.jbot.core.facebook.models.Attachment;
import me.ramswaroop.jbot.core.facebook.models.Button;
import me.ramswaroop.jbot.core.facebook.models.Event;
import me.ramswaroop.jbot.core.facebook.models.Message;
import me.ramswaroop.jbot.core.facebook.models.Payload;

/**
 * A simple Facebook Bot. You can create multiple bots by just extending
 * {@link Bot} class like this one. Though it is recommended to create only bot
 * per jbot instance.
 * 
 */
@JBot
@Profile("facebook")
public class FbBot extends Bot {

	//Invocar Objetos
	FechaHora objFechaHora = new FechaHora();
	private String fecha = objFechaHora.retornaFecha();
	private String hora = objFechaHora.retornaHora();
	private Evaluacion evaluacion = new Evaluacion();
	
	
	
	//Base datos
	BLExamenChat objBL = new BLExamenChat();
	
	//Variables
	private final int total = 5;
	private String qFormat = "%s%s";
	private String pulgarArriba = "(Y) \nCorrecto!";
	private String pulgarAbajo = "(n) \nIncorrecto!";
	private String horaFinal;
	private String nombre;
	private String genero;
	private String provincia;
	private int contador = 0;
	private int nota = 0;
	// Variables para contar los aciertos-errores
	private int acierto = 0 ;
	private int error = 0 ;
	

	/*
	 * static { optiones.add("A"); optiones.add("B"); optiones.add("C"); }
	 * 
	 * 
	 * /**
	 * 
	 * Establezca esta propiedad en {@code application.properties}.
	 */
	@Value("${fbBotToken}")
	private String fbToken;

	/*
	 * 
	 * Establezca esta propiedad en {@code application.properties}.
	 */
	@Value("${fbPageAccessToken}")
	private String pageAccessToken;

	@Override
	public String getFbToken() {
		return fbToken;
	}

	@Override
	public String getPageAccessToken() {
		return pageAccessToken;
	}

	/*
	 * Establece el botón "Comenzar" con una carga útil "hola". También establece el
	 * "Texto de saludo" que el usuario ve cuando abre la ventana de chat Descomente
	 * la anotación {@code @PostConstruct} solo después de que haya verificado su
	 * webhook
	 */

	//@PostConstruct
	public void init() {
		setGetStartedButton("hola");
		setGreetingText(new Payload[] { new Payload().setLocale("default").setText(
		"Hola, Chat_Bot te evaluara y guardara tus respuestas sobre un examen en específico con opciones de respuesta. "
		+ "Puedes comenzar haciendo clic en Empezar ") });
	}

	/**
	 * Este método se invoca cuando un usuario hace clic en el botón "Comenzar" o
	 * simplemente cuando alguien simplemente escribe Hola, hola o hey. Cuando es el
	 * primero, el tipo de evento es {@code EventType.POSTBACK} con la carga útil
	 * "hi" y cuando este último, el tipo de evento es {@code EventType.MESSAGE}.
	 * 
	 * @param event
	 */

	@Controller(events = { EventType.MESSAGE,
			EventType.POSTBACK }, pattern = "(?i)(hola|HOLA|comenzar|empezar|EMPEZAR)$")
	public void onGetStarted(Event event) {
		// quick reply buttons
		Button[] quickReplies = new Button[] { new Button().setContentType("text").setTitle("SI").setPayload("yes"),
				new Button().setContentType("text").setTitle("NO").setPayload("no") };
		reply(event, new Message().setText("Realidad Nacional, Deseas continuar").setQuickReplies(quickReplies));
	}

	/**
	 *
	 * Este método se invoca cuando el usuario hace clic en un botón de respuesta
	 * rápida cuya carga es "sí" o "no".
	 *
	 * @param event
	 * @throws FacebookException 
	 */

	// Pongo Codigo
	
	@Controller(events = EventType.QUICK_REPLY, pattern = "(yes|no)")
	public void nombres(Event event) throws FacebookException {
		
		if("yes".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText("Ingresa tus nombres por favor Ej: Luis Febre"));
		}else {
			reply(event, new Message().setText("Adios"));
		}
		
	}
		


	// Respuesta Pregunta Nombre
	@Controller(next = "text") // TODO LO QUE SEA TEXTO GUARDA EVENTO
	public void respuestaNombre(Event event) {
		// Guarda el evento y trasforma a numero
		String guardaNumero = event.getMessage().getText();

		if (guardaNumero.equalsIgnoreCase("1") || guardaNumero.equalsIgnoreCase("2")
				|| guardaNumero.equalsIgnoreCase("3") || guardaNumero.equalsIgnoreCase("4")
				|| guardaNumero.equalsIgnoreCase("5") || guardaNumero.equalsIgnoreCase("6")
				|| guardaNumero.equalsIgnoreCase("7") || guardaNumero.equalsIgnoreCase("8")
				|| guardaNumero.equalsIgnoreCase("9") || guardaNumero.equalsIgnoreCase("10")
				|| guardaNumero.equalsIgnoreCase("11") || guardaNumero.equalsIgnoreCase("12")
				|| guardaNumero.equalsIgnoreCase("13") || guardaNumero.equalsIgnoreCase("14")
				|| guardaNumero.equalsIgnoreCase("15") || guardaNumero.equalsIgnoreCase("16")
				|| guardaNumero.equalsIgnoreCase("17") || guardaNumero.equalsIgnoreCase("18")
				|| guardaNumero.equalsIgnoreCase("19") || guardaNumero.equalsIgnoreCase("20")
				|| guardaNumero.equalsIgnoreCase("21") || guardaNumero.equalsIgnoreCase("22")
				|| guardaNumero.equalsIgnoreCase("23") || guardaNumero.equalsIgnoreCase("24")) {

			respuestaProvincia(event);
		} else {
			// Guarda el nombre y lo convierte a mayuscula
			String nombreC = event.getMessage().getText();
			nombre = nombreC.toUpperCase();
			genero(event);
		}

	}

	//Metodod Genero
	public void genero(Event event) {
		Button[] ge = new Button[] { new Button().setContentType("text").setTitle("MASCULINO").setPayload("Ma"),
				new Button().setContentType("text").setTitle("FEMENINO").setPayload("Fe") };
		reply(event, new Message().setText("Seleccione su género").setQuickReplies(ge));

	}

	//Metodod respuesta Genero y comienza
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(Ma|Fe)")
	public void respuestaGenro(Event event) {
		if ("Fe".equals(event.getMessage().getQuickReply().getPayload())
				|| "Ma".equals(event.getMessage().getQuickReply().getPayload())) {
			provincia(event);
		} else {
			reply(event, new Message().setText("Error vuelva a seleccionar su Genero"));
			genero(event);
		}
		// Guarda en la variable el genero
		genero = event.getMessage().getText();

	}

	// Metodo Provincia
	public void provincia(Event event) {
		int preguntaActual = 10;
		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat,evaluacion.getFuente(), evaluacion.getPregunta().get(preguntaActual).getCamino())))));

	}

	//Metodo respuesta provincia
	public void respuestaProvincia(Event event) {
		String variable = event.getMessage().getText();
		switch (variable) {
		case "1":
			this.provincia = "ZAMORA CHIMCHIPE";
			break;

		case "2":
			this.provincia = "LOJA";
			break;

		case "3":
			this.provincia = "EL ORO";
			break;
		case "4":
			this.provincia = "AZUAY";
			break;

		case "5":
			this.provincia = "MORONA SANTIAGO";
			break;

		case "6":
			this.provincia = "PASTAZA";
			break;

		case "7":
			this.provincia = "ORELLANA";
			break;

		case "8":
			this.provincia = "SUCUMBÍOS";
			break;

		case "9":
			this.provincia = "CARCHI";
			break;

		case "10":
			this.provincia = "IMBABURA";
			break;

		case "11":
			this.provincia = "ESMERALDAS";
			break;

		case "12":
			this.provincia = "SANTO DOMINGO DE LOS TSÁCHILAS";
			break;

		case "13":
			this.provincia = "PICHINCHA";
			break;

		case "14":
			this.provincia = "COTOPAXI";
			break;

		case "15":
			this.provincia = "NAPO";
			break;

		case "16":
			this.provincia = "TUNGURUHUA";
			break;

		case "17":
			this.provincia = "BOLÍVAR";
			break;

		case "18":
			this.provincia = "CHIMBORAZO";
			break;

		case "19":
			this.provincia = "LOS RÍOS";
			break;

		case "20":
			this.provincia = "MANABÍ";
			break;

		case "21":
			this.provincia = "SANTA ELENEA";
			break;

		case "22":
			this.provincia = "GUAYAS";
			break;

		case "23":
			this.provincia = "CAÑÁR";
			break;

		case "24":
			this.provincia = "GALÁPAGOS";
			break;

		}
		pasoPregunta(event);
	}

	public void pasoPregunta(Event event) {
		reply(event, new Message().setText(
				"Observe las preguntas y seleccione los  botones \n\t\tA \n\t\tB \n\t\tC \nsegún sea su respuesta\n \nComienza Exámen"));
		pregunta1(event);
	}

	// Pregunta 1
	public void pregunta1(Event event) {

		int preguntaActual = 0;

		Button[] boton1 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a1"),
				new Button().setContentType("text").setTitle("B").setPayload("b1"),
				new Button().setContentType("text").setTitle("C").setPayload("c1") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton1));

	}

	// Respuesta Pregunta 1
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a1|b1|c1)")
	public void respuesta1(Event event) {
		if ("c1".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
			acierto++;
		} else if ("a1".equals(event.getMessage().getQuickReply().getPayload())
				|| "b1".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));
			error++;
		}
		pregunta2(event);

	}

	// Pregunta 2
	public void pregunta2(Event event) {
		int preguntaActual = 1;
		Button[] boton2 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a2"),
				new Button().setContentType("text").setTitle("B").setPayload("b2"),
				new Button().setContentType("text").setTitle("C").setPayload("c2") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton2));

	}

	// Respuesta Pregunta 2
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a2|b2|c2)")
	public void respuesta2(Event event) {
		if ("c2".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
			acierto++;
		} else if ("a2".equals(event.getMessage().getQuickReply().getPayload())
				|| "b2".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));
			error++;
		}
		pregunta3(event);

	}

	// Pregunta 3
	public void pregunta3(Event event) {
		int preguntaActual = 2;
		Button[] boton3 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a3"),
				new Button().setContentType("text").setTitle("B").setPayload("b3"),
				new Button().setContentType("text").setTitle("C").setPayload("c3") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton3));

	}

	// Respuesta Pregunta 3
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a3|b3|c3)")
	public void respuesta3(Event event) {
		if ("b3".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
			acierto++;
		} else if ("a3".equals(event.getMessage().getQuickReply().getPayload())
				|| "c3".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));
			error++;
		}
		pregunta4(event);

	}

	// Pregunta 4
	public void pregunta4(Event event) {
		int preguntaActual = 3;
		Button[] boton4 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a4"),
				new Button().setContentType("text").setTitle("B").setPayload("b4"),
				new Button().setContentType("text").setTitle("C").setPayload("c4") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton4));

	}

	//Respuesta Pregunta 4
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a4|b4|c4)")
	public void respuesta4(Event event) {
		if ("c4".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
			acierto++;
		} else if ("a4".equals(event.getMessage().getQuickReply().getPayload())
				|| "b4".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));
			error++;
		}

		pregunta5(event);

	}

	//Pregunta 5
	public void pregunta5(Event event) {
		int preguntaActual = 4;
		Button[] boton5 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a5"),
				new Button().setContentType("text").setTitle("B").setPayload("b5"),
				new Button().setContentType("text").setTitle("C").setPayload("c5") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton5));

	}

	// Respuesta Pregunta 5
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a5|b5|c5)")
	public void respuesta5(Event event) {

		if ("a5".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
			acierto++;
		} else if ("b5".equals(event.getMessage().getQuickReply().getPayload())
				|| "c5".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));
			error++;
		}

		// pregunta6(event);
		finales(event);

	}

	//Pregunta 6
	public void pregunta6(Event event) {
		int preguntaActual = 5;
		Button[] boton6 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a6"),
				new Button().setContentType("text").setTitle("B").setPayload("b6"),
				new Button().setContentType("text").setTitle("C").setPayload("c6") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton6));

	}

	//Respuesta Pregunta 6
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a6|b6|c6)")

	public void respuesta6(Event event) {
		if ("c6".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
		} else if ("a6".equals(event.getMessage().getQuickReply().getPayload())
				|| "b6".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));

		}
		pregunta7(event);

	}

	//Pregunta 7
	public void pregunta7(Event event) {
		int preguntaActual = 6;
		Button[] boton7 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a7"),
				new Button().setContentType("text").setTitle("B").setPayload("b7"),
				new Button().setContentType("text").setTitle("C").setPayload("c7") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton7));

	}

	//Respuesta Pregunta 7
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a7|b7|c7)")

	public void respuesta7(Event event) {
		if ("b7".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
		} else if ("a7".equals(event.getMessage().getQuickReply().getPayload())
				|| "c7".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));

		}
		pregunta8(event);

	}

	//Pregunta 8
	public void pregunta8(Event event) {
		int preguntaActual = 7;
		Button[] boton8 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a8"),
				new Button().setContentType("text").setTitle("B").setPayload("b8"),
				new Button().setContentType("text").setTitle("C").setPayload("c8") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton8));

	}

	//Respuesta Pregunta 8
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a8|b8|c8)")

	public void respuesta8(Event event) {

		if ("c8".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
		} else if ("a8".equals(event.getMessage().getQuickReply().getPayload())
				|| "b8".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));

		}
		pregunta9(event);

	}

	//Pregunta 9
	public void pregunta9(Event event) {

		int preguntaActual = 8;
		Button[] boton9 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("a9"),
				new Button().setContentType("text").setTitle("B").setPayload("b9"),
				new Button().setContentType("text").setTitle("C").setPayload("c9") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton9));

	}

	//Respuesta Pregunta 9
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(a9|b9|c9)")

	public void respuesta9(Event event) {
		if ("a9".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
		} else if ("b9".equals(event.getMessage().getQuickReply().getPayload())
				|| "c9".equals(event.getMessage().getQuickReply().getPayload())) {
			reply(event, new Message().setText(pulgarAbajo));

		}
		pregunta10(event);

	}

	//Pregunta 10
	public void pregunta10(Event event) {

		int preguntaActual = 9;
		Button[] boton10 = new Button[] { new Button().setContentType("text").setTitle("A").setPayload("ah10"),
				new Button().setContentType("text").setTitle("B").setPayload("bh10"),
				new Button().setContentType("text").setTitle("C").setPayload("ch10") };

		reply(event,new Message().setAttachment(new Attachment().setType("image").setPayload(new Payload().setUrl(String.format(qFormat, evaluacion.getFuente(),evaluacion.getPregunta().get(preguntaActual).getCamino())))).setQuickReplies(boton10));

	}

	//Respuesta Pregunta 10
	@Controller(events = EventType.QUICK_REPLY, pattern = "(?i)(ah10|bh10|ch10)")

	public void respuesta10(Event event) {
		if ("ch10".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarArriba));
			contador++;
		} else if ("ah10".equals(event.getMessage().getQuickReply().getPayload())
				|| "bh10".equals(event.getMessage().getQuickReply().getPayload())) {

			reply(event, new Message().setText(pulgarAbajo));

		}
		finales(event);
	}

	

	public void finales(Event event) {
		nota = (contador * 100) / total;
		int nota2 = nota;
		// Convertir
		String cadena, cadena2;
		cadena = String.valueOf(nota);
		cadena2 = String.valueOf(contador);
		//Hora que finaliza
		horaFinal = objFechaHora.retornaHora();

		// aqui
		reply(event,
				new Message().setText("\n=======NOTAS=======\n\t\t" + cadena + "%\n\t\t DE\n\t\t100%" + "\n\nNombre: "
						+ this.nombre + "\nGenero: " + this.genero + "\nFecha: " + this.fecha + "\nProvincia: "
						+ this.provincia + "\nHora Inicia: " + this.hora + "\nHora Final: " + this.horaFinal
						+ "\n\n====================="));
		
		try {
			
			
			ExamenChat objExa = new ExamenChat(nombre, provincia, genero, fecha, hora, horaFinal,nota2,acierto,error);
			 int retorno = objBL.ingresarNotas(objExa);
			
			
			
		} catch (Exception e) {
			System.out.println("No se pudo enviar");
			
			
		}
		
		// Refresca variables
		contador = 0;
		this.provincia = "";
		acierto=0;
		error=0;
	}

}
