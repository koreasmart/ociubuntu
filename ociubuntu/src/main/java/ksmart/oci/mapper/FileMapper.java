package ksmart.oci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.oci.dto.FileDto;

@Mapper
public interface FileMapper {

	public int addFile(List<FileDto> fileList); 
	
	public List<FileDto> getFileList();
	
	public FileDto getFileInfoByIdx(String fileIdx);
	
	public int deleteFileByIdx(String fileIdx);
}
