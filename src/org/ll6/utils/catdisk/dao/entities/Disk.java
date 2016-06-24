package org.ll6.utils.catdisk.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="location")
public class Disk {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Size(min=5, max=20)
	@Column(name="VolumeID")
	private String volumeID;
	
	@Size(min=4, max=16)
	@Column(name="Serial")
	private String serial;
	
	@Column(name="CopyID")
	private Integer copyID;
	
	@Size(min=0, max=100)
	@Column(name="DescriptionTxt")
	private String descriptionTxt;
	
	@Size(min=0, max=20)
	@Column(name="LocationTxt")
	private String locationTxt;
	
	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	public Disk(String volumeID, String serial, Integer copyID, String descriptionTxt, String locationTxt,
			Timestamp createdOn) 
	{
		super();
		this.volumeID = volumeID;
		this.serial = serial;
		this.copyID = copyID;
		this.descriptionTxt = descriptionTxt;
		this.locationTxt = locationTxt;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Disk [id=" + id + ", volumeID=" + volumeID + ", serial=" + serial + ", copyID=" + copyID
				+ ", descriptionTxt=" + descriptionTxt + ", locationTxt=" + locationTxt + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVolumeID() {
		return volumeID;
	}

	public void setVolumeID(String volumeID) {
		this.volumeID = volumeID;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getCopyID() {
		return copyID;
	}

	public void setCopyID(Integer copyID) {
		this.copyID = copyID;
	}

	public String getDescriptionTxt() {
		return descriptionTxt;
	}

	public void setDescriptionTxt(String descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public String getLocationTxt() {
		return locationTxt;
	}

	public void setLocationTxt(String locationTxt) {
		this.locationTxt = locationTxt;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copyID == null) ? 0 : copyID.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((descriptionTxt == null) ? 0 : descriptionTxt.hashCode());
		result = prime * result + ((locationTxt == null) ? 0 : locationTxt.hashCode());
		result = prime * result + ((modifiedOn == null) ? 0 : modifiedOn.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((volumeID == null) ? 0 : volumeID.hashCode());
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
		Disk other = (Disk) obj;
		if (copyID == null) {
			if (other.copyID != null)
				return false;
		} else if (!copyID.equals(other.copyID))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (descriptionTxt == null) {
			if (other.descriptionTxt != null)
				return false;
		} else if (!descriptionTxt.equals(other.descriptionTxt))
			return false;
		if (locationTxt == null) {
			if (other.locationTxt != null)
				return false;
		} else if (!locationTxt.equals(other.locationTxt))
			return false;
		if (modifiedOn == null) {
			if (other.modifiedOn != null)
				return false;
		} else if (!modifiedOn.equals(other.modifiedOn))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (volumeID == null) {
			if (other.volumeID != null)
				return false;
		} else if (!volumeID.equals(other.volumeID))
			return false;
		return true;
	}
}
