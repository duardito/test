package org.com;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by edu on 17/06/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pepe {

    private Long id;

    @JsonProperty("FastTextSearch")
    private String name;

    private Date alta;

    private Date modificacion;

    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    private String ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Pepe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alta=" + alta +
                ", modificacion=" + modificacion +
                ", apellido='" + apellido + '\'' +
                ", ids='" + ids + '\'' +
                '}';
    }
}
