package tests;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ll6.utils.catdisk.configuration.CatdiskConfiguration;
import org.ll6.utils.catdisk.configuration.JndiDataConfig;
import org.ll6.utils.catdisk.configuration.StandaloneDataConfig;
import org.ll6.utils.catdisk.dao.DiskDao;
import org.ll6.utils.catdisk.entities.Disk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		CatdiskConfiguration.class,
        StandaloneDataConfig.class,
        JndiDataConfig.class})
@ActiveProfiles("dev")
//@WebAppConfiguration
//@ContextConfiguration(locations = {
//        "classpath:test/config/datasource.xml"})

public class FileDataDaoTest {

//	
	
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			org.ll6.utils.catdisk.configuration.CatdiskConfiguration.class);
	
	private DiskDao diskDao = ctx.getBean(DiskDao.class);	
	
	@Test
	public void testDiskCount() {
//		fail("Not yet implemented");
		System.out.println("test prod");
		long diskCount = diskDao.getDiskCount();
		System.out.println(diskCount);
	}
	@Test
	public void testGetDisk() {
//		fail("Not yet implemented");
		System.out.println("test getDisk");
    	Disk disk = diskDao.getDisk(1);
		System.out.println(disk);
	}

}
