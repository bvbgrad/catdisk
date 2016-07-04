<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Tbldisks
 *
 * @ORM\Entity
 * @ORM\Table(name="tbldisks")
 */
class Tbldisks
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="ID", type="integer")
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="VolumeName", type="string", length=32, nullable=false)
     */
    private $volumename;

    /**
     * @var string
     *
     * @ORM\Column(name="DiskSerialNum", type="string", length=16, nullable=false)
     */
    private $diskserialnum;
    
    /**
     * @var integer
     *
     * @ORM\Column(name="DiskCopyNum", type="integer", length=4, nullable=false)
     */
    private $diskcopynum = '0';

    /**
     * @var date
     *
     * @ORM\Column(name="DiskDate", type="date")
     */
    private $diskdate;

    /**
     * @var string
     *
     * @ORM\Column(name="DescriptionTxt", type="string", length=100, nullable=true)
     */
    private $descriptiontxt;

    /**
     * @var string
     *
     * @ORM\Column(name="LocationTxt", type="string", length=20, nullable=true)
     */
    private $locationtxt;

    /**
     * @var date
     *
     * @ORM\Column(name="CreatedOn", type="date")
     */
    private $createdon;

    /**
     * @var date
     *
     * @ORM\Column(name="ModifiedOn", type="date")
     */
    private $modifiedon;



    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set volumename
     *
     * @param string $volumename
     *
     * @return Tbldisks
     */
    public function setVolumeName($volumename)
    {
        $this->volumename = $volumename;

        return $this;
    }

    /**
     * Get volumename
     *
     * @return string
     */
    public function getVolumeName()
    {
        return $this->volumename;
    }

    /**
     * Set diskserialnum
     *
     * @param string $diskserialnum
     *
     * @return Tbldisks
     */
    public function setDiskSerialNum($diskserialnum)
    {
        $this->diskserialnum = $diskserialnum;

        return $this;
    }

    /**
     * Get diskserialnum
     *
     * @return string
     */
    public function getDiskSerialNum()
    {
        return $this->diskserialnum;
    }

    /**
     * Set diskcopynum
     *
     * @param integer $diskcopynum
     *
     * @return Tbldisks
     */
    public function setDiskCopyNum($diskcopynum)
    {
        $this->diskcopynum = $diskcopynum;

        return $this;
    }

    /**
     * Get diskcopynum
     *
     * @return integer
     */
    public function getDiskCopyNum()
    {
        return $this->diskcopynum;
    }

    public function setDiskDate($diskdate)
    {
        $this->diskdate = $diskdate;

        return $this;
    }

    public function getDiskDate()
    {
        return $this->diskdate;
    }

    /**
     * Set descriptiontxt
     *
     * @param string $descriptiontxt
     *
     * @return Tbldisks
     */
    public function setDescriptionTxt($descriptiontxt)
    {
        $this->descriptiontxt = $descriptiontxt;

        return $this;
    }

    /**
     * Get descriptiontxt
     *
     * @return string
     */
    public function getDescriptionTxt()
    {
        return $this->descriptiontxt;
    }

    /**
     * Set locationtxt
     *
     * @param string $locationtxt
     *
     * @return Tbldisks
     */
    public function setLocationTxt($locationtxt)
    {
        $this->locationtxt = $locationtxt;

        return $this;
    }

    /**
     * Get locationtxt
     *
     * @return string
     */
    public function getLocationTxt()
    {
        return $this->locationtxt;
    }
    
    public function setCreatedOn($createdon)
    {
        $this->createdon = $createdon;

        return $this;
    }

    public function getCreatedOn()
    {
        return $this->createdon;
    }

    public function setModifiedOn($modifiedon)
    {
        $this->modifiedon = $modifiedon;

        return $this;
    }

    public function getModifiedOn()
    {
        return $this->modifiedon;
    }
}
