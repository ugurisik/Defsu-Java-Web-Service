package defsu.sc.maps.general;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "country", indexes = {
    @Index(name = "commonName", columnList = "commonName"),
    @Index(name = "nativeName", columnList = "nativeName"),
    @Index(name = "a2Code", columnList = "a2Code"),
    @Index(name = "a3Code", columnList = "a3Code"),
    @Index(name = "language", columnList = "language"),
    @Index(name = "status", columnList = "status"),
    @Index(name = "visible", columnList = "visible"),
    @Index(name = "visiblemulti", columnList = "visible,status,commonName,nativeName")
})
public class MapCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "commonName", nullable = false, length = 64)
    public String commonName;

    @Column(name = "nativeName", nullable = false, length = 64)
    public String nativeName;

    @Column(name = "a2Code", nullable = true, length = 2)
    public String a2Code;

    @Column(name = "a3Code", nullable = true, length = 3)
    public String a3Code;

    @Column(name = "phoneCode", nullable = true, length = 3)
    public String phoneCode;

    @Column(name = "flagIcon", nullable = true, length = 255)
    public String flagIcon;

    @Column(name = "currency", nullable = true, length = 8)
    public String currency;

    @Column(name = "language", nullable = true, length = 8)
    public String language;

    @Column(name = "timezone", nullable = true, length = 16)
    public String timezone;

    @Column(name = "capital", nullable = true, length = 64)
    public String capital;

    @Column(name = "region", nullable = true, length = 64)
    public String region;

    @Column(name = "subregion", nullable = true, length = 64)
    public String subregion;

    @Column(name = "latLong", nullable = true, length = 255)
    public String latLong;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    public Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt", nullable = false)
    public Date updatedAt;

    @Column(name = "visible", nullable = false, columnDefinition = "boolean default true")
    public boolean visible;

    @Column(name = "status", nullable = false, columnDefinition = "int default 1")
    public int status;

    @Column(name = "createdBy", nullable = false, columnDefinition = "bigint default 0")
    public Long createdBy;

    @Column(name = "updatedBy", nullable = false, columnDefinition = "bigint default 0")
    public Long updatedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getA2Code() {
        return a2Code;
    }

    public void setA2Code(String a2Code) {
        this.a2Code = a2Code;
    }

    public String getA3Code() {
        return a3Code;
    }

    public void setA3Code(String a3Code) {
        this.a3Code = a3Code;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(String flagIcon) {
        this.flagIcon = flagIcon;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
