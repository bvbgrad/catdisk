package org.ll6.utils.catdisk.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disk")
public class Disk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diskId")
	private Long diskID;
	
	@Column(name = "volumeID")
	private String volumeID;
	
	@Column(name = "copyID")
	private Integer copyID;
	
	@Column(name = "locationID")
	private Long locationID;
	
	@Column(name = "description")
	private String diskDescription;

}
