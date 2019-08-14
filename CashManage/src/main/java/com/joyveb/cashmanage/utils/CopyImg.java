package com.joyveb.cashmanage.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.web.ApplicationStarter;
import com.joyveb.cashmanage.web.InitParm;
@Slf4j
@Component("copyRequestor")
public class CopyImg {
	private @Resource InitParm initParm;
	public void initCopy() {
		//需要拷贝的文件或文件夹路径
		String fromDir=initParm.getStringDbp("game.pic.disk", "/home/javaops/pic");
		File fromFile =new File(fromDir); 
		//目标路径
		String toDirString =ApplicationStarter.class.getResource("").getPath();
		if (toDirString.contains("WEB-INF")) {
			int port1=toDirString.toString().lastIndexOf("/WEB-INF/classes/");
			int port2=toDirString.toString().lastIndexOf("\\WEB-INF\\classes\\");
			int port=Math.max(port1, port2);
			toDirString=toDirString.substring(0,port+1);	
		}else {
			int port1=toDirString.toString().lastIndexOf("/classes/");
			int port2=toDirString.toString().lastIndexOf("\\classes\\");
			int port=Math.max(port1, port2);
			toDirString=toDirString.substring(0,port+1);
		}
		File file = new File(toDirString,File.separator+"upload"+File.separator+"gamesImage");
		if (!fromFile.exists()) {
			fromFile.mkdirs();
		}
		if (fromFile.exists()) {
			try {
				copy(fromFile,file);
			} catch (Exception e) {
				log.warn("copy file error",e);
			}
		}
	}
	public void copy(File from,File to) {  
        //获得复制文件下下所有文件  
        File []fs=from.listFiles();  
        //遍历文件  
        if (fs.length==0) {
		}else {
        for(int i=0;i<fs.length ;i++){  
            //如果是一个目录  
            if(fs[i].isDirectory()){  
                //获得目录的名字  
                String dirname=fs[i].getName();  
                //创建要生成目录的绝对路径  
                String dirpath=to+File.separator+dirname;    
                File f=new File(dirpath);  
                //创建一个目录  
                f.mkdir();  
                //调用复制文件夹方法  
                copy(fs[i],f);  
            }else{  
                //获得文件名  
                String filename=fs[i].getName();  
                //获得的文件的绝对路径  
                String filepath=to+File.separator+filename; 
                if(!to.exists()){
                	to.mkdirs();
                }
                //建好文件；  
                
                File f=new File(filepath);  
                
                if(f.exists()){
              	  continue;
                }
                //复制文件内容方法  
                try {
					copyFile(fs[i],f);
				} catch (Exception e) {
					log.debug("CopyImg copy 异常 ",e);
				e.getStackTrace();
				} //将原文件的内容复制到新文件里来；  
            }  
        }  
		}
    }  
      
    /** 
     *  //文件复制的方法 
     * @param from 要复制的文件 
     * @param to //复制到的文件 
     * @throws IOException 
     * @throws Exception 
     */  
    public  void copyFile(File from ,File to) throws IOException {  
    	   //构建一个文件输入流对象  
        FileInputStream fin=null; 
        FileOutputStream fout=null; 
        //缓冲输入流  
        BufferedInputStream bin=null;  
        //缓存输出流  
        BufferedOutputStream bout=null;         
        //定义个字节数组，作为输入流和输出流的中介  
        byte [] b=new byte[2048];  
          
        //读入的字节长度如果为-1,说明没有内容了  
        int len;
		try {
			   //构建一个文件输入流对象  
	       fin=new FileInputStream(from);  
	        //构建以个文件输出流对象  
	       fout=new FileOutputStream(to);  
	        //缓冲输入流  
	       bin=new BufferedInputStream(fin);  
	        //缓存输出流  
	       bout=new BufferedOutputStream(fout);  
			len = bin.read(b);
			while(len !=-1){  
		            //将字节数组写入输出流中  
		            bout.write(b,0,len);  
		            len=bin.read(b);  
		        } 
			} catch (IOException e) {
				log.debug("CopyImg copyFile 异常 ",e);
				e.getStackTrace();			
				}finally{
			 //关闭流，注意顺序  
					if (bout!=null) {
						bout.close();
					}
					if (fout!=null) {
						fout.close();
					}
					if (bin!=null) {
						bin.close();
					}
					if (fin!=null) {
						fin.close();
					}
		}
        
          
        
    }  
}
