
package com.github.jhaucke.mylittlesmarthome.service.hue.types;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Generated("org.jsonschema2pojo")
public class State {

    @SerializedName("on")
    @Expose
    private Boolean on;
    @SerializedName("bri")
    @Expose
    private Integer bri;
    @SerializedName("hue")
    @Expose
    private Integer hue;
    @SerializedName("sat")
    @Expose
    private Integer sat;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("xy")
    @Expose
    private List<Double> xy = new ArrayList<Double>();
    @SerializedName("ct")
    @Expose
    private Integer ct;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("colormode")
    @Expose
    private String colormode;
    @SerializedName("reachable")
    @Expose
    private Boolean reachable;

    /**
     * 
     * @return
     *     The on
     */
    public Boolean getOn() {
        return on;
    }

    /**
     * 
     * @param on
     *     The on
     */
    public void setOn(Boolean on) {
        this.on = on;
    }

    /**
     * 
     * @return
     *     The bri
     */
    public Integer getBri() {
        return bri;
    }

    /**
     * 
     * @param bri
     *     The bri
     */
    public void setBri(Integer bri) {
        this.bri = bri;
    }

    /**
     * 
     * @return
     *     The hue
     */
    public Integer getHue() {
        return hue;
    }

    /**
     * 
     * @param hue
     *     The hue
     */
    public void setHue(Integer hue) {
        this.hue = hue;
    }

    /**
     * 
     * @return
     *     The sat
     */
    public Integer getSat() {
        return sat;
    }

    /**
     * 
     * @param sat
     *     The sat
     */
    public void setSat(Integer sat) {
        this.sat = sat;
    }

    /**
     * 
     * @return
     *     The effect
     */
    public String getEffect() {
        return effect;
    }

    /**
     * 
     * @param effect
     *     The effect
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }

    /**
     * 
     * @return
     *     The xy
     */
    public List<Double> getXy() {
        return xy;
    }

    /**
     * 
     * @param xy
     *     The xy
     */
    public void setXy(List<Double> xy) {
        this.xy = xy;
    }

    /**
     * 
     * @return
     *     The ct
     */
    public Integer getCt() {
        return ct;
    }

    /**
     * 
     * @param ct
     *     The ct
     */
    public void setCt(Integer ct) {
        this.ct = ct;
    }

    /**
     * 
     * @return
     *     The alert
     */
    public String getAlert() {
        return alert;
    }

    /**
     * 
     * @param alert
     *     The alert
     */
    public void setAlert(String alert) {
        this.alert = alert;
    }

    /**
     * 
     * @return
     *     The colormode
     */
    public String getColormode() {
        return colormode;
    }

    /**
     * 
     * @param colormode
     *     The colormode
     */
    public void setColormode(String colormode) {
        this.colormode = colormode;
    }

    /**
     * 
     * @return
     *     The reachable
     */
    public Boolean getReachable() {
        return reachable;
    }

    /**
     * 
     * @param reachable
     *     The reachable
     */
    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(on).append(bri).append(hue).append(sat).append(effect).append(xy).append(ct).append(alert).append(colormode).append(reachable).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof State) == false) {
            return false;
        }
        State rhs = ((State) other);
        return new EqualsBuilder().append(on, rhs.on).append(bri, rhs.bri).append(hue, rhs.hue).append(sat, rhs.sat).append(effect, rhs.effect).append(xy, rhs.xy).append(ct, rhs.ct).append(alert, rhs.alert).append(colormode, rhs.colormode).append(reachable, rhs.reachable).isEquals();
    }

}
