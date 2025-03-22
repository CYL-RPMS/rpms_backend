package kr.co.cyberline.cmm.web.file;

import java.util.List;

public interface CylFileService {
    List<CylFileVO> selectAtchFileList(CylFileVO var1);

    CylFileVO selectAtchFileDetail(CylFileVO var1);

    int insertAtchFile(CylFileVO var1);

    int insertAtchFileDetail(CylFileVO var1);

    int updateAtchFileUseAt(CylFileVO var1);

    int updateAtchFileDetailUseAt(CylFileVO var1);

    int deleteAtchFile(CylFileVO var1);

    int deleteAtchFileDetail(String var1, int var2, boolean var3, boolean var4);

    int deleteAtchFiles(CylFileVO var1, boolean var2, boolean var3);

    int deleteAtchFiles(String var1, boolean var2, boolean var3);
}
