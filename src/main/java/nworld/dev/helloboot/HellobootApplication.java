package nworld.dev.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {

		//스프링 컨테이너 등록
		GenericApplicationContext applicationContext = new GenericApplicationContext(); // 스프링 컨테이너 생성
		applicationContext.registerBean(HelloController.class); // 빈 등록
		applicationContext.refresh(); // 등록 후 초기화

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			//HelloController helloController = new HelloController();

			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
					// 인증, 보안, 다국어, 공통기능 -> 프론트 컨트롤러는 공통적인 기능을 처리하고 요청정보를 이용해서 매핑을 해준다.
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class); // 컨테이너에서 관리하는 빈 오브젝트를 가져와서 요청
						String ret = helloController.hello(name); // 바인딩 작업을 분리했다.

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value()); // 패턴을 찾지 못하면 404 반환.
					}

				}
			}).addMapping("/*"); // 모든 요청에 매핑된다.
		});
		webServer.start();
	}
}
