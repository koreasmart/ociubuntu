package ksmart.oci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ksmart.oci.dto.FileDto;
import ksmart.oci.mapper.FileMapper;
import ksmart.oci.util.FileUtil;



@Service
@Transactional
public class FileService {
	
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private FileMapper fileMapper;	
	
	public void fileUpload(MultipartFile[] uploadfile) {
		
		List<FileDto> fileList= fileUtil.parseFileInfo(uploadfile);
				
		if(fileList != null) fileMapper.addFile(fileList);
		
	}
	
	public List<FileDto> getFileList(){
		List<FileDto> fileList = fileMapper.getFileList();
		
		return fileList;
	}
	
	public FileDto getFileInfoByIdx(String fileIdx) {
		return fileMapper.getFileInfoByIdx(fileIdx);
	}
	
	public void deleteFileByIdx(FileDto fileDto) {
		Boolean isDelete = fileUtil.deleteFileByIdx(fileDto);
		if(isDelete) fileMapper.deleteFileByIdx(fileDto.getFileIdx());
	}
}
