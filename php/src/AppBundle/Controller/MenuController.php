<?php
namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class MenuController extends Controller
{
	/**
	 * @Route("/", name = "home")
	 */
	public function homeAction()
	{
		$json = file_get_contents("http://localhost:8080/catdisk/getDiskCount");
		$diskCount = json_decode($json, TRUE);
		if (json_last_error() === JSON_ERROR_NONE) {
			//do something with $json. It's ready to use
			dump($diskCount);
		} else {
			//yep, it's not JSON. Log error or alert someone or do nothing
			dump(json_last_error());
		}
		
		$json = file_get_contents
		("http://localhost:8080/catdisk/getFileCount");
		$fileCount = json_decode($json, TRUE);
		
		return $this->render('menu/home.html.twig', array(
	   		'diskCount' => $diskCount,
			'fileCount' => $fileCount,
			'base_dir' => realpath($this->container->getParameter('kernel.root_dir').'/..'),
        ));
	}
	
	/**
	 * @Route("/menu/mainmenu", name = "mainmenu")
	 */
	public function mainMenuAction()
	{
		return $this->render('menu/mainmenu.html.twig');
	}
	
	/**
	 * @Route("/menu/create", name = "menu_create")
	 */
	public function createAction(Request $request)
	{
		return $this->render('menu/create.html.twig');
	}
	/**
	 * @Route("/menu/edit/{id}", name = "menu_edit")
	 */
	public function editAction($id, Request $request)
	{
		return $this->render('menu/edit.html.twig');
	}
	/**
	 * @Route("/menu/details/{id}", name = "menu_details")
	 */
	public function detailsAction($id)
	{
		return $this->render('menu/details.html.twig');
	}
}