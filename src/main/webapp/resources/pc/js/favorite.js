
function favoriteHouse(btn) {
    var ctxPath = $("input[name='ctxPath']").val();
    var houseId = $("#houseId").val();
    $.ajax({
        url : ctxPath + "/pc/collect/house.json",
        type : 'POST',
        data : {
            houseId : houseId
        },
        dataType : 'json',
        success : function(data) {
            if(data.collect){
            $(btn).addClass("yuyue-active");
            }else{
                $(btn).removeClass("yuyue-active");
            }
        }
    });
}

function isfavoriteHouse(btn) {
    var ctxPath = $("input[name='ctxPath']").val();
    var houseId = $("#houseId").val();
    $.ajax({
        url : ctxPath + "/pc/collect/house/is.json",
        type : 'POST',
        data : {
            houseId : houseId
        },
        dataType : 'json',
        success : function(data) {
            if (data.collect) {
                $(btn).addClass("yuyue-active");
            }
        }
    });
}