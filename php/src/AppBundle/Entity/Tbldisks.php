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
     * @ORM\Column(name="VolumeID", type="string", length=20, nullable=false)
     */
    private $volumeid;

    /**
     * @var boolean
     *
     * @ORM\Column(name="CopyID", type="boolean", nullable=false)
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
     * @ORM\Column(name="CreatedOn", type="DateTime")
     */
    private $createdon;



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
}
