<?php
namespace AppBundle\Controller;

use AppBundle\Entity\Tbldisks;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DiskFileController extends Controller
{
	/**
	 * @Route("/disk")
	 */
	public function formDiskInfoAction(Request $request)
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
	    $disk->setSerial($volumeSerial);
	    $disk->setCopyid(0);
	    $disk->setDescriptiontxt('new disk');
	    $disk->setCreatedOn(new \DateTime('today'));
	    $disk->setModifiedOn(new \DateTime('today'));
	    
	    $form = $this->createFormBuilder($disk)
	    	->add('volumeid', TextType::class, [
	    			'label' => 'Volume Name: ',
	    			'trim' => 'true'
	    	])
	    	->add('serial', TextType::class, [
	    			'label' => 'Serial No: ',
	    			'trim' => 'true'
	    	])
	    	->add('copyid', TextType::class, [
	    			'label' => 'Disk Copy No: '
	    	])
	    	->add('descriptiontxt', TextType::class, [
	    			'label' => 'Contents: '
	    	])
	    	->add('createdon', DateType::class, [
	    			'label' => 'Disk Creation date: ',
	    			'widget' => 'choice',
	    			'years' => range(2000, 2020),
	    	])
	    	->add('modifiedon', DateType::class, [
	    			'label' => 'Disk data modified date: ',
	    			'widget' => 'single_text',
	    			'disabled' => 'true'
	    	])
	    	->add('save', SubmitType::class, [
    			'label' => 'Scan Disk',
    			'attr' => [
    				'class' => 'btn btn-success'
    			]
	    	])
	    	->getForm();
    	
	    $form->handleRequest($request);
	    
    	if ($form->isSubmitted() && $form->isValid()) {
    		// ... perform some action, such as saving the task to the database
    		dump($form->getData());
    		
//     		return $this->redirectToRoute('task_success');
    	}
    	
	    	
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