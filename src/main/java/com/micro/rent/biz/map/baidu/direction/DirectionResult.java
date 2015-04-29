package com.micro.rent.biz.map.baidu.direction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("result")
public class DirectionResult {
    @XStreamImplicit(itemFieldName = "scheme")
    private List<Scheme> schemes = new ArrayList<Scheme>();
    private Origin origin;
    private Destination destination;
    @XStreamOmitField
    private TaxiInfo taxi;

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public TaxiInfo getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxiInfo taxi) {
        this.taxi = taxi;
    }

}
