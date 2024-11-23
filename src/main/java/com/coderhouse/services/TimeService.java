package com.coderhouse.services;
import java.sql.Date;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.coderhouse.dto.TimeResponseDTO;


@Service
public class TimeService {
	
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://worldclockapi.com/api/json/utc/now";
   
    public Date getCurrentDateFromAPI(){
        try {
            //Desde el servicio externo obtengo el UTC y lo convierto en mi objeto TimeResponseDTO.
            TimeResponseDTO timeHourObject = restTemplate.getForObject(URL,TimeResponseDTO.class);
                
           if (timeHourObject == null || timeHourObject.getCurrentDateTime() == null) {
        	   //En este caso si el api fallo o devolvio algo vacio uso la hora del sistema...
        	   //Pero como el sistema en nuestro caso estamos en arg tenemos horario UTC-3 y hay que convertirlo a UTC
        	   long millisecondsSystem = System.currentTimeMillis();
        	   Date systemCurrentDate = new Date(millisecondsSystem);
        	   return systemCurrentDate;              
             }
           
          //Desde el objeto mapeado obtengo la fecha que me dio el api.
           String utcDateTime = timeHourObject.getCurrentDateTime();  
           // EL API DEVUELVE LA FECHA ASI => "2024-11-23T18:23Z" Y necesito hacer un objeto instant a partir del string.
           //El objeto Instant representa el tiempo transcurrido desde el 1 de enero de 1970.
           //Su metodo parse acepta una cadena "2024-11-23T18:23:00Z" me faltan los segundos, se los agrego...
           String completeString = utcDateTime.replace("Z", ":00Z");

           //AHora convierto la string completa en instant usando su metodo parse.
           Instant instant = Instant.parse(completeString);  
           //Obtengo la cantidad de milisegundos del instant (xq sqlDate necesita eso para construir el objeto)
           long millisecondsNowUtc = instant.toEpochMilli();
           // Convertir el Instant a java.sql.Date
           Date sqlDate = new Date(millisecondsNowUtc) ; 
           	//System.out.println("Fecha convertida: " + sqlDate);
            return sqlDate;
        }catch(Exception err) {
        	  // Imprimir la excepción completa en la consola
            err.printStackTrace();
        	throw(err);
        }
    }
}
