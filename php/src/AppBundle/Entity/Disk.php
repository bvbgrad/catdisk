<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Disk
 *
 * @ORM\Table(name="disk")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\DiskRepository")
 */
class Disk
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="VolumeName", type="string", length=32)
     */
    private $volumeName;

    /**
     * @var string
     *
     * @ORM\Column(name="DiskSerialNum", type="string", length=16, unique=true)
     */
    private $diskSerialNum;

    /**
     * @var int
     *
     * @ORM\Column(name="DiskCopyNum", type="integer")
     */
    private $diskCopyNum;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DiskDate", type="datetime")
     */
    private $diskDate;

    /**
     * @var string
     *
     * @ORM\Column(name="DescriptionTxt", type="string", length=100, nullable=true)
     */
    private $descriptionTxt;

    /**
     * @var string
     *
     * @ORM\Column(name="LocationTxt", type="string", length=20, nullable=true)
     */
    private $locationTxt;
    /**
     * @var integer
     *
     * @ORM\Column(name="NumberFiles", type="string", length=20, nullable=true)
     */
    private $numberFiles;
    
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="CreatedOn", type="datetime")
     */
    private $createdOn;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="ModifiedOn", type="datetime")
     */
    private $modifiedOn;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set volumeName
     *
     * @param string $volumeName
     *
     * @return Disk
     */
    public function setVolumeName($volumeName)
    {
        $this->volumeName = $volumeName;

        return $this;
    }

    /**
     * Get volumeName
     *
     * @return string
     */
    public function getVolumeName()
    {
        return $this->volumeName;
    }

    /**
     * Set diskSerialNum
     *
     * @param string $diskSerialNum
     *
     * @return Disk
     */
    public function setDiskSerialNum($diskSerialNum)
    {
        $this->diskSerialNum = $diskSerialNum;

        return $this;
    }

    /**
     * Get diskSerialNum
     *
     * @return string
     */
    public function getDiskSerialNum()
    {
        return $this->diskSerialNum;
    }

    /**
     * Set diskCopyNum
     *
     * @param integer $diskCopyNum

     * @return Disk
     */
    public function setDiskCopyNum($diskCopyNum)
    {
        $this->diskCopyNum = $diskCopyNum;

        return $this;
    }

    /**
     * Get diskCopyNum
     *
     * @return int
     */
    public function getDiskCopyNum()
    {
        return $this->diskCopyNum;
    }

    /**
     * Set diskDate
     *
     * @param integer $diskDate
     *
     * @return Disk
     */
    public function setDiskDate($diskDate)
    {
        $this->diskDate = $diskDate;

        return $this;
    }

    /**
     * Get diskDate
     *
     * @return int
     */
    public function getDiskDate()
    {
        return $this->diskDate;
    }

    /**
     * Set descriptionTxt
     *
     * @param string $descriptionTxt
     *
     * @return Disk
     */
    public function setDescriptionTxt($descriptionTxt)
    {
        $this->descriptionTxt = $descriptionTxt;

        return $this;
    }

    /**
     * Get descriptionTxt
     *
     * @return string
     */
    public function getDescriptionTxt()
    {
        return $this->descriptionTxt;
    }

    /**
     * Set locationTxt
     *
     * @param string $locationTxt
     *
     * @return Disk
     */
    public function setLocationTxt($locationTxt)
    {
        $this->locationTxt = $locationTxt;

        return $this;
    }

    /**
     * Get locationTxt
     *
     * @return string
     */
    public function getLocationTxt()
    {
        return $this->locationTxt;
    }

    /**
     * Set NumberFiles
     *
     * @param integer $numberFiles
     *
     * @return Tbldisks
     */
    public function setNumberFiles($numberFiles)
    {
        $this->numberFiles = $numberFiles;

        return $this;
    }

    /**
     * Get numberFiles
     *
     * @return integer
     */
    public function getNumberFiles()
    {
        return $this->numberFiles;
    }
    
    /**
     * Set createdOn
     *
     * @param integer $createdOn
     *
     * @return Disk
     */
    public function setCreatedOn($createdOn)
    {
        $this->createdOn = $createdOn;

        return $this;
    }

    /**
     * Get createdOn
     *
     * @return int
     */
    public function getCreatedOn()
    {
        return $this->createdOn;
    }

    /**
     * Set modifiedOn
     *
     * @param integer $modifiedOn
     *
     * @return Disk
     */
    public function setModifiedOn($modifiedOn)
    {
        $this->modifiedOn = $modifiedOn;

        return $this;
    }

    /**
     * Get modifiedOn
     *
     * @return int
     */
    public function getModifiedOn()
    {
        return $this->modifiedOn;
    }
}

