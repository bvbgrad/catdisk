<?php
namespace AppBundle\Controller;

use AppBundle\Entity\Tbldisks;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DiskFileController extends Controller
{
	/**
	 * @Route("/disk")
	 */
	public function showAction()
	{
		$dirPath = dir(getcwd())->path;
		$path = realpath($dirPath);
		$volumeName = $_SERVER['DOCUMENT_ROOT'];
		$now = date('l jS \of F Y h:i:s A');
		
	 	$curl = curl_init();
	    curl_setopt ($curl, CURLOPT_URL, "http://localhost:8080/catdisk/getVol");
	    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
	
	    $result = curl_exec ($curl);
	    curl_close ($curl);
	    
	    list($volumeName, $volumeSerial) = explode(":", $result);
	    
	    $disk = new Tbldisks();
	    $disk->setVolumeid($volumeName);
	    $disk->setCopyid($volumeSerial);
	    $disk->setDescriptiontxt('new disk');
	    $disk->setCreatedOn(new \DateTime('today'));
	    
	    $form = $this->createFormBuilder($disk)
	    	->add('volumeid', TextType::class)
	    	->add('copyid', TextType::class)
	    	->add('descriptiontxt', TextType::class)
	    	->add('createdon', DateType::class)
	    	->add('save', SubmitType::class, array('label' => 'Create Disk'))
	    	->getForm();
	    	
	   	return $this->render('diskfile/disk.html.twig', array(
            'form' => $form->createView(),
   			'volume' => $result,
   			'volumeName' => $volumeName,
   			'volumeSerial' => $volumeSerial,
   			'now' => $now,
   			'path' => $path,
        ));

	}
}