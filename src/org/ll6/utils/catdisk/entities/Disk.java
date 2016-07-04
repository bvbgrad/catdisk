package org.ll6.utils.catdisk.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="TBLDISKS")
public class Disk {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long diskID;
	
	@Column(name="VolumeName")
	private String volumeName;
	

	@Column(name="DiskSerialNum")
	private String diskSerialNum;
	
	@Column(name="DiskCopyNum")
	private int diskCopyNum;
	
	@Column(name="DiskDate")	
	private Date diskDate;
	
	@Column(name="DescriptionTxt")	
	private String descriptionTxt;
	
	@Column(name="LocationTxt")	
	private String locationTxt;
	
	@Column(name="CreatedOn")	
	private Timestamp createdOn;
	
	@Column(name="ModifiedOn")	
	private Timestamp modifiedOn;

	@Override
	public String toString() {
		return "Disk [diskID=" + diskID + ", volumeName=" + volumeName + ", diskSerialNum=" + diskSerialNum
				+ ", diskCopyNum=" + diskCopyNum + ", diskDate=" + diskDate + ", descriptionTxt=" + descriptionTxt
				+ ", locationTxt=" + locationTxt + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
	}

	public long getDiskID() {
		return diskID;
	}

	public void setDiskID(long diskID) {
		this.diskID = diskID;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public String getDiskSerialNum() {
		return diskSerialNum;
	}

	public void setDiskSerialNum(String diskSerialNum) {
		this.diskSerialNum = diskSerialNum;
	}

	public int getDiskCopyNum() {
		return diskCopyNum;
	}

	public void setDiskCopyNum(int diskCopyNum) {
		this.diskCopyNum = diskCopyNum;
	}

	public Date getDiskDate() {
		return diskDate;
	}

	public void setDiskDate(Date diskDate) {
		this.diskDate = diskDate;
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
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((descriptionTxt == null) ? 0 : descriptionTxt.hashCode());
		result = prime * result + diskCopyNum;
		result = prime * result + ((diskDate == null) ? 0 : diskDate.hashCode());
		result = prime * result + (int) (diskID ^ (diskID >>> 32));
		result = prime * result + ((diskSerialNum == null) ? 0 : diskSerialNum.hashCode());
		result = prime * result + ((locationTxt == null) ? 0 : locationTxt.hashCode());
		result = prime * result + ((modifiedOn == null) ? 0 : modifiedOn.hashCode());
		result = prime * result + ((volumeName == null) ? 0 : volumeName.hashCode());
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
		if (diskCopyNum != other.diskCopyNum)
			return false;
		if (diskDate == null) {
			if (other.diskDate != null)
				return false;
		} else if (!diskDate.equals(other.diskDate))
			return false;
		if (diskID != other.diskID)
			return false;
		if (diskSerialNum == null) {
			if (other.diskSerialNum != null)
				return false;
		} else if (!diskSerialNum.equals(other.diskSerialNum))
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
		if (volumeName == null) {
			if (other.volumeName != null)
				return false;
		} else if (!volumeName.equals(other.volumeName))
			return false;
		return true;
	}

}
