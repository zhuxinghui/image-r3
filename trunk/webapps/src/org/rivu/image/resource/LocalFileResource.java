package org.rivu.image.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.DocumentBuilderFactory;

import org.rivu.image.core.RivuContext;
import org.rivu.image.task.JobDetail;
import org.rivu.image.task.OutputTextFormat;
import org.rivu.image.task.Resource;
import org.rivu.image.tools.ImageUtils;
import org.rivu.image.tools.MD5;
import org.rivu.image.tools.RivuTools;

/**
 * 采集本地目录中的 图片文件
 * @author iceworld,jaddy0302,起点R3
 *
 */
public class LocalFileResource extends Resource{
	private JobDetail jobDetail ;
	private List<File> fileList ;
	private FilenameFilter filter ;
	
	public LocalFileResource(JobDetail jobDetail){
		this.jobDetail = jobDetail ;
		fileList = new ArrayList<File>() ;
		filter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				return new File(dir , name).isDirectory() || RivuContext.IMAGE_FILE.toLowerCase().indexOf(name.substring(name.lastIndexOf(".")+1).toLowerCase())>=0;
			}
		} ;
		listFile( new File(jobDetail.getSource()));
	}
	@Override
	public void close() throws Exception {
		
	}

	@Override
	public long getBytes(OutputTextFormat object) throws Exception {
		return object.getLength();
	}

	@Override
	public String getId(OutputTextFormat object) {
		return object.getId();
	}

	@Override
	public JobDetail getJob() {
		return this.jobDetail;
	}
	/**
	 * 遍历目录
	 * @param dir
	 */
	private void listFile(File dir){
		for(File file:dir.listFiles(filter)){
			fileList.add(file) ;
		}
	}
	@Override
	public OutputTextFormat getText(OutputTextFormat object) throws Exception {
		File imageFile = (File)object.getData() ;
		object.setLength((int)imageFile.length());
		object.setTitle(imageFile.getPath()) ;
		BufferedImage image = ImageIO.read(imageFile) ;
		object.getProperty().put("url", imageFile.getPath()) ;
		/**
		 * 生成缩略图
		 */
		ImageUtils.saveImageToPriview(ImageUtils.scaleImage(image, 128),RivuContext.SAVE_FILE_DIR,object.getId()) ;
		if(image!=null){
			object.getProperty().put("width", String.valueOf(image.getWidth())) ;
			object.getProperty().put("height", String.valueOf(image.getHeight())) ;
			object.setData(DocumentBuilderFactory.getFullDocumentBuilder().createDocument(image, object.getId())) ;
		}
		return object;
	}

	@Override
	public OutputTextFormat next() throws Exception {
		File imageFile = fileList.size()>0?fileList.remove(0):null ;
		String id = null;
		if(imageFile!=null){
			id = MD5.encoding(imageFile.getPath()) ;
		}
		while(imageFile!=null && imageFile.isDirectory()){
			listFile(imageFile);
			imageFile = fileList.size()>0?fileList.remove(0):null ;
		}
		return imageFile!=null?RivuTools.isAva((id = MD5.encoding(imageFile.getPath())))?new OutputTextFormat(imageFile,id):next():null;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
}
