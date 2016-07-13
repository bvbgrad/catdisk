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
@Table (name="tblfiles")
public class FileData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long ID;
	
	@Column(name="DiskID")
	private long diskID;
	
	@Column(name="VolumeName")
	private String volumeName;
	
	@Column(name="DiskCopyNum")
	private int diskCopyNum;
	
	@Column(name="FileName")	
	private String fileName;
	
	@Column(name="FilePath")	
	private String filePath;
	
	@Column(name="DateCreated")	
	private Date datecreated;
	
	@Column(name="FileLength")	
	private Integer fileLength;
	
	@Column(name="CreatedOn")	
	private Timestamp createdOn;
	
	@Column(name="ModifiedOn")	
	private Timestamp modifiedOn;

	public FileData() {
	}

	@Override
	public String toString() {
		return "File [fileID=" + ID + ", diskID=" + diskID + ", volumeName=" + volumeName + ", diskCopyNum="
				+ diskCopyNum + ", fileName=" + fileName + ", filePath=" + filePath + ", datecreated=" + datecreated
				+ ", fileLength=" + fileLength + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
	}

	public long getID() {
		return ID;
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

	public int getDiskCopyNum() {
		return diskCopyNum;
	}

	public void setDiskCopyNum(int diskCopyNum) {
		this.diskCopyNum = diskCopyNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Integer getFileLength() {
		return fileLength;
	}

	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
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
		result = prime * result + ((datecreated == null) ? 0 : datecreated.hashCode());
		result = prime * result + diskCopyNum;
		result = prime * result + (int) (diskID ^ (diskID >>> 32));
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((fileLength == null) ? 0 : fileLength.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
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
		FileData other = (FileData) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (datecreated == null) {
			if (other.datecreated != null)
				return false;
		} else if (!datecreated.equals(other.datecreated))
			return false;
		if (diskCopyNum != other.diskCopyNum)
			return false;
		if (diskID != other.diskID)
			return false;
		if (ID != other.ID)
			return false;
		if (fileLength == null) {
			if (other.fileLength != null)
				return false;
		} else if (!fileLength.equals(other.fileLength))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
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
