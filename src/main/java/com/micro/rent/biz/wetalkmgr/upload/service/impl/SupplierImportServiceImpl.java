package com.micro.rent.biz.wetalkmgr.upload.service.impl;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.wetalkmgr.upload.service.SupplierImportService;
import com.micro.rent.common.comm.ImportExecl;
import com.micro.rent.common.comm.RWzipUtils;
import com.micro.rent.common.comm.StringUtil;
import com.micro.rent.common.exceptions.BizException;
import com.micro.rent.common.support.Identities;
import com.micro.rent.dbaccess.dao.myrent.*;
import com.micro.rent.dbaccess.entity.myrent.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class SupplierImportServiceImpl implements SupplierImportService {
    protected Logger log = LoggerFactory
            .getLogger(SupplierImportServiceImpl.class);
    @Autowired
    private TprojectDao tprojectDao;
    @Autowired
    private HouseInfoDao houseInfoDao;
    @Autowired
    private ThouseTrafficDao thouseTrafficDao;
    @Autowired
    private TprojectPicDao tprojectPicDao;
    @Autowired
    private ThousePicDao thousePicDao;
    @Autowired
    private MapService baiduMapService;
    @Value("${unZipPath}")
    private String unZipPath;

    /**
     * 导入房源
     */
    @Override
    @Transactional
    public void importSupplier(String serverFilePath) {
        ImportExecl importExecl = new ImportExecl();
        Map<String, List<List<String>>> houseSourceMap = importExecl
                .read(serverFilePath);
        List<List<String>> projectlist = houseSourceMap.get("project");
        List<List<String>> houselist = houseSourceMap.get("house");
        List<List<String>> houseTrafficList = houseSourceMap.get("houseTraffic");
        try {
            //项目入库
            for (int i = 1; i < projectlist.size(); i++) {
                TProject project = new TProject();
                List<String> tPro = projectlist.get(i);
                project.settProjectId(Identities.create32LenUUID());
                project.setProjectId(tPro.get(0));
                project.setBrandName(tPro.get(1));
                project.setProjectDesc(tPro.get(2));
                project.setCompanyDesc(tPro.get(3));
                MapPoint mp = baiduMapService.getPoint(tPro.get(9), "北京");
                project.setLongitude(mp.getLng());
                project.setLatitude(mp.getLat());
                project.setProvinceId(tPro.get(6));
                project.setCityCode(tPro.get(7));
                project.setDistrictCode(tPro.get(8));
                project.setStreet(tPro.get(9));
                project.setDoorplate(tPro.get(10));
                project.setCommunityName(tPro.get(11));
                project.setConstructionDate(tPro.get(12));
                project.setQuantity(Integer.valueOf(tPro.get(13)));
                project.setCategory(tPro.get(14));
                if (StringUtils.isNotBlank(tPro.get(15))) {
                    project.setTotalFloor(new BigDecimal(tPro.get(15)));
                }
                project.setElevatorQuantity(Integer.valueOf(tPro.get(16)));
                tprojectDao.batchSave(project);// 待优化，优化成baseDao批处理
            }
            //房源入库
            for (int j = 1; j < houselist.size(); j++) {
                HouseInfo houseInfo = new HouseInfo();
                List<String> hou = houselist.get(j);
                houseInfo.settHouseId(Identities.create32LenUUID());
                houseInfo.setHouseId(hou.get(0));     // hou.get()
                houseInfo.setProjectId(hou.get(1));
                houseInfo.setStatus(hou.get(2));
                houseInfo.setRentBeginDate(hou.get(3));
                houseInfo.setRentEndDate(hou.get(4));
                houseInfo.setRentAvailPeriod(hou.get(5));
                houseInfo.setProvinceId(hou.get(6));
                houseInfo.setCityCode(hou.get(7));
                houseInfo.setDistrictCode(hou.get(8));
                houseInfo.setCommunityName(hou.get(9));
                houseInfo.setDoorplate(hou.get(10));
                houseInfo.setBuildingNo(hou.get(11));
                houseInfo.setRoomNumber(hou.get(12));
                houseInfo.setUseArea(new BigDecimal(hou.get(13)));
                houseInfo.setLayout(hou.get(14));
                houseInfo.setHallQuantity(Integer.valueOf(hou.get(15)));
                houseInfo.setToiletQuantity(Integer.valueOf(hou.get(16)));
                houseInfo.setKitchenQuantity(Integer.valueOf(hou.get(17)));
                houseInfo.setFloor(new BigDecimal(hou.get(18)));
                houseInfo.setTotalFloor(new BigDecimal(hou.get(19)));
                houseInfo.setOrientation(hou.get(20));
                houseInfo.setArea(new BigDecimal(hou.get(21)));
                houseInfo.setLongPrice(new BigDecimal(hou.get(22)));
                houseInfo.setShortPrice(new BigDecimal(hou.get(23)));
                houseInfo.setPaymentType(hou.get(24));
                houseInfo.setRenovation(hou.get(25));
                houseInfo.setFurniture(hou.get(26));
                houseInfo.setAppliances(hou.get(27));
                houseInfo.setTv(hou.get(28));
                houseInfo.setInternet(hou.get(29));
                houseInfo.setWaterPrice(new BigDecimal(hou.get(30)));
                houseInfo.setElectricPrice(new BigDecimal(hou.get(31)));
                houseInfo.setWarmPrice(new BigDecimal(hou.get(32)));
                houseInfo.setRefrigerationPrice(new BigDecimal(hou.get(33)));
                houseInfo.setReleaseTime(StringUtil.StringToDate(hou.get(34), "yyyyMMdd"));
                houseInfo.setRentalType(hou.get(35));
                houseInfo.setTelephone(hou.get(36));
                houseInfoDao.batchSave(houseInfo);// 待优化，优化成baseDao批处理
            }

            for (int k = 1; k < houseTrafficList.size(); k++) {
                ThouseTraffic tra = new ThouseTraffic();
                List<String> temp = houseTrafficList.get(k);
                tra.settHouseTrafficId(Identities.create32LenUUID());
                tra.setProjectId(temp.get(0));
                tra.setTrafficType(temp.get(1));
                tra.setTrafficName(temp.get(2));
                tra.setTrafficStation(temp.get(3));
                tra.setDistance(new BigDecimal(temp.get(4)));
                thouseTrafficDao.batchSave(tra);
            }
        } catch (BizException e) {
            e.printStackTrace();
        }

    }

    public void importSupplierPic(String serverFilePath) {
        this.zipFileRead(serverFilePath, unZipPath);//解压 到 unZipPath下
    }

    /**
     * 1.解压 到 unZipPath下
     *
     * @param file
     * @param saveRootDirectory
     * @author wff
     */
    public void zipFileRead(String file, String saveRootDirectory) {
        try {
            ZipFile zipFile = new ZipFile(file);
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
            String fileSeparator = System.getProperty("file.separator");
            String newFilePath = saveRootDirectory + fileSeparator + DateTime.now().toString("yyyyMMdd") + fileSeparator + DateTime.now().toString("yyyyMMddHHmmss");
            String imagePath = DateTime.now().toString("yyyyMMdd") + "/" + DateTime.now().toString("yyyyMMddHHmmss");
            while (enu.hasMoreElements()) {
                ZipEntry zipElement = (ZipEntry) enu.nextElement();
                InputStream read = zipFile.getInputStream(zipElement);
                String fileName = zipElement.getName();
                if (fileName != null && fileName.indexOf(".") != -1) {//是否为文件 （文件带有路径如：/images/a.jpg）
                    RWzipUtils.execute(zipElement, read, newFilePath);
                    String pathPic = imagePath + fileName;
                    String imageName = fileName.substring(8);
                    this.picToStorage(pathPic, imageName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.解析图片  把路径入库
     *
     * @param path
     * @throws Exception
     * @author wff
     */
    public void picToStorage(String pathPic, String imageName) throws Exception {

        if (imageName.startsWith("P")) {//项目图片
            String projectId = imageName.substring(2, 10);
            TprojectPic tprojectPic = new TprojectPic();
            tprojectPic.settProjectPicId(Identities.create32LenUUID());
            tprojectPic.setProjectId(projectId);
            tprojectPic.setPicture(pathPic);
            tprojectPicDao.batchSave(tprojectPic);
        } else if (imageName.startsWith("H")) {//房源图片
            String houseId = imageName.substring(2, 14);
            ThousePic thousePic = new ThousePic();
            thousePic.settHousePicId(Identities.create32LenUUID());
            thousePic.setHouseId(houseId);
            thousePic.setPicture(pathPic);
            thousePicDao.batchSave(thousePic);
        }
    }


}
