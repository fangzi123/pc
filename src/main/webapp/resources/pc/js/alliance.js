$(document).ready(function() {

    var ctxPath = $("input[name='ctxPath']").val();
   
    $("#alliance-btn").on("click", function() {
        var contactPerson=$("input[name='contactPerson']").val();
        var gender=$('input:radio[name="gender"]:checked').val();
        var phone=$("input[name='phone']").val();
        var verifyCode=$("#alliance_verifyCode").val();
        var email=$("input[name='email']").val();
        
        var province=$("#province").find("option:selected").text();
        var city=$("#city").find("option:selected").text();
        var districtId=$("#districtId").find("option:selected").text();
        var community=$("input[name='community']").val();
        var address=province+city+districtId+community;
        
        var brandName=$("input[name='brandName']").val();
        var managementType=$("select[name='managementType']").val();
        var totalHouseNumber=$("input[name='totalHouseNumber']").val();
        var description=$("#alliance_description").val();
        if($("#alliance-detail").validation("validate")){
            $.ajax({
                url : ctxPath + "/alliance/add.json",
                type : 'POST',
                data : {
                    contactPerson:contactPerson,
                    gender:gender,
                    phone:phone,
                    verifyCode:verifyCode,
                    email:email,
                    address:address,
                    provinceId:$("#province").val(),
                    cityId:$("#city").val(),
                    districtId:$("#districtId").val(),
                    brandName:brandName,
                    managementType:managementType,
                    totalHouseNumber:totalHouseNumber,
                    description:description
                },
                dataType : 'json',
                success : function(data) {
                    if (data.success == true) {
                        alert("加盟成功");
                    }else{
                        alert("验证码输入有误");
                    } 
                }
            })
        }
    });
    
    $("#province,#city").on("change",function(){
        var obj=$(this);
        var parentId=obj.find("option:selected").attr("data");
        obj.nextAll().not(".help-block").empty().append("<option value=''>请选择</option>");
        if(parentId){
            var dom=obj.next();
            $.ajax({
                url : ctxPath + "/region/list.json",
                type : 'POST',
                data : {
                    parentId:parentId
                },
                dataType : 'json',
                success : function(data) {
                    $.each(data, function (index, region) {
                        var optionHTML="<option value='"+region.regionCode+"' data='"+region.tRegionInfoId+"'>"+region.regionName+"</option>";
                        dom.append($(optionHTML));
                    })
                }
            });
        }
       
    })
});