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
	 * @Route("/diskfile/manage" 
	 * )
	 */
	public function diskManageAction(Request $request)
	{

		$json = file_get_contents("http://localhost:8080/catdisk/getDiskCount");
		$diskCount = json_decode($json, TRUE);
		
		$defaultData = array('start' => '1', 'stop' => '0', 'page' => '25');
		$form = $this->createFormBuilder($defaultData)
	        ->add('start', IntegerType::class, [
	    			'label' => 'Disk start number: '
	    	])
	        ->add('stop', IntegerType::class, [
	    			'label' => 'Disk stop number: '
	    	])
// 	        ->add('page', IntegerType::class, [
// 	    			'label' => 'Number per page: ',
// 	    	])
	        ->add('save', SubmitType::class, 
	        	[
	        		'label' => 'Get Disk Information',
	        		'attr' => [
	        			'class' => 'btn btn-success'
	        		]
	        	]
	        )
	        ->getForm();
	
	    $form->handleRequest($request);
	
	    if ($form->isValid() && $form->isSubmitted()) {
	        $data = $form->getData();
	        
	        $start = $data['start'];
	        $stop = $data['stop'];
	        
	        if ($stop == 0)
	        {
		        return $this->redirectToRoute("disk", array('diskID' => $start));
	        } else
	        {
		        return $this->redirectToRoute("diskrange", array('start' => $start, 'stop' => $stop));
	        }
	        
	        
		}

		return $this->render('diskfile/diskmanage.html.twig', array(
            'form' => $form->createView(),
			'diskCount' => $diskCount
			
        ));		
	}

	/**
	 * @Route("/diskrange/{start}/{stop}", name="diskrange",
	 * 		defaults={"start" = 1, "stop" = 10}
	 * )
	 */
	public function formDiskListRangeAction(Request $request, $start, $stop)
	{
		$json = file_get_contents("http://localhost:8080/catdisk/getDiskCount");
		$diskCount = json_decode($json, TRUE);
		
		$json = file_get_contents
	    	("http://localhost:8080/catdisk/getDiskRange/".$start."/".$stop);
	    $diskData = json_decode($json, TRUE);
	    if (json_last_error() === JSON_ERROR_NONE) {
	    	//do something with $json. It's ready to use
		    dump($diskData);
	    } else {
	    	//yep, it's not JSON. Log error or alert someone or do nothing
		    dump(json_last_error());
	    }

		return $this->render('diskfile/diskrange.html.twig', array(
			'diskCount' => $diskCount,
			'start' => $start,
			'end' => $stop,
			'count' => $stop - $start +1,
			'disks' => $diskData
        ));		
	}
	
	/**
	 * @Route("/disk/{diskID}", name="disk",
	 * 		defaults={"diskID" = 1}
	 * )
	 */
	public function formDiskInfoAction(Request $request, $diskID)
	{
	    $json = file_get_contents("http://localhost:8080/catdisk/getDisk/".$diskID);
	    $diskData = json_decode($json, TRUE);
	    if (json_last_error() === JSON_ERROR_NONE) {
	    	//do something with $json. It's ready to use
		    dump($diskData);
	    } else {
	    	//yep, it's not JSON. Log error or alert someone or do nothing
		    dump(json_last_error());
	    }

	    $disk = new Disk();
	    $disk->setVolumeName($diskData['volumeName']);
	    $diskData['diskSerialNum'] === ""? 
	    	$disk->setDiskSerialNum('xxxx-xxxx'):
		    $disk->setDiskSerialNum($diskData['diskSerialNum']);
	    $diskData['diskCopyNum'] === Null? 
	    	$disk->setDiskCopyNum(0):
		    $disk->setDiskCopyNum($diskData['diskCopyNum']);
	    $diskData['diskDate'] === Null? 
	    	$disk->setDiskDate(time()):
	    	$disk->setDiskDate($diskData['diskDate']/1000);
	    $diskData['descriptionTxt'] === Null? 
	    	$disk->setDescriptionTxt('new disk'):
	    	$disk->setDescriptionTxt($diskData['descriptionTxt']);
	    $diskData['locationTxt'] === Null? 
	    	$disk->setLocationTxt('TBD'):
	    	$disk->setLocationTxt($diskData['locationTxt']);
	    $diskData['createdOn'] === Null? 
		    $disk->setCreatedOn(time()):
	    	$disk->setCreatedOn($diskData['createdOn']/1000);
	    $diskData['modifiedOn'] === Null? 
		    $disk->setModifiedOn(time()):
	    	$disk->setModifiedOn($diskData['modifiedOn']/1000);
	    
	    dump($disk);
	    
	    $form = $this->createFormBuilder($disk)
	    	->add('volumename', TextType::class, [
	    			'label' => 'Volume Name: '
	    	])
	    	->add('diskserialnum', TextType::class, [
	    			'label' => 'Serial No: '
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
    			'label' => 'Update Disk Information',
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
	   		'diskID' => $diskID,
        ));

	}
}