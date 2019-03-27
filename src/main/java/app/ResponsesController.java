package app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ResponsesController {

    @RequestMapping(method = {RequestMethod.HEAD}, path = "/status")
    @ResponseStatus(HttpStatus.OK)
    public void checkStatusForHead() {
    }

    @RequestMapping(method = {RequestMethod.HEAD}, path = "/{\\d*\\.\\d*\\.\\d*\\.\\d*}")
    public void checkIfExitNode(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletPath();
        String ip = path.substring(1);
        if (TorExitNodeChecker.isExitNode(ip))
            response.setStatus(200);
        else
            response.setStatus(404);
    }

}
