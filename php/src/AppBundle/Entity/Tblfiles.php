<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Tblfiles
 *
 * @ORM\Table(name="tblfiles", indexes={@ORM\Index(name="CopyID", columns={"CopyID"}), @ORM\Index(name="ID", columns={"ID"}), @ORM\Index(name="VolumeID", columns={"VolumeID"})})
 * @ORM\Entity
 */
class Tblfiles
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
     * @var integer
     *
     * @ORM\Column(name="DiskID", type="integer", nullable=false)
     */
    private $diskid;

    /**
     * @var string
     *
     * @ORM\Column(name="VolumeID", type="string", length=20, nullable=true)
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
     * @ORM\Column(name="FName", type="string", length=50, nullable=true)
     */
    private $fname;

    /**
     * @var string
     *
     * @ORM\Column(name="FPath", type="string", length=255, nullable=true)
     */
    private $fpath;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateCreated", type="datetime", nullable=false)
     */
    private $datecreated = 'CURRENT_TIMESTAMP';

    /**
     * @var integer
     *
     * @ORM\Column(name="FLen", type="integer", nullable=true)
     */
    private $flen = '0';



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
     * Set diskid
     *
     * @param integer $diskid
     *
     * @return Tblfiles
     */
    public function setDiskid($diskid)
    {
        $this->diskid = $diskid;

        return $this;
    }

    /**
     * Get diskid
     *
     * @return integer
     */
    public function getDiskid()
    {
        return $this->diskid;
    }

    /**
     * Set volumeid
     *
     * @param string $volumeid
     *
     * @return Tblfiles
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
     * @return Tblfiles
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
     * Set fname
     *
     * @param string $fname
     *
     * @return Tblfiles
     */
    public function setFname($fname)
    {
        $this->fname = $fname;

        return $this;
    }

    /**
     * Get fname
     *
     * @return string
     */
    public function getFname()
    {
        return $this->fname;
    }

    /**
     * Set fpath
     *
     * @param string $fpath
     *
     * @return Tblfiles
     */
    public function setFpath($fpath)
    {
        $this->fpath = $fpath;

        return $this;
    }

    /**
     * Get fpath
     *
     * @return string
     */
    public function getFpath()
    {
        return $this->fpath;
    }

    /**
     * Set datecreated
     *
     * @param \DateTime $datecreated
     *
     * @return Tblfiles
     */
    public function setDatecreated($datecreated)
    {
        $this->datecreated = $datecreated;

        return $this;
    }

    /**
     * Get datecreated
     *
     * @return \DateTime
     */
    public function getDatecreated()
    {
        return $this->datecreated;
    }

    /**
     * Set flen
     *
     * @param integer $flen
     *
     * @return Tblfiles
     */
    public function setFlen($flen)
    {
        $this->flen = $flen;

        return $this;
    }

    /**
     * Get flen
     *
     * @return integer
     */
    public function getFlen()
    {
        return $this->flen;
    }
}
