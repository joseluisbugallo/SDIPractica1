package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.util.UserCheck;
import uo.sdi.dto.User;
import uo.sdi.persistence.Persistence;
import alb.util.log.Log;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String resultado="EXITO";
		String nombreUsuario=request.getParameter("nombreUsuario");
		String email=request.getParameter("nombreUsuario");
		String contraseña=request.getParameter("contraseña");
		String repetirContraseña=request.getParameter("repetircontraseña");
		if(contraseña.equals(repetirContraseña)){
			User user = new User();
			user.setLogin(nombreUsuario);
			user.setEmail(email);
			user.setPassword(contraseña);
					
			try {
				UserCheck.isNotAdmin( user );
				UserCheck.isValidEmailSyntax( user );
				UserCheck.minLoginLength( user );
				UserCheck.minPasswordLength( user );
				UserCheck.notRepeatedLogin( user );
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
	//		return Persistence.getUserDao().save( user );
			return resultado;
		}
		return resultado;
	}

}
