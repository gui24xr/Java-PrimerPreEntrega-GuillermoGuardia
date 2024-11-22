
package com.coderhouse.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dto.TimeHourResponseDTO;
import com.coderhouse.dto.TimeResponseDTO;

@RestController
@RequestMapping("/api/hora")
public class ApiControllers {

    @Autowired
    private RestTemplate restTemplate;


/*
    @GetMapping
    public ResponseEntity<?> getHour(){
        try {
            final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
            String response = restTemplate.getForObject(URL, String.class);
                     TimeHourResponseDTO timeHourObject = restTemplate.getForObject(URL,TimeHourResponseDTO.class);
            return ResponseEntity.ok(timeHourObject);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    */


    @GetMapping
    public ResponseEntity<?> getHour(){
        try {
            final String URL = "http://worldclockapi.com/api/json/utc/now";
            String response = restTemplate.getForObject(URL, String.class);
       
           TimeResponseDTO timeHourObject = restTemplate.getForObject(URL,TimeResponseDTO.class);
         
            return ResponseEntity.ok(timeHourObject);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    //SI ESTO DEVUELVE NULL EL SERVICIO LE VA A PREGUNTAR AL DATE
    //ESTE METODO SERA PRIVADO PARA EL SERVICIO Y EL METODO QUE USARAN LOS OTROS SERVICIOS ES EL DE GETCURRENTHOUR 	qUE SI No saca la 
    //INFO DEL API LA SACA DEL DATE Y LA DEVUELVE EN FORMATO DE DATE
    /*

	private TimeResponseDTO obtenerFechaActual() {
		try {
			final String URL = "http://worldclockapi.com/api/json/utc/now";
			return restTemplate.getForObject(URL, TimeResponseDTO.class);
		}catch(RestClientException error) {
			System.err.println("Error, No se pudo conectar a la API Externa: " + error.getMessage());
			return null;
		}
		
	}
	
	public getCurrentHour(){
	optional<TimeResponseDTO> horarecibidadelapi = new this.Thime
	
	iftime responseDTO lo convierto a date y lo devuelvo, si no anda entonces le pregunto a la clase date y deuvlvo siempre date
	si o si devuelvo date
	}
	}
	*/

}