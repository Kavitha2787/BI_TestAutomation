package BI.automation.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import BI.automation.common.BILogger;

public class ZipUtils
{

	private List<String> fileList;
	private String OUTPUT_ZIP_FILE;
	private String SOURCE_FOLDER; // SourceFolder path

	public ZipUtils(String sourcefolder,String targetzipfile)
	{
		fileList = new ArrayList<String>();
		SOURCE_FOLDER=sourcefolder;
		OUTPUT_ZIP_FILE=targetzipfile;

	}

	public static void main(String[] args)
	{
		ZipUtils appZip = new ZipUtils("C:\\JavaPrograms\\DSSWebPublisher1\\outputfolder\\BILaunchPadDirectURLHomePageTest_20160217142654","C:\\JavaPrograms\\DSSWebPublisher1\\outputfolder\\BILaunchPadDirectURLHomePageTest_20160217142654\\BILaunchPadDirectURLHomePageTest_20160217142654.zip");
		appZip.generateFileList(new File("C:\\JavaPrograms\\DSSWebPublisher1\\outputfolder\\BILaunchPadDirectURLHomePageTest_20160217142654"));
		appZip.zipIt("C:\\JavaPrograms\\DSSWebPublisher1\\outputfolder\\BILaunchPadDirectURLHomePageTest_20160217142654\\BILaunchPadDirectURLHomePageTest_20160217142654.zip");
	}

	public void zipIt(String zipFile)
	{


		byte[] buffer = new byte[1024];
		String source = "";
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try
		{
			try
			{
				source = SOURCE_FOLDER.substring(SOURCE_FOLDER.lastIndexOf("\\") + 1, SOURCE_FOLDER.length());
			}
			catch (Exception e)
			{
				source = SOURCE_FOLDER;
			}
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			BILogger.writeInfoToLog(this.getClass(),"Output to Zip : " + zipFile);
			FileInputStream in = null;

			for (String file : this.fileList)
			{
				BILogger.writeInfoToLog(this.getClass(),"File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try
				{
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0)
					{
						zos.write(buffer, 0, len);
					}
				}
				finally
				{
					in.close();
				}
			}

			zos.closeEntry();
			BILogger.writeInfoToLog(this.getClass(),"Folder successfully compressed");

		}
		catch (IOException ex)
		{
			BILogger.writeInfoToLog(this.getClass(),ex);
		}
		finally
		{
			try
			{
				zos.close();
			}
			catch (IOException e)
			{
				BILogger.writeInfoToLog(this.getClass(),e);
			}
		}
	}

	public void generateFileList(File node)
	{

		// add file only
		if (node.isFile())
		{
			fileList.add(generateZipEntry(node.toString()));

		}

		if (node.isDirectory())
		{
			String[] subNote = node.list();
			for (String filename : subNote)
			{
				generateFileList(new File(node, filename));

			}
		}
	}

	private String generateZipEntry(String file)
	{
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}    


