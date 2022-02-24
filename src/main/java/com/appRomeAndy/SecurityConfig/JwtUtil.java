package com.appRomeAndy.SecurityConfig;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	
	
static void addAuthentication(HttpServletResponse res, String username) throws IOException {
         
	   
    	
        String token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 86000000))
            .signWith(SignatureAlgorithm.HS256, "duvanceliz")
            .compact();
        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer " + token);
        Token oToken = new Token();
        oToken.setDataToken(token);
        String json = new ObjectMapper().writeValueAsString(oToken);
        res.getWriter().write(json);
        res.flushBuffer();
        
        
    }

//Método para validar el token enviado por el cliente
static Authentication getAuthentication(HttpServletRequest request) {

    // Obtenemos el token que viene en el encabezado de la peticion
    String token = request.getHeader("Authorization");
  
    // si hay un token presente, entonces lo validamos
    if (token != null) {
    	
        String user = Jwts.parser()
                .setSigningKey("duvanceliz")
                .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                .getBody()
                .getSubject();
                 

        // Recordamos que para las demás peticiones que no sean /login
        // no requerimos una autenticacion por username/password
        // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
        return user != null ?
                new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                null;
    }
    return null;
}

}

class Token{
	private String dataToken;

	public String getDataToken() {
		return dataToken;
	}

	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}
	
}


