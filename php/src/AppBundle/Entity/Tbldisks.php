<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Tbldisks
 *
 * @ORM\Table(name="tbldisks", indexes={@ORM\Index(name="CopyID", columns={"CopyID"}), @ORM\Index(name="VolumeID", columns={"VolumeID"})})
 * @ORM\Entity
 */
class Tbldisks
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Serial", type="string", length=16, nullable=false)
     */
    private $serial;
    
    /**
     * @var string
     *
     * @ORM\Column(name="VolumeID", type="string", length=20, nullable=false)
     */
    private $volumeid;

    /**
     * @var integer
     *
     * @ORM\Column(name="CopyID", type="integer", length=4, nullable=false)
     */
    private $copyid = '0';

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
     * Set volumeid
     *
     * @param string $volumeid
     *
     * @return Tbldisks
     */
    public function setVolumeid($volumeid)
    {
        $this->volumeid = $volumeid;

        return $this;
    }

    /**
     * Get volumeid
     *
     * @return string
     */
    public function getVolumeid()
    {
        return $this->volumeid;
    }

    /**
     * Set serial
     *
     * @param string $serial
     *
     * @return Tbldisks
     */
    public function setSerial($serial)
    {
        $this->serial = $serial;

        return $this;
    }

    /**
     * Get serial
     *
     * @return string
     */
    public function getSerial()
    {
        return $this->serial;
    }

    /**
     * Set copyid
     *
     * @param boolean $copyid
     *
     * @return Tbldisks
     */
    public function setCopyid($copyid)
    {
        $this->copyid = $copyid;

        return $this;
    }

    /**
     * Get copyid
     *
     * @return boolean
     */
    public function getCopyid()
    {
        return $this->copyid;
    }

    /**
     * Set descriptiontxt
     *
     * @param string $descriptiontxt
     *
     * @return Tbldisks
     */
    public function setDescriptiontxt($descriptiontxt)
    {
        $this->descriptiontxt = $descriptiontxt;

        return $this;
    }

    /**
     * Get descriptiontxt
     *
     * @return string
     */
    public function getDescriptiontxt()
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
    public function setLocationtxt($locationtxt)
    {
        $this->locationtxt = $locationtxt;

        return $this;
    }

    /**
     * Get locationtxt
     *
     * @return string
     */
    public function getLocationtxt()
    {
        return $this->locationtxt;
    }
    
    /**
     * Set CreatedOn
     *
     * @param string $createdon
     *
     * @return Tbldisks
     */
    public function setCreatedOn(\DateTime $createdon = null)
    {
        $this->createdon = $createdon;

        return $this;
    }

    /**
     * Get $createdon
     *
     * @return DateTime
     */
    public function getCreatedOn()
    {
        return $this->createdon;
    }
    /**
     * Set ModifiedOn
     *
     * @param string $modifiedon
     *
     * @return Tbldisks
     */    
    public function setModifiedOn(\DateTime $modifiedon = null)
    {
        $this->modifiedon = $modifiedon;

        return $this;
    }

    /**
     * Get $modifiedon
     *
     * @return DateTime
     */
    public function getModifiedOn()
    {
        return $this->modifiedon;
    }
}
