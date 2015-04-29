package com.micro.rent.biz.wetalkmgr.upload.web;

import com.micro.rent.biz.bean.EExtensionName;
import com.micro.rent.biz.bean.EFileType;
import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.biz.myrent.service.ProjectService;
import com.micro.rent.biz.wetalkmgr.upload.service.SupplierImportService;
import com.micro.rent.biz.wetalkmgr.upload.service.UploadService;
import com.micro.rent.biz.wetalkmgr.upload.vo.FileTypeVo;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.exceptions.BizException;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.TProject;
import com.micro.rent.dbaccess.entity.myrent.TUpload;
import com.micro.rent.msg.Errors;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UpLoadController extends _BaseController {

    private transient static final Logger log = LoggerFactory.getLogger(UpLoadController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private SupplierImportService supplierImportService;

    @Autowired
    private MessageSourceAccessor msa;

    @RequestMapping("/displayUploadPage")
    public ModelAndView displayUploadPage(HttpServletRequest request,
                                          HttpServletResponse response) {
        ModelAndView mv = createMAV("/wetalkmgr/upload/uploadRead");
        mv.addObject("lstFileType", createFileType());
        return mv;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/newUpload")
    public ModelAndView newUpload(HttpServletRequest request,
                                  HttpServletResponse response) {
        ModelAndView mv = createMAV("/wetalkmgr/upload/new_uploadRead");
        return mv;
    }

    @RequestMapping("/importPro")
    public ModelAndView importPro(HttpServletRequest request,
                                  TProject pro, HttpServletResponse response) {
        ModelAndView mv = null;
        try {
            projectService.batchSavePro(pro);
            mv = createMAV("redirect:/upload/importProPic");
            mv.addObject("projectId", pro.getProjectId());
            mv.addObject("brandName", pro.getBrandName());
            mv.addObject("communityName", pro.getCommunityName());

        } catch (Exception e) {
            log.error("项目信息导入失败！", e);
            mv = createMAV("/wetalkmgr/upload/new_uploadRead");
            mv.addObject("isError", true);
            mv.addObject("errorMessage", "1. 根据“街道”取百度经纬度失败 2. 项目ID冲突");
            mv.addObject("brandName", pro.getBrandName());
            mv.addObject("communityName", pro.getCommunityName());
            mv.addObject("districtCode", pro.getDistrictCode());
            mv.addObject("street", pro.getStreet());
            mv.addObject("quantity", pro.getQuantity());
            mv.addObject("totalFloor", pro.getTotalFloor());
            mv.addObject("elevatorQuantity", pro.getElevatorQuantity());
            mv.addObject("projectDesc", pro.getProjectDesc());
            mv.addObject("companyDesc", pro.getCompanyDesc());
            mv.addObject("updateFlag", findBooleanParameterValue(request, "updateFlag"));
        }

        return mv;
    }

    @RequestMapping("/importProPic")
    public ModelAndView importProPic(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = createMAV("/wetalkmgr/upload/importProPic");
        mv.addObject("projectId", findStringParameterValue(request, "projectId"));
        try {
            String communityName = new String(findStringParameterValue(request, "communityName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            String brandName = new String(findStringParameterValue(request, "brandName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            mv.addObject("brandName", brandName);
            mv.addObject("communityName", communityName);
        } catch (UnsupportedEncodingException e) {
        }
        return mv;
    }

    @RequestMapping("/toimportHouse")
    public ModelAndView toimportHouse(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = createMAV("/wetalkmgr/upload/importHouse");
        mv.addObject("projectId", findStringParameterValue(request, "projectId"));
        try {
            String communityName = new String(findStringParameterValue(request, "communityName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            String brandName = new String(findStringParameterValue(request, "brandName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            mv.addObject("brandName", brandName);
            mv.addObject("communityName", communityName);
        } catch (UnsupportedEncodingException e) {
        }
        return mv;
    }

    @RequestMapping("/importHouse")
    public ModelAndView importHouse(HttpServletRequest request, HouseInfo houseInfo, HttpServletResponse response) {
        String projectId = findStringParameterValue(request, "projectId");
        ModelAndView mv = null;
        try {
            houseService.batchSaveHouse(houseInfo);
            mv = createMAV("redirect:/upload/importHousePic");
            mv.addObject("projectId", projectId);
            mv.addObject("houseId", houseInfo.getHouseId());
            mv.addObject("brandName", findStringParameterValue(request, "brandName"));
            mv.addObject("communityName", houseInfo.getCommunityName());

        } catch (Exception e) {
            log.error("房屋信息导入失败！", e);
            mv = createMAV("/wetalkmgr/upload/importHouse");
            mv.addObject("isError", true);
            mv.addObject("errorMessage", "1. 房屋ID冲突");
            mv.addObject("projectId", projectId);
            mv.addObject("brandName", findStringParameterValue(request, "brandName"));
            mv.addObject("communityName", houseInfo.getCommunityName());
            mv.addObject("useArea", houseInfo.getUseArea());
            mv.addObject("longPrice", houseInfo.getLongPrice());
            mv.addObject("telephone", houseInfo.getTelephone());
            mv.addObject("layout", houseInfo.getLayout());
            mv.addObject("hallQuantity", houseInfo.getHallQuantity());
            mv.addObject("toiletQuantity", houseInfo.getToiletQuantity());
            mv.addObject("kitchenQuantity", houseInfo.getKitchenQuantity());
            mv.addObject("floor", houseInfo.getFloor());
            mv.addObject("totalFloor", houseInfo.getTotalFloor());
            mv.addObject("orientation", houseInfo.getOrientation());
            mv.addObject("paymentType", houseInfo.getPaymentType());
            mv.addObject("renovation", houseInfo.getRenovation());
            mv.addObject("furniture", houseInfo.getFurniture());
            mv.addObject("appliances", houseInfo.getAppliances());
            mv.addObject("tv", ("有".equals(houseInfo.getTv()) ? "1" : ""));
            mv.addObject("internet", ("有".equals(houseInfo.getInternet()) ? "1" : ""));
            mv.addObject("waterPrice", houseInfo.getWaterPrice());
            mv.addObject("electricPrice", houseInfo.getElectricPrice());
            mv.addObject("gasPrice", houseInfo.getGasPrice());
            mv.addObject("rentalType", houseInfo.getRentalType());
            mv.addObject("flag", houseInfo.getFlag());
            mv.addObject("updateFlag", findBooleanParameterValue(request, "updateFlag"));
        }

        return mv;
    }

    @RequestMapping("/importHousePic")
    public ModelAndView importHousePic(HttpServletRequest request, HouseInfo houseInfo, HttpServletResponse response) {
        String projectId = this.findStringParameterValue(request, "projectId");
        ModelAndView mv = createMAV("/wetalkmgr/upload/importHousePic");
        mv.addObject("projectId", projectId);
        mv.addObject("houseId", houseInfo.getHouseId());
        try {
            String communityName = new String(findStringParameterValue(request, "communityName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            String brandName = new String(findStringParameterValue(request, "brandName").getBytes("ISO-8859-1"),
                    request.getCharacterEncoding());
            mv.addObject("brandName", brandName);
            mv.addObject("communityName", communityName);
        } catch (UnsupportedEncodingException e) {
        }
        return mv;
    }

    @RequestMapping("/imageupload")
    public ModelAndView imageupload(@RequestParam(value = "file", required = false) MultipartFile file,
                                    HttpServletRequest request) {
        String proId = this.findStringParameterValue(request, "projectId");
        String houId = this.findStringParameterValue(request, "houseId");
        if (file.getSize() > 0) {
            uploadService.doUploadImg(file, proId, houId);
        }

        return null;
    }

    @RequestMapping("/displayUpload")
    public ModelAndView displayUpload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "fileTypeValue", required = false) String fileType, @RequestParam(value = "uploadBtn", required = false) String uploadBtn, HttpServletRequest request) {
        if ("uploadBtn".equalsIgnoreCase(uploadBtn)) {
            String filePath = "";
            if (file.getSize() == 0)
                throw new BizException(msa.getMessage(Errors.E_100000, new Object[]{"file"}, getLocal(request)));
            try {
                filePath = uploadService.doUpload(file, fileType);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            log.info("filePath:" + filePath);

            final String serverFilePath = filePath;
            final String fileTypeinner = fileType;
            if (EFileType.HOUSEINFO.getCode().equals(fileTypeinner)) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        String extensionName = FilenameUtils
                                .getExtension(serverFilePath);

                        EExtensionName objExtensionName = EExtensionName
                                .obtainExtensionNameByCode(extensionName);

                        switch (objExtensionName) {
                            case TXT:
                                break;
                            case XLS:
                                break;
                            case XLSX:
                                log.info(fileTypeinner);
                                if (EFileType.HOUSEINFO.getCode().equals(fileTypeinner)) {
                                    supplierImportService.importSupplier(serverFilePath);
                                }
                                break;
                            default:
                                log.error("不支持的文件格式[%s]");
                                throw new BizException("不支持的文件格式");
                        }

                    }
                });

                thread.start();
            } else if (EFileType.HOUSEPICTURE.getCode().equals(fileTypeinner)) {
                Thread threadPic = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        supplierImportService.importSupplierPic(serverFilePath);
                    }
                });
                threadPic.start();
            }
        }


        Page<TUpload> page = this.findPageFinal(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fileType", fileType);

        uploadService.findUploadPaged(page);
        ModelAndView mv = createMAV("/wetalkmgr/upload/uploadRead", page);
        mv.addObject("lstFileType", createFileType());
        mv.addObject("fileTypeValue", fileType);
        return mv;
    }

    private List<FileTypeVo> createFileType() {
        List<FileTypeVo> lstFileType = new ArrayList<FileTypeVo>();
        lstFileType.add(new FileTypeVo("houseinfo", "房源信息"));
        lstFileType.add(new FileTypeVo("housepicture", "房源图片"));
        return lstFileType;
    }
//
//	@Autowired
//	private SupplierGoodsImportService supplierGoodsImportService;
//
//	@Autowired
//	private CompetitorImportService competitorImportService;
//	@Resource
//	private BaseDataImport goodsImportService;
//	@Resource
//	private BaseDataImport sortImportService;
//	@Resource
//	private BaseDataImport goodsSaleImportService;
//	@Resource
//	private BaseDataImport familyMappingImportService;
}
