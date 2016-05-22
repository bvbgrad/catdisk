<?php
namespace AppBundle\Controller;

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
		$ls = system('ls -al');
		$now = date('l jS \of F Y h:i:s A');
		
	 	$curl = curl_init();
	    curl_setopt ($curl, CURLOPT_URL, "http://localhost:8080/catdisk/getVol");
	    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
	
	    $result = curl_exec ($curl);
	    curl_close ($curl);
	    
	    list($volumeName, $volumeSerial) = explode(":", $result);
		
		return $this->render('diskfile/home.html.twig', [
				'volume' => $result,
				'volumeName' => $volumeName,
				'volumeSerial' => $volumeSerial,
				'ls' => $ls,
				'now' => $now,
				'path' => $path,
		]);
	}
}