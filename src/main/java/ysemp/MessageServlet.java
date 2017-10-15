/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ysemp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Stuart Douglas
 */
public class MessageServlet extends HttpServlet {

    public static final String DEFAULTMESSAGE = "defaultMessage";

    private String defaultMessage;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        defaultMessage = config.getInitParameter(DEFAULTMESSAGE);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter writer = resp.getWriter();
        String response = "Default";
        String val1 = req.getParameter("v1");
        if (val1 == null)
        {
            writer.write(defaultMessage);
            writer.close();
            return;
        }
        String val2 = req.getParameter("v2");
        response = makeMessage(val1, val2);
        writer.write(response);
        writer.close();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("You posted!!");
        writer.close();
    }

    public String makeMessage(String s1, String s2)
    {
        return s1 + " is " + s2 + "!!";
    }
}
