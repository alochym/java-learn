package com.github.springbootmvcdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.validator.constraints.Length;
// Validation Section
import org.hibernate.validator.constraints.Range;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "locations")
public class Location {
    @Column(length = 12, nullable = false, unique = true)
    @Id
    @NotBlank(message = "Location Code cannot be blank")
    // used for String, Collection, Map and Arrays type
    @Size(min = 1, max = 12, message = "Max Location Code is 12 character")
    private String code;

    @Column(length = 128, nullable = false)
    @JsonProperty("city_name")
    @NotBlank(message = "Location City Name cannot be blank")
    // used for String, Collection, Map and Arrays type
    @Size(min = 1, max = 128, message = "Max Location City Name is 128 character")
    private String cityName;

    @Column(length = 128, nullable = false)
    @JsonProperty("region_name")
    @NotBlank(message = "Location Region Name cannot be blank")
    // used for String, Collection, Map and Arrays type
    // @Size(min = 1, max = 128, message = "Max Location Region Name is 128 character")
    @Length(min = 1, max = 128, message = "Max Location Region Name is 128 character")
    private String regionName;

    @Column(length = 64, nullable = false)
    @JsonProperty("country_name")
    @NotBlank(message = "Location Country Name cannot be blank")
    // used for String, Collection, Map and Arrays type
    @Size(min = 1, max = 64, message = "Max Location Country Name is 64 character")
    private String countryName;

    @Column(length = 2, nullable = false)
    @JsonProperty("country_code")
    @NotBlank(message = "Location Country Code cannot be blank")
    // used for String, Collection, Map and Arrays type
    @Size(min = 2, max = 2, message = "Location Country Code should be 2 Upper case character")
    private String countryCode;

    @Column(length = 1)
    @Range(min = 0, max = 1, message = "Location Enable should be 0 or 1")
    private Integer enable;

    @Column(length = 1)
    @JsonIgnore
    @Range(min = 0, max = 1, message = "Location Trashed should be 0 or 1")
    private Integer trashed;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int isEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int isTrashed() {
        return trashed;
    }

    public void setTrashed(int trashed) {
        this.trashed = trashed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
