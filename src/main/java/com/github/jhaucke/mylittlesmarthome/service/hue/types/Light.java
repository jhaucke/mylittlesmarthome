
package com.github.jhaucke.mylittlesmarthome.service.hue.types;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Generated("org.jsonschema2pojo")
public class Light {

    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("modelid")
    @Expose
    private String modelid;
    @SerializedName("manufacturername")
    @Expose
    private String manufacturername;
    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;
    @SerializedName("swversion")
    @Expose
    private String swversion;

    /**
     * 
     * @return
     *     The state
     */
    public State getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The modelid
     */
    public String getModelid() {
        return modelid;
    }

    /**
     * 
     * @param modelid
     *     The modelid
     */
    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    /**
     * 
     * @return
     *     The manufacturername
     */
    public String getManufacturername() {
        return manufacturername;
    }

    /**
     * 
     * @param manufacturername
     *     The manufacturername
     */
    public void setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername;
    }

    /**
     * 
     * @return
     *     The uniqueid
     */
    public String getUniqueid() {
        return uniqueid;
    }

    /**
     * 
     * @param uniqueid
     *     The uniqueid
     */
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    /**
     * 
     * @return
     *     The swversion
     */
    public String getSwversion() {
        return swversion;
    }

    /**
     * 
     * @param swversion
     *     The swversion
     */
    public void setSwversion(String swversion) {
        this.swversion = swversion;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(state).append(type).append(name).append(modelid).append(manufacturername).append(uniqueid).append(swversion).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Light) == false) {
            return false;
        }
        Light rhs = ((Light) other);
        return new EqualsBuilder().append(state, rhs.state).append(type, rhs.type).append(name, rhs.name).append(modelid, rhs.modelid).append(manufacturername, rhs.manufacturername).append(uniqueid, rhs.uniqueid).append(swversion, rhs.swversion).isEquals();
    }

}
