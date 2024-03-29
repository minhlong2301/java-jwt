//package vn.learning.jwt.jwt;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//public class JwtEntryPoint implements AuthenticationEntryPoint {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        logger.error("Unauthorization error message : {} " + e.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,  "Error: Unauthorization");
//
//    }
//}
