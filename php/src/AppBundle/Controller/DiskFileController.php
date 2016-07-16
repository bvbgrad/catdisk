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
	 * @Route("/diskfile/manage", name="diskfile_manage")
	 */
	public function diskManageAction(Request $request)
	{

		$json = file_get_contents("http://localhost:8080/catdisk/getDiskCount");
		$diskCount = json_decode($json, TRUE);
		
		$defaultData = array('start' => '1', 'end' => '0', 'page' => '25');
		$form = $this->createFormBuilder($defaultData)
	        ->add('start', IntegerType::class, [
	    			'label' => 'Disk start number: '
	    	])
	        ->add('end', IntegerType::class, [
	    			'label' => 'Disk end number: '
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
	        $end = $data['end'];
	        
	        if ($end == 0)
	        {
		        return $this->redirectToRoute("diskfile_diskinfo", array('diskID' => $start));
	        } else
	        {
		        return $this->redirectToRoute("diskfile_diskrange", array('start' => $start, 'end' => $end));
	        }
	        
	        
		}

		return $this->render('diskfile/diskmanage.html.twig', array(
            'form' => $form->createView(),
			'diskCount' => $diskCount
			
        ));		
	}

	/**
	 * @Route("/diskfile/diskfiles/{diskID}", name="diskfile_diskfiles",
	 *	defaults={"diskID" = 1})
	 * )
	 */
	public function formDiskFilesAction(Request $request, $diskID)
	{
		$json = file_get_contents
			("http://localhost:8080/catdisk/getFileCount");
		$fileCount = json_decode($json, TRUE);
		dump($fileCount);
		dump($diskID);
		
		$json = file_get_contents
	    	("http://localhost:8080/catdisk/getFiles/".$diskID);
	    $fileData = json_decode($json, TRUE);
	    if (json_last_error() === JSON_ERROR_NONE) {
	    	//do something with $json. It's ready to use
		    dump($fileData);
		    foreach ($fileData as &$a) 
		    {
		    	$a['datecreated'] = date("Y-m-d", $a['datecreated']/1000);
		    	$a['createdOn'] = date("Y-m-d", $a['createdOn']/1000);
		    	$a['modifiedOn'] = date("Y-m-d", $a['modifiedOn']/1000);
		    };
	    	unset($a);
	    	dump ($fileData);
	
			return $this->render('diskfile/diskfiles.html.twig', array(
				'diskFileCount' => 3,
				'diskID' => $diskID,
				'files' => $fileData
	        ));		
	    } else {
	    	//yep, it's not JSON. Log error or alert someone or do nothing
		    dump(json_last_error());
		    dump(json_last_error_msg());
		    
		    $msg = "<script> alert('json error: '.json_last_error_msg()) </script>";
		    echo $msg;
		    
		    return $this->render('menu/home.html.twig', array(
		    		'fileCount' => $fileCount
		    ));
	    }
	}
	
	/**
	 * @Route("/diskfile/diskrange/{start}/{end}", name="diskfile_diskrange",
	 * 		defaults={"start" = 1, "end" = 10}
	 * )
	 */
	public function formDiskRangeAction(Request $request, $start, $end)
	{
		$json = file_get_contents("http://localhost:8080/catdisk/getDiskCount");
		$diskCount = json_decode($json, TRUE);
		
		$json = file_get_contents
	    	("http://localhost:8080/catdisk/getDiskRange/".$start."/".$end);
	    $diskData = json_decode($json, TRUE);
	    if (json_last_error() === JSON_ERROR_NONE) {
	    	//do something with $json. It's ready to use
		    dump($diskData);
		    foreach ($diskData as &$a) 
		    {
		    	$a['diskDate'] = date("Y-m-d", $a['diskDate']/1000);
		    	$a['createdOn'] = date("Y-m-d", $a['createdOn']/1000);
		    	$a['modifiedOn'] = date("Y-m-d", $a['modifiedOn']/1000);
		    };
	    	unset($a);
	    	dump ($diskData);
	
			return $this->render('diskfile/diskrange.html.twig', array(
				'diskCount' => $diskCount,
				'start' => $start,
				'end' => $end,
				'count' => $end - $start + 1,
				'disks' => $diskData
	        ));		
	    } else {
	    	//yep, it's not JSON. Log error or alert someone or do nothing
		    dump(json_last_error());
		    dump(json_last_error_msg());
		    
		    $msg = "<script> alert('json error: '.json_last_error_msg()) </script>";
		    echo $msg;
		    
		    return $this->render('menu/home.html.twig', array(
		    		'diskCount' => $diskCount
		    ));
	    }
	    
	}
	
	/**
	 * @Route("/diskfile/diskinfo/{diskID}", name="diskfile_diskinfo",
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
    	
	   	return $this->render('diskfile/diskinfo.html.twig', array(
            'form' => $form->createView(),
	   		'diskID' => $diskID,
        ));

	}
}