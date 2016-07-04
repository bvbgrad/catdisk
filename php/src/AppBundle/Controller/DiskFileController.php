<?php
namespace AppBundle\Controller;

use AppBundle\Entity\Disk;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
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
		
	    $format = 'l jS \of F Y h:i:s A';
		$now = date($format);
		$dateTS = time();
		dump($dateTS);
		
	    $json = file_get_contents("http://localhost:8080/catdisk/getDisk/299");
	    $diskData = json_decode($json, TRUE);
	    if (json_last_error() === JSON_ERROR_NONE) {
	    	//do something with $json. It's ready to use
		    dump($diskData);
	    } else {
	    	//yep, it's not JSON. Log error or alert someone or do nothing
		    dump(json_last_error());
	    }
	    
	    $volumeName = $diskData['volumeName'];
	    $volumeSerial = $diskData['diskSerialNum'];
	    
// 	    $date = date($diskData['diskDate']/1000);
// 	    $date = new DateTime(date("Y-m-d", $diskData['diskDate']/1000));
// 		$date = new DateTime('2000-01-01');


	    $date = date('Y-m-d', $diskData['diskDate']/1000);
	    dump($date);
	    
	    $disk = new Disk();
	    $disk->setVolumeName($diskData['volumeName']);
	    $disk->setDiskSerialNum($diskData['diskSerialNum']);
	    $disk->setDiskCopyNum($diskData['diskCopyNum']);
	    $diskData['diskDate'] === Null? 
	    	$disk->setDiskDate($dateTS):
	    	$disk->setDiskDate($diskData['diskDate']/1000);
	    $diskData['descriptionTxt'] === Null? 
	    	$disk->setDescriptionTxt('new disk'):
	    	$disk->setDescriptionTxt($diskData['descriptionTxt']);
	    $diskData['locationTxt'] === Null? 
	    	$disk->setLocationTxt('TBD'):
	    	$disk->setLocationTxt($diskData['locationTxt']);
	    $diskData['createdOn'] === Null? 
		    $disk->setCreatedOn($dateTS):
	    	$disk->setCreatedOn($diskData['createdOn']/1000);
	    $diskData['modifiedOn'] === Null? 
		    $disk->setModifiedOn($dateTS):
	    	$disk->setModifiedOn($diskData['modifiedOn']/1000);
	    
	    dump($disk);
	    
	    $form = $this->createFormBuilder($disk)
	    	->add('volumename', TextType::class, [
	    			'label' => 'Volume Name: ',
	    			'trim' => 'true'
	    	])
	    	->add('diskserialnum', TextType::class, [
	    			'label' => 'Serial No: ',
	    			'trim' => 'true'
	    	])
	    	->add('diskcopynum', IntegerType::class, [
	    			'label' => 'Disk Copy No: '
	    	])
	    	->add('diskdate', DateType::class, [
	    			'input' => 'timestamp',
	    			'label' => 'Disk Creation date: ',
	    			'widget' => 'choice',
	    			'years' => range(2000, 2020),
	    	])
	    	->add('descriptiontxt', TextType::class, [
	    			'label' => 'Contents: '
	    	])
	    	->add('locationtxt', TextType::class, [
	    			'label' => 'Location: '
	    	])
	    	->add('createdon', DateType::class, [
	    			'input' => 'timestamp',
	    			'label' => 'Disk record entry date: ',
	    			'widget' => 'single_text',
	    			'disabled' => 'true'
	    	])
	    	->add('modifiedon', DateType::class, [
	    			'input' => 'timestamp',
	    			'label' => 'Disk record modified date: ',
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
   			'volume' => $volumeName,
   			'volumeName' => $volumeName,
   			'volumeSerial' => $volumeSerial,
   			'now' => $now,
   			'path' => $path,
        ));

	}
}