package com.sde.conversion;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Conversion extends HttpServlet {

    abstract Unit[] getUnits();

    abstract String convert(String from, String to, String value) throws Exception;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setAccessControlHeaders(response);
        final String responseBody;
        if (request.getParameterMap().isEmpty()) {
            responseBody = unitsAsJson();
        } else {
            try {
                responseBody = answerAsJson(request);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                return;
            }
        }
        try {
            OutputStream out = response.getOutputStream();
            out.write(responseBody.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }

    private String unitsAsJson() {
        StringBuilder unitBuilder = new StringBuilder();
        unitBuilder.append("{\"units\": [");
        for (Unit unit : getUnits()) {
            unitBuilder.append("{\"name\":\"")
                    .append(unit.getName())
                    .append("\",\"abbreviation\":\"")
                    .append(unit.getAbbreviation())
                    .append("\"},");
        }
        unitBuilder.delete(unitBuilder.length()-1, unitBuilder.length());
        unitBuilder.append("]}");
        return unitBuilder.toString();
    }

    private String answerAsJson(HttpServletRequest req) throws Exception {
        String value = convert(req.getParameter("from"), req.getParameter("to"), req.getParameter("value"));
        return "{\"value\":\"" + value + "\",\"unit\":\"" + req.getParameter("to") + "\"}";
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
      response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
      response.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
