package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ll6.utils.catdisk.dao.DiskDao;
import org.ll6.utils.catdisk.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.CatdiskTestConfiguration;

@ActiveProfiles("dev")
@ContextConfiguration(classes = CatdiskTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FileDataDaoTest {

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
			config.CatdiskTestConfiguration.class);
	
	private DiskDao diskDao = ctx.getBean(DiskDao.class);	
//	@Autowired
//	private DiskDao diskDao;	
	
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
