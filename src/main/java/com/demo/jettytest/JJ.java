package com.demo.jettytest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Date;

/**
 * @author make
 * @creare 25/09/2018
 */

@Path("/json")
public class JJ {
    private static int i = 0;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Bean index() {
        Bean b = new Bean();
        b.setDate(null);
        b.setI(i++);
        return b;
    }
}


class Bean {

    private int i;
    private Date date;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }
}