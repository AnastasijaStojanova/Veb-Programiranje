package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "workshop-list-servlet", urlPatterns = "/")
public class WorkshopListServlet extends HttpServlet {

    private final WorkshopService workshopService;
    private final SpringTemplateEngine springTemplateEngine;

    public WorkshopListServlet(WorkshopService workshopService,
                               SpringTemplateEngine springTemplateEngine) {
        this.workshopService = workshopService;
        this.springTemplateEngine = springTemplateEngine;
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String textParam = req.getParameter("text");
        String maxPriceParam = req.getParameter("maxPrice");

        String text = (textParam == null || textParam.isBlank()) ? null : textParam.trim();
        Double maxPrice = null;
        String errorMessage = req.getParameter("errorMessage");

        if (maxPriceParam != null && !maxPriceParam.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceParam.trim());
            } catch (NumberFormatException e) {
                errorMessage = "Invalid maximum price";
            }
        }

        List<Workshop> workshops;
        if (text != null || maxPrice != null) {
            workshops = workshopService.search(text, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", errorMessage);
        context.setVariable("text", text);
        context.setVariable("maxPrice", maxPriceParam);

        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("listWorkshops.html", context, resp.getWriter());
    }
    }
